package practica8;

import java.util.ArrayList;
import java.util.Scanner;

public class Practica8{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Automovil> autos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            System.out.println("\nAutomovil #" + (i + 1));
            System.out.print("\nMarca: ");
            String marca = sc.nextLine();
            System.out.print("\nModelo: "); 
            String modelo = sc.nextLine();
            System.out.print("\nPrecio: ");
            String precio = sc.nextLine();
            
            int gas = 0;
            boolean valido;
            do {
                System.out.print("\nGasolina (entero): ");
                if (sc.hasNextInt()) {
                    gas = sc.nextInt();
                    if (gas>=1) {
                        valido = true;
                    } else {
                        System.out.println("\nDato incorrecto");
                        valido = false;
                    }
                } else {
                    System.out.println("Tipo de dato incorrecto");
                    sc.next(); //limpiar buffer
                    valido = false;
                }
            } while (!valido);
            sc.nextLine();
            
           String mov;
           do{
                System.out.print("\nMovimiento\n1.Derecha\n2.Izquierda\n ");
                mov = sc.nextLine();
                if (!mov.equals("1")&&!mov.equals("2")){
                    System.out.println("Opción inválida, ingresa 1 o 2");
                }
            }while(!mov.equals("1")&&!mov.equals("2"));
            String movimientoTexto = mov.equals("1")?"Derecha":"Izquierda";
            System.out.println();
            Automovil auto = new Automovil(marca, modelo, precio, gas, movimientoTexto);
            autos.add(auto);
        }

        System.out.println("\n\n\n=== LISTA DE AUTOMOVILES ===");
        for(Automovil auto : autos) {
            auto.mostrar();
        }
    }
}