package cliente.interfaz;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cliente.interfaz.fonts.Fonts;

/**
 * Ventana del menu principal de la aplicacion
 * @author Esteban
 */
public class Menu extends JPanel implements ActionListener{
    private JLabel titulo = new JLabel("MENU PRINCIPAL");
    private JButton admin = new JButton("Administracion");
    private JButton cliente = new JButton("Clientes");
    
    /**
     * Constructor con sus partes
     */
    public Menu() {
        // Setup inicial
        setSize(480, 480);
        setVisible(true);
        setLayout(null);

        titulo.setFont(Fonts.titulos);
        titulo.setBounds(85, 30, 300, 25);
        add(titulo);

        admin.setFont(Fonts.botones);
        admin.setBounds(150, 140, 160, 40);
        admin.addActionListener(this);
        add(admin);

        cliente.setFont(Fonts.botones);
        cliente.setBounds(150, 200, 160, 40);
        cliente.addActionListener(this);
        add(cliente);

        
    }
    /**
     * Metodo con los action listeners
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == admin) {
            GestorVentanas.abrirAdminIngreso();
        } else if(e.getSource() == cliente) {
            GestorVentanas.cliente();
        }
    }
}
