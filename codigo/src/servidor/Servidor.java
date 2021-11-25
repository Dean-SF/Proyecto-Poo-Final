/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controladores.ServerPetition;
import controladores.TPeticion;
import datos.IConstantes;
import datos.Peticion;
import servidor.interfaz.ServerInterface;

/**
 * Clase que crea un servidor para poder comunicar tanto serverPetition con la
 * interfaz de administracion y usuario
 * @author DeanSF
 */
public class Servidor {
    private boolean encendido = true;
    private ServerPetition interprete;
    private Thread proceso;
    private String historial;
    /***
     * Contructor del servidor
     */
    public Servidor() {
        interprete = new ServerPetition();
        cargarDatos();
        proceso = new Thread( new Runnable() {
           @Override
           public void run() {
                try {
                    ServerSocket conexion = new ServerSocket(IConstantes.PUERTO);
                    while(encendido){
                        Socket cliente = conexion.accept();

                        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                        
                        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                        
                        
                        Peticion peticion = (Peticion) entrada.readObject();
                        historial(peticion);
                        if(peticion.getPeticion() == TPeticion.APAGAR)
                            break;

                        peticion = interprete.serverPeticion(peticion);

                        salida.writeObject(peticion);
                        
                        cliente.close();
                    }
                    conexion.close();
                    
                } catch (Exception e) {
                    ServerInterface.mensajeDebug(e.toString());
                }
           } 
        });
        proceso.start();
    }
    /***
     * Metodo que apaga el servidor y envia un mensaje a la interfaz
     * de que se cerro correctamente o por un error.
     */
    public void apagar() {
        this.encendido = false;
        try {
            Socket apagado = new Socket(IConstantes.HOST,IConstantes.PUERTO);

            ObjectOutputStream salida = new ObjectOutputStream(apagado.getOutputStream());

            salida.writeObject(new Peticion(TPeticion.APAGAR, null));

            apagado.close();
        } catch (Exception e) {
            ServerInterface.mensajeDebug(e.toString());
        }
        ServerInterface.mensajeErrorDetenido("Servidor apagado.");
        guardarDatos();
    }

    public void guardarDatos() {
        try {
            FileOutputStream archivo = new FileOutputStream("./src/servidor/data/data.dat");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(interprete);
            salida.close();
        } catch (IOException e) {
            System.out.println("error de guardado");
            ServerInterface.mensajeDebug(e.toString());
            return;
        }
    }

    public void cargarDatos() {
        try {
            FileInputStream archivo = new FileInputStream("./src/servidor/data/data.dat");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            interprete = (ServerPetition)entrada.readObject();
            System.out.println("cargo bien");
            entrada.close();
        } catch (IOException e) {
            System.out.println("error de carga");
            String mensaje = "Archivo de lectura aun no creado.\nEl error: " + e + " fue detenido";
            ServerInterface.mensajeErrorDetenido(mensaje);
        } catch(ClassNotFoundException e) {
            System.out.println("error de carga");
            ServerInterface.mensajeDebug(e.toString());
        }
    }

    private void historial(Peticion peticion) {
        switch(peticion.getPeticion()) {
        case AGREGAR_PED:
            historial += "\nSe agrego un pedido";
            break;
        case AGREGAR_PROD:
            historial += "\nSe agrego un producto";
            break;
        case APAGAR:
            historial += "\nSe solicito apagar el server";
            break;
        case CANTIDADES:
            historial += "\nSe solicito saber la cantidad de pedidos por tipo de solicitud";
            break;
        case CANTIDAD_PEDIDOS:
            historial += "\nSe solicito saber la cantidad total de pedidos realizados";
            break;
        case CONSULTAR_LISTA_PED:
            historial += "\nSe solicito la lista de pedidos";
            break;
        case CONSULTAR_LISTA_PROD:
            historial += "\nSe solicito la lista de productos";
            break;
        case CONSULTAR_PED:
            historial += "\nSe consulto por un pedido";
            break;
        case CONSULTAR_PROD:
            historial += "\nSe consulto por un producto";
            break;
        case ELIMINAR_PED:
            historial += "\nSe elimino un pedido";
            break;
        case ELIMINAR_PROD:
            historial += "\nSe elimino un producto";
            break;
        case ESTA_ENCENDIDO:
            historial += "\nSe solicito saber si el server esta abierto (nueva instancia del programa cliente)";
            break;
        case INGRESAR:
            historial += "\nSe solicita la comprobacion de credenciales de acceso al administrador";
            break;
        case LISTA_SIN_PEDIR:
            historial += "\nSe solicita la lista de productos sin pedir";
            break;
        case LISTA_TOP:
            historial += "\nSe solicita la lista de productos mas pedidos";
            break;
        case MODEXPRESS:
            historial += "\nSe modifico el monto extra por express";
            break;
        case MODIFICAR_PROD:
            historial += "\nSe solicito la modificacion de un producto";
            break;
        case MODRECOGER:
            historial += "\nSe modifico el porcentaje extra por empaquetado";
            break;
        default:
            historial += "\nSe realiza una peticion no existente";
            break;
        }
    }
}
