package util;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.function.Predicate;

public class FiltroBusqueda<T> {
    public void aplicarFiltro(
            TextField txtBusqueda,
            TableView<T> tabla,
            FilteredList<T> listaFiltrada,
            Predicate<T> condicionBusqueda
    ) {
        txtBusqueda.textProperty().addListener((obs, oldVal, newVal) -> {

            listaFiltrada.setPredicate(item -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                return condicionBusqueda.test(item);
            });
        });
        SortedList<T> sorted = new SortedList<>(listaFiltrada);
        sorted.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sorted);
    }
}