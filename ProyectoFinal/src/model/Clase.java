package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Clase implements Serializable {

    private String nombre;
    private String rol; 
    private LocalDate fecha; //puede ser null en plantillas
    private String horaInicio;
    private String horaFin;
    private int cupos;

    public Clase() {
    }

    public Clase(String nombre, String rol, LocalDate fecha, String horaInicio, String horaFin) {
        this(nombre, rol, fecha, horaInicio, horaFin, 0);
    }

    public Clase(String nombre, String rol, LocalDate fecha, String horaInicio, String horaFin, int cupos) {
        this.nombre = nombre;
        this.rol = rol;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cupos = cupos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCoach() {
        return rol;
    }

    public void setCoach(String coach) {
        this.rol = coach;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFechaTexto() {
        return fecha == null ? "" : fecha.toString();
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    @Override
    public String toString() {
        String fechaTxt = fecha == null ? "" : " - " + fecha.toString();
        return rol + " | " + nombre + fechaTxt + " | " + horaInicio + "-" + horaFin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Clase)) return false;
        Clase other = (Clase) obj;
        return cupos == other.cupos
                && Objects.equals(nombre, other.nombre)
                && Objects.equals(rol, other.rol)
                && Objects.equals(fecha, other.fecha)
                && Objects.equals(horaInicio, other.horaInicio)
                && Objects.equals(horaFin, other.horaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, rol, fecha, horaInicio, horaFin, cupos);
    }
}