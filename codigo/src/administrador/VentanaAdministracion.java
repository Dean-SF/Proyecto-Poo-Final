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
public class VentanaAdministracion extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Administración");
    
    private JButton verCatalogo = new JButton("Ver Catalogo");
    private JButton manejoProductos = new JButton("Manejo de Productos");
    private JButton verPedidos = new JButton("Ver Pedidos");
    private JButton estadisticasPedidos = new JButton("Estadisticas de Pedidos");
    private JButton volver = new JButton("Volver");
    
    public VentanaAdministracion() {
        // Titulo de la ventana
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(170,50,590,25);

        // Botones
        verCatalogo.setFont(new Font("Segoe UI",Font.PLAIN,20));
        verCatalogo.setBounds(220,100,200,30);
        verCatalogo.addActionListener(this);
        
        manejoProductos.setFont(new Font("Segoe UI",Font.PLAIN,20));
        manejoProductos.setBounds(170,170,300,30);
        manejoProductos.addActionListener(this);
        
        verPedidos.setFont(new Font("Segoe UI",Font.PLAIN,20));
        verPedidos.setBounds(220,240,200,30);
        verPedidos.addActionListener(this);
        
        estadisticasPedidos.setFont(new Font("Segoe UI",Font.PLAIN,20));
        estadisticasPedidos.setBounds(150,310,350,30);
        estadisticasPedidos.addActionListener(this);
        
        volver.setFont(new Font("Segoe UI",Font.PLAIN,15));
        volver.setBounds(150, 400, 100, 25);
        volver.addActionListener(this);
        
         // Tamaño de la ventana y Layout manager
        this.setBounds(0, 0, 640, 512);
        this.setLayout(null);
        
        // Agregado de todos los elementos de la ventana
        this.add(titulo);
        this.add(verCatalogo);
        this.add(manejoProductos);
        this.add(verPedidos);
        this.add(estadisticasPedidos);
        this.add(volver);
        
        this.setVisible(false);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            ControladorVentanas.volverAtras();
        }
        if(e.getSource()== verCatalogo){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== manejoProductos){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== verPedidos){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== estadisticasPedidos){
            //GestorVentanas.abrirMenuRegistro();
        }
    }
}

