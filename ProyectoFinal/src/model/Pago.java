package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Pago implements Serializable {

    private String titular, numeroTarjeta, fechaExpiracion, cvv;
    private LocalDate fechaRegistro;

    public Pago(String titular, String numeroTarjeta, String fechaExpiracion, String cvv) {
        this.titular = titular;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.fechaRegistro = LocalDate.now();
    }

    public String getTitular() { return titular; }
}