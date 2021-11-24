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
        ArrayList<String> codigos = new  ArrayList<String>();
        KVPair<Producto,Integer> tempPair;
        Pedido tempP;
        for(int i = 0; i<pedidos.size(); i++){
            tempP = pedidos.get(i);
            for(int j = 0; j<tempP.getProductos().size(); j++){
                tempPair = tempP.getProductos().get(j);
                if(codigos.contains(tempPair.getKey().getCodigo())){
                    int pos = codigos.indexOf(tempPair.getKey().getCodigo());
                    top.get(pos).setValue(top.get(pos).getValue()+tempP.getProductos().get(j).getValue());
                }else{
                    codigos.add(tempPair.getKey().getCodigo());
                    top.add(tempPair);
                }
                /*if(!(tempL.contains(tempP.getProductos().get(j).getKey()))){
                    tempL.add(tempP.getProductos().get(j).getKey());
                    top.add(tempP.getProductos().get(j));
                }else{
                    int pos = tempL.indexOf(tempP.getProductos().get(j).getKey());
                    top.get(pos).setValue(top.get(pos).getValue()+tempP.getProductos().get(j).getValue());
                }*/
            }
        }
        Collections.sort(top, new Comparator<KVPair<Producto,Integer>>(){
            @Override
            public int compare(KVPair<Producto,Integer> P1, KVPair<Producto,Integer> P2){
                if(P1.getValue()<P2.getValue()){
                    return 1;
                }else{
                    return -1;
                }
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
        ArrayList<Producto> vendidos = new ArrayList<Producto>();
        ArrayList<String> codigos = new  ArrayList<String>();
        Producto tempTotal;
        Pedido tempSi;
        for(int i = 0; i<pedidos.size(); i++){
            tempSi = pedidos.get(i);
            for(int j = 0; j<tempSi.getProductos().size(); j++){
                tempTotal = tempSi.getProductos().get(j).getKey();
                if(!codigos.contains(tempTotal.getCodigo())){
                    codigos.add(tempTotal.getCodigo());
                    vendidos.add(tempTotal);
                }
            }
        }
        ArrayList<Producto> noVendidos = new ArrayList<Producto>();
        for(Producto actual : productos){
            if(!codigos.contains(actual.getCodigo())){
                noVendidos.add(actual);
            }
        }
        return noVendidos;
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
