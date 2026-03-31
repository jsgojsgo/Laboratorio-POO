\# BITACORA IA



\- Main ejecuta todo el código

\- Clase Cliente (genera aleatoriamente clientes y su tiempo de ejecución en el cajero)

\- Clase Cajero (3 objetos solamente, 3 cajeros, todos se ejecutan a la vez, de manera independiente)



\*\*Prompt 1:\*\* 

explícame programación multihilos en java y dame un ejemplo



\*\*Respuesta 1:\*\*



\*\*Conceptos clave\*\*

\- Hilo (Thread): Es una unidad de ejecución dentro de un proceso.

\- Proceso: Un programa en ejecución (por ejemplo, tu app Java).

\- Concurrencia: Varios hilos avanzan al mismo tiempo (no necesariamente en paralelo real).

\- Paralelismo: Varios hilos ejecutándose literalmente al mismo tiempo (en CPUs multinúcleo).

\- Sincronización: Controlar el acceso a recursos compartidos para evitar errores.



Formas de crear hilos en Java:



1\. Extender la clase Thread

```

class MiHilo extends Thread {

&#x20;   public void run() {

&#x20;       System.out.println("Hola desde el hilo");

&#x20;   }

}



public class Main {

&#x20;   public static void main(String\[] args) {

&#x20;       MiHilo hilo = new MiHilo();

&#x20;       hilo.start(); // inicia el hilo

&#x20;   }

}

```

2\. Implementar la interfaz Runnable

```

class MiTarea implements Runnable {

&#x20;   public void run() {

&#x20;       System.out.println("Ejecutando tarea en hilo");

&#x20;   }

}



public class Main {

&#x20;   public static void main(String\[] args) {

&#x20;       Thread hilo = new Thread(new MiTarea());

&#x20;       hilo.start();

&#x20;   }

}

```

\*\*Prompt 2:\*\* 

cómo se puede manejar la concurrencia de hilos a un mismo recurso?



\*\*Respuesta 2:\*\*

Synchronized es una de las formas más importantes de manejar la concurrencia en Java. Sirve para bloquear una sección de código y permitir que solo un hilo entre a la vez.



Ejemplo:

```

class Contador {

&#x20;   private int valor = 0;



&#x20;   public synchronized void incrementar() {

&#x20;       valor++;

&#x20;   }



&#x20;   public int getValor() {

&#x20;       return valor;

&#x20;   }

}

```

\*\*Prompt 3:\*\* 

y cómo podemos detener manualmente los hilos, o su ejecución?



\*\*Respuesta 3:\*\*

Para detener hilos en Java de forma segura, la práctica moderna no es usar Thread.stop() (que es peligrosa y obsoleta), sino controlar la ejecución con un flag, generalmente usando volatile. Ejemplo:

```

class MiHilo extends Thread {

&#x20;   private volatile boolean activo = true;



&#x20;   public void run() {

&#x20;       while (activo) {

&#x20;           System.out.println("Hilo corriendo...");

&#x20;           try {

&#x20;               Thread.sleep(500);

&#x20;           } catch (InterruptedException e) {

&#x20;               System.out.println("Hilo interrumpido");

&#x20;               break;

&#x20;           }

&#x20;       }

&#x20;       System.out.println("Hilo detenido");

&#x20;   }



&#x20;   public void detener() {

&#x20;       activo = false; // la bandera indica al hilo que pare

&#x20;   }

}



public class Main {

&#x20;   public static void main(String\[] args) throws InterruptedException {

&#x20;       MiHilo hilo = new MiHilo();

&#x20;       hilo.start();



&#x20;       Thread.sleep(3000); // dejamos que corra 3 segundos

&#x20;       hilo.detener();     // pedimos que se detenga

&#x20;   }

}

```



\*\*Prompt 4:\*\*

pero cómo se puede hacer, con hilos, alguna forma de que el usuario presione una tecla y detenga los procesos?



\*\*Respuesta 4:\*\*

La idea clave es tener un hilo que haga tu tarea principal y otro hilo que esté escuchando la entrada del usuario, de forma que cuando el usuario presione una tecla se pueda señalizar al hilo principal que debe detenerse.



Ejemplo:

```

import java.util.Scanner;



public class DetenerConTecla {



&#x20;   // Variable de control compartida

&#x20;   private static volatile boolean running = true;



&#x20;   public static void main(String\[] args) {

&#x20;       // Hilo principal que hace la tarea

&#x20;       Thread tarea = new Thread(() -> {

&#x20;           while (running) {

&#x20;               System.out.println("Trabajando...");

&#x20;               try {

&#x20;                   Thread.sleep(1000); // simula trabajo

&#x20;               } catch (InterruptedException e) {

&#x20;                   e.printStackTrace();

&#x20;               }

&#x20;           }

&#x20;           System.out.println("Proceso detenido.");

&#x20;       });



&#x20;       // Hilo que escucha la entrada del usuario

&#x20;       Thread escucha = new Thread(() -> {

&#x20;           Scanner scanner = new Scanner(System.in);

&#x20;           System.out.println("Presiona Enter para detener...");

&#x20;           scanner.nextLine(); // espera a Enter

&#x20;           running = false;

&#x20;           scanner.close();

&#x20;       });



&#x20;       // Iniciar hilos

&#x20;       tarea.start();

&#x20;       escucha.start();



&#x20;       try {

&#x20;           tarea.join();

&#x20;           escucha.join();

&#x20;       } catch (InterruptedException e) {

&#x20;           e.printStackTrace();

&#x20;       }

&#x20;   }

}

```



\*\*Prompt 5:\*\*

hay alguna otra alternativa a hilos simples en java?



\*\*Respuesta 5:\*\*

Sí, en Java hay varias alternativas y mejoras sobre los “hilos simples” que pueden hacer tu programa más seguro y flexible. Algunas de las más comunes incluyen:

1\. Hilos daemon: Cuando todos los hilos no-daemon terminan, la JVM finaliza aunque los daemon sigan corriendo.

```

Thread daemon = new Thread(() -> {

&#x20;   while (true) {

&#x20;       System.out.println("Hilo daemon trabajando...");

&#x20;       try {

&#x20;           Thread.sleep(1000);

&#x20;       } catch (InterruptedException e) {

&#x20;           break;

&#x20;       }

&#x20;   }

});

daemon.setDaemon(true); // lo marcamos como daemon

daemon.start();

```

2\. ExecutorService: En vez de crear hilos a mano, puedes usar un pool de hilos, lo que es más eficiente y escalable.

```

import java.util.concurrent.\*;



public class ThreadPoolExample {

&#x20;   public static void main(String\[] args) throws InterruptedException {

&#x20;       ExecutorService executor = Executors.newFixedThreadPool(2);



&#x20;       Runnable tarea = () -> {

&#x20;           for (int i = 0; i < 5; i++) {

&#x20;               System.out.println(Thread.currentThread().getName() + " trabajando...");

&#x20;               try {

&#x20;                   Thread.sleep(500);

&#x20;               } catch (InterruptedException e) {

&#x20;                   System.out.println(Thread.currentThread().getName() + " interrumpido");

&#x20;                   break;

&#x20;               }

&#x20;           }

&#x20;       };



&#x20;       executor.submit(tarea);

&#x20;       executor.submit(tarea);



&#x20;       executor.shutdown(); // no acepta nuevas tareas

&#x20;       executor.awaitTermination(10, TimeUnit.SECONDS);

&#x20;   }

}

```





