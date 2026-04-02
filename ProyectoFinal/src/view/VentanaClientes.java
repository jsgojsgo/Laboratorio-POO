package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class VentanaClientes {

    private Stage stage;
    
    public VentanaClientes(Stage stage) {
        this.stage = stage;
    }
    
    public void mostrar() {
        Label lbl = new Label("Selecciona un plan para registrarte");
        Button regresar = new Button("Regresar");
        
        VBox root = new VBox(20, lbl, regresar);
        
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(
            getClass().getResource("/resources/Formato.css").toExternalForm()
        );
        stage.setScene(scene);
    }
}