/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

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
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.imgscalr.Scalr;

/**
 *
 * @author DMV
 */
@SuppressWarnings("unchecked")
public class VentanaRealizarPedido extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Solicitar Pedido");
    
    //productos
    private JLabel listaProductosLabel = new JLabel("Lista de Productos");
    private JComboBox<String> productos = new JComboBox<String>();
   
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
    
    public void cargarLista(){
        productos.removeAllItems();
        Peticion peticion = pedirLista();
        productosLista = (LinkedList<Producto>)peticion.getDatos();
        for(int  i = 0; i<productosLista.size(); i++){
            Producto actual = productosLista.get(i);
            String temp = actual.getCodigo() + " " + actual.getNombre();
            productos.addItem(temp);
        }
    }
    
    private int dentroNuevos(String nombre){
        Producto temp;
        for(int i =0; i<nuevos.size();i++){
            temp = nuevos.get(i).getKey();
            if(temp.getNombre().equals(nombre)){
                return i;
            }
        }
        return -1;
    }
    
    private void agregarProduto(){
        String texto = String.valueOf(productos.getSelectedItem());
        String[] nombre = texto.split(" ");
        Peticion peticion = pedirLista();
        productosLista = (LinkedList<Producto>)peticion.getDatos();
        for(int  i = 0; i<productosLista.size(); i++){
            Producto actual = productosLista.get(i);
            //System.out.println(nombre[1]);
            //System.out.println(actual.getNombre());
            if(nombre[1].equals(actual.getNombre())){
                int numero = Integer.parseInt(cantidad.getText());
                if(dentroNuevos(nombre[1])!=-1){
                    int pos = dentroNuevos(nombre[1]);
                    nuevos.get(pos).setValue(numero+nuevos.get(pos).getValue());
                    //System.out.println(actual.getNombre()+":"+nuevos.get(pos).getValue());
                }else{
                    KVPair<Producto, Integer> temp = new KVPair<Producto, Integer>(actual,numero);
                    nuevos.add(temp);
                    //System.out.println(actual.getNombre()+":"+numero);
                }
                agregarSelecionados();
                setCantidadesMas(actual,numero);
            }
        }
        /*int num = Integer.parseInt(cantidad.getText());
        System.out.println(nombre+":"+num);
        agregarSelecionados();*/
    }
    
    private void eliminarProducto(){
        String texto = String.valueOf(productos.getSelectedItem());
        String[] nombre = texto.split(" ");
        Peticion peticion = pedirLista();
        productosLista = (LinkedList<Producto>)peticion.getDatos();
        if(nuevos.size()==0){
            JOptionPane.showMessageDialog(this, "La lista de productos selecionados esta vacia","Error",
                        JOptionPane.ERROR_MESSAGE);
                        return;
        }
        for(int  i = 0; i<productosLista.size(); i++){
            Producto actual = productosLista.get(i);
            if(nombre[1].equals(actual.getNombre())){
                int numero = Integer.parseInt(cantidad.getText());
                if(dentroNuevos(nombre[1])!=-1){
                    int pos = dentroNuevos(nombre[1]);
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
        }
    }
    
    private void agregarSelecionados(){
        String[] lista  = new String[nuevos.size()];
        for(int  i = 0; i<nuevos.size(); i++){
            KVPair<Producto, Integer> actual = nuevos.get(i);
            lista[i] = actual.getKey().getNombre()+"-"+actual.getValue();
        }
        productosSeleccionados.setListData(lista);
    }
    
    private void setCantidadesMas(Producto actual,int num){
        caloriasTotal += (actual.getPorcion().getCalorias())*num;
        precioTotal += (actual.getPrecio())*num;
        precioLabel.setText("Precio: "+precioTotal);
        caloriasLabel.setText("Calorias: "+caloriasTotal);
    }
    
    private void setCantidadesMenos(Producto actual,int num){
        caloriasTotal -= (actual.getPorcion().getCalorias())*num;
        precioTotal -= (actual.getPrecio())*num;
        precioLabel.setText("Precio: "+precioTotal);
        caloriasLabel.setText("Calorias: "+caloriasTotal);
    }
    
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
            temp.celular(celularInt)
                .precio(((double)precioTotal+((double)precioTotal*nuevo.getPorRecoger())));
        }else if(recogida.equals("Express")){
            tipo = TRecoger.EXPRESS;
            temp.celular(celularInt).direccion(direccion)
                                    .precio(((double)precioTotal+((double)precioTotal*
                                    nuevo.getPorRecoger())+nuevo.getPorExpress()));
        }
        nuevo = temp.recoger(tipo).buildPedido();
        nuevo.setProductos(nuevos);
        Cliente.enviarPeticion(new Peticion(TPeticion.AGREGAR_PED, nuevo));
        JOptionPane.showMessageDialog(this, "Se realizo el pedido con un costo total de: "+
                nuevo.getPrecio()+", y su coddigo es '"+codigo+"'.","Aviso",
            JOptionPane.INFORMATION_MESSAGE);
        reset();
    }
    
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
    
    public VentanaRealizarPedido() {
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
        productosSeleccionados.setEnabled(false);
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

    private void mostrarImagen() throws IOException {
        String codigo = (String)productos.getSelectedItem();
        codigo = codigo.substring(0, 7);
        Peticion retorno = Cliente.enviarPeticion(new Peticion(TPeticion.CONSULTAR_PROD, codigo));
        File archivo = ((Producto)retorno.getDatos()).getImagen();
        BufferedImage img = ImageIO.read(archivo);
        BufferedImage newImg = Scalr.resize(img,190);
        ImageIcon labelImage = new ImageIcon(newImg);
        imagen.setIcon(labelImage);
    }

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
        if(e.getSource()== express){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== local){
            //GestorVentanas.abrirMenuRegistro();
        }
        if(e.getSource()== recoger){
            //GestorVentanas.abrirMenuRegistro();
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
