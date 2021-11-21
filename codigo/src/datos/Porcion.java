package datos;

import java.io.Serializable;

/**
 *
 * @author Esteban
 */
public class Porcion implements Serializable{
    private int cantidad;
    private int size;
    private int calorias;
    
    public Porcion(){};
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
