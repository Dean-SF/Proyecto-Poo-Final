package datos;

/**
 *
 * @author Esteban
 */
public class PedidoBuilder {
    private String codigo;
    private String nombre;
    private int celular = 0;
    private String direccion = "N/A";
    private int precio;
    private int calorias;
    private TRecoger recoger; 
    
    public PedidoBuilder(){};
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
