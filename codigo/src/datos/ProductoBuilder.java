package datos;

import java.io.File;

/**
 *
 * @author Esteban
 */
public class ProductoBuilder {
   private String codigo;
   private String nombre;
   private String descripcion;
   private int size;
   private int cantidad;
   private int calorias;
   private int precio;
   private File imagen;
   
   public ProductoBuilder(){};
    public Producto buildProducto(){
        Porcion temp = new Porcion(cantidad,size,calorias);
        return new Producto(codigo,nombre,descripcion,temp,precio,imagen);
    }
    public ProductoBuilder codigo(String codigo){
        this.codigo = codigo;
        return this;
    }
    public ProductoBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public ProductoBuilder descripcion(String descripcion){
        this.descripcion = descripcion;
        return this;
    }
    public ProductoBuilder size(int size){
        this.size = size;
        return this;
    }
    public ProductoBuilder cantidad(int cantidad){
        this.cantidad = cantidad;
        return this;
    }
    public ProductoBuilder calorias(int calorias){
        this.calorias = calorias;
        return this;
    }
    public ProductoBuilder precio(int precio){
        this.precio = precio;
        return this;
    }
    public ProductoBuilder imagen(File imagen){
        this.imagen = imagen;
        return this;
    }
}
