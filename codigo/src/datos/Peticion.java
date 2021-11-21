package datos;

import java.io.Serializable;

import controladores.TPeticion;

public class Peticion implements Serializable{
    private TPeticion peticion;
    private Object datos;

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
