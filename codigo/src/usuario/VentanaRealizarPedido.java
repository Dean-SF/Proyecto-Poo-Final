/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import interfaz.ControladorVentanas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author DMV
 */
public class VentanaRealizarPedido extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Solicitar Pedido");
    
    //productos
    private JLabel listaProductosLabel = new JLabel("Lista de Productos");
    private JComboBox<String> productos = new JComboBox<String>();
    
    private JSpinner cantidad = new JSpinner();
    
    private JLabel productosSeleccionadosLabel = new JLabel("Productos Seleccionados");
    private JList<String> productosSeleccionados = new JList<String>();
    
    private JLabel caloriasLabel = new JLabel("Calorias:");
    
    //modalidad
    private JLabel modalidadLabel = new JLabel("Modo de Solicitud");
    private JButton express = new JButton("Express");
    private JButton local = new JButton("En Local");
    private JButton recoger = new JButton("Recoger");
    
    private JComboBox<String> modalidad = new JComboBox<String>();
    
    //datos
    private JLabel nombreLabel = new JLabel("Nombre:");
    private JTextField nombreDato = new JTextField();
    
    private JLabel celularLabel = new JLabel("Celular:");
    private JTextField celularDato = new JTextField();
    
    private JLabel direccionLabel = new JLabel("Dirección:");
    private JTextField direccionDato = new JTextField();
    
    //precio
    private JLabel precioLabel = new JLabel("Precio:");
    
    //botones
    private JButton agregar = new JButton("Agregar");  
    private JButton eliminar = new JButton("Eliminar");
    private JButton pedir = new JButton("Pedir");   
    private JButton volver = new JButton("Cancelar");
    
    public VentanaRealizarPedido() {
        // Titulo de la ventana
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(200,50,590,25);
        
        //productos
        listaProductosLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        listaProductosLabel.setBounds(50,100,300,30);
        
        productos.setFont(new Font("Segoe UI",Font.PLAIN,20));
        productos.setBounds(50,150,300,30);
        
        
        cantidad.setFont(new Font("Segoe UI",Font.PLAIN,20));
        cantidad.setBounds(50,180,50,30);
        
        productosSeleccionadosLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        productosSeleccionadosLabel.setBounds(50,270,300,30);
        
        productosSeleccionados.setFont(new Font("Segoe UI",Font.PLAIN,20));
        productosSeleccionados.setBounds(50,320,300,30);
        
        caloriasLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        caloriasLabel.setBounds(50,420,300,30);
        
        //modalidad
        modalidadLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        modalidadLabel.setBounds(400,400,300,30);
        
        modalidad.setFont(new Font("Segoe UI",Font.PLAIN,20));
        modalidad.setBounds(400,450,300,30);
        
        
        //Datos
        nombreLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        nombreLabel.setBounds(400,100,250,30);
        
        nombreDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        nombreDato.setBounds(480,100,250,30);
        
        celularLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        celularLabel.setBounds(400,150,250,30);
        
        celularDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        celularDato.setBounds(480,150,250,30);
        
        direccionLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        direccionLabel.setBounds(400,200,300,30);
        
        direccionDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        direccionDato.setBounds(400,250,300,100);
        
        //precio
        precioLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        precioLabel.setBounds(400,500,300,30);
        
        //botones
        express.setFont(new Font("Segoe UI",Font.PLAIN,15));
        express.setBounds(350, 400, 100, 25);
        express.addActionListener(this);
        
        local.setFont(new Font("Segoe UI",Font.PLAIN,15));
        local.setBounds(350, 400, 100, 25);
        local.addActionListener(this);
        
        recoger.setFont(new Font("Segoe UI",Font.PLAIN,15));
        recoger.setBounds(350, 400, 100, 25);
        recoger.addActionListener(this);
        
        agregar.setFont(new Font("Segoe UI",Font.PLAIN,15));
        agregar.setBounds(50,220, 100, 25);
        agregar.addActionListener(this);
        
        eliminar.setFont(new Font("Segoe UI",Font.PLAIN,15));
        eliminar.setBounds(160, 220, 100, 25);
        eliminar.addActionListener(this);
        
        pedir.setFont(new Font("Segoe UI",Font.PLAIN,15));
        pedir.setBounds(600, 650, 100, 25);
        pedir.addActionListener(this);
        
        volver.setFont(new Font("Segoe UI",Font.PLAIN,15));
        volver.setBounds(150, 650, 100, 25);
        volver.addActionListener(this);
        
        this.setBounds(0, 0, 800, 750);
        this.setLayout(null);
        
        // Agregado de todos los elementos de la ventana
        this.add(titulo);
        this.add(nombreLabel);
        this.add(nombreDato);
        this.add(celularLabel);
        this.add(celularDato);
        
        this.add(listaProductosLabel);
        this.add(productos);
        this.add(cantidad);
        this.add(productosSeleccionadosLabel);
        this.add(productosSeleccionados);
        
        this.add(caloriasLabel);
        this.add(modalidadLabel);
        this.add(modalidad);
        this.add(direccionLabel);
        this.add(direccionDato);
        
        this.add(precioLabel);
        
        this.add(express);
        this.add(local);
        this.add(recoger);
        this.add(agregar);
        this.add(pedir);
        this.add(eliminar);
        this.add(volver);
        
        this.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            ControladorVentanas.volverAtras();
        }
        if(e.getSource()== pedir){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== agregar){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== eliminar){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== express){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== local){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== recoger){
            //GestorVentanas.abrirMenuRegistro();
        }
    }
}
