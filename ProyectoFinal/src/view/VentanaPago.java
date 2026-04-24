package view;

import controller.GestionPago;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cliente;
import model.Pago;
import util.BotonHover;
import util.DialogoPersonalizado;

import java.time.YearMonth;

public class VentanaPago {

    private final Stage stage;
    private final Cliente cliente;
    private final double montoBase;
    private final String concepto;
    private final Runnable onRegresar;

    public VentanaPago(Stage stage, Cliente cliente, double montoBase, String concepto, Runnable onRegresar) {
        this.stage = stage;
        this.cliente = cliente;
        this.montoBase = montoBase;
        this.concepto = concepto;
        this.onRegresar = onRegresar;
    }

    public void mostrar() {
        GestionPago gestionPago = new GestionPago();

        Label titulo = new Label("Procesamiento de pago");
        Label descripcion = new Label(
                "Descubre los beneficios de registrar tu método de pago para autocobro.\n"
                        + "• Obtén descuentos exclusivos para clientes frecuentes.\n"
                        + "• Cambia de plan en cualquier momento.\n"
                        + "• Ya no más \"Se me olvidó pagar el gym\"."
        );

        double montoFinal = gestionPago.calcularTotal(cliente, montoBase);
        Label lblConcepto = new Label("Concepto: " + concepto);
        Label lblMonto = new Label("Monto a cobrar: $" + String.format("%.2f", montoFinal));

        TextField txtTitular = new TextField(cliente != null ? cliente.getNombre() : "");
        TextField txtTarjeta = new TextField();
        ComboBox<String> cbMes = new ComboBox<String>();
        ComboBox<String> cbAnio = new ComboBox<String>();
        TextField txtCP = new TextField();
        PasswordField txtCVV = new PasswordField();

        txtTitular.setPromptText("Nombre del titular");
        txtTarjeta.setPromptText("Número de tarjeta (16 dígitos)");
        txtCP.setPromptText("Código postal (5 dígitos)");
        txtCVV.setPromptText("CVV");

        cbMes.setPromptText("Mes");
        cbAnio.setPromptText("Año");

        for (int mes = 1; mes <= 12; mes++) {
            cbMes.getItems().add(String.format("%02d", mes));
        }

        int anioActual = YearMonth.now().getYear();
        for (int anio = anioActual; anio <= anioActual + 10; anio++) {
            cbAnio.getItems().add(String.valueOf(anio));
        }

        Button pagar = new Button("Pagar");
        Button regresar = new Button("Regresar");

        BotonHover.aplicar(pagar);
        BotonHover.aplicar(regresar);

        pagar.setOnAction(e -> {
            if (!gestionPago.validarNoVacio(txtTitular.getText())
                    || !gestionPago.validarNoVacio(txtTarjeta.getText())
                    || !gestionPago.validarNoVacio(txtCP.getText())
                    || !gestionPago.validarNoVacio(txtCVV.getText())
                    || cbMes.getValue() == null
                    || cbAnio.getValue() == null) {
                DialogoPersonalizado.mostrar("Error", "Completa todos los campos.");
                return;
            }

            if (!gestionPago.validarTarjeta(txtTarjeta.getText().trim())) {
                DialogoPersonalizado.mostrar("Error", "La tarjeta debe tener exactamente 16 dígitos.");
                return;
            }

            if (!txtCP.getText().trim().matches("\\d{5}")) {
                DialogoPersonalizado.mostrar("Error", "El código postal debe tener 5 dígitos.");
                return;
            }

            if (!txtCVV.getText().trim().matches("\\d{3}")) {
                DialogoPersonalizado.mostrar("Error", "El CVV debe tener 3 dígitos.");
                return;
            }

            int mes = Integer.parseInt(cbMes.getValue());
            int anio = Integer.parseInt(cbAnio.getValue());
            YearMonth expiracion = YearMonth.of(anio, mes);

            if (expiracion.isBefore(YearMonth.now())) {
                DialogoPersonalizado.mostrar("Error", "La fecha de expiración no puede estar vencida.");
                return;
            }

            if (cliente == null) {
                DialogoPersonalizado.mostrar("Error", "No hay cliente seleccionado.");
                return;
            }

            String fechaExpiracion = cbMes.getValue() + "/" + cbAnio.getValue();

            Pago pago = new Pago(
                    txtTitular.getText().trim(),
                    txtTarjeta.getText().trim(),
                    fechaExpiracion,
                    txtCVV.getText().trim()
            );

            gestionPago.registrarPago(cliente, pago);
            DialogoPersonalizado.mostrar("Éxito", "Pago registrado correctamente. Total cobrado: $" + String.format("%.2f", montoFinal));

            if (onRegresar != null) {
                onRegresar.run();
            }
        });

        regresar.setOnAction(e -> {
            if (onRegresar != null) {
                onRegresar.run();
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Titular:"), 0, 0);
        grid.add(txtTitular, 1, 0);

        grid.add(new Label("Tarjeta:"), 0, 1);
        grid.add(txtTarjeta, 1, 1);

        grid.add(new Label("Expiración:"), 0, 2);
        grid.add(cbMes, 1, 2);
        grid.add(cbAnio, 2, 2);

        grid.add(new Label("Código postal:"), 0, 3);
        grid.add(txtCP, 1, 3);

        grid.add(new Label("CVV:"), 0, 4);
        grid.add(txtCVV, 1, 4);

        VBox root = new VBox(12, titulo, descripcion, lblConcepto, lblMonto, grid, pagar, regresar);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 760, 520);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);
    }
}