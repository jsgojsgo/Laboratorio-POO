package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Membresia implements Serializable {

    private TipoMembresia tipo;
    private LocalDate fechaInicio, fechaFin;
    private boolean activa;

    public Membresia(TipoMembresia tipo) {
        this.tipo = tipo;
        this.fechaInicio = LocalDate.now();
        this.fechaFin = calcularFechaFin(tipo);
        this.activa = true;
    }

    private LocalDate calcularFechaFin(TipoMembresia tipo) {
        switch (tipo) {
            case STANDARD: return LocalDate.now().plusMonths(12);
            case PRO: return LocalDate.now().plusMonths(1);
            case ULTRA: return LocalDate.now().plusMonths(12);
            default: return LocalDate.now();
        }
    }

    public boolean estaVencida() {
        return LocalDate.now().isAfter(fechaFin);
    }
    
    public TipoMembresia getTipo() { return tipo; }
    public void setTipo(TipoMembresia tipo) { this.tipo = tipo; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}