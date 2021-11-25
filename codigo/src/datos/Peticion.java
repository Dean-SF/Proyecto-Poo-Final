package datos;

import java.io.Serializable;

import controladores.TPeticion;

/***
 * Clase peticion que se usa para mandar una peticion a travez del servidor
 * con tiene un tipo peticion y un dato con sus set y get.
 * @author Esteban
 */
public class Peticion implements Serializable{
    private TPeticion peticion;
    private Object datos;

    /**
     * Constructor de la clase
     * @param peticion
     * @param datos
     */
    public Peticion(TPeticion peticion,Object datos) {
        this.peticion = peticion;
        this.datos = datos;
    }

    public Object getDatos() {
        return datos;
    }

    public TPeticion getPeticion() {
        return peticion;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }

    public void setPeticion(TPeticion peticion) {
        this.peticion = peticion;
    }
}
