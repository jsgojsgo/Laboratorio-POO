package util;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogoPersonalizado {

    public static void mostrar(String titulo, String mensaje) {

        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle(titulo);
        Label lblMensaje = new Label(mensaje);
        Button btnCerrar = new Button("Aceptar");
        btnCerrar.setOnAction(e -> ventana.close());
        VBox root = new VBox(15, lblMensaje, btnCerrar);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 150);
        ventana.setScene(scene);
        ventana.showAndWait();
    }

    public static boolean confirmar(String mensaje) {

        final boolean[] respuesta = {false};
        Stage ventana = new Stage();
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle("Confirmación");
        Label lbl = new Label(mensaje);
        Button btnSi = new Button("Sí");
        Button btnNo = new Button("No");
        btnSi.setOnAction(e -> {
            respuesta[0] = true;
            ventana.close();
        });
        btnNo.setOnAction(e -> ventana.close());
        VBox root = new VBox(10, lbl, btnSi, btnNo);
        root.setAlignment(Pos.CENTER);
        ventana.setScene(new Scene(root, 300, 180));
        ventana.showAndWait();

        return respuesta[0];
    }
}