package practica10;

import java.util.Random;

public class Cliente implements Runnable {

    private static int contadorGlobal = 1;
    private int id,  tiempoAtencion;
    
    public Cliente(int id, int tiempoAtencion) {
        this.id = id;
        this.tiempoAtencion = tiempoAtencion;
    }
    public int getId() {
        return id;
    }
    public int getTiempoAtencion() {
        return tiempoAtencion;
    }

    //para crear clientes
    private QueueClientes cola;
    private Random random = new Random();

    public Cliente(QueueClientes cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) {
            int tiempo = (random.nextInt(5) + 1) * 2000;
            Cliente nuevoCliente = new Cliente(contadorGlobal++, tiempo);
            cola.agregarCliente(nuevoCliente);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}