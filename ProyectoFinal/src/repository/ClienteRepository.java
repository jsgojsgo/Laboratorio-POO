package repository;

import model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private static final String RUTA = "resources/BaseDatos.csv";
    public List<Cliente> cargarClientes() {
        List<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            br.readLine(); //saltar primera linea

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

                Cliente cliente = new Cliente(nombre, matricula);
                cliente.setMembresia(new Membresia(tipo));
                cliente.setRenovacionAutomatica(renovacion);
                cliente.setDiaRenovacion(dia);
                cliente.setUltimoAcceso(ultimo);
                cliente.setFrecuente(frecuente);
                lista.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}