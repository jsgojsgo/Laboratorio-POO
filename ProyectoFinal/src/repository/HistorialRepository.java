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

        java.util.Map<Integer, Integer> conteoPorHora = new java.util.TreeMap<Integer, Integer>();
        java.util.Set<String> clientesUnicos = new java.util.HashSet<String>();

        for (Acceso acceso : lista) {
            if (acceso == null || acceso.getFechaHoraEntrada() == null) {
                continue;
            }

            int hora = acceso.getFechaHoraEntrada().getHour();

            Integer actual = conteoPorHora.get(hora);
            conteoPorHora.put(hora, actual == null ? 1 : actual + 1);

            if (acceso.getMatricula() != null && !acceso.getMatricula().trim().isEmpty()) {
                clientesUnicos.add(acceso.getMatricula().trim());
            }
        }

        int horaPico = -1;
        int maximo = -1;

        for (java.util.Map.Entry<Integer, Integer> entry : conteoPorHora.entrySet()) {
            if (entry.getValue() > maximo) {
                maximo = entry.getValue();
                horaPico = entry.getKey();
            }
        }

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(rutaTxt), StandardCharsets.UTF_8))) {

            bw.write("REPORTE DE ENTRADAS");
            bw.newLine();
            bw.write("Total de entradas registradas: " + lista.size());
            bw.newLine();
            bw.write("Clientes únicos con acceso: " + clientesUnicos.size());
            bw.newLine();

            if (horaPico >= 0) {
                bw.write("Hora con mayor concurrencia: " + String.format("%02d:00", horaPico) + " (" + maximo + " entradas)");
            } else {
                bw.write("Hora con mayor concurrencia: N/A");
            }
            bw.newLine();

            bw.write("Entradas por hora:");
            bw.newLine();

            for (java.util.Map.Entry<Integer, Integer> entry : conteoPorHora.entrySet()) {
                bw.write(String.format("%02d:00 - %02d:59 -> %d", entry.getKey(), entry.getKey(), entry.getValue()));
                bw.newLine();
            }

            bw.newLine();
            bw.write("HISTORIAL DETALLADO");
            bw.newLine();
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
}