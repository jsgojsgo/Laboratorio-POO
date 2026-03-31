package practica11;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogoPersonalizado {

    public static boolean mostrar(String mensaje) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Confirmación");

        Label lbl = new Label(mensaje);

        Button btnOk = new Button("OK");
        Button btnCancelar = new Button("Cancelar");

        final boolean[] resultado = {false};

        btnOk.setOnAction(e -> {
            resultado[0] = true;
            dialog.close();
        });

        btnCancelar.setOnAction(e -> dialog.close());

        VBox root = new VBox(10, lbl, btnOk, btnCancelar);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 250, 150);
        dialog.setScene(scene);
        dialog.showAndWait();

        return resultado[0];
    }
}