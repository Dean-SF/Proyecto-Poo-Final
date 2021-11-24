package cliente.interfaz.admin;

import cliente.Cliente;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;
import controladores.TPeticion;
import datos.KVPair;
import datos.Pedido;
import datos.PedidoBuilder;
import datos.Peticion;
import datos.Producto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("unchecked")
public class Pedidos extends JPanel implements ActionListener{
    private JButton volver = new JButton("Volver");
    private JButton graficar = new JButton("Graficar");
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    private String [] titulos = {"Codigo", "Nombre","Productos","Precio","Calorias"};
    private Object [][] datos = {{null,null,null,null,null}};
    private JTable tabla = new JTable(datos,titulos);
    private JScrollPane pane = new JScrollPane(tabla);
    
    /**
     * Carga un JTable con los datos de los productos.
     */
    private void cargarTabla(){
        try{
            /*Pedido ned = new PedidoBuilder()
                    .codigo("RE-01")
                    .nombre("Perrito caliente")
                    .precio(3500)
                    .calorias(300)
                    .buildPedido();*/
            //Cliente.enviarPeticion(new Peticion(TPeticion.AGREGAR_PED,ned));
            Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_LISTA_PED,""));
            pedidos = (ArrayList<Pedido>)peticion.getDatos();
            tabla.setVisible(true);
            DefaultTableModel modeloTabla = new DefaultTableModel(titulos, pedidos.size());
            String productosD;
            ArrayList<KVPair<Producto, Integer>> listaProductos;
            System.out.println(pedidos.size());
            for(int  i = 0; i<pedidos.size(); i++){
                System.out.println(i);
                Pedido actual = pedidos.get(i);
                productosD = "";
                listaProductos = actual.getProductos();
                Producto temp;
                int cantidad;
                productosD += "[";
                for(int j = 0; j<listaProductos.size(); j++){
                    temp = listaProductos.get(j).getKey();
                    cantidad = listaProductos.get(j).getValue();
                    productosD += "{"+temp.getNombre()+"("+temp.getCodigo()+
                            ") Cantidad: "+cantidad+"}";
                    if(j<listaProductos.size()-1){
                        productosD+=", \n";
                    }else{
                        productosD+="]";
                    }
                }
                modeloTabla.setValueAt(actual.getCodigo(), i, 0);
                modeloTabla.setValueAt(actual.getUsuario().getNombre(), i, 1); 
                modeloTabla.setValueAt(productosD,i,2);
                modeloTabla.setValueAt(actual.getPrecio(), i, 3);
                modeloTabla.setValueAt(actual.getCalorias(), i, 4);
            }
            System.out.println("1");
            tabla.setModel(modeloTabla);
            System.out.println("1");
            tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
        } catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Esto no deberia de estar aqui","Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    /**
    * Constructoder de la ventana con los botones y el JTable.
    */
    public Pedidos(){
        // Setup
        setSize(1000, 540);
        setVisible(false);
        setLayout(null);
        
        tabla.setFont(Fonts.tabla);
        tabla.setEnabled(false);
        pane.setBounds(30, 90, 925, 360);
        add(pane);
        
        graficar.setFont(Fonts.botones);
        graficar.setBounds(350, 455, 100, 40);
        graficar.addActionListener(this);
        add(graficar);
        
        volver.setFont(Fonts.botones);
        volver.setBounds(550, 455, 100, 40);
        volver.addActionListener(this);
        add(volver);
        
        this.setVisible(false);
    }
    
    /**
     * Contiene los actionListeners de los botones del panel.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver){
            tabla.setVisible(false);
            GestorVentanas.volverAtras();
        }else if(e.getSource() == graficar){
            cargarTabla();
        }
    }
    
}
