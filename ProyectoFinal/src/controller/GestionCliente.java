package controller;

import model.Cliente;
import service.ClienteService;

import java.util.List;

public class GestionCliente {
    private ClienteService service;
    public GestionCliente() {
        service = new ClienteService();
    }
    public List<Cliente> obtenerTodos() {
        return service.obtenerClientes();
    }
    public Cliente buscar(String matricula) {
        return service.buscarPorMatricula(matricula);
    }
}