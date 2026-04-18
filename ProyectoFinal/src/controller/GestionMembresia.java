package controller;

import model.Cliente;
import model.TipoMembresia;
import service.MembresiaService;

public class GestionMembresia {

    private final MembresiaService service;

    public GestionMembresia() {
        service = new MembresiaService();
    }
    
    public boolean estaVencida(Cliente cliente) {
        return cliente != null
                && cliente.getMembresia() != null
                && service.estaVencida(cliente.getMembresia());
    }

    public void cambiar(Cliente cliente, TipoMembresia tipo) {
        service.cambiarMembresia(cliente, tipo);
    }
}