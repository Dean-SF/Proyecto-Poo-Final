package datos;

import java.io.Serializable;

/**
 * Clase que  ejemplifica la porcion de comida de un producto
 * y con tiene sus atributos con los set y get basicos.
 * @author Esteban
 */
public class Porcion implements Serializable{
    private int cantidad;
    private int size;
    private int calorias;
    
    /** 
     * Constructor de la clase
    */
    public Porcion(){};
    /** 
     * Constructor de la clase
    */
    public Porcion(int cantidad, int size, int calorias) {
        this.cantidad = cantidad;
        this.size = size;
        this.calorias = calorias;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getSize() {
        return size;
    }

    public int getCalorias() {
        return calorias;
    } 
}
