package repository;

import model.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    public List<Cliente> cargarClientes() {
        List<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/resources/BaseDatos.csv")
                )
        )) {

            String linea;
            br.readLine(); //primera linea
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String matricula = datos[1];
               
                TipoMembresia tipo = TipoMembresia.valueOf(datos[2]);
                boolean renovacion = Boolean.parseBoolean(datos[3]);
                String dia = datos[4].isEmpty() ? null : datos[4];
                LocalDate registro = LocalDate.parse(datos[5]);
                LocalDate ultimo = LocalDate.parse(datos[6]);
                boolean frecuente = Boolean.parseBoolean(datos[7]);

                Cliente c = new Cliente(nombre, matricula);
                c.setMembresia(new Membresia(tipo));
                c.setRenovacionAutomatica(renovacion);
                c.setDiaRenovacion(dia);
                c.setUltimoAcceso(ultimo);
                c.setFrecuente(frecuente);

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}