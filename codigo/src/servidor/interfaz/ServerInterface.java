package servidor.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cliente.interfaz.fonts.Fonts;
import servidor.Servidor;

public class ServerInterface implements ActionListener{
    private static Servidor server = null;
    private JLabel lDebug = new JLabel("DEBUG:");
    private static JTextArea debug = new JTextArea();
    private JScrollPane debugScroll = new JScrollPane(debug);
    private JButton iniciar = new JButton("Iniciar");
    private JButton parar = new JButton("Parar");
    private JFrame frame = new JFrame();

    public ServerInterface() {
        frame.setTitle("Proyecto 2 - Programación Orientada a Objetos - Servidor");
        frame.setSize(640, 435);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        lDebug.setFont(Fonts.labels);
        lDebug.setBounds(10,10,100,25);
        frame.add(lDebug);

        debug.setFont(Fonts.textField);
        debug.setEditable(false);
        debug.setLineWrap(true);
        debug.setWrapStyleWord(true);
        debugScroll.setBounds(10, 35, 605, 300);
        frame.add(debugScroll);

        iniciar.setFont(Fonts.botones);
        iniciar.setBounds(10,345,100,40);
        iniciar.addActionListener(this);
        frame.add(iniciar);

        parar.setFont(Fonts.botones);
        parar.setBounds(515,345,100,40);
        parar.addActionListener(this);
        frame.add(parar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == iniciar) {
            if(server == null) {
                server = new Servidor();
                debug.setText("Servidor Iniciado.");
            } else {
                debug.setText("Ya hay un servidor actual corriendo.");
            }
        } else if(e.getSource() == parar) {
            if(server != null) {
                server.apagar();
                server = null;
                debug.setText("Servidor apagado.");
            } else {
                debug.setText("No hay un servidor corriendo.");
            }
        }
        
    }

    public static void mensajeDebug(String mensaje) {
        debug.setText(mensaje);
        server = null;
    }
}
