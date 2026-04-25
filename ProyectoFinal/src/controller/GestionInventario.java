package controller;

import model.Inventario;
import service.InventarioService;

import java.util.List;

public class GestionInventario {

    private final InventarioService service;

    public GestionInventario() {
        service = new InventarioService();
    }

    public List<Inventario> obtenerTodo() {
        return service.obtenerInventario();
    }
}