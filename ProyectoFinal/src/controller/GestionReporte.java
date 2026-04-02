package controller;

import model.Cliente;
import service.ReporteService;

import java.util.List;

public class GestionReporte {
    private ReporteService service;
    public GestionReporte() {
        service = new ReporteService();
    }
    public void generarClientes(List<Cliente> clientes) {
        service.generarReporteClientes(clientes);
    }
}