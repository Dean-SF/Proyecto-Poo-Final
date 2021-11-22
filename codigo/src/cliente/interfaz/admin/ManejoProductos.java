package cliente.interfaz.admin;

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

    private Object [][] vacio = {{null,null,null,null,null,null,null,null,null}};
    private JTable tabla = new JTable(vacio,columnas);
    private JScrollPane tablaScroll = new JScrollPane(tabla);

    private JButton agregar = new JButton("Agregar");
    private JButton modificar = new JButton("Modificar");
    private JButton eliminar = new JButton("Eliminar");

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
        tablaScroll.setBounds(370, 10, 880, 280);
        add(tablaScroll);

        horizontal.setBounds(360, 305, 890, 10);
        add(horizontal);

        agregar.setFont(Fonts.botones);
        agregar.setBounds(470, 320, 100, 40);
        add(agregar);

        modificar.setFont(Fonts.botones);
        modificar.setBounds(750, 320, 150, 40);
        add(modificar);

        eliminar.setFont(Fonts.botones);
        eliminar.setBounds(1070, 320, 100, 40);
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
        }
        
    }
    
}
