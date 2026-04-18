package service;

import model.Cliente;
import repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService() {
        repo = new ClienteRepository();
    }

    public List<Cliente> obtenerClientes() {
        return repo.cargarClientes();
    }

    public Cliente buscarPorMatricula(String matricula) {
        return repo.buscarPorMatricula(matricula);
    }

    public boolean agregarCliente(Cliente cliente) {
        validar(cliente);
        return repo.agregarCliente(cliente);
    }

    public boolean actualizarCliente(Cliente original, Cliente nuevo) {
        validar(nuevo);
        return repo.actualizarCliente(original, nuevo);
    }

    public boolean eliminarCliente(Cliente cliente) {
        return repo.eliminarCliente(cliente);
    }

    private void validar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (cliente.getMatricula() == null || cliente.getMatricula().trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula es obligatoria.");
        }
        if (!cliente.getMatricula().trim().matches("\\d+")) {
            throw new IllegalArgumentException("La matrícula debe contener solo números enteros.");
        }
    }
}