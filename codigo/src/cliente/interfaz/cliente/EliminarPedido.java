/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.interfaz.cliente;

import cliente.Cliente;
import cliente.interfaz.GestorVentanas;
import controladores.TPeticion;
import datos.Peticion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Ventana para eliminar los pedidos por parte del mismo usuario
 * @author DMV
 */
public class EliminarPedido extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Eliminar Pedido");
    
    private JLabel nombreLabel = new JLabel("Nombre:");
    private JTextField nombreDato = new JTextField();
    
    private JLabel pedidoLabel = new JLabel("Número de Pedido:");
    private JTextField pedidoDato = new JTextField();
    
    private JButton eliminar = new JButton("Eliminar");
    
    private JButton volver = new JButton("Volver");
    
    /***
     * Metodo que realiza la funcionalidad de eliminar un pedido
     */
    private void eliminarPedido(){
        try{
            String nombre = String.valueOf(nombreDato.getText());
            String numero = String.valueOf(pedidoDato.getText());
            if(nombre.isBlank() || numero.isBlank()){
                JOptionPane.showMessageDialog(this, "Debe de colocar los datos","Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_PED,numero));
            if(peticion.getDatos() == null){
                JOptionPane.showMessageDialog(this, "El pedido no existe","Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente.enviarPeticion(new Peticion(TPeticion.ELIMINAR_PED,numero));
            JOptionPane.showMessageDialog(this, "El pedido se elimino correctamente","Aviso",
            JOptionPane.INFORMATION_MESSAGE);
            nombreDato.setText("");
            pedidoDato.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error inesperado","Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    /***
     * Contructor de la ventana con sus partes
     */
    public EliminarPedido() {
        // Titulo de la ventana
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(150,50,590,25);
        
        //labels
        nombreLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        nombreLabel.setBounds(100,150,300,30);
        
        nombreDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        nombreDato.setBounds(250,150,300,30);
        
        pedidoLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        pedidoLabel.setBounds(70,250,300,30);
        
        pedidoDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        pedidoDato.setBounds(250,250,300,30);
        
        // Botones
        eliminar.setFont(new Font("Segoe UI",Font.PLAIN,15));
        eliminar.setBounds(350, 400, 100, 25);
        eliminar.addActionListener(this);
        
        volver.setFont(new Font("Segoe UI",Font.PLAIN,15));
        volver.setBounds(150, 400, 100, 25);
        volver.addActionListener(this);
        
        this.setBounds(0, 0, 640, 512);
        this.setLayout(null);
        
        // Agregado de todos los elementos de la ventana
        this.add(titulo);
        this.add(nombreLabel);
        this.add(nombreDato);
        this.add(pedidoLabel);
        this.add(pedidoDato);
        this.add(eliminar);
        this.add(volver);
        
        this.setVisible(false);
    }
    /***
     * Metodo con el action listener de cada boton
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            nombreDato.setText("");
            pedidoDato.setText("");
            GestorVentanas.volverAtras();
        }
        if(e.getSource() == eliminar){
            eliminarPedido();
        }
    }
}
