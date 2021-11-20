/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class Pedido {
    private String codigo;
    private String nombre;
    private int celular;
    private String direccion;
    private int precio;
    private int calorias;
    private TRecoger recoger;
    private ArrayList<KVPair<Producto,Integer>> productos;

    public Pedido(){};
    
    public Pedido(String codigo, String nombre, int celular, String direccion, int precio, int calorias, TRecoger recoger) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
        this.precio = precio;
        this.calorias = calorias;
        this.recoger = recoger;
        productos = new ArrayList<KVPair<Producto,Integer>>(); 
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void setRecoger(TRecoger recoger) {
        this.recoger = recoger;
    }

    public void setProductos(ArrayList<KVPair<Producto,Integer>> productos) {
        this.productos = productos;
    }

    public String getCodigo() {
        return codigo;
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

    public int getPrecio() {
        return precio;
    }

    public int getCalorias() {
        return calorias;
    }

    public TRecoger getRecoger() {
        return recoger;
    }

    public ArrayList<KVPair<Producto,Integer>> getProductos() {
        return productos;
    }
}
