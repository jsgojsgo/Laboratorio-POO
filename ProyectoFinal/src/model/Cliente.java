package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {

    private String nombre, matricula, diaRenovacion;
    private Membresia membresia;
    private boolean renovacionAutomatica, frecuente;
    private LocalDate fechaRegistro, ultimoAcceso;
    private List<Pago> pagos;
    private List<Clase> clasesInscritas;

    public Cliente(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.fechaRegistro = LocalDate.now();
        this.frecuente = false;
        this.pagos = new ArrayList<>();
        this.clasesInscritas = new ArrayList<>();
    }
    
    public void agregarPago(Pago pago) {
        pagos.add(pago);
    }
    
    public void inscribirClase(Clase clase) {
        clasesInscritas.add(clase);
    }
    
    public String getNombre() { return nombre; }
    public String getMatricula() { return matricula; }
    public void setNombre(String nombre) { this.nombre = nombre; }
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

    public List<Pago> getPagos() { return pagos; }
    public List<Clase> getClasesInscritas() { return clasesInscritas; }
}