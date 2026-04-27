package model;

import java.io.Serializable;
import java.util.Objects;

public class Inventario implements Serializable {

    private String nombreElemento;
    private String tipo;
    private String peso; //puede ser vacío
    private int stock;

    public Inventario() {
    }

    public Inventario(String nombreElemento, String tipo, String peso, int stock) {
        this.nombreElemento = nombreElemento;
        this.tipo = tipo;
        this.peso = peso;
        this.stock = stock;
    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Inventario)) return false;
        Inventario other = (Inventario) obj;
        return Objects.equals(nombreElemento, other.nombreElemento)
                && Objects.equals(tipo, other.tipo)
                && Objects.equals(peso, other.peso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreElemento, tipo, peso);
    }
}