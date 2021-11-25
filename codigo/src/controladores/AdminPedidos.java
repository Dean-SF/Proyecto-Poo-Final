package controladores;
import datos.KVPair;
import datos.Pedido;
import datos.Producto;
import datos.TRecoger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Clase que administra los pedidos y la cantidad realizada de estos
 * @author Esteban
 */
public class AdminPedidos implements Serializable{
    private static double porRecoger = 0.1;
    private static double porExpress = 500;
    private ArrayList<Pedido> pedidos;
    private int cantidad;

    public AdminPedidos(){
        pedidos = new ArrayList<Pedido>();
        cantidad = 0;
    }
    /***
     * agrega un producto a la lista de productos y le suma a la cantidad de pedidos
     * @param pedido
     * @return boolean
     */
    public boolean agregarPedido(Pedido pedido){
        if(consultarPedido(pedido.getCodigo())==null){
            pedidos.add(pedido);
            cantidad+=1;
            return true;
        }
        return false;
    }
    /***
     * Consulta un pedido mediante el codigo y si existe retorna el pedido si
     * no retorna null
     * @param codigo
     * @return Pedido
     */
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
    /***
     * Metodo que elimina un pedido de la lista de pedidos
     * @param codigo
     * @return boolean
     */
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
    /***
     * Metodo que devulve una lista con los productos mas vendidos 
     * dentro de los pedidos
     * @return ArrayList-Producto
     */
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
    /***
     * Metodo que retorna una lista con los productos no vendidos segun los 
     * productos pedidos y la lista total de productos
     * @param productos
     * @return ArrayList-Producto
     */
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
    /***
     * Metodo que cuenta la cantidad de pedidos de cada tipo y retorna un []
     * con tres numero, [0] para sitio, [1] para recoger y [2] para express 
     * @return int[]
     */
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
    /***
     * Metodo que retorna la cantidad total de pedidos realizados
     * @return 
     */
    public int cantidadPedidos(){
        return cantidad;
    }

    public static void setPorExpress(double porExpress) {
        AdminPedidos.porExpress = porExpress;
    }

    public static void setPorRecoger(double porRecoger) {
        AdminPedidos.porRecoger = porRecoger;
    }

    public static double getPorExpress() {
        return porExpress;
    }

    public static double getPorRecoger() {
        return porRecoger;
    }
}
