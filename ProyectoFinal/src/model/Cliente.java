package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Cliente implements Serializable {

    private String nombre, matricula, diaRenovacion;
    private Membresia membresia;
    private boolean renovacionAutomatica, frecuente;
    private LocalDate fechaRegistro, ultimoAcceso;

    public Cliente(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.fechaRegistro = LocalDate.now();
        this.frecuente = false;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Membresia getMembresia() { return membresia; }
    public void setMembresia(Membresia membresia) { this.membresia = membresia; }

    public boolean isRenovacionAutomatica() { return renovacionAutomatica; }
    public void setRenovacionAutomatica(boolean renovacionAutomatica) { this.renovacionAutomatica = renovacionAutomatica; }

    public String getDiaRenovacion() { return diaRenovacion; }
    public void setDiaRenovacion(String diaRenovacion) { this.diaRenovacion = diaRenovacion; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }

    public LocalDate getUltimoAcceso() { return ultimoAcceso; }
    public void setUltimoAcceso(LocalDate ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }

    public boolean isFrecuente() { return frecuente; }
    public void setFrecuente(boolean frecuente) { this.frecuente = frecuente; }
}