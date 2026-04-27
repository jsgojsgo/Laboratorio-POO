package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable {
    private String nombre, matricula, diaRenovacion;
    private Membresia membresia;
    private boolean renovacionAutomatica, frecuente;
    private LocalDate fechaRegistro, ultimoAcceso;
    private List<Pago> pagos;
    private List<Clase> clasesInscritas;

    public Cliente() {
        this.pagos = new ArrayList<Pago>();
        this.clasesInscritas = new ArrayList<Clase>();
    }

    public Cliente(String nombre, String matricula) {
        this();
        this.nombre = nombre;
        this.matricula = matricula;
        this.fechaRegistro = LocalDate.now();
        this.frecuente = false;
    }

    public void agregarPago(Pago pago) {
        if (pago != null) {
            pagos.add(pago);
        }
    }

    public void inscribirClase(Clase clase) {
        if (clase != null) {
            clasesInscritas.add(clase);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public boolean isRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    public void setRenovacionAutomatica(boolean renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    public String getDiaRenovacion() {
        return diaRenovacion;
    }

    public void setDiaRenovacion(String diaRenovacion) {
        this.diaRenovacion = diaRenovacion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDate getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(LocalDate ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public boolean isFrecuente() {
        return frecuente;
    }

    public void setFrecuente(boolean frecuente) {
        this.frecuente = frecuente;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<Clase> getClasesInscritas() {
        return clasesInscritas;
    }

    public void setClasesInscritas(List<Clase> clasesInscritas) {
        this.clasesInscritas = clasesInscritas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(matricula, other.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}