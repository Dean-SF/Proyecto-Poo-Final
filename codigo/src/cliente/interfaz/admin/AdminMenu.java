package cliente.interfaz.admin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu de la interfaz de administrador, contiene todas las opciones
 * que puede realizar un administrador
 */
public class AdminMenu extends JPanel implements ActionListener{
    private static JLabel titulo = new JLabel("Administracion");
    private static JButton vCatalogo = new JButton("Ver catalogo");
    private static JButton mProductos = new JButton("Manejo de productos");
    private static JButton pedidos = new JButton("Ver pedidos");
    private static JButton estadisticas = new JButton("Estadistica de pedidos");
    private static JButton volver = new JButton("Volver");
    private static JButton atender = new JButton("Atender");

    /**
     * Constructor de la ventana
     */
    public AdminMenu() {
        // Setup
        setSize(480, 480);
        setVisible(false);
        setLayout(null);

        titulo.setFont(Fonts.titulos);
        titulo.setBounds(82, 40, 400, 25);
        add(titulo);

        vCatalogo.setFont(Fonts.botones);
        vCatalogo.setBounds(160, 110, 135, 40);
        vCatalogo.addActionListener(this);
        add(vCatalogo);

        mProductos.setFont(Fonts.botones);
        mProductos.setBounds(125, 170, 205, 40);
        mProductos.addActionListener(this);
        add(mProductos);

        pedidos.setFont(Fonts.botones);
        pedidos.setBounds(160, 230, 135, 40);
        pedidos.addActionListener(this);
        add(pedidos);

        estadisticas.setFont(Fonts.botones);
        estadisticas.setBounds(120, 290, 215, 40);
        estadisticas.addActionListener(this);
        add(estadisticas);
        
        atender.setFont(Fonts.botones);
        atender.setBounds(120, 340, 215, 40);
        atender.addActionListener(this);
        add(atender);
      
        volver.setFont(Fonts.botones);
        volver.setBounds(360, 397, 100, 40);
        volver.addActionListener(this);
        add(volver);


    }

    /** 
     * Metodo que maneja las acciones que hacen los botones
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            GestorVentanas.volverAtras();
        } else if(e.getSource() == vCatalogo) {
            GestorVentanas.abrirCatalogo();
        } else if(e.getSource() == mProductos) {
            GestorVentanas.abrirManejoProductos();
        } else if(e.getSource() == pedidos) {
            GestorVentanas.abrirPedidos();
        } else if(e.getSource() == estadisticas) {
             GestorVentanas.abrirEstadisticas();
        }else if(e.getSource() == atender){
            GestorVentanas.atenderPedido();
        }   
    }
    
}
