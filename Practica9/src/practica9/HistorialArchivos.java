package practica9;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistorialArchivos{
    private static final String HISTORIAL = "historial.txt";
    
    public static void registrar(String nombreArchivo, String accion) {
        try (FileWriter escribir = new FileWriter(HISTORIAL, true);
             BufferedWriter bw = new BufferedWriter(escribir);
             PrintWriter leer = new PrintWriter(bw)) {

            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            leer.println(timestamp + " - " + accion + ": " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("\nError al registrar el historial: " + e.getMessage());
        }
    }
    public static void mostrarHistorial() {
        File archivo = new File(HISTORIAL);
        if (!archivo.exists()) {
            System.out.println("\nNo hay historial registrado.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(HISTORIAL))) {
            String linea;
            System.out.println("\nHistorial de archivos:");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer historial: " + e.getMessage());
        }
    }
}