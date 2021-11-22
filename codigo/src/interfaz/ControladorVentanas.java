/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import administrador.VentanaAdministracion;
import administrador.VentanaCatalogo;
import administrador.VentanaInicioSesion;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.*;
import usuario.*;

/**
 *
 * @author DMV
 */
public class ControladorVentanas {
    private static JFrame frame = new JFrame(); // Este objeto es el frame principal donde van a ir las ventanas
    
    //private ImageIcon icono = new ImageIcon(getClass().getResource("imagenes/earthquake.png")); // Icono del programa

    // Todas las ventanas disponibles de la interfaz:
    private static VentanaInicioSesion inicioSesion = new VentanaInicioSesion();
    private static MenuPrincipal menu = new MenuPrincipal();
    private static VentanaAdministracion administracion = new VentanaAdministracion();
    private static VentanaCatalogo verCatalogo = new VentanaCatalogo();
    
    private static MenuCliente menuCliente = new MenuCliente();
    private static VentanaEliminarPedido eliminarPedido = new VentanaEliminarPedido();
    private static VentanaRealizarPedido realizarPedido = new VentanaRealizarPedido();
    
    // Historial
    private static Stack<JPanel> pilaVentanas = new Stack<JPanel>();  // Esta pila se llena con las ventanas 
                                                                      // a las que ha ido ingresando el usuario
                                                                      // la que esta por salir es la actual
     public ControladorVentanas() {
        // Inicializacion del historial de ventanas
        pilaVentanas.push(menu); // Se guarda menu de primero porque es la ventana actual
        
        // Setup del frame principal
        frame.setTitle("Proyecto - Programación Orientada a Objetos"); // Titulo del frame
        //frame.setIconImage(icono.getImage());   // Asignacion del icono del programa
        frame.setSize(640,512);   // tamaño INICIAL del programa
        frame.setResizable(false);   // Imposibilita el poder cambiar su tamaño
        frame.setLayout(null);      // quita el layout manager (todo debe hacerse manual)

        frame.add(menu);    // Agrega la ventana menu
        frame.add(inicioSesion); // Agrega la ventana menu de sismos 
        frame.add(administracion);
        frame.add(menuCliente);
        frame.add(eliminarPedido);
        frame.add(realizarPedido);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Hace que cuando se intente cerrar la ventana se 
                                                              // cierre todo el programa
                                                              
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); // la hace visible
        
     }
     static public void cambiarDimensiones(int x,int y) {
        frame.setSize(x,y);
    }
     // Hace que se pueda volver atras
    static public void volverAtras() {
        JPanel ventanaActual = pilaVentanas.pop();
        ventanaActual.setVisible(false);
        ventanaActual = pilaVentanas.peek();
        frame.setSize(ventanaActual.getSize());
        ventanaActual.setVisible(true);  
    }
    static public void administracion() {
        pilaVentanas.push(administracion);
        inicioSesion.setVisible(false);
        administracion.setVisible(true);
        frame.setSize(640, 512);
    }
    static public void iniciarSesion() {
        pilaVentanas.push(inicioSesion);
        inicioSesion.setVisible(true);
        menu.setVisible(false);
        frame.setSize(640, 512);
    }
    static public void cliente() {
        pilaVentanas.push(menuCliente);
        menuCliente.setVisible(true);
        menu.setVisible(false);
        frame.setSize(640, 512);
    }
    static public void eliminarPedido() {
        pilaVentanas.push(eliminarPedido);
        menuCliente.setVisible(false);
        eliminarPedido.setVisible(true);
        frame.setSize(640, 512);
    }
    static public void realizarPedido() {
        pilaVentanas.push(realizarPedido);
        menuCliente.setVisible(false);
        realizarPedido.setVisible(true);
        frame.setSize(800, 750);
    }
    
    
    
}
