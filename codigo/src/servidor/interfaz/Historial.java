package servidor.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cliente.interfaz.fonts.Fonts;

public class Historial extends JPanel implements ActionListener{

    private JButton volver = new JButton("volver");
    private JTextArea historial = new JTextArea();
    private JScrollPane hisScroll = new JScrollPane(historial);

    public Historial() {
        setSize(640, 435);
        setVisible(false);
        setLayout(null);

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
}
