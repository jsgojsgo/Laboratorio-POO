package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Acceso implements Serializable {

    private String matricula;
    private LocalDateTime fechaHoraEntrada;

    public Acceso(String matricula) {
        this.matricula = matricula;
        this.fechaHoraEntrada = LocalDateTime.now();
    }

    public String getMatricula() { return matricula; }
    public LocalDateTime getFechaHoraEntrada() { return fechaHoraEntrada; }
}