/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.interfaz.admin;

import cliente.Cliente;
import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;
import controladores.TPeticion;
import datos.Peticion;
import datos.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Esteban
 */
public class EstadisticasPedidos extends JPanel implements ActionListener{
    private JLabel tituloH = new JLabel("Top 10");
    private JLabel tituloL = new JLabel("No pedidos");
    private JLabel tituloP = new JLabel("Porcentajes de pedidos");
    private JButton volver = new JButton("Volver");
    private JButton graficar = new JButton("Graficar");
    private LinkedList<Producto> productosH = new LinkedList<Producto>();
    private LinkedList<Producto> productosL = new LinkedList<Producto>();
    private String [] titulosH = {"Codigo","Nombre","Descripcion","Tipo"};
    private String [] titulosL = {"Codigo","Nombre","Tipo"};
    private Object [][] datosH = {{null,null,null,null}};
    private Object [][] datosL = {{null,null,null}};
    private JTable tablaH = new JTable(datosH,titulosH);
    private JTable tablaL = new JTable(datosL,titulosL);
    private JScrollPane paneH = new JScrollPane(tablaH);
    private JScrollPane paneL = new JScrollPane(tablaL);
    
    /**
     * Carga un JTable con los datos de los productos.
     */
     private void cargarTabla(){
        try{
            Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_LISTA_PROD,""));
            productosH = (LinkedList<Producto>)peticion.getDatos();
            productosL = (LinkedList<Producto>)peticion.getDatos();
            tablaH.setVisible(true);
            tablaL.setVisible(true);
            DefaultTableModel modeloTablaH = new DefaultTableModel(titulosH, productosH.size());
            DefaultTableModel modeloTablaL = new DefaultTableModel(titulosL, productosL.size());
            /*for(int  i = 0; i<productos.size(); i++){
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
            tabla.getColumnModel().getColumn(4).setPreferredWidth(20);*/
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
    public EstadisticasPedidos(){
        // Setup
        setSize(1100, 700);
        setVisible(false);
        setLayout(null);
        
        tituloH.setFont(Fonts.botones);
        tituloH.setBounds(220, 30, 300, 25);
        add(tituloH);
        
        tituloL.setFont(Fonts.botones);
        tituloL.setBounds(800, 30, 300, 25);
        add(tituloL);
        
        tituloP.setFont(Fonts.botones);
        tituloP.setBounds(450, 400, 300, 25);
        add(tituloP);
        
        tablaH.setFont(Fonts.tabla);
        tablaH.setEnabled(false);
        paneH.setBounds(30, 90, 450, 300);
        add(paneH);
        
        tablaL.setFont(Fonts.tabla);
        tablaL.setEnabled(false);
        paneL.setBounds(600, 90, 450, 300);
        add(paneL);
        
        graficar.setFont(Fonts.botones);
        graficar.setBounds(370, 600, 100, 40);
        graficar.addActionListener(this);
        add(graficar);
        
        volver.setFont(Fonts.botones);
        volver.setBounds(570, 600, 100, 40);
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
            tablaH.setVisible(false);
            tablaL.setVisible(false);
            GestorVentanas.volverAtras();
        }else if(e.getSource() == graficar){
            cargarTabla();
        }
    }
}
