package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.BotonHover;
import util.DialogoPersonalizado;

public class VentanaClientes {

    private final Stage stage;

    public VentanaClientes(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        Label titulo = new Label("¡Nos alegra que quieras formar parte de nuestra comunidad!");
        titulo.getStyleClass().add("titulo-principal");

        VBox standard = crearTarjeta(
                "STANDARD",
                "$1000 inscripción",
                "$350 mensualidad",
                "Acceso a cualquier gym de la cadena",
                "Asistencia personalizada de coaches",
                "Clases grupales",
                "Zona Standard"
        );

        VBox pro = crearTarjeta(
                "PRO",
                "$1000 inscripción",
                "$450 mensualidad",
                "Acceso al gym contratado",
                "Asistencia personalizada de coaches",
                "Clases grupales",
                "Zonas Standard + Pro"
        );

        VBox ultra = crearTarjeta(
                "ULTRA",
                "$1500 inscripción",
                "$550 mensualidad",
                "Acceso a cualquier gym de la cadena",
                "Asistencia personalizada de coaches",
                "Asistencia profesional",
                "Todas las zonas"
        );

        HBox tarjetas = new HBox(15, standard, pro, ultra);
        tarjetas.setAlignment(Pos.CENTER);

        Button regresar = new Button("Regresar");
        BotonHover.aplicar(regresar);
        regresar.setOnAction(e -> new VentanaPrincipal(stage).mostrar());

        VBox root = new VBox(20, titulo, tarjetas, regresar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 1150, 500);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);
    }

    private VBox crearTarjeta(String plan, String line1, String line2, String line3, String line4, String line5, String line6) {
        Label lblPlan = new Label(plan);
        Label a = new Label(line1);
        Label b = new Label(line2);
        Label c = new Label(line3);
        Label d = new Label(line4);
        Label e = new Label(line5);
        Label f = new Label(line6);

        Button solicitar = new Button("Solicitar alta");
        BotonHover.aplicar(solicitar);
        solicitar.setOnAction(ev ->
                DialogoPersonalizado.mostrar("Información", "Contacta al staff para continuar con el plan " + plan)
        );

        VBox card = new VBox(8, lblPlan, a, b, c, d, e, f, solicitar);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.getStyleClass().add("card-oscura");
        return card;
    }
}