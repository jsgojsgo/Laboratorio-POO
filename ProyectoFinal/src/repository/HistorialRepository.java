package repository;

import model.Acceso;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistorialRepository {

    private static final String RUTA = "resources/Historial.csv";

    public List<Acceso> cargarHistorial() {

        List<Acceso> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String matricula = datos[0];
                LocalDateTime fecha = LocalDateTime.parse(datos[1]);
                Acceso acceso = new Acceso(matricula);
                lista.add(acceso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public void guardarAcceso(Acceso acceso) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA, true))) {
            bw.write(acceso.getMatricula() + "," + acceso.getFechaHoraEntrada());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}