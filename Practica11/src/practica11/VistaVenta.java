package practica11;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class VistaVenta {
    private Stage stage;
    private List<Producto> carrito = new ArrayList<>();

    public VistaVenta(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {

        TableView<Producto> tabla = new TableView<>();
        TableColumn<Producto, String> colNombre = new TableColumn<>("Producto");
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        TableColumn<Producto, String> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getPrecio())));
        TableColumn<Producto, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        tabla.getColumns().addAll(colNombre, colPrecio, colDescripcion);

        //leer productos csv
        List<Producto> listaProductos = ManejoCSV.leerProductos("BaseDatos.csv");
        ObservableList<Producto> productos = FXCollections.observableArrayList(listaProductos);
        FilteredList<Producto> filtrados = new FilteredList<>(productos, p -> true);

        tabla.setItems(filtrados);

        //filtar
        ValidacionTextField buscador = new ValidacionTextField("Buscar producto...");
        buscador.textProperty().addListener((obs, oldVal, newVal) -> {
            filtrados.setPredicate(producto -> {
                if (newVal == null || newVal.isEmpty()) return true;
                return producto.getNombre().toLowerCase().contains(newVal.toLowerCase());
            });
        });
        
        BotonHover btnAgregar = new BotonHover("Agregar a venta");
        BotonHover btnCheckout = new BotonHover("Pasar a checkout");
        BotonHover btnVolver = new BotonHover("Volver");

        btnAgregar.setOnAction(e -> {
            Producto seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Selecciona un producto");
            } else {
                carrito.add(seleccionado);
                mostrarAlerta("Producto agregado: " + seleccionado.getNombre());
            }
        });

        btnCheckout.setOnAction(e -> {
            if (carrito.isEmpty()) {
                mostrarAlerta("No hay productos en el carrito");
                return;
            }

            boolean confirmar = DialogoPersonalizado.mostrar("¿Confirmar venta?");
            if (confirmar) {
                //guardar venta CSV
                List<String> registro = new ArrayList<>();
                double total = 0;
                for (Producto p : carrito) {
                    registro.add(p.getNombre());
                    total += p.getPrecio();
                }
                registro.add(0, String.valueOf(total));
                ManejoCSV.guardarVenta("Registro.csv", registro);
                carrito.clear();
                mostrarAlerta("Venta registrada correctamente");
            }
        });

        btnVolver.setOnAction(e -> new VentanaPrincipal(stage).mostrar());

        VBox controles = new VBox(10, buscador, btnAgregar, btnCheckout, btnVolver);
        controles.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(tabla);
        root.setRight(controles);

        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(getClass().getResource("Formato.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Registrar Venta");
        stage.show();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}