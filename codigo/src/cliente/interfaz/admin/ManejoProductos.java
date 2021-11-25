package cliente.interfaz.admin;

import cliente.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;
import controladores.TModificacion;
import controladores.TPeticion;
import datos.Peticion;
import datos.Producto;
import datos.ProductoBuilder;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;

/***
 * Ventana para el manejo de los productos
 * @author Esteban
 */
@SuppressWarnings("unchecked")
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

    private JFileChooser imagen = new JFileChooser();
    private JButton selImagen = new JButton("Imagen");

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

    private JTextField porExpress = new JTextField();
    private JTextField porRecoger = new JTextField();
    private JButton bExpress = new JButton("Extra por Express");
    private JButton bRecoger = new JButton("Porcentaje por empaque");
    
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
            JOptionPane.showMessageDialog(this, "Ocurrio el error: " + e,"Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    /***
     * Constructor con sus partes
     */
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

        imagen.removeChoosableFileFilter(imagen.getFileFilter());
        imagen.setFileFilter(new FileNameExtensionFilter("Imagenes","jpg","jpeg"));
        imagen.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selImagen.setFont(Fonts.botones);
        selImagen.setBounds(200, 357, 100, 25);
        selImagen.addActionListener(this);
        add(selImagen);

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

        bRecoger.setFont(Fonts.botones);
        bRecoger.setBounds(540, 365, 250, 40);
        bRecoger.addActionListener(this);
        add(bRecoger);
        porRecoger.setFont(Fonts.textField);
        porRecoger.setBounds(610, 325, 100, 25);
        add(porRecoger);
        
        bExpress.setFont(Fonts.botones);
        bExpress.setBounds(900, 365, 170, 40);
        bExpress.addActionListener(this);
        add(bExpress);
        porExpress.setFont(Fonts.textField);
        porExpress.setBounds(940, 325, 100, 25);
        add(porExpress);


        volver.setFont(Fonts.botones);
        volver.setBounds(1160, 370, 100, 40);
        volver.addActionListener(this);
        add(volver);
    }
    
    /***
     * Metodo con los action listeners de los botones
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            GestorVentanas.volverAtras();
        }else if(e.getSource() == eliminar){
            eliminarProducto();
            cargarTabla();
        }else if(e.getSource() == modificar){
            modificarProducto();
            cargarTabla();
        }else if(e.getSource() == agregar){
            agregarProducto();
        }else if(e.getSource() == selImagen) {
            imagen.setSelectedFile(null);
            imagen.showOpenDialog(this);
        }else if(e.getSource() == bExpress) {
            cambiarPorExpress();
        }else if(e.getSource() == bRecoger) {
            cambiarPorRecoger();
        }
        
    }
    /***
     * Metodo para cambiar el monto extra por expres
     */
    public void cambiarPorExpress() {
        int numero = verifNumPos(porExpress.getText());
        if(numero == -1) {
            JOptionPane.showMessageDialog(this, "Introduzca un numero igual o mayor a 0","ERROR", 
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        Cliente.enviarPeticion(new Peticion(TPeticion.MODEXPRESS, numero));
        JOptionPane.showMessageDialog(this, "Se cambio el monto extra por express","ENHORABUENA", 
        JOptionPane.INFORMATION_MESSAGE);
        porExpress.setText("");
    }
    /***
     * Metodo para cambiar el porcentajo por recoger
     */
    public void cambiarPorRecoger() {
        double numero;
        try {
            numero = Double.parseDouble(porRecoger.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introduzca numeros","ERROR", 
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(numero > 100 || numero < 0) {
            JOptionPane.showMessageDialog(this, "El porcentaje va de 0 a 100","ERROR", 
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        numero /= 100;
        Cliente.enviarPeticion(new Peticion(TPeticion.MODRECOGER, numero));
        JOptionPane.showMessageDialog(this, "Se cambio el porcentaje extra por empaque","ENHORABUENA", 
        JOptionPane.INFORMATION_MESSAGE);
        porRecoger.setText("");
    }
    /***
     * Metodo que elimina un producto de la lista de productos
     */
    public void eliminarProducto() {
        if(!verifCodigo()) {
            JOptionPane.showMessageDialog(this, "El codigo esta en mal formato","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Peticion retorno = Cliente.enviarPeticion(new Peticion(TPeticion.ELIMINAR_PROD, codigo.getText()));
        if((Boolean)retorno.getDatos()) {
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente","ENHORABUENA",
            JOptionPane.INFORMATION_MESSAGE);
            vaciarEspacios();
        } else {
            JOptionPane.showMessageDialog(this, "Producto no eliminado, verifique el codigo introducido",
            "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    /***
     * Metodo que modifica un producto 
     */
    public void modificarProducto() {
        if(!verifCodigo()) {
            JOptionPane.showMessageDialog(this, "El codigo esta en mal formato","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int tamanno = -1;
        int cantidad = -1;
        int calorias = -1;
        int precio = -1;

        if(!this.tamanno.getText().equals("")){
            tamanno = verifNumPos(this.tamanno.getText());
            if(tamanno == -1) {
                JOptionPane.showMessageDialog(this, "Digite numeros en el tamaño","ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if(!this.cantidad.getText().equals("")){
            cantidad = verifNumPos(this.cantidad.getText());
            if(cantidad == -1) {
                JOptionPane.showMessageDialog(this, "Digite numeros en la cantidad","ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
        if(!this.calorias.getText().equals("")){
            calorias = verifNumPos(this.calorias.getText());
            if(calorias == -1) {
                JOptionPane.showMessageDialog(this, "Digite numeros en las calorias","ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if(!this.precio.getText().equals("")){
            precio = verifNumPos(this.precio.getText());
            if(precio == -1) {
                JOptionPane.showMessageDialog(this, "Digite numeros en el precio","ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        File imagen = this.imagen.getSelectedFile();
        if(imagen != null){
            File newImagen = new File("./src/cliente/imagenes/"+codigo.getText()+".jpg");
            try {
                FileUtils.copyFile(imagen, newImagen);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "No se pudo guardar la imagen","ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Object array[] = {codigo.getText(),TModificacion.IMAGEN,newImagen};
            Cliente.enviarPeticion(new Peticion(TPeticion.MODIFICAR_PROD,array));
        }
        if(!this.tamanno.getText().equals("")){
            Object array[] = {codigo.getText(),TModificacion.SIZE,tamanno};
            Cliente.enviarPeticion(new Peticion(TPeticion.MODIFICAR_PROD,array));
        }
        if(!this.cantidad.getText().equals("")){
            Object array[] = {codigo.getText(),TModificacion.CANTIDAD,cantidad};
            Cliente.enviarPeticion(new Peticion(TPeticion.MODIFICAR_PROD,array));
        }
        if(!this.calorias.getText().equals("")){
            Object array[] = {codigo.getText(),TModificacion.CALORIAS,calorias};
            Cliente.enviarPeticion(new Peticion(TPeticion.MODIFICAR_PROD,array));
        }
        if(!this.precio.getText().equals("")){
            Object array[] = {codigo.getText(),TModificacion.PRECIO,precio};
            Cliente.enviarPeticion(new Peticion(TPeticion.MODIFICAR_PROD,array));
        }
        if(!nombre.getText().equals("")) {
            Object array[] = {codigo.getText(),TModificacion.NOMBRE,nombre.getText()};
            Cliente.enviarPeticion(new Peticion(TPeticion.MODIFICAR_PROD,array));
        }
        if(!descripcion.getText().equals("")) {
            Object array[] = {codigo.getText(),TModificacion.DESC,descripcion.getText()};
            Cliente.enviarPeticion(new Peticion(TPeticion.MODIFICAR_PROD,array));
        }
        JOptionPane.showMessageDialog(this, "Producto modificado","ENHORABUENA", JOptionPane.INFORMATION_MESSAGE);
        vaciarEspacios();
    }
    
    /***
     * Metodo que agrega un producto
     */
    public void agregarProducto() {
        if(!verifCodigo()) {
            JOptionPane.showMessageDialog(this, "El codigo esta en mal formato","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int tamanno = verifNumPos(this.tamanno.getText());
        int cantidad = verifNumPos(this.cantidad.getText());
        int calorias = verifNumPos(this.calorias.getText());
        int precio = verifNumPos(this.precio.getText());
        File imagen = this.imagen.getSelectedFile();
        if(tamanno == -1) {
            JOptionPane.showMessageDialog(this, "Digite numeros en el tamaño","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(cantidad == -1) {
            JOptionPane.showMessageDialog(this, "Digite numeros en la cantidad","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(calorias == -1) {
            JOptionPane.showMessageDialog(this, "Digite numeros en las calorias","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(precio == -1) {
            JOptionPane.showMessageDialog(this, "Digite numeros en el precio","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else if(imagen == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una imagen","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File newImagen = new File("./src/cliente/imagenes/"+codigo.getText()+".jpg");
        try {
            FileUtils.copyFile(imagen, newImagen);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar la imagen","ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Producto producto = new ProductoBuilder()
                                .codigo(codigo.getText())
                                .nombre(nombre.getText())
                                .descripcion(descripcion.getText())
                                .size(tamanno)
                                .cantidad(cantidad)
                                .calorias(calorias)
                                .precio(precio)
                                .imagen(newImagen)
                                .buildProducto();
        
        Peticion peticion = new Peticion(TPeticion.AGREGAR_PROD, producto);                        
        peticion = Cliente.enviarPeticion(peticion);
        if((Boolean)peticion.getDatos()) {
            JOptionPane.showMessageDialog(this, "Producto agregado","ENHORABUENA", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
            vaciarEspacios();
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Producto no agregado, el codigo ya existe","ERROR", 
            JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    /***
     * Metodo que cavalida que no existan espacios en blanco
     */
    private void vaciarEspacios() {
        codigo.setText("");
        nombre.setText("");
        descripcion.setText("");
        tamanno.setText("");
        cantidad.setText("");
        calorias.setText("");
        precio.setText("");
        imagen.setSelectedFile(null);
    }
    /***
     * Metodo que valida el codigo
     * @return boolean
     */
    private boolean verifCodigo() {
        String codigo = this.codigo.getText();
        if(codigo.length() != 7)
            return false;
        String tipo = codigo.substring(0, 3);
        if(!tipo.equals("ENT") && !tipo.equals("PRN") && !tipo.equals("PTR") && !tipo.equals("BEB")) {
            return false;
        }
        if(codigo.charAt(3) != '-')
            return false;
        for(int i = 4; i < 7; i++) {
            if(codigo.charAt(i) < '0' || codigo.charAt(i) > '9')
                return false;
        }
        return true;
    }   
    /***
     * Metodo que valida los numeros
     * @param numero
     * @return int
     */
    private int verifNumPos(String numero) {
        try {
            int temp = Integer.parseInt(numero);
            if(temp > 0)
                return temp;
            else
                return -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
