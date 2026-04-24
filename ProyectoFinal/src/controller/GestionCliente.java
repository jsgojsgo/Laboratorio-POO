package controller;

import model.Cliente;
import service.ClienteService;

import java.util.List;

public class GestionCliente {

    private final ClienteService service;

    public GestionCliente() {
        service = new ClienteService();
    }

    public List<Cliente> obtenerTodos() {
        return service.obtenerClientes();
    }

    public Cliente buscar(String matricula) {
        return service.buscarPorMatricula(matricula);
    }

    public boolean agregar(Cliente cliente) {
        return service.agregarCliente(cliente);
    }

    public boolean modificar(Cliente original, Cliente nuevo) {
        return service.actualizarCliente(original, nuevo);
    }

    public boolean eliminar(Cliente cliente) {
        return service.eliminarCliente(cliente);
    }
}