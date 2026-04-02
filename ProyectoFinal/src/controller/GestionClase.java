package controller;

import model.Clase;
import model.Cliente;
import service.ClaseService;

public class GestionClase {
    private ClaseService service;
    public GestionClase() {
        service = new ClaseService();
    }
    public boolean inscribir(Cliente cliente, Clase clase) {
        return service.inscribirCliente(cliente, clase);
    }
    public boolean cancelar(Cliente cliente, Clase clase) {
        return service.cancelarAsistencia(cliente, clase);
    }
}