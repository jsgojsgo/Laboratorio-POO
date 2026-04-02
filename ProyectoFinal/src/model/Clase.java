package model;

import java.io.Serializable;

public class Clase implements Serializable {

    private String nombre, instructor, dia, horaInicio, horaFin;
    private int cupos;

    public Clase(String instructor, String nombre, String dia, String horaInicio, String horaFin, int cupos) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cupos = cupos;
    }

    public boolean hayCupo() {
        return cupos > 0;
    }
    public void reducirCupo() {
        if (cupos > 0) cupos--;
    }
    public void aumentarCupo() {
        cupos++;
    }
    public String getNombre() { return nombre; }
    public String getInstructor() { return instructor; }
    public String getDia() { return dia; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public int getCupos() { return cupos; }
}