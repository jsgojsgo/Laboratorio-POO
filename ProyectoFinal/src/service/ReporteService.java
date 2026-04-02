package service;

import model.Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReporteService {

    public void generarReporteClientes(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("resources/Reporte.txt"))) {
            for (Cliente c : clientes) {
                bw.write(c.getNombre() + " - " + c.getMatricula());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}