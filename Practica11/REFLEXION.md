# REFLEXION

**1. ¿Qué es el hilo de la UI (JavaFX Application Thread) y por qué no debes hacer operaciones pesadas en él?**
Es el hilo principal que ejecuta toda la interfaz gráfica de la aplicación. No se debe hacer operaciones pesadas con este porque el hilo está enfocado principalmente para procesar los elementos de la interfaz principal

**2. ¿Qué es un =EventHandler=? ¿Cómo conecta la acción del usuario con la lógica de tu programa?**
Es un objeto que está al pendiente de eventos específicos de la interfaz. Conecta la acción del usuario con la lógica del programa pues cuando el usuario realiza una acción, dispara un evento, el cual el `EventHandler` observa y ejecuta un código asociado

**3. ¿Qué diferencia hay entre un =Stage=, una =Scene= y un =Node= en JavaFX?**
`Stage` representa una ventana de la aplicación, `Scene` representa el contenido de la ventana, `Node` representa cada elemento visual de la escena (`Scene`)
