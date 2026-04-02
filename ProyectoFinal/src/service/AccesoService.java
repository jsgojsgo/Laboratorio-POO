package service;

import model.Acceso;
import model.Cliente;
import repository.HistorialRepository;

import java.time.LocalDate;

public class AccesoService {

    private HistorialRepository repo;

    public AccesoService() {
        repo = new HistorialRepository();
    }

    public boolean permitirAcceso(Cliente cliente) {
        if (cliente.getMembresia() == null) return false;
        if (cliente.getMembresia().estaVencida()) return false;
        registrarAcceso(cliente);
        return true;
    }

    private void registrarAcceso(Cliente cliente) {
        Acceso acceso = new Acceso(cliente.getMatricula());
        repo.guardarAcceso(acceso);
        cliente.setUltimoAcceso(LocalDate.now());
    }
}