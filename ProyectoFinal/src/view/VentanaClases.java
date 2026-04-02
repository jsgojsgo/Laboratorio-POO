package view;

import controller.GestionClase;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Clase;
import model.Cliente;
import util.DialogoPersonalizado;
import java.util.ArrayList;

public class VentanaClases {

    private Stage stage;
    private Cliente cliente;

    public VentanaClases(Stage stage, Cliente cliente) {
        this.stage = stage;
        this.cliente = cliente;
    }

    public void mostrar() {

        TableView<Clase> tabla = new TableView<>();
        TableColumn<Clase, String> nombre = new TableColumn<>("Clase");
        nombre.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNombre()));
        tabla.getColumns().add(nombre);
        tabla.setItems(FXCollections.observableArrayList(new ArrayList<>()));

        Button asistir = new Button("Asistir");
        Button cancelar = new Button("Cancelar");
        GestionClase gc = new GestionClase();

        asistir.setOnAction(e -> {
            Clase c = tabla.getSelectionModel().getSelectedItem();
            if (c != null && gc.inscribir(cliente, c)) {
                DialogoPersonalizado.mostrar("OK", "Registrado");
            }
        });
        cancelar.setOnAction(e -> {
            Clase c = tabla.getSelectionModel().getSelectedItem();
            if (c != null && gc.cancelar(cliente, c)) {
                DialogoPersonalizado.mostrar("OK", "Cancelado");
            }
        });
        VBox root = new VBox(10, tabla, asistir, cancelar);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);

        scene.getStylesheets().add(
            getClass().getResource("/resources/Formato.css").toExternalForm()
        );

        stage.setScene(scene);
    }
}