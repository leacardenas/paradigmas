/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package practica1.paradigmas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author leaca
 */
public class Practica1Paradigmas {
    public static Integer count = 0;
    public VentanaSimple vista = null;
    public Boolean running = Boolean.FALSE;
    List<figure> figures = new ArrayList<>();
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Practica1Paradigmas().empezarApp();
    }
    
    public static class CircleDraw extends Frame {
        Shape circle = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f);
        
        public void paint(Graphics g) {
            Graphics2D ga = (Graphics2D)g;
            ga.setColor(Color.red);
            ga.draw(circle);
            ga.fill(circle);
        }
    }
    
    //Shape circle = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f); x, y, r, r
    //Shape square = new Rectangle2D.Double(100, 100,100, 100); x, y, w, h
    
    //System.out.print("");
    
    /*
    Circle: new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f); x, y, r (r,r)
    Square: new Rectangle2D.Double(100, 100,100, 100); x, y, l (l,l)
    Rectangle: new Rectangle2D.Double(100, 100,100, 100); x, y, w, h
    Triangle:
    Donut: new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f); x, y, r1 / new Ellipse2D.Float(100+25.0f, 100+25.0f, 100.0f, 100.0f); x, y, r2
    */
    public void empezarApp(){
        String op = "";
        Scanner in;
        System.out.print("Start!\n");
        
        while(!op.equals("exit")){
            in = new Scanner(System.in);
            op = in.nextLine();
            String[] stringarray = op.split("\\s+");
            
            switch(stringarray[0]){
                case "circle":
                    if(cantArg(stringarray.length -1, 3)){
                        figures.add(new circle("Circle",count,Double.valueOf(stringarray[1]), Double.valueOf(stringarray[2]), Double.valueOf(stringarray[3])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments");
                    }
                    break;
                case "square":
                    if(cantArg(stringarray.length -1, 3)){
                        figures.add(new square("Square",count,Double.valueOf(stringarray[1]), Double.valueOf(stringarray[2]), Double.valueOf(stringarray[3])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments");
                    }
                    break;
                case "rectangle":
                    if(cantArg(stringarray.length -1, 4)){
                        figures.add(new rectangle("Rectangle",count,Double.valueOf(stringarray[1]), Double.valueOf(stringarray[2]), Double.valueOf(stringarray[3]), Double.valueOf(stringarray[4])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                    }
                    break;
                case "triangle":
                    if(cantArg(stringarray.length -1, 6)){
                        figures.add(new triangle("Triangle",count,Double.valueOf(stringarray[1]), Double.valueOf(stringarray[2]), Double.valueOf(stringarray[3]), Double.valueOf(stringarray[4]), Double.valueOf(stringarray[5]), Double.valueOf(stringarray[6])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                    }
                    break;
                case "donut":
                    if(cantArg(stringarray.length -1, 4)){
                        figures.add(new donut("Donut",count,Double.valueOf(stringarray[1]), Double.valueOf(stringarray[2]), Double.valueOf(stringarray[3]), Double.valueOf(stringarray[4])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                    }
                    break;
                case "ellipse":
                    if(cantArg(stringarray.length -1, 4)){
                        System.out.print("Coming soon");
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                    }
                    break;
                case "delete":
                    if(cantArg(stringarray.length -1, 1)){
                        try {
                            delete(Integer.valueOf(stringarray[1]));
                        } catch (Exception e) {
                            System.out.print("Error: Please use a valid number.\n");
                        }
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                    }
                    break;
                case "list":
                    if(figures.size() > 0){
                        for(figure f : figures){
                            f.print();
                        }
                    }else{
                        System.out.print("There are no figures on the system\n");
                    }
                    break;
                case "help":
                    System.out.print("Valid options:\n\ncircle(3)\nsquare(3)\ntriangle(6)\ndonut(4)\nellipse(4)\ndelete\nhelp\nexit\n\nThe number on the parentesis, is the amount of arguments to be written.\n\n");
                    break;
                case "arcxhive":
                    System.out.print("Valid options:\n\ncircle(3)\nsquare(3)\ntriangle(6)\ndonut(4)\nellipse(4)\ndelete\nhelp\nexit\n\nThe number on the parentesis, is the amount of arguments to be written.\n\n");
                    break;
                case "exit":
                    break;
                default:
                    System.out.print("Error: you have typed an invalid command\n");
                    break;
            }
        }
        
        System.out.print("Bye bye :)\n");
    }
    
    public static Boolean cantArg(Integer args, Integer cant){
        return Objects.equals(args, cant); //Demasiados argumentos
    }
    
    public void registrar(String linea) {
        System.out.printf("\t%s%n", linea);
        vista.registrarMensaje(linea);
        
    }
    
    public void delete(Integer id){
        for(figure f : figures){
            if(f.id.equals(id)){
                figures.remove(f);
                System.out.print("The figure has been deleted\n");
                break;
            }
        }
    }
    
    //Objetos
    public class figure extends Frame {
        public String type;
        public Integer id;
        public Double x;
        public Double y;
        
        public void print(){
            System.out.print("Figure # " + id + " - Type: " + type + " Coords: (" + x + "," + y + ")\n");
        }
    }
    
    public class circle extends figure{
        public Double radio;
        
        public circle(String type, Integer id, Double x, Double y, Double radio) {
            this.type = type;
            this.id = id;
            this.x = x;
            this.y = y;
            this.radio = radio;
            count++;
        }
    }
    
    public class square extends figure{
        public Double side;
        
        public square(String type, Integer id, Double x, Double y, Double side) {
            this.type = type;
            this.id = id;
            this.x = x;
            this.y = y;
            this.side= side;
            count++;
        }
    }
    
    public class rectangle extends figure{
        public Double width;
        public Double height;
        
        public rectangle(String type, Integer id, Double x, Double y, Double width, Double height) {
            this.type = type;
            this.id = id;
            this.x = x;
            this.y = y;
            this.width= width;
            this.height = height;
            count++;
        }
    }
    
    public class triangle extends figure{
        public Double x_2;
        public Double y_2;
        public Double x_3;
        public Double y_4;
        
        public triangle(String type, Integer id, Double x, Double y, Double x_2, Double y_2, Double x_3, Double y_4) {
            this.type = type;
            this.id = id;
            this.x = x;
            this.y = y;
            this.x_2 = x_2;
            this.y_2 = y_2;
            this.x_3 = x_3;
            this.y_4 = y_4;
            count++;
        }
    }
    
    public class donut extends figure{
        public Double radio_1;
        public Double radio_2;
        
        public donut(String type, Integer id, Double x, Double y, Double radio_1, Double radio_2) {
            this.type = type;
            this.id = id;
            this.x = x;
            this.y = y;
            this.radio_1 = radio_1;
            this.radio_2 = radio_2;
            count++;
        }
    }
}
