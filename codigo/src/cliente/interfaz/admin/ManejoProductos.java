package cliente.interfaz.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;

public class ManejoProductos extends JPanel implements ActionListener{

    private JButton volver = new JButton("Volver");

    private JSeparator vertical = new JSeparator(SwingConstants.VERTICAL);
    private JSeparator horizontal = new JSeparator(SwingConstants.HORIZONTAL);

    private JLabel lCodigo = new JLabel("Codigo:");
    private JTextField codigo = new JTextField();

    private JLabel lNombre = new JLabel("Nombre:");
    private JTextField nombre = new JTextField();

    private JLabel lDescripcion = new JLabel("Descripcion:");
    private JTextArea descripcion = new JTextArea();
    private JScrollPane descScroll = new JScrollPane(descripcion);

    private JLabel lTamanno = new JLabel("Tamaño:");
    private JTextField tamanno = new JTextField();

    public ManejoProductos() {
        // Setup
        setSize(1280, 720);
        setVisible(false);
        setLayout(null);

        lCodigo.setFont(Fonts.labels);
        lCodigo.setBounds(10, 10, 100, 35);
        add(lCodigo);
        codigo.setFont(Fonts.textField);
        codigo.setBounds(95, 17, 100, 25);
        add(codigo);

        lNombre.setFont(Fonts.labels);
        lNombre.setBounds(10, 50, 100, 35);
        add(lNombre);
        nombre.setFont(Fonts.textField);
        nombre.setBounds(105, 57, 100, 25);
        add(nombre);

        lDescripcion.setFont(Fonts.labels);
        lDescripcion.setBounds(10, 100, 200, 35);
        add(lDescripcion);
        descripcion.setFont(Fonts.textField);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descScroll.setBounds(10, 135, 330, 100);
        add(descScroll);

        lTamanno.setFont(Fonts.labels);
        lTamanno.setBounds(10, 230, 100, 35);
        add(lTamanno);
        tamanno.setFont(Fonts.textField);
        tamanno.setBounds(105, 237, 100, 25);
        add(tamanno);
        


        vertical.setBounds(350, 10, 10, 660);
        add(vertical);


        volver.setFont(Fonts.botones);
        volver.setBounds(1160, 635, 100, 40);
        volver.addActionListener(this);
        add(volver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            GestorVentanas.volverAtras();
        }
        
    }
    
}
