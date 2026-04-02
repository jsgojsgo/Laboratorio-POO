package controller;

import model.Cliente;
import service.AccesoService;

public class GestionAcceso {
    private AccesoService service;
    public GestionAcceso() {
        service = new AccesoService();
    }
    public boolean permitirEntrada(Cliente cliente) {
        return service.permitirAcceso(cliente);
    }
}