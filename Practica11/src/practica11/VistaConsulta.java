package practica11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class VistaConsulta {

    private Stage stage;

    public VistaConsulta(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {

        //leer csv
        List<String[]> ventasCSV = ManejoCSV.leerVentas("Registro.csv");
        ObservableList<String> listaVentas = FXCollections.observableArrayList();
        for (String[] v : ventasCSV) {
            listaVentas.add(String.join(" | ", v));
        }

        FilteredList<String> filtradas = new FilteredList<>(listaVentas, p -> true);

        TextField buscador = new TextField();
        buscador.setPromptText("Buscar venta...");

        buscador.textProperty().addListener((obs, oldVal, newVal) -> {
            filtradas.setPredicate(v -> {
                if (newVal == null || newVal.isEmpty()) return true;
                return v.toLowerCase().contains(newVal.toLowerCase());
            });
        });

        ListView<String> lista = new ListView<>(filtradas);

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> new VentanaPrincipal(stage).mostrar());

        VBox root = new VBox(10, buscador, lista, btnVolver);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("Formato.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Consultar Ventas");
        stage.show();
    }
}