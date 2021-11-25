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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 *
 * @author DeanSF
 */
public class Cliente {
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
            JOptionPane.showMessageDialog(null, "Ocurrio el error: " + e,"ERROR",
            JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio el error: " + e,"ERROR",
            JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
}
