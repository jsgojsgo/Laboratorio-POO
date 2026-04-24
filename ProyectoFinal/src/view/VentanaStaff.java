package view;

import controller.GestionAutentificador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.BotonHover;
import util.DialogoPersonalizado;

public class VentanaStaff {

    private final Stage stage;

    public VentanaStaff(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        TextField txt = new TextField();
        txt.setPromptText("Ingrese aquí el ID");

        Button acceder = new Button("Acceder");
        Button regresar = new Button("Regresar");

        BotonHover.aplicar(acceder);
        BotonHover.aplicar(regresar);

        GestionAutentificador auth = new GestionAutentificador();

        acceder.setOnAction(e -> {
            if (auth.autenticarStaff(txt.getText())) {
                new VentanaStaffValidado(stage).mostrar();
            } else {
                DialogoPersonalizado.mostrar("Error", "ID incorrecto, intente de nuevo");
            }
        });

        regresar.setOnAction(e -> new VentanaPrincipal(stage).mostrar());

        VBox root = new VBox(10, txt, acceder, regresar);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);
    }
}