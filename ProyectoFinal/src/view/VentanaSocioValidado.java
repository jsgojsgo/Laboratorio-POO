package view;

import controller.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cliente;
import util.DialogoPersonalizado;
import util.BotonHover;

public class VentanaSocioValidado {

    private Stage stage;
    private Cliente cliente;

    public VentanaSocioValidado(Stage stage, Cliente cliente) {
        this.stage = stage;
        this.cliente = cliente;
    }

    public void mostrar() {
        Button acceso = new Button("Acceder al gimnasio");
        Button clases = new Button("Ver clases");
        Button membresia = new Button("Gestionar membresía");
        Button regresar = new Button("Regresar");
        BotonHover.aplicar(acceso);
        BotonHover.aplicar(clases);
        BotonHover.aplicar(membresia);
        BotonHover.aplicar(regresar);
        GestionAcceso ga = new GestionAcceso();
        
        clases.setOnAction(e -> new VentanaClases(stage, cliente).mostrar());
        membresia.setOnAction(e -> new VentanaMembresias(stage, cliente).mostrar());
        acceso.setOnAction(e -> {
            boolean permitido = ga.permitirEntrada(cliente);

            if (permitido) {
                DialogoPersonalizado.mostrar("Acceso", "Bienvenido");
            } else {
                DialogoPersonalizado.mostrar("Error", "Membresía vencida");
            }
        });
        regresar.setOnAction(e -> new VentanaPrincipal(stage).mostrar());
        
        VBox root = new VBox(15, acceso, clases, membresia, regresar);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(
            getClass().getResource("/resources/Formato.css").toExternalForm()
        );
        stage.setScene(scene);
    }
}