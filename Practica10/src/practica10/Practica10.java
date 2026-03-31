package practica10;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        Runnable prod1 = new Cliente(cola);
        Runnable prod2 = new Cliente(cola);
        Thread t1 = new Thread(prod1);
        Thread t2 = new Thread(prod2);

        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();

        Thread stopThread = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.println("\nPresiona ENTER para detener la simulación...\n");
            sc.nextLine();
            Practica10.ejecutando = false;
            synchronized (cola) {
                cola.notifyAll();
            }
            System.out.println("Simulación detenida.");
        });

        stopThread.setDaemon(true);
        stopThread.start();
        try {
            while (ejecutando) {
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            //terminaprograma
        }

        System.out.println("Programa finalizado completamente.");
    }
}