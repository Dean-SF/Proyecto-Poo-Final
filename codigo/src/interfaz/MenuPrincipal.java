/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author DMV
 */
public class MenuPrincipal extends JPanel implements ActionListener{
    // Elementos de la ventana menu
    private JLabel titulo = new JLabel("MENU PRINCIPAL");
    private JButton administrador = new JButton("Administrador");
    private JButton cliente = new JButton("Cliente");
    
    public MenuPrincipal() {

        // Letra y ubicacion del Label de titulo
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(180,50,590,25);

        // Letra, ubicacion y ActionListener del boton administrador
        administrador.setFont(new Font("Segoe UI",Font.PLAIN,18));
        administrador.setBounds(185, 150, 260, 50);
        administrador.addActionListener(this); // Un ActionListener es una clase abstracta que al crearse recibe un evento y
                                                // ejecuta una accion

        // Letra, ubicacion y ActionListener del boton cliente
        cliente.setFont(new Font("Segoe UI",Font.PLAIN,18));
        cliente.setBounds(185, 250, 260, 50);
        cliente.addActionListener(this);
        
        // Setup de la ventana
        this.setBounds(0, 0, 640, 512);
        this.setLayout(null);

        // Se añaden los elementos de la ventan
        this.add(titulo);
        this.add(administrador);
        this.add(cliente);
        
        // Se hace visible puesto a que es la primera ventana que se visualiza
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == administrador) {
            ControladorVentanas.iniciarSesion();
        }
        if(e.getSource()== cliente){
            ControladorVentanas.cliente();
        }
    }
}
