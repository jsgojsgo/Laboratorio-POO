package service;

import model.Clase;
import model.Cliente;
import repository.ClaseRepository;
import java.util.List;

public class ClaseService {
    private ClaseRepository repo = new ClaseRepository();

    public List<Clase> obtenerClases() {
        return repo.cargarClases();
    }
    
    public boolean inscribirCliente(Cliente cliente, Clase clase) {
        if (!clase.hayCupo()) return false;
        cliente.inscribirClase(clase);
        clase.reducirCupo();
        return true;
    }

    public boolean cancelarAsistencia(Cliente cliente, Clase clase) {
        if (!cliente.getClasesInscritas().contains(clase)) return false;
        cliente.getClasesInscritas().remove(clase);
        clase.aumentarCupo();
        return true;
    }
}