package practica10;

import java.util.LinkedList;
import java.util.Queue;

public class QueueClientes {

    private Queue<Cliente> cola = new LinkedList<>();
    private int capacidad = 10;

    public synchronized void agregarCliente(Cliente cliente) {
        while (cola.size() == capacidad && Practica10.ejecutando) {
            esperar();
        }

        cola.add(cliente);
        imprimirEstado();

        notifyAll();
    }

    public synchronized Cliente tomarCliente() {
        while (cola.isEmpty()&&Practica10.ejecutando) {
            esperar();
        }

        Cliente cliente = cola.poll();
        notifyAll();
        return cliente;
    }

    private void esperar() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void imprimirEstado() {
        System.out.print("\n[Cola] ");
        for(Cliente c : cola) {
            System.out.print("Cliente" + c.getId() + ", ");
        }
        System.out.println("\n");
    }
}