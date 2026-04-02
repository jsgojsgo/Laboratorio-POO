package controller;

import model.Cliente;
import service.RenovacionService;

public class GestionRenovacion {
    private RenovacionService service;
    public GestionRenovacion() {
        service = new RenovacionService();
    }
    public void activar(Cliente cliente, String dia) {
        service.activar(cliente, dia);
    }
    public void desactivar(Cliente cliente) {
        service.desactivar(cliente);
    }
}