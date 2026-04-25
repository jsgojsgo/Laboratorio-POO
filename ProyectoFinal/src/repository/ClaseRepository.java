package repository;

import model.Clase;
import util.RutaArchivos;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClaseRepository {

    private static final String ARCHIVO_HISTORIAL = "HistorialClases.csv";
    private static final String ARCHIVO_BASE = "BaseDatosClases.csv";

    public List<Clase> cargarClasesBase() {
        return cargarDesdeArchivo(RutaArchivos.archivoEnResources(ARCHIVO_BASE));
    }

    public List<Clase> cargarClases() {
        File archivo = RutaArchivos.archivoEnResources(ARCHIVO_HISTORIAL);

        if (archivo.exists() && archivo.length() > 0L) {
            return cargarDesdeArchivo(archivo);
        }

        List<Clase> base = cargarClasesBase();
        guardarClases(base);
        return base;
    }

    public boolean agregarClase(Clase clase) {
        List<Clase> lista = cargarClases();
        lista.add(clase);
        guardarClases(lista);
        return true;
    }

    public boolean modificarClase(Clase original, Clase nueva) {
        List<Clase> lista = cargarClases();
        int indice = indiceClase(lista, original);

        if (indice < 0) {
            return false;
        }

        lista.set(indice, nueva);
        guardarClases(lista);
        return true;
    }

    public boolean eliminarClase(Clase clase) {
        List<Clase> lista = cargarClases();
        int indice = indiceClase(lista, clase);

        if (indice < 0) {
            return false;
        }

        lista.remove(indice);
        guardarClases(lista);
        return true;
    }

    public void guardarClases(List<Clase> clases) {
        File archivo = RutaArchivos.archivoEnResources(ARCHIVO_HISTORIAL);

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            bw.write("nombre,rol,fecha,hora_inicio,hora_fin");
            bw.newLine();

            for (Clase c : clases) {
                bw.write(
                        limpio(c.getNombre()) + "," +
                        limpio(c.getRol()) + "," +
                        (c.getFecha() == null ? "" : c.getFecha().toString()) + "," +
                        limpio(c.getHoraInicio()) + "," +
                        limpio(c.getHoraFin())
                );
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Clase> cargarDesdeArchivo(File archivo) {
        List<Clase> lista = new ArrayList<Clase>();

        if (archivo == null || !archivo.exists()) {
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

                if (d.length < 5) {
                    continue;
                }

                LocalDate fecha = null;
                try {
                    if (!d[2].trim().isEmpty()) {
                        fecha = LocalDate.parse(d[2].trim());
                    }
                } catch (Exception ex) {
                    fecha = null;
                }

                lista.add(new Clase( d[0].trim(), d[1].trim(), fecha, d[3].trim(), d[4].trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private int indiceClase(List<Clase> lista, Clase buscada) {
        if (buscada == null) {
            return -1;
        }

        for (int i = 0; i < lista.size(); i++) {
            Clase c = lista.get(i);
            if (mismaClase(c, buscada)) {
                return i;
            }
        }
        return -1;
    }

    private boolean mismaClase(Clase a, Clase b) {
        if (a == null || b == null) {
            return false;
        }

        String fa = a.getFecha() == null ? "" : a.getFecha().toString();
        String fb = b.getFecha() == null ? "" : b.getFecha().toString();

        return limpio(a.getNombre()).equalsIgnoreCase(limpio(b.getNombre()))
                && limpio(a.getRol()).equalsIgnoreCase(limpio(b.getRol()))
                && fa.equals(fb)
                && limpio(a.getHoraInicio()).equalsIgnoreCase(limpio(b.getHoraInicio()))
                && limpio(a.getHoraFin()).equalsIgnoreCase(limpio(b.getHoraFin()));
    }

    private String limpio(String texto) {
        return texto == null ? "" : texto.trim();
    }
}