package controladores;
import datos.KVPair;
import datos.Pedido;
import datos.Producto;
import datos.TRecoger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        ArrayList<Producto> tempL = new ArrayList<Producto>();
        Pedido tempP;
        for(int i = 0; i<pedidos.size(); i++){
            tempP = pedidos.get(i);
            for(int j = 0; j<tempP.getProductos().size(); j++){
                if(!(tempL.contains(tempP.getProductos().get(j).getKey()))){
                    tempL.add(tempP.getProductos().get(j).getKey());
                    top.add(tempP.getProductos().get(j));
                }else{
                    int pos = tempL.indexOf(tempP.getProductos().get(j).getKey());
                    top.get(pos).setValue(top.get(pos).getValue()+tempP.getProductos().get(j).getValue());
                }
            }
        }
        for(int i = 0; i<top.size(); i++){
             System.out.println(top.get(i).getKey().getNombre());
        }
        Collections.sort(top, new Comparator<KVPair<Producto,Integer>>(){
            @Override
            public int compare(KVPair<Producto,Integer> P1, KVPair<Producto,Integer> P2){
                return Integer.valueOf(P1.getValue()).compareTo(P2.getValue());
            }
        });
        ArrayList<Producto> topFinal = new ArrayList<Producto>();
        for(int i = 0; i<top.size(); i++){
            if(i==10){
                break;
            }
            topFinal.add(top.get(i).getKey());
        }
        return topFinal;
    }
    public ArrayList<Producto> noVendidos(LinkedList<Producto> productos){
        ArrayList<Producto> produstoPedidos = new ArrayList<Producto>();
        Pedido tempP;
        Producto tempX;
        for(int i = 0; i<pedidos.size(); i++){
            tempP = pedidos.get(i);
            for(int j =0; j<tempP.getProductos().size();j++){
                tempX = tempP.getProductos().get(j).getKey();
                if(!(produstoPedidos.contains(tempX))){
                    produstoPedidos.add(tempX);
                }
            }
        }
        /*ArrayList<Producto> siPedidos = new ArrayList<Producto>();
        for(int i = 0; i<produstoPedidos.size(); i++){
            siPedidos.add(produstoPedidos.get(i).getKey());
        }*/
        Producto tempI;
        ArrayList<Producto> noPedidos = new ArrayList<Producto>();
        for(int i = 0; i<productos.size(); i++){
            tempI = productos.get(i);
            if(!(produstoPedidos.contains(tempI))){
                noPedidos.add(tempI);
            }
        }
        return noPedidos;
    }
    public int[] cantidadTipos(){
        int[] cantidades = {0,0,0};
        Pedido temp;
        for(int i = 0; i<pedidos.size(); i++){
            temp = pedidos.get(i);
            if(temp.getRecoger()==TRecoger.SITIO){
                cantidades[0] +=1;
            }else if(temp.getRecoger()==TRecoger.RECOGER){
                cantidades[1] +=1;
            }else if(temp.getRecoger()==TRecoger.EXPRESS){
                cantidades[2] +=1;
            }
        }
        return cantidades;
    }
    public int cantidadPedidos(){
        return pedidos.size();
    }
}
