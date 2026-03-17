package practica7;
import java.util.ArrayList;

public class Practica7 {

    public static void main(String[] args) {

        ArrayList<Automovil> autos = MainArrayList.crearAutos();

        for(Automovil auto : autos) {

            auto.mostrar();
            auto.encender();
            System.out.println(auto.avanzar(10));
            auto.vuelta("Izquierda");

            System.out.println();
        }
    }
}