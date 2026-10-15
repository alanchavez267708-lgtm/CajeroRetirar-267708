

package com.mycompany.retirocajer;

import Modelo.Cliente;
import Vista.VistaCajero;
import Controlador.ControladorCajero;
import javax.swing.SwingUtilities;

public class RetiroCajer {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            Cliente cliente = new Cliente("Alan Chavez", "1234567890123456", 5000.00, 15.00); 
            
            VistaCajero vista = new VistaCajero();
           
            new ControladorCajero(cliente, vista);

            vista.setVisible(true);
        });
    }
    }

