package datos;

/**
 *
 * @author DeanSF
 */
public class Producto {
   private String codigo;
   private String nombre;
   private String descripcion;
   private int size;
   private int cantidad;
   private int calorias;
   private int caloriasUnidad;
   private int precio;
   
   public Producto(){};
   public Producto(String codigo, String nombre, String desccripcion, int size, int cantidad, int calorias, int caloriasUnidad, int precio) {
       this.codigo = codigo;
       this.nombre = nombre;
       this.descripcion = desccripcion;
       this.size = size;
       this.cantidad = cantidad;
       this.calorias = calorias;
       this.caloriasUnidad = caloriasUnidad;
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

    public void setSize(int size) {
        this.size = size;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void setCaloriasUnidad(int caloriasUnidad) {
        this.caloriasUnidad = caloriasUnidad;
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

    public int getSize() {
        return size;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCalorias() {
        return calorias;
    }

    public int getCaloriasUnidad() {
        return caloriasUnidad;
    }

    public int getPrecio() {
        return precio;
    }
}
