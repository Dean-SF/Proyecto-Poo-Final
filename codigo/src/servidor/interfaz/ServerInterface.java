package servidor.interfaz;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import servidor.Servidor;

/***
 * Clase que crea la interfaz para el servidor
 * @author Deyan
 */
public class ServerInterface{
    private static Servidor server = null;
    private JFrame frame = new JFrame();
    private static Principal principal = new Principal();
    private static Historial historial = new Historial();

    /***
     * Contructor del serverInterface con sus dos botones la caja de texto y 
     * demas detalles de la ventana
     */
    public ServerInterface() {
        frame.setTitle("Proyecto 2 - Programación Orientada a Objetos - Servidor");
        frame.setSize(640, 435);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(null);

        frame.add(principal);
        frame.add(historial);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                protocoloDeCerrado();
            }
        });

    }
    
    /***
     * Metodo que imprime un mensaje en la caja de texto y cierra el server
     * @param mensaje 
     */
    public static void mensajeDebug(String mensaje) {
        Principal.setDebug(mensaje);
        server = null;
    }
    /**
     * Metodo que imprime un mensaje en la caja de texto pero no cierra el server
     * @param mensaje
     */
    public static void mensajeErrorDetenido(String mensaje) {
        Principal.setDebug(mensaje);
    }

    /** 
     * Metodo que cierra la ventana
    */
    private void protocoloDeCerrado() {
        if(server != null) {
            server.apagar();
        }
        frame.dispose();
    }

    public static Servidor getServer() {
        return server;
    }

    public static void setServer(Servidor server) {
        ServerInterface.server = server;
    }

    /** 
     * Metodo que abre la ventana del historial
    */
    public static void abrirHistorial() {
        principal.setVisible(false);
        historial.setVisible(true);
    }

    /** 
     * Metodo para volver a la pantalla principal
    */
    public static void volver() {
        historial.setVisible(false);
        principal.setVisible(true);
    }
}
