package service;

import model.Cliente;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SimuladorEntradasService {

    private static SimuladorEntradasService instancia;

    private final ScheduledExecutorService executor;
    private final ClienteService clienteService;
    private final AccesoService accesoService;
    private final Random random;
    private volatile boolean iniciado;

    private SimuladorEntradasService() {
        this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "SimuladorEntradas");
            t.setDaemon(true);
            return t;
        });
        this.clienteService = new ClienteService();
        this.accesoService = new AccesoService();
        this.random = new Random();
        this.iniciado = false;
    }

    public static synchronized SimuladorEntradasService getInstancia() {
        if (instancia == null) {
            instancia = new SimuladorEntradasService();
        }
        return instancia;
    }

    public synchronized void iniciar() {
        if (iniciado) {
            return;
        }
        iniciado = true;
        programarSiguienteEntrada();
    }

    private void programarSiguienteEntrada() {
        int segundos = ThreadLocalRandom.current().nextInt(5, 21); // 5 a 20 segundos

        executor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    simularEntradaAleatoria();
                } finally {
                    programarSiguienteEntrada();
                }
            }
        }, segundos, TimeUnit.SECONDS);
    }

    private void simularEntradaAleatoria() {
        List<Cliente> clientes = clienteService.obtenerClientes();

        if (clientes == null || clientes.isEmpty()) {
            return;
        }

        int intentos = 0;
        while (intentos < clientes.size()) {
            Cliente candidato = clientes.get(random.nextInt(clientes.size()));

            if (candidato != null
                    && candidato.getMembresia() != null
                    && !candidato.getMembresia().estaVencida()) {

                accesoService.registrarEntrada(candidato);
                return;
            }

            intentos++;
        }
    }
}
