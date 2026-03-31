package practica10;

import java.util.LinkedList;
import java.util.Queue;

public class QueueClientes {

    private Queue<Cliente> cola = new LinkedList<>();
    private int capacidad = 10;
    private LinkedList<String> logEventos = new LinkedList<>();

    public synchronized void agregarCliente(Cliente cliente) {
        while (cola.size() == capacidad && Practica10.ejecutando) {
            try { wait(); } catch (InterruptedException e) { break; }
        }
        if (!Practica10.ejecutando) return;

        cola.add(cliente);
        agregarEvento("Cliente " + cliente.getId() + " llegó a la cola");
        imprimirEstado();
        notifyAll();
    }

    public synchronized Cliente tomarCliente() {
        while (cola.isEmpty() && Practica10.ejecutando) {
            try { wait(); } catch (InterruptedException e) { break; }
        }
        if (!Practica10.ejecutando) return null;

        Cliente cliente = cola.poll();
        agregarEvento("Cliente " + cliente.getId() + " salió de la cola");
        imprimirEstado();
        notifyAll();
        return cliente;
    }

    public synchronized void imprimirEstado() {
        for (int i = 0; i < 15; i++) System.out.println(); //"limpiar pantalla"
        System.out.print("[Cola] ");
        for (Cliente c : cola) {
            System.out.print("Cliente" + c.getId() + ", ");
        }
        System.out.println("\n");
        Cajero.imprimirCajeros();
        System.out.println("\n--- Eventos recientes ---");
        for (String evento : logEventos) {
            System.out.println(evento);
        }
        System.out.println();
    }
    public synchronized void agregarEvento(String mensaje) {
        logEventos.add(mensaje);
        if (logEventos.size() > 8) logEventos.removeFirst();
    }
}