package view;

import controller.GestionAcceso;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cliente;
import util.BotonHover;
import util.DialogoPersonalizado;

public class VentanaSocioValidado {

    private final Stage stage;
    private final Cliente cliente;

    public VentanaSocioValidado(Stage stage, Cliente cliente) {
        this.stage = stage;
        this.cliente = cliente;
    }

    public void mostrar() {
        GestionAcceso ga = new GestionAcceso();

        Button acceso = new Button("Acceder al gimnasio");
        Button clases = new Button("Ver clases");
        Button membresia = new Button("Gestionar membresía");
        Button pagar = new Button("Realizar pago");
        Button regresar = new Button("Regresar");

        BotonHover.aplicar(acceso);
        BotonHover.aplicar(clases);
        BotonHover.aplicar(membresia);
        BotonHover.aplicar(pagar);
        BotonHover.aplicar(regresar);

        clases.setOnAction(e ->
                DialogoPersonalizado.mostrar("Información", "Contacta un personal del staff para continuar")
        );

        membresia.setOnAction(e ->
                new VentanaMembresias(stage, cliente, new Runnable() {
                    @Override
                    public void run() {
                        new VentanaSocioValidado(stage, cliente).mostrar();
                    }
                }).mostrar()
        );

        pagar.setOnAction(e -> {
            if (cliente.getMembresia() == null) {
                DialogoPersonalizado.mostrar("Error", "Primero selecciona una membresía.");
                return;
            }

            new VentanaPago(stage, cliente,
                    cliente.getMembresia().getTipo().getMensualidad(),
                    "Pago de membresía",
                    new Runnable() {
                        @Override
                        public void run() {
                            new VentanaSocioValidado(stage, cliente).mostrar();
                        }
                    }).mostrar();
        });

        acceso.setOnAction(e -> {
            boolean permitido = ga.permitirEntrada(cliente);
            if (permitido) {
                DialogoPersonalizado.mostrar("Acceso", "Bienvenido");
            } else {
                DialogoPersonalizado.mostrar("Error", "Membresía vencida");
            }
        });

        regresar.setOnAction(e -> new VentanaPrincipal(stage).mostrar());

        VBox root = new VBox(15, acceso, clases, membresia, pagar, regresar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 600, 420);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);
    }
}