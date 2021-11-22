package cliente.interfaz.admin;

import cliente.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;
import controladores.TPeticion;
import datos.Peticion;
import datos.Producto;
import datos.ProductoBuilder;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Catalogo extends JPanel implements ActionListener{
    /*private JLabel titulo = new JLabel("Catalogo");
    private JButton volver = new JButton("Volver");

    // Tabla
    private String [] columnas = {"Tipo","Codigo","Nombre","Descripcion","Porcion",
                                         "Piezas","Calorias","Calorias u/n","precio"};

    private Object [][] vacio = {{null,null,null,null,null,null,null,null,null}};
    private JTable tabla = new JTable(vacio,columnas);
    private JScrollPane tablaScroll = new JScrollPane(tabla);



    public Catalogo() {
        // Setup
        setSize(1000, 540);
        setVisible(false);
        setLayout(null);
        
        titulo.setFont(Fonts.titulos);
        titulo.setBounds(410, 30, 200, 40);
        add(titulo);

        tabla.setFont(Fonts.tabla);
        tabla.setEnabled(false);
        tablaScroll.setBounds(30, 90, 925, 360);
        add(tablaScroll);

        volver.setFont(Fonts.botones);
        volver.setBounds(880, 455, 100, 40);
        volver.addActionListener(this);
        add(volver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            GestorVentanas.volverAtras();
        }
        
    }*/
    private JButton volver = new JButton("Volver");
    private JButton graficar = new JButton("Graficar");
    private LinkedList<Producto> productos = new LinkedList<Producto>();
    private String [] titulos = {"Tipo", "Codigo","Nombre","Descripcion",
        "Porcion","Piezas","Calorias","Calorias u/n","Precio"};
    private Object [][] datos = {{null,null,null,null,null,null,null,null,null}};
    private JTable tabla = new JTable(datos,titulos);
    private JScrollPane pane = new JScrollPane(tabla);
    private int x = 0;
    
    /**
     * Carga un JTable con los datos de los productos.
     */
     private void cargarTabla(){
        try{
            Producto producto = new ProductoBuilder().nombre("Prueba"+x)
                    .codigo("x"+x)
                    .size(1)
                    .buildProducto();
            x+=1;
            Cliente.enviarPeticion(new Peticion(TPeticion.AGREGAR_PROD,producto));
            Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_LISTA_PROD,""));
            productos = (LinkedList)peticion.getDatos();
            
            tabla.setVisible(true);
            DefaultTableModel modeloTabla = new DefaultTableModel(titulos, productos.size());
            for(int  i = 0; i<productos.size(); i++){
                Producto actual = productos.get(i);
                modeloTabla.setValueAt("n/a", i, 0);
                modeloTabla.setValueAt(actual.getCodigo(), i, 1); 
                modeloTabla.setValueAt(actual.getNombre(),i,2);
                modeloTabla.setValueAt(actual.getDescripcion(), i, 3);
                modeloTabla.setValueAt(actual.getPorcion().getSize(), i, 4);
                modeloTabla.setValueAt(actual.getPorcion().getCantidad(), i, 5);
                modeloTabla.setValueAt(actual.getPorcion().getCalorias(), i, 6);
                modeloTabla.setValueAt((actual.getPorcion().getCalorias()/
                        actual.getPorcion().getSize()), i, 7);
                modeloTabla.setValueAt(actual.getPrecio(), i, 8);
            }
            tabla.setModel(modeloTabla);

            tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
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
    public Catalogo(){
        // Setup
        setSize(1000, 540);
        setVisible(false);
        setLayout(null);
        
        tabla.setFont(Fonts.tabla);
        tabla.setEnabled(false);
        pane.setBounds(30, 90, 925, 360);
        add(pane);
        
        graficar.setFont(Fonts.tabla);
        graficar.setBounds(880, 455, 100, 100);
        graficar.addActionListener(this);
        add(graficar);
        
        volver.setFont(Fonts.botones);
        volver.setBounds(880, 455, 100, 40);
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
