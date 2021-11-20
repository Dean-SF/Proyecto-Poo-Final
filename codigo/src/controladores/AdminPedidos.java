package controladores;
import datos.Pedido;
import java.util.ArrayList;

/**
 *
 * @author Esteban
 */
public class AdminPedidos {
    private ArrayList<Pedido> pedidos;

    public AdminPedidos(){
        pedidos = new ArrayList<Pedido>();
    }
    
    public boolean agregarPedido(Pedido pedido){
        if(consultarPedido(pedido.getCodigo())==null){
            pedidos.add(pedido);
            return true;
        }
        return false;
    }
    public Pedido consultarPedido(String codigo){
        Pedido temp;
        for(int i = 0; i<pedidos.size(); i++){
            temp = pedidos.get(i);
            if(temp.getCodigo().equals(codigo)){
                return temp;
            }
        }
        return null;
    }
    public boolean eliminarPedido(Pedido pedido){
        if(consultarPedido(pedido.getCodigo())!=null){
            pedidos.remove(pedido);
            return true;
        }
        return false;
    }
    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }
}
