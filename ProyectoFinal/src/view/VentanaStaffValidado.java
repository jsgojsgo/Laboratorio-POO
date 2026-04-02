package view;

import controller.GestionCliente;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cliente;
import util.FiltroBusqueda;

public class VentanaStaffValidado {
    private Stage stage;
    public VentanaStaffValidado(Stage stage) {
        this.stage = stage;
    }
    public void mostrar() {
        TableView<Cliente> tabla = new TableView<>();
        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        tabla.getColumns().add(colNombre);
        GestionCliente gc = new GestionCliente();
        FilteredList<Cliente> lista = new FilteredList<>(FXCollections.observableArrayList(gc.obtenerTodos()), p -> true);
        TextField filtro = new TextField();
        new FiltroBusqueda<Cliente>().aplicarFiltro(
                filtro,
                tabla,
                lista,
                c -> c.getNombre().toLowerCase().contains(filtro.getText().toLowerCase())
        );
        VBox root = new VBox(10, filtro, tabla);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(
            getClass().getResource("/resources/Formato.css").toExternalForm()
        );
        stage.setScene(scene);
    }
}