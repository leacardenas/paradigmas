/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package practica1.paradigmas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
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
    public Boolean running = Boolean.FALSE;
    List<figure> figures = new ArrayList<>();
    
    float[] b = Color.blue.getColorComponents(null);
    Color blue = new Color(b[0], b[1], b[2], 0.35f); //circles
    
    float[] p = Color.pink.getColorComponents(null);
    Color pink = new Color(p[0], p[1], p[2], 0.35f); //squares
    
    float[] pur = Color.MAGENTA.getColorComponents(null);
    Color purple = new Color(pur[0], pur[1], pur[2], 0.35f); //rectangles
    
    float[] g = Color.green.getColorComponents(null);
    Color green = new Color(g[0], g[1], g[2], 0.35f); //triangles
    
    float[] or = Color.ORANGE.getColorComponents(null);
    Color orange = new Color(or[0], or[1], or[2], 0.35f); //donuts
    
    private static final Font TIPO_BASE = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Practica1Paradigmas().empezarApp();
        //new Practica1Paradigmas().testing();
    }
    
    public void testing(){
        figures.add(new circle("Circle", 0, 200f, 200f, 50f));
        figures.add(new square("Square", 3, 300f, 300f, 30f));
        figures.add(new rectangle("Rectangle", 4, 400f, 400f, 50f, 40f));
        figures.add(new donut("Donut", 5, 500f, 500f, 200f, 150f));
        figures.add(new triangle("Triangle", 6, 100f, 50f, 70f, 100f, 130f, 100f));
        
        Frame frame = new printFigures();
        
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent we){
                frame.dispose();
            }
        });
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
    
    //Shape circle = new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f); x, y, r, r
    //Shape square = new Rectangle2D.Float(100, 100,100, 100); x, y, w, h
    
    //System.out.print("");
    
    /*
    Circle: new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f); x, y, r (r,r)
    Square: new Rectangle2D.Float(100, 100,100, 100); x, y, l (l,l)
    Rectangle: new Rectangle2D.Float(100, 100,100, 100); x, y, w, h
    Triangle:
    Donut: new Ellipse2D.Float(100.0f, 100.0f, 100.0f, 100.0f); x, y, r1 / new Ellipse2D.Float(100+25.0f, 100+25.0f, 100.0f, 100.0f); x, y, r2
    */
    public void empezarApp(){
        String op = "";
        Scanner in;
        System.out.print("Start!\n");
        Boolean print;
        Frame frame = new printFigures();
        
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent we){
                frame.dispose();
            }
        });
        
        while(!op.equals("exit")){
            in = new Scanner(System.in);
            op = in.nextLine();
            String[] stringarray = op.split("\\s+");
            print = true;
            
            switch(stringarray[0]){
                case "circle":
                    if(cantArg(stringarray.length -1, 3)){
                        figures.add(new circle("Circle",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments");
                        print = false;
                    }
                    break;
                case "square":
                    if(cantArg(stringarray.length -1, 3)){
                        figures.add(new square("Square",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments");
                        print = false;
                    }
                    break;
                case "rectangle":
                    if(cantArg(stringarray.length -1, 4)){
                        figures.add(new rectangle("Rectangle",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3]), Float.valueOf(stringarray[4])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                        print = false;
                    }
                    break;
                case "triangle":
                    if(cantArg(stringarray.length -1, 6)){
                        figures.add(new triangle("Triangle",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3]), Float.valueOf(stringarray[4]), Float.valueOf(stringarray[5]), Float.valueOf(stringarray[6])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                        print = false;
                    }
                    break;
                case "donut":
                    if(cantArg(stringarray.length -1, 4)){
                        figures.add(new donut("Donut",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3]), Float.valueOf(stringarray[4])));
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                        print = false;
                    }
                    break;
                case "ellipse":
                    if(cantArg(stringarray.length -1, 4)){
                        System.out.print("Coming soon");
                        print = false;
                    }else{
                        System.out.print("Error: Invalid quantity of arguments\n Try again\n");
                        print = false;
                    }
                    break;
                case "delete":
                    print = false;
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
                    print = false;
                    if(figures.size() > 0){
                        for(figure f : figures){
                            f.print();
                        }
                    }else{
                        System.out.print("There are no figures on the system\n");
                    }
                    break;
                case "help":
                    print = false;
                    
                    System.out.print("Valid options:\n\ncircle(3)\nsquare(3)\ntriangle(6)\ndonut(4)\nellipse(4)\ndelete\nhelp\narchive\nexit\n\nThe number on the parentesis, is the amount of arguments to be written.\n\n");
                    break;
                case "archive":
                    print = true;
                    if(cantArg(stringarray.length -1, 1)){
                        processArchive(stringarray[1]);
                    }else{
                        System.out.print("Error: Invalid quantity of arguments");
                        print = false;
                    }
                    
                    break;
                case "exit":
                    print = false;
                    
                    break;
                default:
                    print = false;
                    
                    System.out.print("Error: you have typed an invalid command\n");
                    break;
            }
            
            if(print){
                frame.setSize(1000, 1000);
                frame.setVisible(true);
            }
        }
        
        System.out.print("Bye bye :)\n");
    }
    
    public class printFigures extends Frame {
        public void paint(Graphics g){
            for(figure f : figures){
                Graphics2D ga = (Graphics2D)g;
                Font tipo = TIPO_BASE.deriveFont(15f);
                ga.setFont(tipo);
                
                switch(f.type){
                    case "Circle":
                        circle c = (circle) f;
                        Shape circle = new Ellipse2D.Float(f.x, f.y, c.radio, c.radio);
                        ga.setColor(blue);
                        ga.draw(circle);
                        ga.fill(circle);
                        
                        ga.setColor(ga.getColor().darker());
                        ga.drawString("Figure: " + f.id + " Coords: " + f.x + "," + f.y,
                                (int) ((f.x + 6)),
                                (int) ((f.y + 4))
                        );
                        
                        break;
                    case "Square":
                        square s = (square) f;
                        Shape square = new Rectangle2D.Float(s.x, s.y, s.side, s.side);
                        ga.setColor(pink);
                        ga.draw(square);
                        ga.fill(square);
                        
                        ga.setColor(ga.getColor().darker());
                        ga.drawString("Figure: " + f.id + " Coords: " + f.x + "," + f.y,
                                (int) ((f.x + 6)),
                                (int) ((f.y + 4))
                        );
                        
                        break;
                    case "Rectangle":
                        rectangle r = (rectangle) f;
                        Shape rectangle = new Rectangle2D.Float(r.x, r.y, r.width, r.height);
                        ga.setColor(purple);
                        ga.draw(rectangle);
                        ga.fill(rectangle);
                        
                        ga.setColor(ga.getColor().darker());
                        ga.drawString("Figure: " + f.id + " Coords: " + f.x + "," + f.y,
                                (int) ((f.x + 6)),
                                (int) ((f.y + 4))
                        );
                        
                        break;
                    case "Triangle":
                        triangle t = (triangle) f;
                        int x1 = Math.round(t.x);
                        int y1 = Math.round(t.y);
                        int x2 = Math.round(t.x_2);
                        int y2 = Math.round(t.y_2);
                        int x3 = Math.round(t.x_3);
                        int y3 = Math.round(t.y_4);
                        
                        ga.setColor(Color.red);
                        
                        ga.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
                        
                        ga.drawString("Figure: " + f.id + " Coords: " + f.x + "," + f.y,
                                (int) ((f.x + 6)),
                                (int) ((f.y + 4))
                        );
                        
                        
                        break;
                    case "Donut":
                        donut d = (donut) f;
                        Shape donut_1 = new Ellipse2D.Float(d.x, d.y, d.radio_1, d.radio_1);
                        Shape donut_2 = new Ellipse2D.Float(d.x + 25f, d.y + 25f, d.radio_2, d.radio_2);
                        ga.setColor(orange);
                        ga.draw(donut_1);
                        ga.fill(donut_1);
                        
                        ga.setColor(Color.white);
                        ga.draw(donut_2);
                        ga.fill(donut_2);
                        
                        ga.setColor(ga.getColor().darker());
                        ga.drawString("Figure: " + f.id + " Coords: " + f.x + "," + f.y,
                                (int) ((f.x + 6)),
                                (int) ((f.y + 4))
                        );
                        break;
                }
            }
        }
    }
    
    public static Boolean cantArg(Integer args, Integer cant){
        return Objects.equals(args, cant); //Demasiados argumentos
    }
    
    public static ArrayList<String> readArchive(String path){
            ArrayList<String> command = new ArrayList();
            
                    try {
                        Scanner input = new Scanner(new File(path));
                        while (input.hasNextLine()) {
                            String line = input.nextLine();
                            command.add(line);
                        }
                        input.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            return command;
        }
    
    public void processArchive(String path){
            ArrayList<String> commands = this.readArchive(path);
            
            for(String c:commands){
                 String[] stringarray = c.split("\\s+");
                 
                switch(stringarray[0]){
                case "circle":
                    figures.add(new circle("Circle",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3])));
                    break;
                case "square":
                    figures.add(new square("Square",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3])));
                    break;
                case "rectangle":
                    figures.add(new rectangle("Rectangle",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3]), Float.valueOf(stringarray[4])));
                    break;
                case "triangle":
                    figures.add(new triangle("Triangle",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3]), Float.valueOf(stringarray[4]), Float.valueOf(stringarray[5]), Float.valueOf(stringarray[6])));
                    break;
                case "donut":
                    figures.add(new donut("Donut",count,Float.valueOf(stringarray[1]), Float.valueOf(stringarray[2]), Float.valueOf(stringarray[3]), Float.valueOf(stringarray[4])));
                    break;
                case "ellipse":
                    System.out.print("Coming soon");
                    break;
                default:
                    System.out.print("Error: you have typed an invalid command\n");
                    break;
                }
            }
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
        public Float x;
        public Float y;
        
        public void print(){
            System.out.print("Figure # " + id + " - Type: " + type + " Coords: (" + x + "," + y + ")\n");
        }
    }
    
    public class circle extends figure{
        public Float radio;
        
        public circle(String type, Integer id, Float x, Float y, Float radio) {
            this.type = type;
            this.id = id;
            this.x = x;
            this.y = y;
            this.radio = radio;
            count++;
        }
    }
    
    public class square extends figure{
        public Float side;
        
        public square(String type, Integer id, Float x, Float y, Float side) {
            this.type = type;
            this.id = id;
            this.x = x;
            this.y = y;
            this.side= side;
            count++;
        }
    }
    
    public class rectangle extends figure{
        public Float width;
        public Float height;
        
        public rectangle(String type, Integer id, Float x, Float y, Float width, Float height) {
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
        public Float x_2;
        public Float y_2;
        public Float x_3;
        public Float y_4;
        
        public triangle(String type, Integer id, Float x, Float y, Float x_2, Float y_2, Float x_3, Float y_4) {
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
        public Float radio_1;
        public Float radio_2;
        
        public donut(String type, Integer id, Float x, Float y, Float radio_1, Float radio_2) {
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
