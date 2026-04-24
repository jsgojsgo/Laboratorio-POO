package service;

import model.Cliente;
import model.Pago;

public class PagoService {

    public void registrarPago(Cliente cliente, Pago pago) {
        if (cliente != null && pago != null) {
            cliente.agregarPago(pago);
        }
    }

    public boolean validarTarjeta(String numero) {
        return numero != null && numero.matches("\\d{16}");
    }

    public boolean validarNoVacio(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    public double aplicarDescuento(Cliente cliente, double monto) {
        if (cliente != null && cliente.isFrecuente()) {
            return monto * 0.85;
        }
        return monto;
    }
}