# README

**El archivo JAR se encuentra en Practica11/dist/**

**Explicación del proyecto:**
El proyecto consiste en una aplicación JavaFX que simula un punto de venta de platillos, el cual permite crear, leer, modificar y eliminar ventas.


A continuación, una breve descripción de lo que realiza cada archivo:
- Practica11 (JavaFX): Inicia el programa al ejecutarse
- VentanaPrincipal (JavaFX): Contiene los elementos del menú principal
- VistaVenta (JavaFX): Contiene los elementos para crear una vents
- VistaModificar (JavaFX): Contiene los elementos para modificar una venta
- VistaConsulta (JavaFX): Contiene los elementos para leer y/o buscar una venta
- BotonHover (JavaFX): Contiene el código para hacer el componente personalizado
- ValidacionTextField (JavaFX): Contiene el código para hacer el componente personalizado
- DialogoPersonalizado (JavaFX): Contiene el código para hacer el diálogo modal personalizado
- ManejoCSV (Java): Contiene el código para el manejo de los archivos CSV
- Producto (Java): Contiene la representación de los productos
- Registro (CSV): Contiene el registro de todas las ventas
- BaseDatos (CSV): Contiene los productos, precios y una breve descripción
- Formato (CSS): Contiene el formato visual para las interfaces JavaFX

Los componentes personalizados son un botón con efecto hover, un textfield que permite búsquedas dinámicas y evita entradas incorrectas
El diálogo modal personalizado bloquea la ventana principal hasta que se cierra


**Requisitos**
- Java 8 o superior
- Compilador de Java (`javac`) y terminal para ejecutar

**Guía para ejecutar**
1. Abrir el archivo `Practica11.java` (contiene el `main`)
2. Compilarlo y ejecutarlo
3. Para registrar una venta, se hace click en `Registrar Venta`, se elige un platillo y se hace click a `Agregar Venta`, se debe repetir este proceso para todos los platillos de una misma venta
4. Para finalizar la venta, se hace click en `Pasar a checkout`, esto hará que se guarde y registre la venta
5. Para modificar una venta se hace click en `Modificar venta`
6. Para filtrar o leer ventas se hace click en `Consultar ventas`
