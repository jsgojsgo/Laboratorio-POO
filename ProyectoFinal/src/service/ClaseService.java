package service;

import model.Clase;
import repository.ClaseRepository;

import java.util.List;

public class ClaseService {

    private final ClaseRepository repository;

    public ClaseService() {
        repository = new ClaseRepository();
    }

    public List<Clase> obtenerClases() {
        return repository.cargarClases();
    }

    public List<Clase> obtenerClasesBase() {
        return repository.cargarClasesBase();
    }

    public boolean agregarClase(Clase clase) {
        validar(clase);
        return repository.agregarClase(clase);
    }

    public boolean modificarClase(Clase original, Clase nueva) {
        validar(nueva);
        return repository.modificarClase(original, nueva);
    }

    public boolean eliminarClase(Clase clase) {
        return repository.eliminarClase(clase);
    }

    private void validar(Clase clase) {
        if (clase == null) {
            throw new IllegalArgumentException("La clase no puede ser nula.");
        }
        if (clase.getNombre() == null || clase.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (clase.getRol() == null || clase.getRol().trim().isEmpty()) {
            throw new IllegalArgumentException("El rol/coach es obligatorio.");
        }
        if (clase.getFecha() == null) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }
        if (clase.getHoraInicio() == null || clase.getHoraInicio().trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de inicio es obligatoria.");
        }
        if (clase.getHoraFin() == null || clase.getHoraFin().trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de fin es obligatoria.");
        }
    }
}