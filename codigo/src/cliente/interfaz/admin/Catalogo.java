package cliente.interfaz.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cliente.interfaz.GestorVentanas;
import cliente.interfaz.fonts.Fonts;

public class Catalogo extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("Catalogo");
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
        
    }
    
}
