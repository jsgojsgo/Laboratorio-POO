package controller;

import model.Cliente;
import repository.StaffRepository;
import service.ClienteService;

public class GestionAutentificador {

    private ClienteService clienteService;
    private StaffRepository staffRepo = new StaffRepository();
        
    public GestionAutentificador() {
        clienteService = new ClienteService();
    }
    
    public boolean autenticarStaff(String id) {

        return staffRepo.cargarStaff()
                .stream()
                .anyMatch(s -> s.getId().equals(id));
    }
    
    public Cliente autenticarCliente(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            return null;
        }
        return clienteService.buscarPorMatricula(matricula);
    }
}