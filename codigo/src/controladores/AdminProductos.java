package controladores;

import java.io.File;
import java.util.LinkedList;
import datos.Producto;

public class AdminProductos {
    private LinkedList<Producto> productos;

    AdminProductos() {
        productos = new LinkedList<Producto>();
    }

    public boolean agregarProducto(Producto producto) {
        if(consultarProducto(producto.getCodigo()) != null) {
            return false;
        }
        productos.add(producto);
        return true;
    }

    public Producto consultarProducto(String codigo) {
        for(Producto actual : productos) {
            if(actual.getCodigo().equals(codigo)) {
                return actual;
            }
        }
        return null;
    }

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
