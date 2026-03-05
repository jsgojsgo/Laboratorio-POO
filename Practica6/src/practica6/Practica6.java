package practica6;

import java.util.Scanner;

public class Practica6 {
    public static void main(String[] args) {
        Contacto[] contactos = new Contacto[5];
        contactos[0] = new Contacto("Juan", "555-1234", "juan@email.com");
        contactos[1] = new Contacto("Juana", "555-2345", "juana@email.com");
        contactos[2] = new Contacto("Mario", "555-3456", "mario@email.com");
        contactos[3] = new Contacto("Maria", "555-4567", "maria@email.com");
        contactos[4] = new Contacto("Luis", "555-5678", "luis@email.com");
        
        Telefono[] telefonos = new Telefono[5];
        telefonos[0] = new Telefono("Samsung", "Galaxy S23", "1200");
        telefonos[1] = new Telefono("Apple", "iPhone 14", "1500");
        telefonos[2] = new Telefono("Xiaomi", "Redmi Note 12", "500");
        telefonos[3] = new Telefono("Motorola", "Moto G Power", "400");
        telefonos[4] = new Telefono("Huawei", "P50", "800");
        
        Scanner input = new Scanner(System.in);
        int opcion = -1;
        do{
           System.out.println("\nContacto a Llamar:\n1.Juan\n2.Juana\n3.Mario\n4.Maria\n5.Luis\n0.Salir\n>> ");
           
           try {
                opcion = input.nextInt();
                
                switch (opcion) {
                    case 0: break;
                    case 1: case 2: case 3: case 4: case 5:
                      Contacto contacto = contactos[opcion - 1];
                      Telefono t = telefonos[opcion - 1];
                      System.out.println("Telefono " + t.getMarca() + " - " + t.getModelo());
                      t.encender();  
                      System.out.println(t.cambioCanal());
                      t.iniciarLlamada(contacto.getTelefono());
                      System.out.println(t.iniciarLlamada(contacto));
                      System.out.println(t.finalizarLlamada());
                      break;
                    default:
                        System.out.println("\nDato incorrecto."); break;
                }

            } catch (Exception e) {
                System.out.println("Entrada invalida. Ingresa un numero.");
                input.nextLine(); //limpiarbuffer
            }

        } while (opcion != 0);

        System.out.println("Programa terminado.");
    }
}