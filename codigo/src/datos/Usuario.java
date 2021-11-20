package datos;

/**
 *
 * @author Esteban
 */
public class Usuario {
    private String nombre;
    private int celular;
    private String direccion;
    
    public Usuario(){};
    public Usuario(String nombre, int celular, String direccion) {
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCelular() {
        return celular;
    }

    public String getDireccion() {
        return direccion;
    }
}
