package repository;

import model.Clase;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClaseRepository {
    private static final String RUTA = "HistorialClases.csv";
    
    public List<Clase> cargarClases() {
        List<Clase> lista = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream("/HistorialClases.csv")) {

            if (is == null) {
                throw new RuntimeException("No se encontró el archivo HistorialClases.csv en el classpath");
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String linea;
                br.readLine(); //primera linea no
                while ((linea = br.readLine()) != null) {
                    String[] d = linea.split(",");
                    lista.add(new Clase(d[0], d[1], d[2], d[3], d[4],
                        Integer.parseInt(d[5].trim())
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    public void guardarClase(String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}