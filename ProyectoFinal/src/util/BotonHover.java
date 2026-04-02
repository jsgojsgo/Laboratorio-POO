package util;

import javafx.scene.control.Button;

public class BotonHover {

    private static final String ESTILO_NORMAL = "-fx-background-color: #2c3e50; -fx-text-fill: white;";
    private static final String ESTILO_HOVER = "-fx-background-color: #34495e; -fx-text-fill: white;";

    public static void aplicar(Button boton) {
        boton.setStyle(ESTILO_NORMAL);
        boton.setOnMouseEntered(e -> boton.setStyle(ESTILO_HOVER));
        boton.setOnMouseExited(e -> boton.setStyle(ESTILO_NORMAL));
    }
}