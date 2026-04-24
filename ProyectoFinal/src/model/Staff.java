package model;

import java.io.Serializable;
import java.util.Objects;

public class Staff implements Serializable {

    private String nombre, rol, id;

    public Staff() {
    }

    public Staff(String nombre, String rol, String id) {
        this.nombre = nombre;
        this.rol = rol;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Staff)) return false;
        Staff other = (Staff) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}