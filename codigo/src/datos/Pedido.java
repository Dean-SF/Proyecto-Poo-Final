/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class Pedido implements Serializable{
    private static double porRecoger = 0.1;
    private static double porExpress = 500;
    private String codigo;
    private Usuario usuario;
    private double precio;
    private int calorias;
    private TRecoger recoger;
    private ArrayList<KVPair<Producto, Integer>> productos;

    public Pedido(){};
    
    public Pedido(String codigo, Usuario usuario, double precio, int calorias, TRecoger recoger) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.precio = precio;
        this.calorias = calorias;
        this.recoger = recoger;
        productos = new ArrayList<KVPair<Producto, Integer>>(); 
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public void setProductos(ArrayList<KVPair<Producto, Integer>> productos) {
        this.productos = productos;
    }

    public static void setPorRecoger(double porRecoger) {
        Pedido.porRecoger = porRecoger;
    }

    public static void setPorExpress(double porExpress) {
        Pedido.porExpress = porExpress;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public double getPrecio() {
        return precio;
    }

    public int getCalorias() {
        return calorias;
    }

    public TRecoger getRecoger() {
        return recoger;
    }

    public double getPorRecoger() {
        return porRecoger;
    }

    public double getPorExpress() {
        return porExpress;
    }
    
    public ArrayList<KVPair<Producto, Integer>> getProductos() {
        return productos;
    }
}
