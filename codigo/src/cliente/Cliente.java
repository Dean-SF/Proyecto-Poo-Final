/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import cliente.interfaz.GestorVentanas;
import datos.IConstantes;
import datos.Peticion;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author DeanSF
 */
public class Cliente {
    public static void main(String[] args) {
        new GestorVentanas();
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

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
