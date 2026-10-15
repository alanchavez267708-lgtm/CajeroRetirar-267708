
package Modelo;


public class Cliente {
    private String nombre;
    private String numeroCuenta; 
    private double saldoActual;
    private double comisionFija;

    public Cliente(String nombre, String numeroCuenta, double saldoActual, double comisionFija) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.saldoActual = saldoActual;
        this.comisionFija = comisionFija;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getUltimosCuatroDigitos() {
        if (numeroCuenta != null && numeroCuenta.length() >= 4) {
            return numeroCuenta.substring(numeroCuenta.length() - 4); 
        }
        return "****";
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public double getComisionFija() {
        return comisionFija;
    }

    public boolean realizarRetiro(double monto) {
        double totalADescontar = monto + comisionFija;
        if (saldoActual >= totalADescontar) {
            saldoActual -= totalADescontar;
            return true;
        }
        return false;
    }

    public double calcularSaldoFinalEstimado(double monto) {
        return saldoActual - monto - comisionFija;
    }
}
