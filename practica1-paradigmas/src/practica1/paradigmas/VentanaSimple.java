package practica1.paradigmas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Georges ALfaro S.
 * @version 1.0
 */
public class VentanaSimple extends JFrame {

    public VentanaSimple(String titulo) throws HeadlessException {
        super(titulo);
        configurar();
    }

    private void configurar() {
        ajustarComponentes(getContentPane());

        setResizable(true);
        Dimension d = new Dimension(480, 320);
        setSize(d);
        setMinimumSize(d);
        setLocationByPlatform(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void ajustarComponentes(Container c) {
        c.setLayout(new BorderLayout(4, 4));

        JPanel e1a = new JPanel();
        e1a.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        e1a.setLayout(new BorderLayout());
        panelDesplazable = new JScrollPane(areaTexto = new JTextArea(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        e1a.add(BorderLayout.CENTER, panelDesplazable);
        c.add(BorderLayout.CENTER, e1a);

        JPanel e2 = new JPanel();
        e2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        e2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        e2.add(etqEstado = new JLabel("Digite el texto en la ventana de la consola.."));
        c.add(BorderLayout.PAGE_END, e2);

        areaTexto.setEditable(false);
    }

    public void init() {
        setVisible(true);
    }

    public void registrarMensaje(String mensaje) {
        if (!mensaje.isEmpty()) {
            etqEstado.setText(String.format(" Mensaje: '%s'", mensaje));

            areaTexto.append(String.format("%s%n", mensaje));

            JScrollBar vertical = panelDesplazable.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        }
    }

    private JLabel etqEstado;
    private JScrollPane panelDesplazable;
    private JTextArea areaTexto;
}
