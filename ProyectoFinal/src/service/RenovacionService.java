package service;

import model.Cliente;

public class RenovacionService {

    public void activar(Cliente cliente, String dia) {
        cliente.setRenovacionAutomatica(true);
        cliente.setDiaRenovacion(dia);
    }

    public void desactivar(Cliente cliente) {
        cliente.setRenovacionAutomatica(false);
        cliente.setDiaRenovacion(null);
    }
}