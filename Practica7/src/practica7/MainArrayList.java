package practica7;

import java.util.ArrayList;

public class MainArrayList {
    public static ArrayList<Automovil> crearAutos() {

        ArrayList<Automovil> lista = new ArrayList<>();

        lista.add(new Automovil("Toyota", "Corolla", "Rojo", "$300,000"));
        lista.add(new Automovil("Nissan", "Versa", "Azul", "$250,000"));
        lista.add(new Automovil("Ford", "Focus", "Negro", "$280,000"));
        lista.add(new Automovil("Chevrolet", "Aveo", "Blanco", "$220,000"));
        lista.add(new Automovil("Honda", "Civic", "Gris", "$320,000"));
        lista.add(new Automovil("Mazda", "3", "Rojo", "$330,000"));
        lista.add(new Automovil("Kia", "Rio", "Azul", "$260,000"));
        lista.add(new Automovil("Hyundai", "Elantra", "Negro", "$310,000"));
        lista.add(new Automovil("Volkswagen", "Jetta", "Blanco", "$350,000"));
        lista.add(new Automovil("BMW", "Serie 3", "Gris", "$600,000"));

        return lista;
    }
}