package util;

import javafx.scene.control.TextField;

public class ValidacionTextField {

    public static boolean esVacio(TextField campo) {
        return campo.getText() == null || campo.getText().trim().isEmpty();
    }
    public static boolean esNumero(TextField campo) {
        return campo.getText().matches("\\d+");
    }
    public static boolean longitudExacta(TextField campo, int longitud) {
        return campo.getText().length() == longitud;
    }
}