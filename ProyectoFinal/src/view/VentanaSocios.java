package view;

import controller.GestionAutentificador;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cliente;
import util.BotonHover;
import util.DialogoPersonalizado;

public class VentanaSocios {

    private final Stage stage;

    public VentanaSocios(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        TextField txt = new TextField();
        txt.setPromptText("Ingresa tu matrícula");

        Button acceder = new Button("Acceder");
        Button regresar = new Button("Regresar");

        BotonHover.aplicar(acceder);
        BotonHover.aplicar(regresar);

        GestionAutentificador auth = new GestionAutentificador();

        acceder.setOnAction(e -> {
            Cliente c = auth.autenticarCliente(txt.getText());
            if (c != null) {
                new VentanaSocioValidado(stage, c).mostrar();
            } else {
                DialogoPersonalizado.mostrar("Error", "Matrícula incorrecta");
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