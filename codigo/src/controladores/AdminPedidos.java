package controladores;
import datos.KVPair;
import datos.Pedido;
import datos.Producto;
import java.util.ArrayList;
import java.util.LinkedList;

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
    public boolean eliminarPedido(String codigo){
        Pedido temp = consultarPedido(codigo);
        if(temp!=null){
            pedidos.remove(temp);
            return true;
        }
        return false;
    }
    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }
    public ArrayList<Producto> topVendidos(){
        ArrayList<KVPair<Producto,Integer>> top = new ArrayList<KVPair<Producto,Integer>>();
        int menor;
        int posMenor;
        Pedido tempP;
        for(int i = 0; i<pedidos.size(); i++){
            tempP = pedidos.get(i);
            top.addAll(tempP.getProductos());
        }
        
        return null;
    }
}
