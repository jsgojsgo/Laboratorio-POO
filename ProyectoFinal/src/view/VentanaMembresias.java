package view;

import controller.GestionMembresia;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cliente;
import model.Membresia;
import model.TipoMembresia;
import util.BotonHover;
import util.DialogoPersonalizado;

public class VentanaMembresias {

    private final Stage stage;
    private final Cliente cliente;
    private final Runnable onRegresar;

    public VentanaMembresias(Stage stage, Cliente cliente) {
        this(stage, cliente, null);
    }

    public VentanaMembresias(Stage stage, Cliente cliente, Runnable onRegresar) {
        this.stage = stage;
        this.cliente = cliente;
        this.onRegresar = onRegresar;
    }

    public void mostrar() {
        GestionMembresia gm = new GestionMembresia();

        Label titulo = new Label("¡Bienvenido, estas son nuestras membresías!");
        Label actual = new Label(textoMembresiaActual());
        Label nota = new Label("Todos los planes cuentan con una anualidad de $800.00, generada en el tercer mes desde la contratación.");

        VBox standard = crearPlan(gm, TipoMembresia.STANDARD);
        VBox pro = crearPlan(gm, TipoMembresia.PRO);
        VBox ultra = crearPlan(gm, TipoMembresia.ULTRA);

        HBox cards = new HBox(15, standard, pro, ultra);
        cards.setAlignment(Pos.CENTER);

        Button regresar = new Button("Regresar");
        BotonHover.aplicar(regresar);
        regresar.setOnAction(e -> regresar());

        VBox root = new VBox(15, titulo, actual, nota, cards, regresar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 1100, 560);
        scene.getStylesheets().add(getClass().getResource("/resources/Formato.css").toExternalForm());
        stage.setScene(scene);
    }

    private VBox crearPlan(final GestionMembresia gm, final TipoMembresia tipo) {
        Label nombre = new Label(tipo.name());
        Label inscripcion = new Label("Inscripción: $" + tipo.getInscripcion());
        Label mensualidad = new Label("Mensualidad: $" + tipo.getMensualidad());
        Label contrato = new Label(tipo == TipoMembresia.PRO ? "Contrato: mes a mes" : "Contrato: 12 meses");
        Label beneficios = new Label(textoBeneficios(tipo));

        Button cambiar = new Button("Cambiar a este plan");
        Button pagar = new Button("Realizar pago");

        BotonHover.aplicar(cambiar);
        BotonHover.aplicar(pagar);

        boolean esActual = cliente != null
                && cliente.getMembresia() != null
                && cliente.getMembresia().getTipo() == tipo;

        if (esActual) {
            cambiar.setText("Membresía actual");
            cambiar.setDisable(true);
        } else {
            cambiar.setOnAction(e -> {
                gm.cambiar(cliente, tipo);
                DialogoPersonalizado.mostrar("Éxito", "Membresía cambiada a " + tipo.name());
                mostrar();
            });
        }

        pagar.setOnAction(e -> {
            if (cliente == null) {
                DialogoPersonalizado.mostrar("Error", "No hay cliente seleccionado.");
                return;
            }

            new VentanaPago(stage, cliente, tipo.getMensualidad(), "Pago de " + tipo.name(),
                    onRegresar != null ? onRegresar : new Runnable() {
                        @Override
                        public void run() {
                            new VentanaMembresias(stage, cliente).mostrar();
                        }
                    }).mostrar();
        });

        VBox card = new VBox(10, nombre, inscripcion, mensualidad, contrato, beneficios, cambiar, pagar);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.getStyleClass().add("card-oscura");
        return card;
    }

    private String textoMembresiaActual() {
        if (cliente == null || cliente.getMembresia() == null) {
            return "Membresía actual: sin membresía";
        }
        if (cliente.getMembresia().estaVencida()) {
            return "Membresía actual: " + cliente.getMembresia().getTipo().name() + " (vencida)";
        }
        return "Membresía actual: " + cliente.getMembresia().getTipo().name();
    }

    private String textoBeneficios(TipoMembresia tipo) {
        switch (tipo) {
            case STANDARD:
                return "Acceso a cualquier gym de la misma cadena, coaches, clases grupales y zona Standard";
            case PRO:
                return "Acceso al gym contratado, coaches, clases grupales y zonas Standard + Pro";
            case ULTRA:
                return "Acceso a cualquier gym de la misma cadena, coaches, profesionales y todas las zonas";
            default:
                return "";
        }
    }

    private void regresar() {
        if (onRegresar != null) {
            onRegresar.run();
        } else {
            new VentanaPrincipal(stage).mostrar();
        }
    }
}