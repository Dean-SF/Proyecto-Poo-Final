package test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controladores.TPeticion;
import datos.IConstantes;
import datos.Peticion;
import datos.Producto;
import datos.ProductoBuilder;
import servidor.Servidor;

public class test {
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        
        Producto pedido = new ProductoBuilder()
                                        .codigo("1234")
                                        .calorias(500)
                                        .nombre("nose")
                                        .buildProducto();

        enviarPeticion(new Peticion(TPeticion.AGREGAR_PROD,pedido));

        servidor.apagar();
    }

    public static void enviarPeticion(Peticion peticion) {
        Peticion nuevaPeticion = peticion;
        try {
            Socket servidor = new Socket(IConstantes.HOST,IConstantes.PUERTO);
            
            ObjectInputStream entrada = new ObjectInputStream(servidor.getInputStream());

            ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());

            salida.writeObject(nuevaPeticion);

            Peticion retorno = (Peticion)entrada.readObject();
            
            System.out.println((boolean)retorno.getDatos());

            servidor.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
