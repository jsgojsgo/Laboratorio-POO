package view;

import controller.GestionMembresia;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cliente;
import model.TipoMembresia;
import util.DialogoPersonalizado;
import util.BotonHover;

public class VentanaMembresias {

    private Stage stage;
    private Cliente cliente;

    public VentanaMembresias(Stage stage, Cliente cliente) {
        this.stage = stage;
        this.cliente = cliente;
    }

    public void mostrar() {
        GestionMembresia gm = new GestionMembresia();
        VBox standard = crearPlan("STANDARD", TipoMembresia.STANDARD, gm);
        VBox pro = crearPlan("PRO", TipoMembresia.PRO, gm);
        VBox ultra = crearPlan("ULTRA", TipoMembresia.ULTRA, gm);
        HBox root = new HBox(20, standard, pro, ultra);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(
            getClass().getResource("/resources/Formato.css").toExternalForm()
        );
        stage.setScene(scene);
    }

    private VBox crearPlan(String nombre, TipoMembresia tipo, GestionMembresia gm) {
        Button btn = new Button("Seleccionar");
        BotonHover.aplicar(btn);

        btn.setOnAction(e -> {
            gm.cambiar(cliente, tipo);
            DialogoPersonalizado.mostrar("Éxito", "Membresía cambiada a " + nombre);
        });
        VBox box = new VBox(10, new javafx.scene.control.Label(nombre), btn);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-border-color: white; -fx-padding: 20;");
        return box;
    }
}