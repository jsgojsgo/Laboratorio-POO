# SINCRONIZACION

Las estrategias utilizadas se describirán a continuación:

**Practica10.java (Main):** Inicia los objetos Cajero, crea los clientes y comienza la simulación

- Se hace uso de hilos daemon en vez de hilos simples, pues estos no bloquean la finalización del programa una vez que terminan los hilos
- Se hace uso de `volatile` para comunicar de forma segura la finalización de cada hilo
- Se hace uso de `Thread(()->{...})` para finalizar todos los procesos, una vez se presione `ENTER`

**QueueClientes:** Contiene la cola de clientes y regula su acceso

- Se hace uso de `synchronized` para evitar que múltiples cajeros (o hilos, en general) entren al mismo tiempo a la cola de clientes o sus respectivos procesos (así se previene race conditions)
- Se hace uso de `wait()` para que el cajero se quede en espera, cuando la cola esté vacía y `notifyAll()` para avisar a todos los cajeros que llegaron clientes (también para cuando se desea finalizar el programa)

**Cliente:** Instancias con tiempo de atención aleatorio (que simula la duración de cada cliente en el cajero)

- Se ejecuta como `Runnable` para hacer uso de `ExecutorService`, para que así se tenga un control sobre los hilos creados

**Cajero:** Crea los cajeros que atienden los clientes en la cola

- Se hace uso de `Thread` (lo extiende), para procesar los clientes uno a uno, gracias también a `synchronized`

