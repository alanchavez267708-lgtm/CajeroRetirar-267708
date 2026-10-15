
package Vista;

import javax.swing.*;
import java.awt.*;


public class VistaCajero extends JFrame{
    
    private JList<String> listaMontos;
    private JTextArea areaDerecha; 
    private JButton btnRetirar;

    public VistaCajero() {
        setTitle("Cajero Automatico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        String[] montos = {"200.00", "400.00", "600.00", "800.00", "1000.00", "1200.00", "1400.00", "1600.00", "1800.00", "2000.00"}; 
        listaMontos = new JList<>(montos);
        listaMontos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaMontos);
        scrollLista.setPreferredSize(new Dimension(180, 0));

        areaDerecha = new JTextArea(); 
        areaDerecha.setEditable(false);
        areaDerecha.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollArea = new JScrollPane(areaDerecha);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollLista, scrollArea);
        splitPane.setDividerLocation(180);
        add(splitPane, BorderLayout.CENTER);

        btnRetirar = new JButton("Retirar efectivo"); 
        btnRetirar.setEnabled(false);
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnRetirar);
        add(panelBoton, BorderLayout.SOUTH);
    }

    public JList<String> getListaMontos() {
        return listaMontos;
    }

    public JTextArea getAreaDerecha() {
        return areaDerecha;
    }

    public JButton getBtnRetirar() {
        return btnRetirar;
    }
}
