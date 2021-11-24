/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import cliente.interfaz.GestorVentanas;
import controladores.TPeticion;
import datos.IConstantes;
import datos.Peticion;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 *
 * @author DeanSF
 */
public class Cliente {
    private Peticion nuevaPeticion;

    public void setNuevaPeticion(Peticion nuevaPeticion) {
        this.nuevaPeticion = nuevaPeticion;
    }

    public Peticion getNuevaPeticion() {
        return nuevaPeticion;
    }
    
    public Object getRespuestaServer(){
        return nuevaPeticion.getDatos();
    }
    public static void main(String[] args) {
        Peticion encendido = new Peticion(TPeticion.ESTA_ENCENDIDO, null);
        if(enviarPeticion(encendido) != null) {
            new GestorVentanas();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexion con el servidor","ERROR",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Peticion enviarPeticion(Peticion peticion) {
        Peticion nuevaPeticion = peticion;
        try {
            Socket servidor = new Socket(IConstantes.HOST,IConstantes.PUERTO);
            
            ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());

            ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());

            salida.writeObject(nuevaPeticion);

            Peticion retorno = (Peticion)entrada.readObject();
            
            servidor.close();

            return retorno;

        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
    
    public Cliente(Peticion laPeticion) {

        nuevaPeticion = laPeticion;
        try {
            // establezco comunicacion con el servidor
            Socket skCliente = new Socket(IConstantes.HOST, IConstantes.PUERTO);

            // abrir el canal de envío del socket que va hacia el servidor
            OutputStream auxSalida = skCliente.getOutputStream();
            ObjectOutputStream flujoSalida = new ObjectOutputStream(auxSalida);

            // abrir el canal de recepcion del socket que viene desde el servidor
            InputStream auxEntrada = skCliente.getInputStream();
            ObjectInputStream flujoEntrada = new ObjectInputStream(auxEntrada);

            // envio al servidor
            flujoSalida.writeObject(nuevaPeticion);
            try {
                // recibiendo la respuesta del servidor
                nuevaPeticion = (Peticion) flujoEntrada.readObject();
                System.out.println(nuevaPeticion.getDatos());
            } catch (ClassNotFoundException ex) {
                System.out.println("problemas de casting");
            }

            //desconecto el socket
            skCliente.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
