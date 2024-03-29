package datos;

import java.io.File;
import java.io.Serializable;

/**
 * Clase sobre un producto comestible de una tienda
 * Solo contiene los dos constructores y los set y get basicos.
 * @author DeanSF
 */
public class Producto implements Serializable{
   private String codigo;
   private String nombre;
   private String descripcion;
   private Porcion porcion;
   private int precio;
   private File imagen;
   
   /** 
    * Constructor de la clase
   */
   public Producto(){};

   /** 
    * Constructor de la clase
   */
   public Producto(String codigo, String nombre, String desccripcion, Porcion porcion, int precio, File imagen) {
       this.codigo = codigo;
       this.nombre = nombre;
       this.descripcion = desccripcion;
       this.porcion = porcion;
       this.precio = precio;
       this.imagen = imagen;
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

    public void setImagen(File imagen) {
        this.imagen = imagen;
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

    public File getImagen() {
        return imagen;
    }

    /**
     * Retorna un string con el tipo de producto
     * @return
     */
    public String saberTipo(){
        String texto = codigo.substring(0,3);
        if(texto.equals("ENT")) {
            return "Entrada";
        }else if(texto.equals("PRN")){
            return "Principal";
        }else if(texto.equals("PTR")){
            return "Postre";
        }else if(texto.equals("BEB")){
            return "Bebida";
        }
        return "";
    }

    @Override
    public String toString() {
        String toString = saberTipo() + " " + getNombre();
        return toString;
    }
}
