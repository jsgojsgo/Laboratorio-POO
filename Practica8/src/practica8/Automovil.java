package practica8;

public class Automovil{
    private String marca, modelo, precio, mov;
    int gas;
    
    public Automovil(String marca, String modelo, String precio, int gas, String mov) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.mov = mov;
        encender(gas);
    }

    public void encender(int gas) {
        if (gas > 1) {
            System.out.println("Enciende con gasolina: " + gas);
        } else {
            System.out.println("No hay suficiente gasolina");
        }
    }
    public String alto() {
        return "\nEl automóvil está detenido";
    }
    public String movimiento() {
        return "\nMovimiento: " + mov;
    }
    public void mostrar() {
        System.out.println("*****Automovil " + marca + "*****\nModelo: "+modelo+"\nPrecio: "+precio+movimiento()+alto());
        System.out.println();
    }
}