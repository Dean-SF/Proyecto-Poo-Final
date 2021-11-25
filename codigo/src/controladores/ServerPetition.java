package controladores;

import java.io.File;

import cliente.interfaz.admin.VerificadorLogin;
import datos.Peticion;
import datos.Pedido;
import datos.Producto;

/**
 * Clase que administra el adminPedidos y Productos y controla los pedidos 
 * proveinentes del servidor
 * @author Esteban
 */
public class ServerPetition {
    private AdminPedidos adminPedidos;
    private AdminProductos adminProductos;
    
    public ServerPetition(){
        adminPedidos = new AdminPedidos();
        adminProductos = new AdminProductos();
    }
    /***
     * Metodo que toma una peticio y segun el TPeticion dentro de esta
     * realiaza una funcionalidad llamando a un metodo de los dos paramatros
     * con la respusta de estos mismos
     * @param peticion
     * @return Peticion
     */
    public Peticion serverPeticion(Peticion peticion){
        TPeticion tipo = peticion.getPeticion();
        if(tipo == TPeticion.ESTA_ENCENDIDO) {
            peticion.setDatos(true);
            return peticion;
        }else if(tipo==TPeticion.AGREGAR_PROD){
            Producto temp = (Producto)peticion.getDatos();
            peticion.setDatos(adminProductos.agregarProducto(temp));
            return peticion;
        }else if(tipo==TPeticion.ELIMINAR_PROD){
            String temp = (String)peticion.getDatos();
            peticion.setDatos(adminProductos.eliminarProducto(temp));
            return peticion;
        }else if(tipo==TPeticion.CONSULTAR_PROD){
            String temp = (String)peticion.getDatos();
            peticion.setDatos(adminProductos.consultarProducto(temp));
            return peticion;
        }else if(tipo==TPeticion.MODIFICAR_PROD){
            Object temp[] = (Object[])peticion.getDatos();
            if(temp[2] instanceof Integer) {
                peticion.setDatos(adminProductos.modificarProducto((String)temp[0], 
                                                                  (TModificacion)temp[1], (Integer)temp[2]));
            } else if(temp[2] instanceof String) {
                peticion.setDatos(adminProductos.modificarProducto((String)temp[0], 
                                                                  (TModificacion)temp[1], (String)temp[2]));
            } else {
                peticion.setDatos(adminProductos.modificarProducto((String)temp[0], 
                                                                  (TModificacion)temp[1], (File)temp[2]));
            }
            return peticion;
        }else if(tipo==TPeticion.AGREGAR_PED){
            Pedido temp = (Pedido)peticion.getDatos();
            peticion.setDatos(adminPedidos.agregarPedido(temp));
            return peticion;
        }else if(tipo==TPeticion.ELIMINAR_PED){
            String temp = (String)peticion.getDatos();
            peticion.setDatos(adminPedidos.eliminarPedido(temp));
            return peticion;
        }else if(tipo==TPeticion.CONSULTAR_PED){
            String temp = (String)peticion.getDatos();
            peticion.setDatos(adminPedidos.consultarPedido(temp));
            return peticion;
        }else if(tipo==TPeticion.CONSULTAR_LISTA_PED){
            peticion.setDatos(adminPedidos.getPedidos());
            return peticion;
        }else if(tipo==TPeticion.CONSULTAR_LISTA_PROD){
            peticion.setDatos(adminProductos.getProductos());
            return peticion;
        }else if(tipo==TPeticion.LISTA_TOP){
            System.out.println("Top");   
            peticion.setDatos(adminPedidos.topVendidos());
            return peticion;
        }else if(tipo==TPeticion.LISTA_SIN_PEDIR){
            System.out.println("Sin");
            peticion.setDatos(adminPedidos.noVendidos(adminProductos.getProductos()));
            return peticion;
        }else if(tipo==TPeticion.CANTIDADES){
            System.out.println("Can");
            peticion.setDatos(adminPedidos.cantidadTipos());
            return peticion;
        }else if(tipo == TPeticion.INGRESAR){
            String credenciales = (String) peticion.getDatos();
            String [] partes  = credenciales.split("-"); 
            boolean correcto = VerificadorLogin.validarAdm(partes[0], partes[1]);
            peticion.setDatos(correcto);
            return peticion;
        }else if(tipo==TPeticion.CANTIDAD_PEDIDOS){
            peticion.setDatos(adminPedidos.cantidadPedidos());
            return peticion;
        }else if(tipo==TPeticion.MODEXPRESS) {
            int express = (Integer)peticion.getDatos();
            Pedido.setPorExpress(express);
            return peticion;
        }else if(tipo==TPeticion.MODRECOGER) {
            Double recoger = (Double)peticion.getDatos();
            Pedido.setPorRecoger(recoger);
            return peticion;
        }
        peticion.setDatos(null);
        System.out.println("A");
        return peticion;
            
    }
}
