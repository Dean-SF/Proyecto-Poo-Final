package cliente.interfaz.admin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pedidos extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Pedidos");
    private JButton volver = new JButton("Volver");

    // Tabla
    private String [] columnas = {"Codigo","Nombre","Productos","Precio","Calorias"};
    private Object [][] vacio = {{null,null,null,null,null}};
    private JTable tabla = new JTable(vacio,columnas);
    private JScrollPane tablaScroll = new JScrollPane(tabla);

    public Pedidos() {
        setSize(800, 540);
        setVisible(false);
        setLayout(null);

        titulo.setFont(Fonts.titulos);
        titulo.setBounds(310, 30, 200, 40);
        add(titulo);

        tabla.setFont(Fonts.tabla);
        tabla.setEnabled(false);
        tablaScroll.setBounds(30, 90, 725, 360);
        add(tablaScroll);

        volver.setFont(Fonts.botones);
        volver.setBounds(680, 455, 100, 40);
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
