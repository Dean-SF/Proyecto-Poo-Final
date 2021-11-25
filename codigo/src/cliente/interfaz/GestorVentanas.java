package cliente.interfaz;

import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cliente.interfaz.admin.AdminMenu;
import cliente.interfaz.admin.AtenderPedidos;
import cliente.interfaz.admin.Catalogo;
import cliente.interfaz.admin.EstadisticasPedidos;
import cliente.interfaz.admin.Ingreso;
import cliente.interfaz.admin.ManejoProductos;
import cliente.interfaz.admin.Pedidos;
import usuario.*;

/**
 * Clase que Gestiona las diferentes ventanas del la aplicacion/cliente
 */
public class GestorVentanas{
    // ventana principal
    private static JFrame frame = new JFrame();

    // Historial de ventanas
    private static Stack<JPanel> historial = new Stack<JPanel>();

    // Ventanas
    private static Menu menu = new Menu();
    private static Ingreso adminIngreso = new Ingreso();
    private static AdminMenu administracion = new AdminMenu();
    private static Catalogo catalogo = new Catalogo();
    private static Pedidos pedidos = new Pedidos();
    private static ManejoProductos adminProductos = new ManejoProductos();
    private static EstadisticasPedidos estadisticas = new EstadisticasPedidos();
    private static AtenderPedidos atender = new AtenderPedidos();
    
    //ventanas de cliente
    private static MenuCliente menuCliente = new MenuCliente();
    private static VentanaEliminarPedido eliminarPedido = new VentanaEliminarPedido();
    private static VentanaRealizarPedido realizarPedido = new VentanaRealizarPedido();
    
    public GestorVentanas() {
        // Inicializacion del historial
        historial.push(menu);

        // Setup inicial
        frame.setTitle("Proyecto 2 - Programación Orientada a Objetos");
        frame.setSize(480, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        
        frame.add(menu);
        frame.add(adminIngreso);
        frame.add(administracion);
        frame.add(catalogo);
        frame.add(pedidos);
        frame.add(adminProductos);
        frame.add(estadisticas);
        frame.add(atender);
        
        frame.add(menuCliente);
        frame.add(eliminarPedido);
        frame.add(realizarPedido);

    }

    // Hace que se pueda volver atras
    /***
     * Permite volver a tras en cada ventana
     */
    static public void volverAtras() {
        JPanel actual = historial.pop();
        actual.setVisible(false);
        actual = historial.peek();
        frame.setSize(actual.getSize());
        actual.setVisible(true);
        frame.setLocationRelativeTo(null);
        
    }
    /***
     * LLama a la ventana de inicion de sesion
     */
    static public void abrirAdminIngreso() {
        menu.setVisible(false);
        adminIngreso.setVisible(true);
        historial.push(adminIngreso);
        frame.setSize(adminIngreso.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama al menu de admin
     */
    static public void abrirAdminMenu() {
        adminIngreso.setVisible(false);
        administracion.setVisible(true);
        historial.push(administracion);
        frame.setSize(administracion.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama al catalogo de productos
     */
    static public void abrirCatalogo() {
        administracion.setVisible(false);
        menuCliente.setVisible(false);
        catalogo.setVisible(true);
        historial.push(catalogo);
        frame.setSize(catalogo.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama a la ventana de pedidos
     */
    static public void abrirPedidos() {
        administracion.setVisible(false);
        pedidos.setVisible(true);
        historial.push(pedidos);
        frame.setSize(pedidos.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama a la ventana de manejo de productos
     */
    static public void abrirManejoProductos() {
        administracion.setVisible(false);
        adminProductos.setVisible(true);
        historial.push(adminProductos);
        frame.setSize(adminProductos.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama a las estadisticas de productos
     */
    static public void abrirEstadisticas() {
        administracion.setVisible(false);
        estadisticas.setVisible(true);
        historial.push(estadisticas);
        frame.setSize(estadisticas.getSize());
        frame.setLocationRelativeTo(null);
    }
    
    //acciones de cliente
    /***
     * LLama al menu del cliente
     */
    static public void cliente() {
        historial.push(menuCliente);
        menuCliente.setVisible(true);
        menu.setVisible(false);
        frame.setSize(menuCliente.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama a la ventanan de eliminar un pedido
     */
    static public void eliminarPedido() {
        historial.push(eliminarPedido);
        menuCliente.setVisible(false);
        eliminarPedido.setVisible(true);
        frame.setSize(eliminarPedido.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama a la ventana de realizar un pedido
     */
    static public void realizarPedido() {
        historial.push(realizarPedido);
        menuCliente.setVisible(false);
        realizarPedido.setVisible(true);
        realizarPedido.cargarLista();
        frame.setSize(realizarPedido.getSize());
        frame.setLocationRelativeTo(null);
    }
    /***
     * LLama a la ventana de atender un pedido
     */
    static public void atenderPedido() {
        administracion.setVisible(false);
        atender.setVisible(true);
        historial.push(atender);
        frame.setSize(atender.getSize());
        frame.setLocationRelativeTo(null);
    }
    
    
}
