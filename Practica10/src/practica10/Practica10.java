package practica10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class Practica10 {

    public static volatile boolean ejecutando = true;
    
    public static void main(String[] args) {

        QueueClientes cola = new QueueClientes();

        Cajero c1 = new Cajero(1, cola);
        Cajero c2 = new Cajero(2, cola);
        Cajero c3 = new Cajero(3, cola);

        c1.start();
        c2.start();
        c3.start();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Cliente(cola));
        executor.execute(new Cliente(cola));
        
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nPresiona ENTER para detener la simulación...\n");
            sc.nextLine();
            ejecutando = false;
            executor.shutdownNow();
            System.out.println("\nSimulación detenida.");
        }).start();
    }
}