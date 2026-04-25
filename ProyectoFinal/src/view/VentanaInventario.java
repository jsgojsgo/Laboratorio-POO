package view;

import controller.GestionInventario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Inventario;
import util.BotonHover;
import util.FiltroBusqueda;

public class VentanaInventario {

    private final Stage stage;
    private final Runnable onRegresar;

    public VentanaInventario(Stage stage) {
        this(stage, null);
    }

    public VentanaInventario(Stage stage, Runnable onRegresar) {
        this.stage = stage;
        this.onRegresar = onRegresar;
    }

    public void mostrar() {
        GestionInventario gestionInventario = new GestionInventario();

        Label titulo = new Label("Inventario del gimnasio");

        TextField filtro = new TextField();
        filtro.setPromptText("Filtrar inventario...");

        TableView<Inventario> tabla = new TableView<Inventario>();

        TableColumn<Inventario, String> colNombre = new TableColumn<Inventario, String>("Elemento");
        colNombre.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNombreElemento()));

        TableColumn<Inventario, String> colTipo = new TableColumn<Inventario, String>("Tipo");
        colTipo.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getTipo()));

        TableColumn<Inventario, String> colPeso = new TableColumn<Inventario, String>("Peso");
        colPeso.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getPeso()));

        TableColumn<Inventario, String> colStock = new TableColumn<Inventario, String>("Stock");
        colStock.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(String.valueOf(d.getValue().getStock())));

        tabla.getColumns().addAll(colNombre, colTipo, colPeso, colStock);

        ObservableList<Inventario> baseDatos = FXCollections.observableArrayList(gestionInventario.obtenerTodo());
        FilteredList<Inventario> filtrada = new FilteredList<Inventario>(baseDatos, i -> true);
        tabla.setItems(filtrada);

        new FiltroBusqueda<Inventario>().aplicarFiltro(
                filtro,
                tabla,
                filtrada,
                i -> contieneInventario(i, filtro.getText())
        );

        Button regresar = new Button("Regresar");
        BotonHover.aplicar(regresar);
        regresar.setOnAction(e -> {
            if (onRegresar != null) {
                onRegresar.run();
            } else {
                new VentanaPrincipal(stage).mostrar();
            }
        });

        HBox botones = new HBox(regresar);
        botones.setAlignment(Pos.CENTER);

        VBox root = new VBox(12, titulo, filtro, tabla, botones);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 820, 480);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);
    }

    private boolean contieneInventario(Inventario i, String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return true;
        }

        String t = texto.toLowerCase().trim();

        return (i.getNombreElemento() != null && i.getNombreElemento().toLowerCase().contains(t))
                || (i.getTipo() != null && i.getTipo().toLowerCase().contains(t))
                || (i.getPeso() != null && i.getPeso().toLowerCase().contains(t))
                || String.valueOf(i.getStock()).contains(t);
    }
}