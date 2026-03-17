package practica7;

public class Automovil extends Transporte implements Avanzar, Frenar {

    private String marca, modelo, precio, color;

    public Automovil(String marca, String modelo, String color, String precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
    }
    @Override
    public void encender() {
        System.out.println("Enciende");
    }

    @Override
    public String avanzar(int gasolina) {
        return "Avanza";
    }
    @Override
    public void frenar() {
        System.out.println("Frena");
    }
    public void vuelta(String direccion) {
        System.out.println("Vuelta"+direccion);
    }
    public void mostrar() {
        System.out.println("***** Automovil "+marca+"*****\nModelo: "+modelo+"\nColor: "+color+"\nPrecio: "+precio);
    }
}