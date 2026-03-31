package practica10;

public class Cajero extends Thread {

    private int id;
    private QueueClientes cola;
    private static final int NUM_CAJEROS = 3;
    private static String[] estados = new String[NUM_CAJEROS];

    public Cajero(int id, QueueClientes cola) {
        this.id = id;
        this.cola = cola;
        estados[id - 1] = "Libre";
    }

    @Override
    public void run() {
        while (Practica10.ejecutando) {
            Cliente cliente = cola.tomarCliente();
            if (cliente == null) continue;
            for (int i = cliente.getTiempoAtencion() / 1000; i > 0; i--) {
                estados[id - 1] = "Cliente " + cliente.getId() + " (" + i + "s restantes)";
                cola.imprimirEstado();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { break; }
            }
            estados[id - 1] = "Libre";
            cola.agregarEvento("Cliente " + cliente.getId() + " terminó en Cajero " + id);
            cola.imprimirEstado();
        }
    }

    public static void imprimirCajeros() {
        for (int i = 0; i < estados.length; i++) {
            System.out.println("[Cajero " + (i + 1) + "] " + estados[i]);
        }
    }
}