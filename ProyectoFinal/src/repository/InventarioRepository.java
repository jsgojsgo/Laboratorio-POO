package repository;

import model.Inventario;
import util.RutaArchivos;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InventarioRepository {

    private static final String ARCHIVO = "BaseDatosInventario.csv";

    public List<Inventario> cargarInventario() {
        File archivo = RutaArchivos.archivoEnResources(ARCHIVO);
        List<Inventario> lista = new ArrayList<Inventario>();

        if (!archivo.exists() || archivo.length() == 0L) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] d = linea.split(",", -1);
                if (d.length < 4) {
                    continue;
                }

                int stock = 0;
                try {
                    stock = Integer.parseInt(d[3].trim());
                } catch (Exception ex) {
                    stock = 0;
                }

                lista.add(new Inventario(d[0].trim(), d[1].trim(), d[2].trim(),stock));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}