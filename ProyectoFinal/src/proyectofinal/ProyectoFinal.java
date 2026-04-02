package proyectofinal;
import view.VentanaPrincipal;

import javafx.application.Application;
import javafx.stage.Stage;

public class ProyectoFinal extends Application {
    @Override
    public void start(Stage stage) {
        new VentanaPrincipal(stage).mostrar();
    }
    public static void main(String[] args) {
        launch(args);
    }
}