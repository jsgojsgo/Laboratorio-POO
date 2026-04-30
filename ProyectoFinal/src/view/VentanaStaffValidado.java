package view;

import controller.GestionAcceso;
import controller.GestionCliente;
import controller.GestionInventario;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.util.Duration;
import model.Acceso;
import model.Cliente;
import model.Membresia;
import model.TipoMembresia;
import util.BotonHover;
import util.DialogoPersonalizado;
import util.FiltroBusqueda;
import service.SimuladorEntradasService;

import java.time.LocalDate;
import java.util.Optional;

public class VentanaStaffValidado {

    private final Stage stage;

    private GestionCliente gc;
    private GestionAcceso ga;
    private GestionInventario gi;

    private ObservableList<Cliente> baseClientes;
    private FilteredList<Cliente> filtradaClientes;
    private TableView<Cliente> tablaClientes;

    private ObservableList<Acceso> baseAccesos;
    private TableView<Acceso> tablaAccesos;

    public VentanaStaffValidado(Stage stage) {
        this.stage = stage;
    }

    public void mostrar() {
        gc = new GestionCliente();
        ga = new GestionAcceso();
        gi = new GestionInventario();

        Label titulo = new Label("¡Bienvenido de vuelta!");
        Label seleccionadoLbl = new Label("Seleccionado: ninguno");

        tablaClientes = new TableView<Cliente>();
        tablaAccesos = new TableView<Acceso>();

        construirTablaClientes();
        construirTablaAccesos();

        TextField filtro = new TextField();
        filtro.setPromptText("Filtrar clientes...");

        baseClientes = FXCollections.observableArrayList(gc.obtenerTodos());
        filtradaClientes = new FilteredList<Cliente>(baseClientes, c -> true);
        tablaClientes.setItems(filtradaClientes);

        new FiltroBusqueda<Cliente>().aplicarFiltro(
                filtro,
                tablaClientes,
                filtradaClientes,
                c -> contieneCliente(c, filtro.getText())
        );

        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            seleccionadoLbl.setText("Seleccionado: " + resumenCliente(newVal));
        });

        Button agregar = new Button("Agregar Cliente");
        Button modificar = new Button("Modificar Cliente");
        Button eliminar = new Button("Eliminar Cliente");
        Button membresia = new Button("Gestionar membresía");
        Button pago = new Button("Realizar pago");
        Button clases = new Button("Gestionar clases");
        Button inventario = new Button("Gestionar inventario");
        Button historialEntradas = new Button("Generar historial de entrada");
        Button regresar = new Button("Regresar");

        BotonHover.aplicar(agregar);
        BotonHover.aplicar(modificar);
        BotonHover.aplicar(eliminar);
        BotonHover.aplicar(membresia);
        BotonHover.aplicar(pago);
        BotonHover.aplicar(clases);
        BotonHover.aplicar(inventario);
        BotonHover.aplicar(historialEntradas);
        BotonHover.aplicar(regresar);

        agregar.setOnAction(e -> {
            Optional<Cliente> resultado = mostrarDialogoCliente(null);
            if (resultado.isPresent()) {
                try {
                    if (gc.agregar(resultado.get())) {
                        refrescarClientes();
                        DialogoPersonalizado.mostrar("Éxito", "Cliente agregado correctamente");
                    } else {
                        DialogoPersonalizado.mostrar("Error", "Ya existe un cliente con esa matrícula");
                    }
                } catch (Exception ex) {
                    DialogoPersonalizado.mostrar("Error", ex.getMessage());
                }
            }
        });

        modificar.setOnAction(e -> {
            Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                DialogoPersonalizado.mostrar("Error", "Selecciona un cliente para modificar");
                return;
            }

            Optional<Cliente> resultado = mostrarDialogoCliente(seleccionado);
            if (resultado.isPresent()) {
                try {
                    gc.modificar(seleccionado, resultado.get());
                    refrescarClientes();
                    DialogoPersonalizado.mostrar("Éxito", "Cliente modificado correctamente");
                } catch (Exception ex) {
                    DialogoPersonalizado.mostrar("Error", ex.getMessage());
                }
            }
        });

        eliminar.setOnAction(e -> {
            Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                DialogoPersonalizado.mostrar("Error", "Selecciona un cliente para eliminar");
                return;
            }

            if (DialogoPersonalizado.confirmar("¿Deseas eliminar al cliente seleccionado?")) {
                gc.eliminar(seleccionado);
                refrescarClientes();
                DialogoPersonalizado.mostrar("Éxito", "Cliente eliminado correctamente");
            }
        });

        membresia.setOnAction(e -> {
            Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                DialogoPersonalizado.mostrar("Error", "Selecciona un cliente");
                return;
            }

            new VentanaMembresias(stage, seleccionado, new Runnable() {
                @Override
                public void run() {
                    new VentanaStaffValidado(stage).mostrar();
                }
            }).mostrar();
        });

        pago.setOnAction(e -> {
            Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                DialogoPersonalizado.mostrar("Error", "Selecciona un cliente");
                return;
            }

            if (seleccionado.getMembresia() == null) {
                DialogoPersonalizado.mostrar("Error", "Primero asigna una membresía al cliente.");
                return;
            }

            double monto = seleccionado.getMembresia().getTipo().getMensualidad();

            new VentanaPago(stage, seleccionado, monto, "Pago de membresía", new Runnable() {
                @Override
                public void run() {
                    new VentanaStaffValidado(stage).mostrar();
                }
            }).mostrar();
        });

        clases.setOnAction(e -> new VentanaClases(stage, new Runnable() {
            @Override
            public void run() {
                new VentanaStaffValidado(stage).mostrar();
            }
        }).mostrar());

        inventario.setOnAction(e -> new VentanaInventario(stage, new Runnable() {
            @Override
            public void run() {
                new VentanaStaffValidado(stage).mostrar();
            }
        }).mostrar());

        historialEntradas.setOnAction(e -> {
            ga.generarReporteEntradas();
            DialogoPersonalizado.mostrar("Éxito", "ReporteEntradas.txt generado correctamente");
        });

        regresar.setOnAction(e -> new VentanaPrincipal(stage).mostrar());

        HBox filaCrud = new HBox(10, agregar, modificar, eliminar);
        filaCrud.setAlignment(Pos.CENTER);

        HBox filaAcciones = new HBox(10, membresia, pago, clases, inventario, historialEntradas);
        filaAcciones.setAlignment(Pos.CENTER);

        VBox root = new VBox(12, titulo, filtro, seleccionadoLbl, tablaClientes, filaCrud, filaAcciones, tablaAccesos, regresar);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1300, 800);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);

        iniciarRefrescoAutomatico();
        refrescarClientes();
        refrescarAccesos();
        SimuladorEntradasService.getInstancia().iniciar();
    }

    private void construirTablaClientes() {
        TableColumn<Cliente, String> colNombre = new TableColumn<Cliente, String>("Nombre");
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Cliente, String> colMatricula = new TableColumn<Cliente, String>("Matrícula");
        colMatricula.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMatricula()));

        TableColumn<Cliente, String> colMembresia = new TableColumn<Cliente, String>("Membresía");
        colMembresia.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getMembresia() == null ? "Sin plan" : data.getValue().getMembresia().getTipo().name()
        ));

        TableColumn<Cliente, String> colRenovacion = new TableColumn<Cliente, String>("Renovación Auto");
        colRenovacion.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isRenovacionAutomatica())));

        TableColumn<Cliente, String> colDia = new TableColumn<Cliente, String>("Día");
        colDia.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().isRenovacionAutomatica() ? safe(data.getValue().getDiaRenovacion()) : "N/A"
        ));

        TableColumn<Cliente, String> colDesde = new TableColumn<Cliente, String>("Desde");
        colDesde.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getFechaRegistro() == null ? "" : data.getValue().getFechaRegistro().toString()
        ));

        TableColumn<Cliente, String> colUltimo = new TableColumn<Cliente, String>("Último acceso");
        colUltimo.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getUltimoAcceso() == null ? "" : data.getValue().getUltimoAcceso().toString()
        ));

        TableColumn<Cliente, String> colFrecuente = new TableColumn<Cliente, String>("¿Frecuente?");
        colFrecuente.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().isFrecuente())));

        tablaClientes.getColumns().addAll(colNombre, colMatricula, colMembresia, colRenovacion, colDia, colDesde, colUltimo, colFrecuente);
    }

    private void construirTablaAccesos() {
        TableColumn<Acceso, String> colMatricula = new TableColumn<Acceso, String>("Matrícula");
        colMatricula.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMatricula()));

        TableColumn<Acceso, String> colFecha = new TableColumn<Acceso, String>("Fecha y hora");
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getFechaHoraEntrada() == null ? "" : data.getValue().getFechaHoraEntrada().toString()
        ));

        tablaAccesos.getColumns().addAll(colMatricula, colFecha);
    }

    private void refrescarClientes() {
        baseClientes.setAll(gc.obtenerTodos());
        tablaClientes.refresh();
    }

    private void refrescarAccesos() {
        baseAccesos = FXCollections.observableArrayList(ga.obtenerHistorial());
        tablaAccesos.setItems(baseAccesos);
    }

    private void iniciarRefrescoAutomatico() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            refrescarClientes();
            refrescarAccesos();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private boolean contieneCliente(Cliente c, String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return true;
        }

        String t = texto.toLowerCase().trim();

        return (c.getNombre() != null && c.getNombre().toLowerCase().contains(t))
                || (c.getMatricula() != null && c.getMatricula().toLowerCase().contains(t))
                || (c.getMembresia() != null && c.getMembresia().getTipo().name().toLowerCase().contains(t))
                || String.valueOf(c.isRenovacionAutomatica()).toLowerCase().contains(t)
                || (c.getDiaRenovacion() != null && c.getDiaRenovacion().toLowerCase().contains(t))
                || (c.getFechaRegistro() != null && c.getFechaRegistro().toString().contains(t))
                || (c.getUltimoAcceso() != null && c.getUltimoAcceso().toString().contains(t))
                || String.valueOf(c.isFrecuente()).toLowerCase().contains(t);
    }

    private Optional<Cliente> mostrarDialogoCliente(Cliente existente) {
        Dialog<Cliente> dialog = new Dialog<Cliente>();
        dialog.setTitle(existente == null ? "Agregar cliente" : "Modificar cliente");

        ButtonType guardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(guardar, ButtonType.CANCEL);

        TextField txtNombre = new TextField();
        TextField txtMatricula = new TextField();
        ComboBox<TipoMembresia> comboMembresia = new ComboBox<TipoMembresia>();
        CheckBox chkRenovacion = new CheckBox("Renovación automática");
        TextField txtDia = new TextField();
        CheckBox chkFrecuente = new CheckBox("Cliente frecuente");

        comboMembresia.getItems().addAll(TipoMembresia.values());
        txtNombre.setPromptText("Nombre completo");
        txtMatricula.setPromptText("Matrícula");
        txtDia.setPromptText("Día de renovación");

        if (existente != null) {
            txtNombre.setText(existente.getNombre());
            txtMatricula.setText(existente.getMatricula());
            txtMatricula.setDisable(true);
            comboMembresia.getSelectionModel().select(existente.getMembresia() == null ? TipoMembresia.STANDARD : existente.getMembresia().getTipo());
            chkRenovacion.setSelected(existente.isRenovacionAutomatica());
            txtDia.setText(existente.getDiaRenovacion() == null ? "" : existente.getDiaRenovacion());
            chkFrecuente.setSelected(existente.isFrecuente());
        } else {
            comboMembresia.getSelectionModel().select(TipoMembresia.STANDARD);
        }

        VBox box = new VBox(10,
                new Label("Nombre:"), txtNombre,
                new Label("Matrícula:"), txtMatricula,
                new Label("Membresía:"), comboMembresia,
                chkRenovacion,
                new Label("Día de renovación:"), txtDia,
                chkFrecuente
        );
        box.setPadding(new Insets(10));

        dialog.getDialogPane().setContent(box);

        dialog.setResultConverter(btn -> {
            if (btn != guardar) {
                return null;
            }

            if (txtNombre.getText().trim().isEmpty()) {
                DialogoPersonalizado.mostrar("Error", "El nombre es obligatorio");
                return null;
            }

            if (txtMatricula.getText().trim().isEmpty() || !txtMatricula.getText().trim().matches("\\d+")) {
                DialogoPersonalizado.mostrar("Error", "La matrícula debe contener solo números enteros");
                return null;
            }

            Cliente c = new Cliente(txtNombre.getText().trim(), txtMatricula.getText().trim());
            c.setMembresia(new Membresia(comboMembresia.getValue() == null ? TipoMembresia.STANDARD : comboMembresia.getValue()));
            c.setRenovacionAutomatica(chkRenovacion.isSelected());
            c.setDiaRenovacion(chkRenovacion.isSelected() ? txtDia.getText().trim() : null);
            c.setFrecuente(chkFrecuente.isSelected());

            if (existente != null) {
                c.setFechaRegistro(existente.getFechaRegistro());
                c.setUltimoAcceso(existente.getUltimoAcceso());
            } else {
                c.setFechaRegistro(LocalDate.now());
                c.setUltimoAcceso(LocalDate.now());
            }

            return c;
        });

        return dialog.showAndWait();
    }

    private String resumenCliente(Cliente c) {
        if (c == null) {
            return "ninguno";
        }
        return c.getNombre() + " - " + c.getMatricula();
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}