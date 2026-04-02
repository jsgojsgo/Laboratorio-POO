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
        this.fechaFin = calcularFin(tipo);
        this.activa = true;
    }

    private LocalDate calcularFin(TipoMembresia tipo) {
        switch (tipo) {
            case PRO: return LocalDate.now().plusMonths(1);
            default: return LocalDate.now().plusMonths(12);
        }
    }

    public boolean estaVencida() {
        return LocalDate.now().isAfter(fechaFin);
    }

    public TipoMembresia getTipo() { return tipo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}