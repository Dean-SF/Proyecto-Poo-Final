package controladores;

import datos.Pedido;
import datos.TRecoger;
import datos.Usuario;

/**
 * Clase que contruye un pedido con sus diferentes atributos
 * y contiene todos los set, que en este caso retornan PedidoBuilder y
 * el this correspondiente al atribto del metodo para hacer un set a la
 * misma clase de esta menera.
 * @author Esteban
 */
public class PedidoBuilder {
    private String codigo;
    private String nombre;
    private int celular = 0;
    private String direccion = "N/A";
    private double precio;
    private int calorias;
    private TRecoger recoger; 
    
    public PedidoBuilder(){};
    /**
    * Crea un pedido con sus diferentes atributos y lo retorna
    * @return Pedido
    */
    public Pedido buildPedido(){
        Usuario temp = new Usuario(nombre, celular, direccion);
        return new Pedido(codigo,temp,precio,calorias,recoger);
    }
    public PedidoBuilder codigo(String codigo){
        this.codigo = codigo;
        return this;
    }
    public PedidoBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public PedidoBuilder celular(int celular){
        this.celular = celular;
        return this;
    }
    public PedidoBuilder direccion(String direccion){
        this.direccion = direccion;
        return this;
    }
    public PedidoBuilder precio(double precio){
        this.precio = precio;
        return this;
    }
    public PedidoBuilder calorias(int calorias){
        this.calorias = calorias;
        return this;
    }
    public PedidoBuilder recoger(TRecoger recoger){
        this.recoger = recoger;
        return this;
    }
}
