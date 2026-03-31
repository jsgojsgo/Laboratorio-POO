package practica11;

import javafx.scene.control.TextField;

public class ValidacionTextField extends TextField {

    public ValidacionTextField(String placeholder) {
        setPromptText(placeholder);

        textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                setStyle("-fx-border-color: red;");
            } else {
                setStyle("-fx-border-color: green;");
            }
        });
    }
}