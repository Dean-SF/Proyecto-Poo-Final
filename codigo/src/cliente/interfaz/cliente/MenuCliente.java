/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.interfaz.cliente;

import cliente.interfaz.GestorVentanas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase que crea la ventana del menu para el cliente
 * @author DMV
 */
public class MenuCliente extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Cliente");
    
    private JButton listaProductos = new JButton("Lista de Productos");
    private JButton realizarPedido = new JButton("Realizar Pedido");
    private JButton eliminarPedido = new JButton("Eliminar Pedido");
    
    private JButton volver = new JButton("Volver");
    /***
     * Contructor con las diferentes partes del menu
     */
    public MenuCliente() {
        // Titulo de la ventana
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(250,50,590,25);
        
        // Botones
        listaProductos.setFont(new Font("Segoe UI",Font.PLAIN,20));
        listaProductos.setBounds(170,100,300,30);
        listaProductos.addActionListener(this);
        
        realizarPedido.setFont(new Font("Segoe UI",Font.PLAIN,20));
        realizarPedido.setBounds(170,170,300,30);
        realizarPedido.addActionListener(this);
        
        eliminarPedido.setFont(new Font("Segoe UI",Font.PLAIN,20));
        eliminarPedido.setBounds(170,240,300,30);
        eliminarPedido.addActionListener(this);
        
        volver.setFont(new Font("Segoe UI",Font.PLAIN,15));
        volver.setBounds(150, 400, 100, 25);
        volver.addActionListener(this);
        
        this.setBounds(0, 0, 640, 512);
        this.setLayout(null);
        
        // Agregado de todos los elementos de la ventana
        this.add(titulo);
        this.add(listaProductos);
        this.add(realizarPedido);
        this.add(eliminarPedido);
        this.add(volver);
        
        this.setVisible(false);
    }
    /***
     * Metodod con el action listener de los diferentes botones.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            GestorVentanas.volverAtras();
        }
        if(e.getSource()== listaProductos){
           GestorVentanas.abrirCatalogo();
        }
        if(e.getSource()== realizarPedido){
            GestorVentanas.realizarPedido();
        }
        if(e.getSource()== eliminarPedido){
            GestorVentanas.eliminarPedido();
        }
    }
}
