package service;

import model.Inventario;
import repository.InventarioRepository;

import java.util.List;

public class InventarioService {

    private final InventarioRepository repository;

    public InventarioService() {
        repository = new InventarioRepository();
    }

    public List<Inventario> obtenerInventario() {
        return repository.cargarInventario();
    }
}