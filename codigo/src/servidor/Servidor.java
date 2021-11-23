/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

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
 *
 * @author DeanSF
 */
public class Servidor {
    private boolean encendido = true;
    private ServerPetition interprete = new ServerPetition();
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
                        
                        
                        Peticion peticion = (Peticion) entrada.readObject();
                        if(peticion.getPeticion() == TPeticion.APAGAR)
                            break;

                        peticion = interprete.serverPeticion(peticion);

                        salida.writeObject(peticion);
                        
                        cliente.close();
                    }
                    conexion.close();
                    
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    ServerInterface.mensajeDebug(e.toString());
                }
           } 
        });
        proceso.start();
    }
    public void apagar() {
        this.encendido = false;
        try {
            Socket apagado = new Socket(IConstantes.HOST,IConstantes.PUERTO);

            ObjectOutputStream salida = new ObjectOutputStream(apagado.getOutputStream());

            salida.writeObject(new Peticion(TPeticion.APAGAR, null));

            apagado.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            ServerInterface.mensajeDebug(e.toString());
        }
        
    }
}
