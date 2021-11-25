package controladores;

import java.io.File;
import java.util.LinkedList;
import datos.Producto;

/***
 * Clase que se encarga del manejo de los diferentes produtos.
 * @author Deyan
 */
public class AdminProductos {
    private LinkedList<Producto> productos;

    AdminProductos() {
        productos = new LinkedList<Producto>();
    }
    /***
     * Metodo que agrega un un producto a la lista de producto
     * si esta no esta agregado
     * @param producto
     * @return boolean
     */
    public boolean agregarProducto(Producto producto) {
        if(consultarProducto(producto.getCodigo()) != null) {
            return false;
        }
        productos.add(producto);
        return true;
    }
    /***
     * Metodo que consulta un producto mediante su codigo, si este esta
     * retorna el producto si no retorna null.
     * @param codigo
     * @return Prodcuto
     */
    public Producto consultarProducto(String codigo) {
        for(Producto actual : productos) {
            if(actual.getCodigo().equals(codigo)) {
                return actual;
            }
        }
        return null;
    }
    /***
     * Metodo que modifica un producto segun el parametro de entrada (string) y el
     * tipo de modificacion
     * @param codigo
     * @param tipo
     * @param valor
     * @return boolean
     */
    public boolean modificarProducto(String codigo,TModificacion tipo, String valor) {
        Producto producto = consultarProducto(codigo);
        if(producto == null) {
            return false;
        }
        switch (tipo) {
        case CODIGO:
            producto.setCodigo(valor);
            return true;
        case DESC:
            producto.setDescripcion(valor);
            return true;
        case NOMBRE:
            producto.setNombre(valor);
            return true;
        default:
            return false;
        }

    }
    /***
     * Metodo que modifica un producto segun el parametro de entrada (int) y el
     * tipo de modificacion
     * @param codigo
     * @param tipo
     * @param valor
     * @return boolean
     */
    public boolean modificarProducto(String codigo,TModificacion tipo, int valor) {
        Producto producto = consultarProducto(codigo);
        if(producto == null) {
            return false;
        }
        switch (tipo) {
        case CALORIAS:
            producto.getPorcion().setCalorias(valor);
            return true;
        case CANTIDAD:
            producto.getPorcion().setCantidad(valor);
            return true;
        case PRECIO:
            producto.setPrecio(valor);
            return true;
        case SIZE:
            producto.getPorcion().setSize(valor);
            return true;
        default:
            return false;

        }
    }
    /***
     * Metodo que modifica la imagen de un producto 
     * @param codigo
     * @param tipo
     * @param imagen
     * @return boolean
     */
    public boolean modificarProducto(String codigo,TModificacion tipo, File imagen) {
        Producto producto = consultarProducto(codigo);
        if(producto == null) {
            return false;
        }
        if(tipo == TModificacion.IMAGEN) {
            producto.setImagen(imagen);
            return true;
        }
        return false;
    }
    /***
     * Metodo que elimina un producto de la lista de productos
     * @param codigo
     * @return 
     */
    public boolean eliminarProducto(String codigo) {
        Producto producto = consultarProducto(codigo);
        if(producto == null) {
            return false;
        }
        productos.remove(producto);
        return true;
    }
    
    public LinkedList<Producto> getProductos(){
        return productos;
    }

}
