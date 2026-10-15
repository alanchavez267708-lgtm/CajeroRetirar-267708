
package Controlador;

import Modelo.Cliente;
import Vista.VistaCajero;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorCajero {
    
    private Cliente cliente;
    private VistaCajero vista;
    private double montoSeleccionado = 0;

    public ControladorCajero(Cliente cliente, VistaCajero vista) {
        this.cliente = cliente;
        this.vista = vista;

        this.vista.getListaMontos().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && vista.getListaMontos().getSelectedValue() != null) {
                    montoSeleccionado = Double.parseDouble(vista.getListaMontos().getSelectedValue());
                    mostrarEstadoCuenta();
                }
            }
        });

        this.vista.getBtnRetirar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarRetiro();
            }
        });
    }

    private void mostrarEstadoCuenta() {
        double saldoEstimado = cliente.calcularSaldoFinalEstimado(montoSeleccionado);

        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTADO DE CUENTA ===\n\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n"); 
        sb.append("Saldo Actual: $").append(String.format("%.2f", cliente.getSaldoActual())).append("\n"); 
        sb.append("Cantidad a Retirar: $").append(String.format("%.2f", montoSeleccionado)).append("\n"); 
        sb.append("Comision por Retiro: $").append(String.format("%.2f", cliente.getComisionFija())).append("\n"); 
        sb.append("-----------------------------\n");
        sb.append("Monto Final si realiza el retiro: $").append(String.format("%.2f", saldoEstimado)).append("\n");

        vista.getAreaDerecha().setText(sb.toString()); 

        if (saldoEstimado >= 0) {
            vista.getBtnRetirar().setEnabled(true);
        } else {
            vista.getAreaDerecha().append("\n¡ERROR: Saldo insuficiente!");
            vista.getBtnRetirar().setEnabled(false);
        }
    }

    private void ejecutarRetiro() {
        boolean exito = cliente.realizarRetiro(montoSeleccionado);

        if (exito) {
            StringBuilder recibo = new StringBuilder();
            recibo.append("===================================\n");
            recibo.append("          RECIBO DE RETIRO         \n"); 
            recibo.append("===================================\n\n");
            recibo.append("Ultimos 4 digitos cuenta: **** ").append(cliente.getUltimosCuatroDigitos()).append("\n"); 
            recibo.append("Nombre del cliente: ").append(cliente.getNombre()).append("\n"); 
            recibo.append("Cantidad retirada: $").append(String.format("%.2f", montoSeleccionado)).append("\n"); 
            recibo.append("Cobro por comision: $").append(String.format("%.2f", cliente.getComisionFija())).append("\n\n");
            recibo.append("Nuevo saldo disponible: $").append(String.format("%.2f", cliente.getSaldoActual())).append("\n");
            recibo.append("===================================\n");
            recibo.append("       ¡Gracias por su retiro!     \n");

            vista.getAreaDerecha().setText(recibo.toString()); 
            vista.getBtnRetirar().setEnabled(false);
            vista.getListaMontos().clearSelection();
        }
    }
}
