package cliente.interfaz.admin;

import cliente.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;
import controladores.TPeticion;
import datos.Peticion;
import datos.Producto;
import datos.ProductoBuilder;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManejoProductos extends JPanel implements ActionListener{

    private JButton volver = new JButton("Volver");

    private JSeparator vertical = new JSeparator(SwingConstants.VERTICAL);
    private JSeparator horizontal = new JSeparator(SwingConstants.HORIZONTAL);

    private JLabel lCodigo = new JLabel("Codigo:");
    private JTextField codigo = new JTextField();

    private JLabel lNombre = new JLabel("Nombre:");
    private JTextField nombre = new JTextField();

    private JLabel lDescripcion = new JLabel("Descripcion:");
    private JTextArea descripcion = new JTextArea();
    private JScrollPane descScroll = new JScrollPane(descripcion);

    private JLabel lTamanno = new JLabel("Tamaño:");
    private JTextField tamanno = new JTextField();

    private JLabel lCantidad = new JLabel("Cantidad:");
    private JTextField cantidad = new JTextField();

    private JLabel lCalorias = new JLabel("Calorias:");
    private JTextField calorias = new JTextField();

    private JLabel lPrecio = new JLabel("Precio:");
    private JTextField precio = new JTextField();

    // Tabla
    private String [] columnas = {"Tipo","Codigo","Nombre","Descripcion","Porcion",
                                         "Piezas","Calorias","Calorias u/n","precio"};
    private LinkedList<Producto> productos = new LinkedList<Producto>();
    private Object [][] vacio = {{null,null,null,null,null,null,null,null,null}};
    private JTable tabla = new JTable(vacio,columnas);
    private JScrollPane tablaScroll = new JScrollPane(tabla);

    private JButton agregar = new JButton("Agregar");
    private JButton modificar = new JButton("Modificar");
    private JButton eliminar = new JButton("Eliminar");
    
    /**
     * Carga un JTable con los datos de los productos.
     */
     private void cargarTabla(){
        try{
            tabla.setVisible(false);
            Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_LISTA_PROD,""));
            productos = (LinkedList<Producto>)peticion.getDatos();
            tabla.setVisible(true);
            DefaultTableModel modeloTabla = new DefaultTableModel(columnas, productos.size());
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
    
    public ManejoProductos() {
        // Setup
        setSize(1280, 450);
        setVisible(false);
        setLayout(null);
        
        lCodigo.setFont(Fonts.labels);
        lCodigo.setBounds(10, 10, 100, 35);
        add(lCodigo);
        codigo.setFont(Fonts.textField);
        codigo.setBounds(95, 17, 100, 25);
        add(codigo);

        lNombre.setFont(Fonts.labels);
        lNombre.setBounds(10, 50, 100, 35);
        add(lNombre);
        nombre.setFont(Fonts.textField);
        nombre.setBounds(105, 57, 100, 25);
        add(nombre);

        lDescripcion.setFont(Fonts.labels);
        lDescripcion.setBounds(10, 100, 200, 35);
        add(lDescripcion);
        descripcion.setFont(Fonts.textField);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descScroll.setBounds(10, 135, 330, 100);
        add(descScroll);

        lTamanno.setFont(Fonts.labels);
        lTamanno.setBounds(10, 235, 100, 35);
        add(lTamanno);
        tamanno.setFont(Fonts.textField);
        tamanno.setBounds(105, 242, 100, 25);
        add(tamanno);

        lCantidad.setFont(Fonts.labels);
        lCantidad.setBounds(10, 275, 100, 35);
        add(lCantidad);
        cantidad.setFont(Fonts.textField);
        cantidad.setBounds(110, 282, 100, 25);
        add(cantidad);

        lCalorias.setFont(Fonts.labels);
        lCalorias.setBounds(10, 315, 100, 35);
        add(lCalorias);
        calorias.setFont(Fonts.textField);
        calorias.setBounds(100, 322, 100, 25);
        add(calorias);

        lPrecio.setFont(Fonts.labels);
        lPrecio.setBounds(10, 350, 100, 35);
        add(lPrecio);
        precio.setFont(Fonts.textField);
        precio.setBounds(85, 357, 100, 25);
        add(precio);

        vertical.setBounds(350, 10, 10, 660);
        add(vertical);

        tabla.setFont(Fonts.tabla);
        tabla.setEnabled(false);
        tabla.setVisible(true);
        tablaScroll.setBounds(370, 10, 880, 280);
        add(tablaScroll);
        cargarTabla();

        horizontal.setBounds(360, 305, 890, 10);
        add(horizontal);

        agregar.setFont(Fonts.botones);
        agregar.setBounds(470, 320, 100, 40);
        agregar.addActionListener(this);
        add(agregar);

        modificar.setFont(Fonts.botones);
        modificar.setBounds(750, 320, 150, 40);
        modificar.addActionListener(this);
        add(modificar);

        eliminar.setFont(Fonts.botones);
        eliminar.setBounds(1070, 320, 100, 40);
        eliminar.addActionListener(this);
        add(eliminar);

        volver.setFont(Fonts.botones);
        volver.setBounds(1160, 370, 100, 40);
        volver.addActionListener(this);
        add(volver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            GestorVentanas.volverAtras();
        }else if(e.getSource() == eliminar){
            cargarTabla();
        }else if(e.getSource() == modificar){
            cargarTabla();
        }else if(e.getSource() == agregar){
            cargarTabla();
        }
        
    }
    
}
