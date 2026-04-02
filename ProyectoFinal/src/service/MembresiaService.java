package service;

import model.*;

public class MembresiaService {

    public boolean estaVencida(Membresia membresia) {
        return membresia.estaVencida();
    }

    public void cambiarMembresia(Cliente cliente, TipoMembresia nueva) {
        cliente.setMembresia(new Membresia(nueva));
    }

    public String obtenerDescripcion(TipoMembresia tipo) {
        switch (tipo) {
            case STANDARD: return "Acceso básico";
            case PRO: return "Acceso intermedio";
            case ULTRA: return "Acceso total";
            default: return "";
        }
    }
}