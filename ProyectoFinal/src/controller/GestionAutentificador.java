package controller;

import model.Cliente;
import model.Staff;
import repository.StaffRepository;
import service.ClienteService;
import java.util.List;

public class GestionAutentificador {

    private final ClienteService clienteService;
    private final StaffRepository staffRepo;

    public GestionAutentificador() {
        clienteService = new ClienteService();
        staffRepo = new StaffRepository();
    }

    public Cliente autenticarCliente(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            return null;
        }
        return clienteService.buscarPorMatricula(matricula.trim());
    }

    public boolean autenticarStaff(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        String buscado = id.trim();
        List<Staff> staff = staffRepo.cargarStaff();
        for (Staff s : staff) {
            if (s.getId() != null && s.getId().trim().equalsIgnoreCase(buscado)) {
                return true;
            }
        }
        return false;
    }
}