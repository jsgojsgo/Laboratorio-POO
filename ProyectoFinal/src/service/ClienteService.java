package service;

import model.*;
import repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private ClienteRepository repo;
    public ClienteService() {
        repo = new ClienteRepository();
    }

    public List<Cliente> obtenerClientes() {
        return repo.cargarClientes();
    }

    public Cliente buscarPorMatricula(String matricula) {
        return obtenerClientes().stream()
                .filter(c -> c.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }

    public boolean esFrecuente(Cliente cliente) {
        return cliente.isFrecuente();
    }
}