package service;

import model.Acceso;
import model.Cliente;
import repository.HistorialRepository;

import java.time.LocalDate;
import java.util.List;

public class AccesoService {

    private final HistorialRepository historialRepo;
    private final ClienteService clienteService;

    public AccesoService() {
        historialRepo = new HistorialRepository();
        clienteService = new ClienteService();
    }

    public boolean permitirAcceso(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        if (cliente.getMembresia() == null) {
            return false;
        }
        if (cliente.getMembresia().estaVencida()) {
            return false;
        }

        registrarEntrada(cliente);
        return true;
    }

    public void registrarEntrada(Cliente cliente) {
        Acceso acceso = new Acceso(cliente.getMatricula());
        historialRepo.registrarAcceso(acceso);

        cliente.setUltimoAcceso(LocalDate.now());
        clienteService.actualizarCliente(cliente, cliente);
    }

    public List<Acceso> obtenerHistorial() {
        return historialRepo.cargarHistorial();
    }

    public void generarReporteEntradas() {
        historialRepo.generarReporteEntradas("ReporteEntradas.txt");
    }
}