package controller;

import model.Cliente;
import model.Pago;
import service.PagoService;

public class GestionPago {

    private final PagoService service;

    public GestionPago() {
        service = new PagoService();
    }

    public void registrarPago(Cliente cliente, Pago pago) {
        service.registrarPago(cliente, pago);
    }

    public double calcularTotal(Cliente cliente, double monto) {
        return service.aplicarDescuento(cliente, monto);
    }

    public boolean validarTarjeta(String numero) {
        return service.validarTarjeta(numero);
    }

    public boolean validarNoVacio(String valor) {
        return service.validarNoVacio(valor);
    }
}