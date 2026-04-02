package model;

public enum TipoMembresia {
    STANDARD(1000, 350),
    PRO(1000, 450),
    ULTRA(1500, 550);

    private double inscripcion, mensualidad;

    TipoMembresia(double inscripcion, double mensualidad) {
        this.inscripcion = inscripcion;
        this.mensualidad = mensualidad;
    }

    public double getInscripcion() { return inscripcion; }
    public double getMensualidad() { return mensualidad; }
}