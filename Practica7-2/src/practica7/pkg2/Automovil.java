package practica7.pkg2;

import java.util.ArrayList;

public class Automovil extends Transporte implements Avanzar, Frenar, IAutomovil {

    private String marca,  modelo,  color, precio;
    
    public Automovil() {//constructor
    }
    public Automovil(String marca, String modelo, String color, String precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public String getMarca() {
        return marca;
    }
    
    @Override
    public void encender() {
        System.out.println("Enciende");
    }
    
    @Override
    public String avanzar(int gasolina) {
        return "Avanza con gasolina: " + gasolina;
    }
    @Override
    public void frenar() {
        System.out.println("Frena");
    }
    public void vuelta(String direccion) {
        System.out.println("Vuelta " + direccion);
    }
    @Override
    public void listaMetodos() {
        System.out.println("Métodos de Automovil");
    }
    public void mostrar() {
        System.out.println("*****Automovil "+marca+"*****\nModelo: "+modelo+"\nColor: "+color+"\nPrecio: " +precio);
    }
}