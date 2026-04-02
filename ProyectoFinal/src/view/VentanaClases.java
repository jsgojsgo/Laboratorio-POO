package view;

import controller.GestionClase;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Clase;
import model.Cliente;
import util.BotonHover;
import util.DialogoPersonalizado;
import util.FiltroBusqueda;

import java.util.ArrayList;

public class VentanaClases {

    private Stage stage;
    private Cliente cliente;

    public VentanaClases(Stage stage, Cliente cliente) {
        this.stage = stage;
        this.cliente = cliente;
    }

    public void mostrar() {
        GestionClase gc = new GestionClase();

        TableView<Clase> tabla = new TableView<>();
        TableColumn<Clase, String> nombreCol = new TableColumn<>("Clase");
        nombreCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNombre()));
        tabla.getColumns().add(nombreCol);
        
        TextField filtro = new TextField();
        filtro.setPromptText("Buscar clase...");
        
        FilteredList<Clase> lista = new FilteredList<>(
                FXCollections.observableArrayList(gc.obtenerClases()), p -> true
        );
        new FiltroBusqueda<Clase>().aplicarFiltro(filtro,tabla,lista,
                c -> c.getNombre().toLowerCase().contains(filtro.getText().toLowerCase())
        );
        tabla.setItems(lista);
        
        Button agregar = new Button("Agregar clase");
        Button asistir = new Button("Asistir");
        Button cancelar = new Button("Cancelar");
        Button regresar = new Button("Regresar");

        BotonHover.aplicar(agregar);
        BotonHover.aplicar(asistir);
        BotonHover.aplicar(cancelar);
        BotonHover.aplicar(regresar);
        
        asistir.setOnAction(e -> {
            Clase c = tabla.getSelectionModel().getSelectedItem();
            if (c != null && gc.inscribir(cliente, c)) {
                DialogoPersonalizado.mostrar("OK", "Registrado");
            }
        });
        cancelar.setOnAction(e -> {
            Clase clase = tabla.getSelectionModel().getSelectedItem();
            if (clase != null) {
                Clase nuevaClase = gc.crearClase(clase.getNombre(), clase.getCoach(), diaSeleccionado, clase.getHoraInicio(), clase.getHoraFin(), 40);
                lista.getSource().add(nuevaClase);
                tabla.refresh();

                DialogoPersonalizado.mostrar("OK", "Clase agregada correctamente");
            }
        });
        agregar.setOnAction(e -> {
            try {
                ArrayList<Clase> predefinidas = gc.obtenerClasesPredefinidas();
                ComboBox<String> comboClases = new ComboBox<>();
                for (Clase c : predefinidas) {
                    comboClases.getItems().add(c.getNombre());
                }
                comboClases.setPromptText("Selecciona una clase");
                
                ComboBox<String> comboDias = new ComboBox<>();
                comboDias.getItems().addAll("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
                comboDias.setPromptText("Selecciona el día");
                
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Agregar Clase");
                VBox dialogVBox = new VBox(10);
                dialogVBox.getChildren().addAll(
                        new Label("Clase:"), comboClases,
                        new Label("Día:"), comboDias
                );
                dialog.getDialogPane().setContent(dialogVBox);

                ButtonType okButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

                dialog.showAndWait().ifPresent(response -> {
                    if (response == okButton) {
                        String claseSeleccionada = comboClases.getValue();
                        String diaSeleccionado = comboDias.getValue();

                        if (claseSeleccionada == null || diaSeleccionado == null) {
                            DialogoPersonalizado.mostrar("Error", "Debes seleccionar una clase y un día");
                            return;
                        }
                        Clase clase = predefinidas.stream()
                                .filter(cl -> cl.getNombre().equals(claseSeleccionada))
                                .findFirst()
                                .orElse(null);

                        if (clase != null) {
                            gc.crearClase(clase.getNombre(),clase.getCoach(), diaSeleccionado, clase.getHoraInicio(), clase.getHoraFin(), 40
                            );
                            DialogoPersonalizado.mostrar("OK", "Clase agregada correctamente");
                        }
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                DialogoPersonalizado.mostrar("Error", "No se pudo agregar la clase");
            }
        });
        regresar.setOnAction(e -> new VentanaSocioValidado(stage, cliente).mostrar());
        VBox root = new VBox(10, filtro, tabla, agregar, asistir, cancelar, regresar);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(
                getClass().getResource("/resources/Formato.css").toExternalForm()
        );
        stage.setScene(scene);
    }
}