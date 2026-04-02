package model;

import java.io.Serializable;

public class Staff implements Serializable {

    private String nombre, rol, id;

    public Staff(String nombre, String rol, String id) {
        this.nombre = nombre;
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
    public String getId() { return id; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setRol(String rol) { this.rol = rol; }
    public void setId(String id) { this.id = id; }
}