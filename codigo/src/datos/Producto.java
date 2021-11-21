package datos;

import java.io.Serializable;

/**
 *
 * @author DeanSF
 */
public class Producto implements Serializable{
   private String codigo;
   private String nombre;
   private String descripcion;
   private Porcion porcion;
   private int precio;
   
   public Producto(){};
   public Producto(String codigo, String nombre, String desccripcion, Porcion porcion, int precio) {
       this.codigo = codigo;
       this.nombre = nombre;
       this.descripcion = desccripcion;
       this.porcion = porcion;
       this.precio = precio;
   }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPorcion(Porcion porcion) {
        this.porcion = porcion;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Porcion getPorcion() {
        return porcion;
    }
    
    public int getPrecio() {
        return precio;
    }
}
