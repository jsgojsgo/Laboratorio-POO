package actividad4;

import java.util.ArrayList;
import java.util.Scanner;

public class Actividad4{
    static ArrayList<Television> listaTV = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Agregar otro");
            System.out.println("2. Mostrar elementos hasta ahora");
            System.out.println("3. Encender/Apagar");
            System.out.println("4. Cambiar de canal");
            System.out.println("5. Configuración");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1: agregarTelevisor(); break;
                case 2: mostrarTelevisores(); break;
                case 3: encenderApagar(); break;
                case 4: cambiarCanal(); break;
                case 5: configuracion(); break;
                case 0: break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
    public static void agregarTelevisor() {
        System.out.print("Ingrese Marca: ");
        String marca = sc.nextLine();

        System.out.print("Ingrese Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Ingrese Color: ");
        String color = sc.nextLine();

        Television tv = new Television(marca, modelo, color);
        listaTV.add(tv);

        System.out.println("Televisor agregado correctamente.");
    }
    public static void mostrarTelevisores() {
        if (listaTV.isEmpty()) {
            System.out.println("No hay televisores registrados.");
            return;
        }

        for (Television tv : listaTV) {
            tv.mostrarInformacion();
        }
    }
    public static Television buscarTelevisor() {
        System.out.print("Ingrese la marca del televisor: ");
        String marcaBuscada = sc.nextLine();
        System.out.print("Ingrese el modelo del televisor: ");
        String modeloBuscado = sc.nextLine();

        for (Television tv : listaTV) {
            if (tv.getMarca().equalsIgnoreCase(marcaBuscada) &&
                tv.getModelo().equalsIgnoreCase(modeloBuscado)) {
                return tv;
            }
        }
        System.out.println("Televisor no encontrado.");
        return null;
    }
    public static void encenderApagar() {
        Television tv = buscarTelevisor();
        if (tv != null) {
            System.out.println("1. Encender");
            System.out.println("2. Apagar");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                tv.encender();
            } else if (op == 2) {
                tv.apagar();
            }
        }
    }
    public static void cambiarCanal() {
        Television tv = buscarTelevisor();
        if (tv != null) {
            System.out.println(tv.cambioCanal());
        }
    }
    public static void configuracion() {
        Television tv = buscarTelevisor();
        if (tv != null) {
            System.out.println(tv.confMenu());
        }
    }
}