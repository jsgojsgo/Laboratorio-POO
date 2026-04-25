package controller;

import model.Clase;
import service.ClaseService;

import java.util.List;

public class GestionClase {

    private final ClaseService service;

    public GestionClase() {
        service = new ClaseService();
    }

    public List<Clase> obtenerClases() {
        return service.obtenerClases();
    }

    public List<Clase> obtenerClasesBase() {
        return service.obtenerClasesBase();
    }

    public boolean crearClase(Clase clase) {
        return service.agregarClase(clase);
    }

    public boolean modificarClase(Clase original, Clase nueva) {
        return service.modificarClase(original, nueva);
    }

    public boolean eliminarClase(Clase clase) {
        return service.eliminarClase(clase);
    }
}