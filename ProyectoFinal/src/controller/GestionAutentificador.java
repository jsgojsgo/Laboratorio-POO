package controller;

import model.Cliente;
import service.ClienteService;

public class GestionAutentificador {

    private ClienteService clienteService;
    public GestionAutentificador() {
        clienteService = new ClienteService();
    }

    public Cliente autenticarCliente(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            return null;
        }
        return clienteService.buscarPorMatricula(matricula);
    }

    public boolean autenticarStaff(String id) {
        return id != null && id.equals("admin");
    }
}