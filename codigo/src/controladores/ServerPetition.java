package controladores;

import datos.KVPair;
import datos.Pedido;
import datos.Producto;

/**
 *
 * @author Esteban
 */
public class ServerPetition {
    private AdminPedidos adminPedidos;
    private AdminProductos andminProductos;
    
    public ServerPetition(){
        adminPedidos = new AdminPedidos();
        andminProductos = new AdminProductos();
    }
    
    public boolean serverPeticion(KVPair<TPeticion, Object> peticion){
        TPeticion tipo = peticion.getKey();
        if(tipo==TPeticion.AGREGAR_PROD){
            Producto temp = (Producto)peticion.getValue();
            return andminProductos.agregarProducto(temp);
        }else if(tipo==TPeticion.ELIMINAR_PROD){
            Producto temp = (Producto)peticion.getValue();
            return andminProductos.eliminarProducto(temp.getCodigo());
        }else if(tipo==TPeticion.CONSULTAR_PROD){
            Producto temp = (Producto)peticion.getValue();
            return(andminProductos.consultarProducto(temp.getCodigo())!=null);
        }else if(tipo==TPeticion.MODIFICAR_PROD){
            //return andminProductos
        }else if(tipo==TPeticion.AGREGAR_PED){
            Pedido temp = (Pedido)peticion.getValue();
            return adminPedidos.agregarPedido(temp);
        }else if(tipo==TPeticion.ELIMINAR_PED){
            Pedido temp = (Pedido)peticion.getValue();
            return adminPedidos.eliminarPedido(temp);
        }else if(tipo==TPeticion.CONSULTAR_PED){
            Pedido temp = (Pedido)peticion.getValue();
            return(adminPedidos.consultarPedido(temp.getCodigo())!=null);
        }
        return false;
    }
}
