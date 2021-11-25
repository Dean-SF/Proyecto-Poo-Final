package cliente.interfaz.admin;

import cliente.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;
import controladores.TPeticion;
import datos.Peticion;
import datos.Producto;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/***
 * Ventana con el catalogo de productos
 * @author Esteban
 */
@SuppressWarnings("unchecked")
public class Catalogo extends JPanel implements ActionListener{
    private JButton volver = new JButton("Volver");
    private JButton graficar = new JButton("Graficar");
    private LinkedList<Producto> productos = new LinkedList<Producto>();
    private String [] titulos = {"Tipo", "Codigo","Nombre","Descripcion",
        "Porcion","Piezas","Calorias","Calorias u/n","Precio"};
    private Object [][] datos = {{null,null,null,null,null,null,null,null,null}};
    private JTable tabla = new JTable(datos,titulos);
    private JScrollPane pane = new JScrollPane(tabla);
    private JLabel filtrarLabel = new JLabel("Filtrar por:");
    private JComboBox<String> filtrarBox = new JComboBox<String>();
    
    
    private String saberTipo(String texto){
        texto = texto.substring(0,3);
        if(texto.equals("ENT")) {
            return "Entrada";
        }else if(texto.equals("PRN")){
            return "Princippal";
        }else if(texto.equals("PTR")){
            return "Postre";
        }else if(texto.equals("BEB")){
            return "Bebida";
        }
        return "";
    }
    private boolean validarFiltro(String codigo){
        String tipo =String.valueOf(filtrarBox.getSelectedItem());
        if("Ninguno".equals(tipo)){
            return true;
        }else if("Entrada".equals(tipo) && "Entrada".equals(saberTipo(codigo))){
            return true;
        }else if("Principal".equals(tipo) && "Principal".equals(saberTipo(codigo))){
            return true;
        }else if("Postre".equals(tipo) && "Postre".equals(saberTipo(codigo))){
            return true;
        }else if("Bebida".equals(tipo) && "Bebida".equals(saberTipo(codigo))){
            return true;
        }
        return false;
    }
    
    /**
     * Carga un JTable con los datos de los productos.
     */
     private void cargarTabla(){
        try{
            Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_LISTA_PROD,""));
            productos = (LinkedList<Producto>)peticion.getDatos();
            
            tabla.setVisible(true);
            DefaultTableModel modeloTabla = new DefaultTableModel(titulos, productos.size());
            for(int  i = 0; i<productos.size(); i++){
                Producto actual = productos.get(i);
                if(validarFiltro(actual.getCodigo())){
                    modeloTabla.setValueAt(saberTipo(actual.getCodigo()), i, 0);
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
            }
            tabla.setModel(modeloTabla);

            tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio el error: " + e,"Error",
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
        pane.setBounds(30, 40, 925, 360);
        add(pane);
        
        filtrarLabel.setFont(Fonts.botones);
        filtrarLabel.setBounds(450, 410, 100, 40);
        add(filtrarLabel);
        filtrarBox.setFont(Fonts.botones);
        filtrarBox.setBounds(450, 455, 100, 40);
        filtrarBox.addItem("Ninguno");
        filtrarBox.addItem("Entrada");
        filtrarBox.addItem("Principal");
        filtrarBox.addItem("Postre");
        filtrarBox.addItem("Bebida");
        add(filtrarBox);
        
        graficar.setFont(Fonts.botones);
        graficar.setBounds(340, 455, 100, 40);
        graficar.addActionListener(this);
        add(graficar);
        
        volver.setFont(Fonts.botones);
        volver.setBounds(560, 455, 100, 40);
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
