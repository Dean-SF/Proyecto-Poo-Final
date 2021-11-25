package servidor.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cliente.interfaz.fonts.Fonts;
import servidor.Servidor;

public class Principal extends JPanel implements ActionListener{
    private JLabel lDebug = new JLabel("DEBUG:");
    private static JTextArea debug = new JTextArea();
    private JScrollPane debugScroll = new JScrollPane(debug);
    private JButton iniciar = new JButton("Iniciar");
    private JButton parar = new JButton("Parar");
    private JButton historial = new JButton("Historial");
    

    public Principal() {
        setSize(640, 435);
        setVisible(true);
        setLayout(null);

        lDebug.setFont(Fonts.labels);
        lDebug.setBounds(10,10,100,25);
        add(lDebug);

        debug.setFont(Fonts.textField);
        debug.setEditable(false);
        debug.setLineWrap(true);
        debug.setWrapStyleWord(true);
        debugScroll.setBounds(10, 35, 605, 300);
        add(debugScroll);

        iniciar.setFont(Fonts.botones);
        iniciar.setBounds(10,345,100,40);
        iniciar.addActionListener(this);
        add(iniciar);

        historial.setFont(Fonts.botones);
        historial.setBounds(262,345,100,40);
        historial.addActionListener(this);
        add(historial);

        parar.setFont(Fonts.botones);
        parar.setBounds(515,345,100,40);
        parar.addActionListener(this);
        add(parar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == iniciar) {
            if(ServerInterface.getServer() == null) {
                ServerInterface.setServer(new Servidor());
                debug.setText("Servidor Iniciado.");
            } else {
                debug.setText("Ya hay un servidor actual corriendo.");
            }
        } else if(e.getSource() == parar) {
            if(ServerInterface.getServer() != null) {
                ServerInterface.getServer().apagar();
                ServerInterface.setServer(null);
            } else {
                debug.setText("No hay un servidor corriendo.");
            }
        } else if(e.getSource() == historial) {
            ServerInterface.abrirHistorial();
        }
    }

    public static void setDebug(String mensaje) {
        debug.setText(mensaje);
    }
}
