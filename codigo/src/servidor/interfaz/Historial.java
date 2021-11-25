package servidor.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cliente.interfaz.fonts.Fonts;
import servidor.Servidor;

public class Historial extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("HISTORIAL:");
    private JButton volver = new JButton("volver");
    private static JTextArea historial = new JTextArea();
    private JScrollPane hisScroll = new JScrollPane(historial);

    public Historial() {
        setSize(640, 435);
        setVisible(false);
        setLayout(null);

        titulo.setFont(Fonts.labels);
        titulo.setBounds(10,10,200,25);
        add(titulo);

        historial.setFont(Fonts.textField);
        historial.setEditable(false);
        historial.setLineWrap(true);
        historial.setWrapStyleWord(true);
        hisScroll.setBounds(10, 35, 605, 300);
        add(hisScroll);

        volver.setFont(Fonts.botones);
        volver.setBounds(262,345,100,40);
        volver.addActionListener(this);
        add(volver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver ) {
            ServerInterface.volver();
        }
        
    }

    public static void actualizarHistorial() {
        historial.setText(Servidor.getHistorial());
    }
}
