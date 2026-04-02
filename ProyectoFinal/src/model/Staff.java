package model;

import java.io.Serializable;

public class Staff implements Serializable {

    private String nombre, id;

    public Staff(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setId(String id) { this.id = id; }
}