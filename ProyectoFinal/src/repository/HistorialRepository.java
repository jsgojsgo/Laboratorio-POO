package repository;

import model.Acceso;
import util.RutaArchivos;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialRepository {

    private static final String ARCHIVO = "Historial.csv";

    public List<Acceso> cargarHistorial() {
        File archivo = RutaArchivos.archivoEnResources(ARCHIVO);

        if (!archivo.exists() || archivo.length() == 0L) {
            return new ArrayList<Acceso>();
        }

        return cargarDesdeArchivo(archivo);
    }

    public void registrarAcceso(Acceso acceso) {
        List<Acceso> lista = cargarHistorial();
        lista.add(acceso);
        guardarHistorial(lista);
    }

    public void guardarHistorial(List<Acceso> lista) {
        File archivo = RutaArchivos.archivoEnResources(ARCHIVO);

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            bw.write("matricula,fecha_hora_entrada");
            bw.newLine();

            for (Acceso a : lista) {
                bw.write(
                        (a.getMatricula() == null ? "" : a.getMatricula()) + "," +
                        (a.getFechaHoraEntrada() == null ? "" : a.getFechaHoraEntrada().toString())
                );
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Acceso> cargarDesdeArchivo(File archivo) {
        List<Acceso> lista = new ArrayList<Acceso>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] d = linea.split(",");
                if (d.length < 2) {
                    continue;
                }

                LocalDateTime fecha;
                try {
                    fecha = LocalDateTime.parse(d[1].trim());
                } catch (Exception ex) {
                    fecha = LocalDateTime.now();
                }

                lista.add(new Acceso(d[0].trim(), fecha));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void generarReporteEntradas(String rutaTxt) {
        List<Acceso> lista = cargarHistorial();

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(rutaTxt), StandardCharsets.UTF_8))) {

            bw.write("HISTORIAL DE ENTRADAS");
            bw.newLine();
            bw.newLine();

            for (Acceso a : lista) {
                bw.write((a.getMatricula() == null ? "" : a.getMatricula())
                        + " | "
                        + (a.getFechaHoraEntrada() == null ? "" : a.getFechaHoraEntrada().toString()));
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}