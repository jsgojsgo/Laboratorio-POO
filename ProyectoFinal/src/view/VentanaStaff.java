package view;

import controller.GestionAutentificador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DialogoPersonalizado;

public class VentanaStaff {

    private Stage stage;
    public VentanaStaff(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        TextField txt = new TextField();
        txt.setPromptText("ID");
        Button acceder = new Button("Acceder");
        GestionAutentificador auth = new GestionAutentificador();
        acceder.setOnAction(e -> {
            if (auth.autenticarStaff(txt.getText())) {
                new VentanaStaffValidado(stage).mostrar();
            } else {
                DialogoPersonalizado.mostrar("Error", "ID incorrecto");
            }
        });
        VBox root = new VBox(10, txt, acceder);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(
            getClass().getResource("/resources/Formato.css").toExternalForm()
        );
        stage.setScene(scene);
    }
}