/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.interfaz.admin;

import cliente.Cliente;
import cliente.interfaz.GestorVentanas;
import controladores.TPeticion;
import datos.Peticion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Opcion para atender pedidos de la interfaz
 * @author Esteban
 */
public class AtenderPedidos extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Atender");
    
    private JLabel pedidoLabel = new JLabel("Número de Pedido:");
    private JTextField pedidoDato = new JTextField();
    
    private JButton eliminar = new JButton("Atender");
    
    private JButton volver = new JButton("Volver");
    
    /***
     * Metodo que realiza la funcionalidad de eliminar un pedido
     */
    private void eliminarPedido(){
        try{
            String numero = String.valueOf(pedidoDato.getText());
            System.out.println("1");
            if(numero.isBlank()){
                JOptionPane.showMessageDialog(this, "Debe de colocar los datos","Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_PED,numero));
            if(peticion.getDatos()==null){
                JOptionPane.showMessageDialog(this, "El pedido no existe","Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente.enviarPeticion(new Peticion(TPeticion.ELIMINAR_PED,numero));
            JOptionPane.showMessageDialog(this, "El pedido se elimino correctamente","Aviso",
            JOptionPane.INFORMATION_MESSAGE);
            pedidoDato.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error inesperado","Error",
            JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            return;
        }
    }
    /**
     * Contructor de la ventana con sus partes
     */
    public AtenderPedidos() {
        // Titulo de la ventana
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(260,30,590,25);
        
        //labels
        pedidoLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        pedidoLabel.setBounds(70,100,300,30);
        
        pedidoDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        pedidoDato.setBounds(250,100,300,30);
        
        // Botones
        eliminar.setFont(new Font("Segoe UI",Font.PLAIN,15));
        eliminar.setBounds(350, 150, 100, 25);
        eliminar.addActionListener(this);
        
        volver.setFont(new Font("Segoe UI",Font.PLAIN,15));
        volver.setBounds(150, 150, 100, 25);
        volver.addActionListener(this);
        
        this.setBounds(0, 0, 640, 250);
        this.setLayout(null);
        
        // Agregado de todos los elementos de la ventana
        this.add(titulo);
        this.add(pedidoLabel);
        this.add(pedidoDato);
        this.add(eliminar);
        this.add(volver);
        
        this.setVisible(false);
    }
    /**
     * Metodo con el action listener de cada boton
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            pedidoDato.setText("");
            GestorVentanas.volverAtras();
        }
        if(e.getSource()== eliminar){
            eliminarPedido();
        }
    }
}
