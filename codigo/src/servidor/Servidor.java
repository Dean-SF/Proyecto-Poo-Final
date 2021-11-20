/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import datos.IConstantes;

/**
 *
 * @author DeanSF
 */
public class Servidor {
    private boolean encendido = true;
    private Thread proceso;
    public Servidor() {
        proceso = new Thread( new Runnable() {
           @Override
           public void run() {
                try {
                    ServerSocket conexion = new ServerSocket(IConstantes.PUERTO);
                    while(encendido){
                        Socket cliente = conexion.accept();

                        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());

                        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());


                    }
                    conexion.close();
                    
                } catch (Exception e) {
                    
                }
           } 
        });
        proceso.start();
    }
    public void apagar() {
        this.encendido = false;
    }
}
