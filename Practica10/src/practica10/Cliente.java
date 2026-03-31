package practica10;

import java.util.Random;

public class Cliente implements Runnable {

    private static int contadorGlobal = 1;
    private int id, tiempoAtencion;
    private QueueClientes cola;
    private Random random = new Random();

    public Cliente(QueueClientes cola) {
        this.cola = cola;
    }

    public Cliente(int id, int tiempoAtencion) {
        this.id = id;
        this.tiempoAtencion = tiempoAtencion;
    }

    public int getId() { return id; }
    public int getTiempoAtencion() { return tiempoAtencion; }

    @Override
    public void run() {
        while (Practica10.ejecutando) {
            int tiempo = (random.nextInt(5) + 1) * 1500;
            Cliente nuevo = new Cliente(contadorGlobal++, tiempo);
            cola.agregarCliente(nuevo);

            try {
                Thread.sleep(random.nextInt(1500) + 500); 
            } catch (InterruptedException e) { break; }
        }
    }
}