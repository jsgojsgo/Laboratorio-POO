package controller;

import model.Acceso;
import model.Cliente;
import service.AccesoService;

import java.util.List;

public class GestionAcceso {

    private final AccesoService service;

    public GestionAcceso() {
        service = new AccesoService();
    }

    public boolean permitirEntrada(Cliente cliente) {
        return service.permitirAcceso(cliente);
    }

    public void registrarEntrada(Cliente cliente) {
        service.registrarEntrada(cliente);
    }

    public List<Acceso> obtenerHistorial() {
        return service.obtenerHistorial();
    }

    public void generarReporteEntradas() {
        service.generarReporteEntradas();
    }
}