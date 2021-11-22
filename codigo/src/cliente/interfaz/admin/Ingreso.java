package cliente.interfaz.admin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ingreso extends JPanel implements ActionListener{
    private static JButton volver = new JButton("Volver");
    private static JLabel titulo = new JLabel("Inicio de Sesion");
    private static JLabel nombre = new JLabel("Nombre:");
    private static JTextField fNombre = new JTextField();
    private static JLabel clave = new JLabel("Clave:");
    private static JTextField fClave = new JTextField();
    private static JButton ingresar = new JButton("Ingresar");

    public Ingreso() {
        // Setup inicial
        setSize(480, 480);
        setVisible(false);
        setLayout(null);

        titulo.setFont(Fonts.titulos);
        titulo.setBounds(65, 60, 450, 25);
        add(titulo);

        nombre.setFont(Fonts.labels);
        nombre.setBounds(100, 150, 150, 25);
        add(nombre);
        fNombre.setFont(Fonts.textField);
        fNombre.setBounds(200, 152, 150, 25);
        add(fNombre);

        clave.setFont(Fonts.labels);
        clave.setBounds(120, 210, 150, 25);
        add(clave);
        fClave.setFont(Fonts.textField);
        fClave.setBounds(190, 212, 150, 25);
        add(fClave);

        ingresar.setFont(Fonts.botones);
        ingresar.setBounds(190, 270, 100, 40);
        ingresar.addActionListener(this);
        add(ingresar);

        volver.setFont(Fonts.botones);
        volver.setBounds(360, 397, 100, 40);
        volver.addActionListener(this);
        add(volver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            GestorVentanas.volverAtras();
        } else if(e.getSource() == ingresar) {
            GestorVentanas.abrirAdminMenu(); // cambiar esto para comprobar el inicio de sesion
        }
    }
}
