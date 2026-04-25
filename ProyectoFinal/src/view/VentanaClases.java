package view;

import controller.GestionClase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Clase;
import util.BotonHover;
import util.DialogoPersonalizado;
import util.FiltroBusqueda;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class VentanaClases {

    private final Stage stage;
    private final Runnable onRegresar;

    public VentanaClases(Stage stage) {
        this(stage, null);
    }

    public VentanaClases(Stage stage, Runnable onRegresar) {
        this.stage = stage;
        this.onRegresar = onRegresar;
    }

    public void mostrar() {
        GestionClase gc = new GestionClase();

        Label titulo = new Label("Gestión de clases (solo staff)");

        TextField filtro = new TextField();
        filtro.setPromptText("Filtrar clases...");

        TableView<Clase> tabla = new TableView<Clase>();

        TableColumn<Clase, String> colCoach = new TableColumn<Clase, String>("Coach");
        colCoach.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getRol()));

        TableColumn<Clase, String> colNombre = new TableColumn<Clase, String>("Clase");
        colNombre.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNombre()));

        TableColumn<Clase, String> colFecha = new TableColumn<Clase, String>("Fecha");
        colFecha.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getFechaTexto()));

        TableColumn<Clase, String> colInicio = new TableColumn<Clase, String>("Hora inicio");
        colInicio.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getHoraInicio()));

        TableColumn<Clase, String> colFin = new TableColumn<Clase, String>("Hora fin");
        colFin.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getHoraFin()));

        tabla.getColumns().addAll(colCoach, colNombre, colFecha, colInicio, colFin);

        final ObservableList<Clase> baseDatos = FXCollections.observableArrayList(gc.obtenerClases());
        final FilteredList<Clase> filtrada = new FilteredList<Clase>(baseDatos, c -> true);
        tabla.setItems(filtrada);

        new FiltroBusqueda<Clase>().aplicarFiltro(
                filtro,
                tabla,
                filtrada,
                c -> contieneClase(c, filtro.getText())
        );

        Button agregar = new Button("Agregar");
        Button modificar = new Button("Modificar");
        Button eliminar = new Button("Eliminar");
        Button regresar = new Button("Regresar");

        BotonHover.aplicar(agregar);
        BotonHover.aplicar(modificar);
        BotonHover.aplicar(eliminar);
        BotonHover.aplicar(regresar);

        agregar.setOnAction(e -> {
            Optional<Clase> resultado = mostrarDialogoClase(gc, null);
            if (resultado.isPresent()) {
                try {
                    gc.crearClase(resultado.get());
                    refrescar(baseDatos, gc);
                    DialogoPersonalizado.mostrar("Éxito", "Clase agregada correctamente");
                } catch (Exception ex) {
                    DialogoPersonalizado.mostrar("Error", ex.getMessage());
                }
            }
        });

        modificar.setOnAction(e -> {
            Clase seleccionada = tabla.getSelectionModel().getSelectedItem();
            if (seleccionada == null) {
                DialogoPersonalizado.mostrar("Error", "Selecciona una clase para modificar");
                return;
            }

            Optional<Clase> resultado = mostrarDialogoClase(gc, seleccionada);
            if (resultado.isPresent()) {
                try {
                    gc.modificarClase(seleccionada, resultado.get());
                    refrescar(baseDatos, gc);
                    DialogoPersonalizado.mostrar("Éxito", "Clase modificada correctamente");
                } catch (Exception ex) {
                    DialogoPersonalizado.mostrar("Error", ex.getMessage());
                }
            }
        });

        eliminar.setOnAction(e -> {
            Clase seleccionada = tabla.getSelectionModel().getSelectedItem();
            if (seleccionada == null) {
                DialogoPersonalizado.mostrar("Error", "Selecciona una clase para eliminar");
                return;
            }

            if (DialogoPersonalizado.confirmar("¿Deseas eliminar la clase seleccionada?")) {
                gc.eliminarClase(seleccionada);
                refrescar(baseDatos, gc);
                DialogoPersonalizado.mostrar("Éxito", "Clase eliminada correctamente");
            }
        });

        regresar.setOnAction(e -> {
            if (onRegresar != null) {
                onRegresar.run();
            } else {
                new VentanaPrincipal(stage).mostrar();
            }
        });

        HBox botones = new HBox(10, agregar, modificar, eliminar, regresar);
        botones.setAlignment(Pos.CENTER);

        VBox root = new VBox(12, titulo, filtro, tabla, botones);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1000, 520);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);
    }

    private void refrescar(ObservableList<Clase> baseDatos, GestionClase gc) {
        baseDatos.setAll(gc.obtenerClases());
    }

    private boolean contieneClase(Clase clase, String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return true;
        }

        String t = texto.toLowerCase().trim();

        return (clase.getNombre() != null && clase.getNombre().toLowerCase().contains(t))
                || (clase.getRol() != null && clase.getRol().toLowerCase().contains(t))
                || (clase.getFechaTexto() != null && clase.getFechaTexto().toLowerCase().contains(t))
                || (clase.getHoraInicio() != null && clase.getHoraInicio().toLowerCase().contains(t))
                || (clase.getHoraFin() != null && clase.getHoraFin().toLowerCase().contains(t));
    }

    private Optional<Clase> mostrarDialogoClase(GestionClase gc, Clase existente) {
        Dialog<Clase> dialog = new Dialog<Clase>();
        dialog.setTitle(existente == null ? "Agregar clase" : "Modificar clase");

        ButtonType guardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(guardar, ButtonType.CANCEL);

        List<Clase> plantillas = gc.obtenerClasesBase();
        if (plantillas == null || plantillas.isEmpty()) {
            plantillas = gc.obtenerClases();
        }

        if (plantillas == null || plantillas.isEmpty()) {
            DialogoPersonalizado.mostrar("Error", "No hay clases base disponibles en BaseDatosClases.csv");
            return Optional.empty();
        }

        ComboBox<Clase> comboPlantillas = new ComboBox<Clase>(FXCollections.observableArrayList(plantillas));
        DatePicker dpFecha = new DatePicker();

        comboPlantillas.setPromptText("Selecciona una clase base");

        comboPlantillas.setConverter(new StringConverter<Clase>() {
            @Override
            public String toString(Clase clase) {
                if (clase == null) {
                    return "";
                }
                return clase.getNombre() + " | " + clase.getRol() + " | " + clase.getHoraInicio() + " - " + clase.getHoraFin();
            }

            @Override
            public Clase fromString(String string) {
                return null;
            }
        });

        comboPlantillas.setCellFactory(list -> new ListCell<Clase>() {
            @Override
            protected void updateItem(Clase item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre() + " | " + item.getRol() + " | " + item.getHoraInicio() + " - " + item.getHoraFin());
            }
        });

        comboPlantillas.setButtonCell(new ListCell<Clase>() {
            @Override
            protected void updateItem(Clase item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre() + " | " + item.getRol() + " | " + item.getHoraInicio() + " - " + item.getHoraFin());
            }
        });

        if (existente != null) {
            Clase plantilla = buscarPlantilla(plantillas, existente);
            if (plantilla != null) {
                comboPlantillas.getSelectionModel().select(plantilla);
            }
            dpFecha.setValue(existente.getFecha());
        } else {
            comboPlantillas.getSelectionModel().select(0);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        grid.add(new Label("Clase base:"), 0, 0);
        grid.add(comboPlantillas, 1, 0);
        grid.add(new Label("Fecha:"), 0, 1);
        grid.add(dpFecha, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(button -> {
            if (button != guardar) {
                return null;
            }

            Clase plantilla = comboPlantillas.getValue();
            LocalDate fecha = dpFecha.getValue();

            if (plantilla == null || fecha == null) {
                DialogoPersonalizado.mostrar("Error", "Selecciona la clase base y la fecha.");
                return null;
            }

            return new Clase(
                    plantilla.getNombre(),
                    plantilla.getRol(),
                    fecha,
                    plantilla.getHoraInicio(),
                    plantilla.getHoraFin()
            );
        });

        return dialog.showAndWait();
    }

    private Clase buscarPlantilla(List<Clase> plantillas, Clase existente) {
        for (Clase c : plantillas) {
            if (c.getNombre().equals(existente.getNombre())
                    && c.getRol().equals(existente.getRol())
                    && c.getHoraInicio().equals(existente.getHoraInicio())
                    && c.getHoraFin().equals(existente.getHoraFin())) {
                return c;
            }
        }
        return null;
    }
}