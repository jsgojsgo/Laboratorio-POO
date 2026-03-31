package practica11;

import javafx.scene.control.Button;

public class BotonHover extends Button {

    public BotonHover (String texto) {
        super(texto);
        setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        setOnMouseEntered(e -> setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-weight: bold;"));
        setOnMouseExited(e -> setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;"));
    }
}