package service;

import model.Cliente;
import model.Membresia;
import model.TipoMembresia;

public class MembresiaService {

    private final ClienteService clienteService;

    public MembresiaService() {
        clienteService = new ClienteService();
    }

    public boolean estaVencida(Membresia m) {
        return m != null && m.estaVencida();
    }

    public void cambiarMembresia(Cliente cliente, TipoMembresia nueva) {
        if (cliente == null || nueva == null) {
            return;
        }

        cliente.setMembresia(new Membresia(nueva));
        clienteService.actualizarCliente(cliente, cliente);
    }
}