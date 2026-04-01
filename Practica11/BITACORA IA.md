# BITACORA IA

**Prompt 1:**
explícame javaFX en java8.2 con netbeans y dame ejemplos de cómo implementarla

**Respuesta 1:**

JavaFX es un framework de Java para crear aplicaciones gráficas de escritorio (GUI) con interfaces modernas.
En Java 8, JavaFX viene incluido dentro del JDK, así que no necesitas instalar nada adicional.

**Explicación de lo básico**

- Application → Clase base para todas las apps JavaFX.
- start(Stage primaryStage) → Método principal donde se crea la interfaz.
- Stage → Ventana principal.
- Scene → Contenedor de los elementos visuales (Label, Button, etc.).
- Label, Button, TextField → Controles UI.
- launch(args) → Inicializa la aplicación.

**Prompt 2:**
cómo se hace el manejo de eventos y dame un ejemplo

**Respuesta 2:**
En JavaFX, un evento es cualquier acción que hace el usuario, por ejemplo: Clic en un botón, Escribir en un TextField, Mover el ratón, Presionar una tecla
El manejo de eventos consiste en detectar esa acción y ejecutar un bloque de código en respuesta.

**Ejemplo de cómo manejar los eventos:**

1. Usando `setOnAction`
```
Button boton = new Button("Clic aquí");
boton.setOnAction(e -> {
    System.out.println("¡Botón presionado!");
});
```

2. Usando `EventHandler`:
```
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

Button boton = new Button("Clic aquí");

boton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        System.out.println("¡Botón presionado (EventHandler)!");
    }
});
```

**Prompt 3:** 
cómo hago las implementaciones de los botones y sus formatos? dame ejemplos

**Respuesta 3:**
JavaFX tiene muchos controles en el paquete javafx.scene.control, que se agregan al layout (por ejemplo, `VBox`, `HBox`, `StackPane`, `GridPane`) usando `getChildren().add()`:
```
Button boton = new Button("Click");
Label etiqueta = new Label("Hola Mundo");
TextField campoTexto = new TextField();
CheckBox check = new CheckBox("Opción");
VBox layout = new VBox(10); // 10px de espacio entre elementos
layout.getChildren().addAll(etiqueta, campoTexto, boton, check);
```
Para dar estilo, JavaFX permite aplicar CSS de manera muy parecida a HTML. Para esto crea un archivo CSS y cárgalo a tu escena:
```
.button {
    -fx-background-color: #3498db;
    -fx-text-fill: white;
    -fx-font-size: 16px;
    -fx-padding: 10px 20px;
}

.label {
    -fx-font-weight: bold;
    -fx-font-size: 14px;
}
scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
```
Layouts comunes:
- StackPane → Apila elementos uno encima del otro.
- VBox / HBox → Organiza vertical / horizontalmente.
- GridPane → Organiza en filas y columnas.
- BorderPane → Divide en top, bottom, left, right, center.


**Prompt 4:**
cómo hago un diálogo modal personalizado?

**Respuesta 4:**
Un diálogo modal personalizado es básicamente una ventana secundaria (Stage) que bloquea la ventana principal y tiene tu propio diseño (botones, campos, etc.)

Forma básica de hacerlo en JavaFX:
```
//Crea un nuevo Stage
Stage dialog = new Stage();

//Hacerlo modal
dialog.initModality(Modality.APPLICATION_MODAL);

//Opcional pero recomendado
dialog.initOwner(primaryStage); 

//Crear contenido
Label mensaje = new Label("¿Deseas continuar?");
Button btnOK = new Button("Aceptar");

VBox layout = new VBox(10);
layout.getChildren().addAll(mensaje, btnOK);
layout.setStyle("-fx-padding: 20;");

//Cerrar diálogo
btnOK.setOnAction(e -> dialog.close());

//Crear escena y mostrar
Scene scene = new Scene(layout, 250, 150);
dialog.setScene(scene);
dialog.showAndWait(); // IMPORTANTE (modal real)
```

**Prompt 5:**
dame un ejemplo de cómo implementar CSS en JavaFX

**Respuesta 5:**
/* Estilo para botones */
.button {
    -fx-background-color: #3498db;
    -fx-text-fill: white;
    -fx-font-size: 14px;
    -fx-padding: 10px 20px;
    -fx-background-radius: 5px;
}

/* Estilo para VBox */
.vbox {
    -fx-spacing: 15px;
    -fx-padding: 20px;
    -fx-alignment: center;
    -fx-background-color: #ecf0f1;
}

/* Estilo para Label */
.label {
    -fx-font-size: 16px;
    -fx-text-fill: #2c3e50;
    -fx-font-weight: bold;
}

**Prompt 6:**
dame una base de datos en formato CSV para un restaurante con 30 platillos diferentes, que sea "nombre, precio, descripción breve"

**Respuesta 6:**
nombre,precio,descripcion
Tacos al Pastor,12.5,Tacos con carne adobada y piña
Enchiladas Verdes,14.0,Tortillas rellenas con pollo bañadas en salsa verde
Chiles Rellenos,15.0,Chiles poblanos rellenos de queso y cubiertos de salsa
Pozole Rojo,13.0,Sopa tradicional con maíz, carne y condimentos
Quesadillas de Flor de Calabaza,10.0,Tortillas con flor de calabaza y queso
Sopa de Lima,12.0,Sopa típica de Yucatán con pollo y lima
Tamales de Rajas,11.0,Tamales rellenos de rajas de chile poblano
Carnitas,16.0,Cerdo cocido lentamente y servido con tortillas
Tostadas de Tinga,12.0,Tostadas con pollo deshebrado en salsa de chipotle
Guacamole con Totopos,8.0,Aguacate triturado con cebolla y chile
Ensalada César,9.0,Lechuga, crutones y aderezo César
Hamburguesa Clásica,14.5,Hamburguesa con queso, lechuga y tomate
Pasta Alfredo,13.0,Pasta con salsa cremosa de queso parmesano
Pizza Margherita,15.0,Pizza con tomate, albahaca y mozzarella
Lasagna de Carne,16.0,Láminas de pasta con carne, queso y salsa de tomate
Pollo a la Parrilla,17.0,Pollo sazonado y asado a la parrilla
Filete de Res,20.0,Filete de res con guarnición de verduras
Camarones al Ajillo,18.0,Camarones salteados con ajo y mantequilla
Paella Mixta,22.0,Arroz con mariscos, pollo y chorizo
Ceviche de Pescado,14.0,Pescado marinado con limón, cebolla y cilantro
Chilaquiles Rojos,11.0,Tortillas fritas bañadas en salsa roja y queso
Molletes,9.0,Pan bolillo con frijoles, queso y pico de gallo
Sopes de Pollo,12.0,Sopes con frijoles, pollo, lechuga y crema
Queso Fundido,13.0,Queso derretido con chorizo para untar
Nachos con Carne,14.0,Tortilla chips con carne, queso y jalapeños
Alambre de Res,16.0,Carne de res con pimientos y tocino
Fajitas de Pollo,15.0,Tiras de pollo con pimientos y cebolla
Salmón a la Plancha,19.0,Salmón fresco servido con salsa de limón
Crepas de Nutella,10.0,Crepas dulces rellenas de Nutella
Flan Casero,7.0,Postre tradicional de huevo y caramelo