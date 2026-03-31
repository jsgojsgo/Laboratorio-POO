package practica11;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPrincipal {

    private Stage stage;
    public VentanaPrincipal(Stage stage) {
        this.stage = stage;
    }
    public void mostrar() {

        //menú
        BotonHover btnNuevaVenta = new BotonHover("Registrar venta");
        BotonHover btnModificarVenta = new BotonHover("Modificar venta");
        BotonHover btnConsultarVenta = new BotonHover("Consultar ventas");
        BotonHover btnSalir = new BotonHover("Salir");

        //eventos
        btnNuevaVenta.setOnAction(e -> {
            VistaVenta vista = new VistaVenta(stage);
            vista.mostrar();
        });

        btnModificarVenta.setOnAction(e -> {
            VistaModificar vista = new VistaModificar(stage);
            vista.mostrar();
        });

        btnConsultarVenta.setOnAction(e -> {
            VistaConsulta vista = new VistaConsulta(stage);
            vista.mostrar();
        });

        btnSalir.setOnAction(e -> {
            stage.close();
        });
        
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
                btnNuevaVenta,
                btnModificarVenta,
                btnConsultarVenta,
                btnSalir
        );
        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("Formato.css").toExternalForm());
        stage.setTitle("Sistema de Ventas");
        stage.setScene(scene);
        stage.show();
    }
}