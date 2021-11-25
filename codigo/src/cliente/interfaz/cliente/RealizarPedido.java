/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.interfaz.cliente;

import cliente.Cliente;
import cliente.interfaz.GestorVentanas;
import controladores.TPeticion;
import datos.KVPair;
import datos.Pedido;
import datos.PedidoBuilder;
import datos.Peticion;
import datos.Producto;
import datos.TRecoger;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.imgscalr.Scalr;

/**
 *
 * @author DMV
 */
@SuppressWarnings("unchecked")
public class RealizarPedido extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Solicitar Pedido");
    
    //productos
    private JLabel listaProductosLabel = new JLabel("Lista de Productos");
    private JComboBox<Producto> productos = new JComboBox<Producto>();
   
    private JLabel cantidadLabel = new JLabel("Cantidad:");
    private JTextField cantidad = new JTextField();
    
    private JLabel productosSeleccionadosLabel = new JLabel("Productos Seleccionados");
    private JList<String> productosSeleccionados = new JList<String>();
    private JScrollPane pane = new JScrollPane(productosSeleccionados);
    
    private JLabel caloriasLabel = new JLabel("Calorias:");

    private JLabel imagen = new JLabel();
    
    //modalidad
    private JLabel modalidadLabel = new JLabel("Modo de Solicitud");
    private JButton express = new JButton("Express");
    private JButton local = new JButton("En Local");
    private JButton recoger = new JButton("Recoger");
    
    private JComboBox<String> modalidad = new JComboBox<String>();
    
    //datos
    private JLabel nombreLabel = new JLabel("Nombre:");
    private JTextField nombreDato = new JTextField();
    
    private JLabel celularLabel = new JLabel("Celular:");
    private JTextField celularDato = new JTextField();
    
    private JLabel direccionLabel = new JLabel("Dirección:");
    private JTextArea direccionDato = new JTextArea();
    
    //precio
    private JLabel precioLabel = new JLabel("Precio:");
    
    //botones
    private JButton mImagen = new JButton("Imagen");
    private JButton agregar = new JButton("Agregar");  
    private JButton eliminar = new JButton("Eliminar");
    private JButton pedir = new JButton("Pedir");   
    private JButton volver = new JButton("Cancelar");
    
    private LinkedList<Producto> productosLista = new LinkedList<Producto>();
    private ArrayList<KVPair<Producto, Integer>> nuevos = new ArrayList<KVPair<Producto, Integer>>();
    private int precioTotal = 0;
    private int caloriasTotal = 0;
    private Peticion pedirLista(){
        return Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_LISTA_PROD,""));
    }
    /***
     * Metodo que carga la lista de productos selecionados
    */
    public void cargarLista(){
        productos.removeAllItems();
        Peticion peticion = pedirLista();
        productosLista = (LinkedList<Producto>)peticion.getDatos();
        for(int  i = 0; i<productosLista.size(); i++){
            Producto actual = productosLista.get(i);
            productos.addItem(actual);
        }
    }
    /***
     * Metodo que verifica si un producto esta dentro de los elegidos o no y 
     * retorna su posicion o un -1
     * @param nombre
     * @return int
     */
    private int dentroNuevos(String nombre){
        Producto temp;
        for(int i =0; i<nuevos.size();i++){
            temp = nuevos.get(i).getKey();
            if(temp.getCodigo().equals(nombre)){
                return i;
            }
        }
        return -1;
    }
    /***
     * Metodo que agrega un producto a la lista de productos y actualiza las
     * diferente tablas del menu
     */
    private void agregarProduto(){
        Producto actual = (Producto)productos.getSelectedItem();
        int numero = Integer.parseInt(cantidad.getText());
        if(dentroNuevos(actual.getCodigo())!=-1){
            int pos = dentroNuevos(actual.getCodigo());
            nuevos.get(pos).setValue(numero+nuevos.get(pos).getValue());
        }else{
            KVPair<Producto, Integer> temp = new KVPair<Producto, Integer>(actual,numero);
            nuevos.add(temp);
        }
        agregarSelecionados();
        setCantidadesMas(actual,numero);
    }
    /***
     * Metodo que elimina un producto de la lista de selecionado y actualiza
     * las diferentes tablas del menu
     */
    private void eliminarProducto(){
        Producto actual = (Producto)productos.getSelectedItem();
        int numero = Integer.parseInt(cantidad.getText());
        if(dentroNuevos(actual.getCodigo())!=-1){
            int pos = dentroNuevos(actual.getCodigo());
            if(numero>nuevos.get(pos).getValue()){
                JOptionPane.showMessageDialog(this, "La cantidad a eliminar es mayor","Error",
                JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(numero==nuevos.get(pos).getValue()){
                nuevos.remove(pos);
            }else{
                nuevos.get(pos).setValue(nuevos.get(pos).getValue()-numero);
            }
        }else{
            JOptionPane.showMessageDialog(this, "El producto no esta en la lista","Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        agregarSelecionados();
        setCantidadesMenos(actual,numero);
    }
    
    /***
     * Metodo que carga la tabla de los productos  selecionados
     */
    private void agregarSelecionados(){
        String[] lista  = new String[nuevos.size()];
        for(int  i = 0; i<nuevos.size(); i++){
            KVPair<Producto, Integer> actual = nuevos.get(i);
            lista[i] = actual.getKey().getNombre()+"-"+actual.getValue();
        }
        productosSeleccionados.setListData(lista);
    }
    /***'
     * Metodo que agrega el precio y las calorias a sus respectivos label
     * @param actual
     * @param num 
     */
    private void setCantidadesMas(Producto actual,int num){
        caloriasTotal += (actual.getPorcion().getCalorias())*num;
        precioTotal += (actual.getPrecio())*num;
        precioLabel.setText("Precio: "+precioTotal);
        caloriasLabel.setText("Calorias: "+caloriasTotal);
    }
    /***
     * Metodo que elimina el precio y las calorias de su respectivo label
     * @param actual
     * @param num 
     */
    private void setCantidadesMenos(Producto actual,int num){
        caloriasTotal -= (actual.getPorcion().getCalorias())*num;
        precioTotal -= (actual.getPrecio())*num;
        precioLabel.setText("Precio: "+precioTotal);
        caloriasLabel.setText("Calorias: "+caloriasTotal);
    }
    /***
     * Metodo que valida todos los  datos y segun esto contruye el pedido y lo
     * agrega a la lista de los diferentes pedidos
     */
    private void pedirPedido(){
        if(nuevos.size()==0){
            JOptionPane.showMessageDialog(this, "Debe de elegir un producto","Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = String.valueOf(nombreDato.getText());
        String celular = String.valueOf(celularDato.getText());
        String direccion = String.valueOf(direccionDato.getText());
        String recogida = String.valueOf(modalidad.getSelectedItem());
        Pedido nuevo = new Pedido();
        if(recogida.equals("En Local")&&nombre.isBlank()){
            JOptionPane.showMessageDialog(this, "Debe de agregar un nombre","Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }else if(recogida.equals("Recoger")&&(nombre.isBlank()||celular.isBlank())){
            JOptionPane.showMessageDialog(this, "Debe de agregar un nombre y el celular","Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }else if(recogida.equals("Express")&&(nombre.isBlank()||celular.isBlank()||direccion.isBlank())){
            JOptionPane.showMessageDialog(this, "Debe de agregar un nombre, el celular y la direccion","Error",
            JOptionPane.ERROR_MESSAGE);
            return;
        }
        Peticion peticion = Cliente.enviarPeticion(new Peticion(TPeticion.CANTIDAD_PEDIDOS, ""));
        int codigo = (int)peticion.getDatos();
        PedidoBuilder temp = new PedidoBuilder()
                        .calorias(caloriasTotal)
                        .codigo(""+codigo)
                        .precio(precioTotal)
                        .nombre(nombre);
        TRecoger tipo = TRecoger.SITIO;
        int celularInt = 0;
        if(!celular.isBlank()){
            celularInt = Integer.parseInt(celularDato.getText());
        }
        if(recogida.equals("Recoger")){
            tipo = TRecoger.RECOGER;
            peticion = Cliente.enviarPeticion(new Peticion(TPeticion.RECOGER, ""));
            double porRecoger = (Double)peticion.getDatos();
            temp.celular(celularInt)
                .precio(((double)precioTotal+((double)precioTotal*porRecoger)));
        }else if(recogida.equals("Express")){
            tipo = TRecoger.EXPRESS;
            peticion = Cliente.enviarPeticion(new Peticion(TPeticion.RECOGER, ""));
            double porRecoger = (Double)peticion.getDatos();
            peticion = Cliente.enviarPeticion(new Peticion(TPeticion.EXPRESS, ""));
            double porExpress = (Double)peticion.getDatos();
            temp.celular(celularInt).direccion(direccion)
                                    .precio(((double)precioTotal+((double)precioTotal*
                                    porRecoger)+porExpress));
        }
        nuevo = temp.recoger(tipo).buildPedido();
        nuevo.setProductos(nuevos);
        Cliente.enviarPeticion(new Peticion(TPeticion.AGREGAR_PED, nuevo));
        JOptionPane.showMessageDialog(this, "Se realizo el pedido con un costo total de: "+
                nuevo.getPrecio()+", y su coddigo es '"+codigo+"'.","Aviso",
            JOptionPane.INFORMATION_MESSAGE);
        reset();
    }
    /***
     * Metodo que reinicia todos los componentes de la ventana
     */
    public void reset(){
        nuevos.clear();
        nombreDato.setText("");
        cantidad.setText("");
        precioLabel.setText("Precio: ");
        caloriasLabel.setText("Calorias: ");
        precioTotal = 0;
        caloriasTotal = 0;
        String celular = String.valueOf(celularDato.getText());
        String direccion = String.valueOf(direccionDato.getText());
        if(!celular.isBlank()){
            celularDato.setText("");
        }
        if(!direccion.isBlank()){
            direccionDato.setText("");
        }
        agregarSelecionados();
    }
    /***
     * Constructor de la ventana con sus partes
     */
    public RealizarPedido() {
        // Titulo de la ventana
        titulo.setFont(new Font("OCR A Extended",Font.PLAIN,34));
        titulo.setBounds(200,50,590,25);
        
        //productos
        listaProductosLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        listaProductosLabel.setBounds(50,100,300,30);
        
        productos.setFont(new Font("Segoe UI",Font.PLAIN,20));
        productos.setBounds(50,150,300,30);
        
        cantidadLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        cantidadLabel.setBounds(50,180,100,30);
        
        cantidad.setFont(new Font("Segoe UI",Font.PLAIN,20));
        cantidad.setBounds(300,180,50,30);
       
        limitarCantidad(cantidad);
       
        productosSeleccionadosLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        productosSeleccionadosLabel.setBounds(50,270,300,30);
        
        productosSeleccionados.setFont(new Font("Segoe UI",Font.PLAIN,20));
        pane.setBounds(50,320,300,100);
        
        caloriasLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        caloriasLabel.setBounds(50,420,300,30);

        imagen.setBounds(50,400,300,300);
        
        
        //modalidad
        modalidadLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        modalidadLabel.setBounds(400,400,300,30);
        
        modalidad.setFont(new Font("Segoe UI",Font.PLAIN,20));
        modalidad.setBounds(400,450,300,30);
        modalidad.addItem("Express");
        modalidad.addItem("En Local");
        modalidad.addItem("Recoger");
        
        //Datos
        nombreLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        nombreLabel.setBounds(400,100,250,30);
        
        nombreDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        nombreDato.setBounds(480,100,250,30);
        
        celularLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        celularLabel.setBounds(400,150,250,30);
        
        celularDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        celularDato.setBounds(480,150,100,30);
        limitarNumero(celularDato);
        
        direccionLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        direccionLabel.setBounds(400,200,300,30);
        
        direccionDato.setFont(new Font("Segoe UI",Font.PLAIN,20));
        direccionDato.setBounds(400,250,300,100);
        direccionDato.setWrapStyleWord(true);
        direccionDato.setLineWrap(true);
        
        //precio
        precioLabel.setFont(new Font("Segoe UI",Font.PLAIN,20));
        precioLabel.setBounds(400,500,300,30);
        
        //botones
        express.setFont(new Font("Segoe UI",Font.PLAIN,15));
        express.setBounds(350, 400, 100, 25);
        express.addActionListener(this);
        
        local.setFont(new Font("Segoe UI",Font.PLAIN,15));
        local.setBounds(350, 400, 100, 25);
        local.addActionListener(this);
        
        recoger.setFont(new Font("Segoe UI",Font.PLAIN,15));
        recoger.setBounds(350, 400, 100, 25);
        recoger.addActionListener(this);
        
        agregar.setFont(new Font("Segoe UI",Font.PLAIN,15));
        agregar.setBounds(50,220, 90, 25);
        agregar.addActionListener(this);
        
        eliminar.setFont(new Font("Segoe UI",Font.PLAIN,15));
        eliminar.setBounds(150, 220, 90, 25);
        eliminar.addActionListener(this);
        
        mImagen.setBounds(250, 220, 90, 25);
        mImagen.setFont(new Font("Segoe UI",Font.PLAIN,15));
        mImagen.addActionListener(this);

        pedir.setFont(new Font("Segoe UI",Font.PLAIN,15));
        pedir.setBounds(600, 650, 100, 25);
        pedir.addActionListener(this);
        
        volver.setFont(new Font("Segoe UI",Font.PLAIN,15));
        volver.setBounds(150, 650, 100, 25);
        volver.addActionListener(this);
        
        this.setBounds(0, 0, 800, 750);
        this.setLayout(null);
        
        // Agregado de todos los elementos de la ventana
        this.add(titulo);
        this.add(nombreLabel);
        this.add(nombreDato);
        this.add(celularLabel);
        this.add(celularDato);
        
        this.add(listaProductosLabel);
        this.add(productos);
        this.add(cantidadLabel);
        this.add(cantidad);
        this.add(productosSeleccionadosLabel);
        this.add(pane);
        cargarLista();
        
        this.add(caloriasLabel);
        this.add(modalidadLabel);
        this.add(modalidad);
        this.add(direccionLabel);
        this.add(direccionDato);
        
        this.add(imagen);
        this.add(precioLabel);
        
        //this.add(express);
        //this.add(local);
        //this.add(recoger);
        this.add(agregar);
        this.add(pedir);
        this.add(eliminar);
        this.add(mImagen);
        this.add(volver);
        
        this.setVisible(false);
    }
    /***
     * Metodo que limita la cantidad de numeros
     * @param amount 
     */
    private void limitarCantidad(JTextField amount){
        amount.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){//Hace que solo se puedan ingresar numeros
                    e.consume();
                }
                if(amount.getText().length() > 1){// solo se puedan ingresar 2 digitos
                    e.consume();
                }
            }
        });
    }
    /***
     * Metodo que evita el ingreso de letras
     * @param amount 
     */
    private void limitarNumero(JTextField amount){
        amount.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){//Hace que solo se puedan ingresar numeros
                    e.consume();
                }
                if(amount.getText().length() > 7){// solo se puedan ingresar 8 digitos
                    e.consume();
                }
            }
        });
    }
    /***
     * Metodo que muestra la imagen de un producto
     * @throws IOException 
     */
    private void mostrarImagen() throws IOException {
        Producto actual = (Producto)productos.getSelectedItem();
        File archivo = actual.getImagen();
        BufferedImage img = ImageIO.read(archivo);
        BufferedImage newImg = Scalr.resize(img,190);
        ImageIcon labelImage = new ImageIcon(newImg);
        imagen.setIcon(labelImage);
    }
    /***
     * Metodo con los action listener da cada boton
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == volver) {
            productosSeleccionados.setListData(new String[0]);
            reset();
            GestorVentanas.volverAtras();
        }
        if(e.getSource()== pedir){
            if(!Validaciones.validarNombre(nombreDato)){
                JOptionPane.showMessageDialog(this, "FAVOR INGRESAR UN NOMBRE","ERROR",
                JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                pedirPedido();
            }
        }
        if(e.getSource()== agregar){
            if(!Validaciones.validarCantidad(cantidad)){
                JOptionPane.showMessageDialog(this, "FAVOR INGRESAR UNA CANTIDAD MAYOR A 0","ERROR",
                JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                agregarProduto();
            }
        }
        if(e.getSource()== eliminar){
            eliminarProducto();
        }
        if(e.getSource() == mImagen) {
            try {
                mostrarImagen();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this,"Ocurrio el error: " + e1,"ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
