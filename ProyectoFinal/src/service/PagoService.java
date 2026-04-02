package service;

import model.Cliente;
import model.Pago;

public class PagoService {

    public void registrarPago(Cliente cliente, Pago pago) {
        cliente.agregarPago(pago);
    }

    public double aplicarDescuento(Cliente cliente, double monto) {
        if (cliente.isFrecuente()) {
            return monto * 0.85;
        }
        return monto;
    }
}