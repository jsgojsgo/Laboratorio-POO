package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Acceso implements Serializable {
    private String matricula;
    private LocalDateTime fechaHoraEntrada;
    
    public Acceso() {
    }
    
    public Acceso(String matricula) {
        this.matricula = matricula;
        this.fechaHoraEntrada = LocalDateTime.now();
    }
    
    public Acceso(String matricula, LocalDateTime fechaHoraEntrada) {
        this.matricula = matricula;
        this.fechaHoraEntrada = fechaHoraEntrada;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }
    
    public void setFechaHoraEntrada(LocalDateTime fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }
}