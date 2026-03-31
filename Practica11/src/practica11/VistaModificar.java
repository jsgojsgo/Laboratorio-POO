package practica11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class VistaModificar {

    private Stage stage;

    public VistaModificar(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {

        //leer ventas
        List<String[]> ventasCSV = ManejoCSV.leerVentas("Registro.csv");
        ObservableList<String> listaVentas = FXCollections.observableArrayList();
        for (String[] v : ventasCSV) {
            listaVentas.add(String.join(" | ", v));
        }

        ListView<String> lista = new ListView<>(listaVentas);

        TextField txtNuevoValor = new TextField();
        txtNuevoValor.setPromptText("Nuevo valor");

        Button btnModificar = new Button("Modificar");
        Button btnEliminar = new Button("Eliminar");
        Button btnVolver = new Button("Volver");
        
        txtNuevoValor.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                txtNuevoValor.setStyle("-fx-border-color: red;");
            } else {
                txtNuevoValor.setStyle("");
            }
        });
        
        //modificar
        btnModificar.setOnAction(e -> {
            String seleccion = lista.getSelectionModel().getSelectedItem();
            if (seleccion == null || txtNuevoValor.getText().isEmpty()) {
                mostrarAlerta("Selecciona una venta y escribe un valor válido");
            } else {
                int index = lista.getSelectionModel().getSelectedIndex();
                String[] original = ventasCSV.get(index);
                original[0] = txtNuevoValor.getText();
                ManejoCSV.guardarTodasVentas("Registro.csv", ventasCSV);
                listaVentas.set(index, String.join(" | ", original));
                mostrarAlerta("Venta modificada");
            }
        });
        
        btnEliminar.setOnAction(e -> {
            int index = lista.getSelectionModel().getSelectedIndex();
            if (index == -1) {
                mostrarAlerta("Selecciona una venta");
            } else {
                ventasCSV.remove(index);
                ManejoCSV.guardarTodasVentas("Registro.csv", ventasCSV);
                listaVentas.remove(index);
            }
        });
        
        btnVolver.setOnAction(e -> {
            new VentanaPrincipal(stage).mostrar();
        });

        VBox root = new VBox(10, lista, txtNuevoValor, btnModificar, btnEliminar, btnVolver);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("Formato.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Modificar Ventas");
        stage.show();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}