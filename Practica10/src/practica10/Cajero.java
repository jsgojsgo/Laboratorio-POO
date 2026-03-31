package practica10;

public class Cajero extends Thread {

    private int id;
    private QueueClientes cola;

    public Cajero(int id, QueueClientes cola) {
        this.id = id;
        this.cola = cola;
    }

    @Override
    public void run() {
        while(Practica10.ejecutando) {
            Cliente cliente = cola.tomarCliente();
            if (cliente == null) continue;
            System.out.println("[Cajero " + id + "] Cliente " + cliente.getId());
            try {
                Thread.sleep(cliente.getTiempoAtencion());
            } catch (InterruptedException e) {
                break;
            }
            System.out.println(">>> Cliente " + cliente.getId() + " terminó\n");
        }
    }
}