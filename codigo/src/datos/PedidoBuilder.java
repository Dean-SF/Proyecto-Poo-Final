package datos;

/**
 *
 * @author Esteban
 */
public class PedidoBuilder {
    private String codigo;
    private String nombre = "N/A";
    private int celular = 0;
    private String direccion = "N/A";
    private int precio;
    private int calorias;
    private TRecoger recoger; 
    
    public PedidoBuilder(){};
    public Pedido buildPedido(){
        return new Pedido(codigo,nombre,celular,direccion,precio,calorias,recoger);
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
    public PedidoBuilder precio(int precio){
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
