# REFLEXION



**1. ¿Qué es una race condition? Describe un escenario concreto de tu código
donde podría ocurrir.**

`Race condition` es un error que ocurre cuando dos o más hilos intentan acceder al mismo proceso; normalmente uno logra entrar, lo que causa que el otro genere un comportamiento impredecible.
Podría ocurrir en la clase `QueueClientes`, pero justamente se hace uso de `synchronized` en esa sección de código, para evitar este problema

**2. ¿Por qué =synchronized= resuelve el problema? ¿Qué desventaja de
rendimiento tiene?**

Porque justamente `synchronized` se encarga de restringir o bloquear el acesso de múltiples hilos a un mismo proceso, al mismo tiempo. En cuanto a la desventaja de rendimiento, puede hacer que el sistema se retrase si hay demasiados hilos, lo que generaría problemas de latencia


**3. ¿Qué diferencia hay entre =Thread.sleep()= y =Object.wait()=? ¿Cuándo
usarías cada uno?**

`Thread.sleep()`se usa principalmente para poner en espera a un hilo, un tiempo fijo, como cuando se quieren simular retrasos o simplemente se desea dejar esperando hilos, para evitar problemas como `race condition`
`Object.wait()`se usa para que un hilo se detenga, hasta que otro lo notifique mediante un `notify()` o `notifyAll()`, pues esto permite la coordinación entre hilos

