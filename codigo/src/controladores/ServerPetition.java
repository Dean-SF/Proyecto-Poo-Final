package controladores;

import datos.Peticion;
import datos.Pedido;
import datos.Producto;

/**
 *
 * @author Esteban
 */
public class ServerPetition {
    private AdminPedidos adminPedidos;
    private AdminProductos adminProductos;
    
    public ServerPetition(){
        adminPedidos = new AdminPedidos();
        adminProductos = new AdminProductos();
    }
    
    public Peticion serverPeticion(Peticion peticion){
        TPeticion tipo = peticion.getPeticion();
        if(tipo==TPeticion.AGREGAR_PROD){
            Producto temp = (Producto)peticion.getDatos();
            peticion.setDatos(adminProductos.agregarProducto(temp));
            return peticion;
        }else if(tipo==TPeticion.ELIMINAR_PROD){
            Producto temp = (Producto)peticion.getDatos();
            peticion.setDatos(adminProductos.eliminarProducto(temp.getCodigo()));
            return peticion;
        }else if(tipo==TPeticion.CONSULTAR_PROD){
            Producto temp = (Producto)peticion.getDatos();
            peticion.setDatos((adminProductos.consultarProducto(temp.getCodigo())!=null));
            return peticion;
        }else if(tipo==TPeticion.MODIFICAR_PROD){
            Object temp[] = (Object[])peticion.getDatos();
            if(temp[2] instanceof Integer) {
                peticion.setDatos(adminProductos.modificarProducto((String)temp[0], 
                                                                  (TModificacion)temp[1], (Integer)temp[2]));
            } else {
                peticion.setDatos(adminProductos.modificarProducto((String)temp[0], 
                                                                  (TModificacion)temp[1], (String)temp[2]));
            }
            return peticion;
        }else if(tipo==TPeticion.AGREGAR_PED){
            Pedido temp = (Pedido)peticion.getDatos();
            peticion.setDatos(adminPedidos.agregarPedido(temp));
            return peticion;
        }else if(tipo==TPeticion.ELIMINAR_PED){
            Pedido temp = (Pedido)peticion.getDatos();
            peticion.setDatos(adminPedidos.eliminarPedido(temp));
            return peticion;
        }else if(tipo==TPeticion.CONSULTAR_PED){
            Pedido temp = (Pedido)peticion.getDatos();
            peticion.setDatos((adminPedidos.consultarPedido(temp.getCodigo())!=null));
            return peticion;
        }
        peticion.setDatos(null);
        return peticion;
            
    }
}
