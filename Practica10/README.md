# README

**Explicación del proyecto:**
El proyecto consiste en una simulación de un sistema de 3 cajeros, que recibe clientes:

- La clase main ejecuta el código
- La clase Cajero es la encargada de manejar la cola de los clientes y permitir el paso solamente cuando un cliente ya haya terminado
- La clase Cliente simplemente crea instancias, con un tiempo de atención y ejecución aleatorio, que 'usará' algún cajero
- La clase QueueClientes es quien contiene la cola de clientes, y controla su acceso

Se decidió hacer una simulación de un sistema de cajeros que reciben clientes, debido a que es un ejemplo práctico y cotidiano que implementa en gran medida la programación multihilos, pues los clientes deben esperar a que otros clientes terminen sus pendientes (sus procesos) en el cajero, para poder continuar

En cuanto a la cola, se escogió que fuera estática (10 elementos) porque en un banco real, una vez se llena la cola para usar el cajero, se le solicita al cliente que espere fuera, porque tienen una capacidad limitada (estática) de clientes que pueden atender al mismo tiempo y de clientes que pueden estar esperando al mismo tiempo dentro de la sucursal


**Requisitos**
- Java 8 o superior
- Compilador de Java (`javac`) y terminal para ejecutar

**Guía para ejecutar**
1. Abrir el archivo `Practica10.java` (contiene el `main`)
2. Compilarlo y ejecutarlo (a partir de aquí, el código realiza todos los procesos por su cuenta)