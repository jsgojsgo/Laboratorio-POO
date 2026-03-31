package practica10;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Practica10 {
    public static volatile boolean ejecutando = true;

    public static void main(String[] args) {

        QueueClientes cola = new QueueClientes();
        Cajero c1 = new Cajero(1, cola);
        Cajero c2 = new Cajero(2, cola);
        Cajero c3 = new Cajero(3, cola);
        c1.setDaemon(true);
        c2.setDaemon(true);
        c3.setDaemon(true);
        c1.start();
        c2.start();
        c3.start();
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Cliente(cola));
        executor.execute(new Cliente(cola));
        
        Thread stopThread = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nPresiona ENTER para detener la simulación...\n");
            sc.nextLine();
            Practica10.ejecutando = false;
            synchronized (cola) {
                cola.notifyAll();
            }
            executor.shutdownNow();//detenerpool
            try {
                if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                    System.out.println("Algunos productores no terminaron correctamente.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Simulación detenida.");
        });

        stopThread.setDaemon(true);
        stopThread.start();
        while (ejecutando) {
            try {
                Thread.sleep(200); //pausa solo para no saturar CPU
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Programa finalizado completamente.");
    }
}