package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.BotonHover;

public class VentanaPrincipal {

    private final Stage stage;

    public VentanaPrincipal(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        Button socio = new Button("¡Sí, ya soy socio!");
        Button cliente = new Button("¡Aún no, pero quiero serlo!");
        Button staff = new Button("¡Soy Staff!");
        Button salir = new Button("Salir");

        BotonHover.aplicar(socio);
        BotonHover.aplicar(cliente);
        BotonHover.aplicar(staff);
        BotonHover.aplicar(salir);

        socio.setOnAction(e -> new VentanaSocios(stage).mostrar());
        cliente.setOnAction(e -> new VentanaClientes(stage).mostrar());
        staff.setOnAction(e -> new VentanaStaff(stage).mostrar());
        salir.setOnAction(e -> stage.close());

        VBox root = new VBox(15, socio, cliente, staff, salir);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("GymPOS");
        stage.show();
    }
}