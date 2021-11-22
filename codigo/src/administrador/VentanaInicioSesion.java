/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador;

import interfaz.ControladorVentanas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *
 * @author DMV
 */
public class VentanaInicioSesion extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Inicio de Sesión");
    
    //botones y labels
    private JLabel usuarioLabel = new JLabel("Usuario");
    private JTextField usuarioDato = new JTextField();
    
    private JLabel contrasenaLabel = new JLabel("Contraseña");
    private JTextField contrasenaDato = new JTextField();
    
    private JButton btnAceptar = new JButton("Aceptar");
    
    private JButton btnCancelar = new JButton("Cancelar");
    
    
    //Acomodo en la pantalla
    
    public VentanaInicioSesion() {

        // Titulo de la ventana
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(150,50,590,25);
        
        // Usuario
        usuarioLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,25));
        usuarioLabel.setBounds(270, 100, 260, 50);
        
        usuarioDato.setFont(new Font("Segoe UI Light",Font.PLAIN,25));
        usuarioDato.setBounds(180, 150, 260, 25);
        
        //contraseña
        contrasenaLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,25));
        contrasenaLabel.setBounds(250, 200, 500, 25);
        
        contrasenaDato.setFont(new Font("Segoe UI Light",Font.PLAIN,25));
        contrasenaDato.setBounds(180, 250, 260, 25);
        
        //botones
        btnAceptar.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
        btnAceptar.setBounds(350, 400, 100, 25);
        btnAceptar.addActionListener(this);
        
        btnCancelar.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
        btnCancelar.setBounds(150, 400, 100, 25);
        btnCancelar.addActionListener(this);
         // Tamaño de la ventana y Layout manager
        this.setBounds(0, 0, 640, 512);
        this.setLayout(null);
        
        // Agregado de todos los elementos de la ventana
        this.add(titulo);

        this.add(usuarioLabel);
        this.add(usuarioDato);
        this.add(contrasenaLabel);
        this.add(contrasenaDato);
        this.add(btnAceptar);
        this.add(btnCancelar);
        
         // La hace invisible
        this.setVisible(false);
    }
        @Override
        public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCancelar) {
            ControladorVentanas.volverAtras();
        }
        if(e.getSource() == btnAceptar) {
            ControladorVentanas.administracion();
        }
      }
}
