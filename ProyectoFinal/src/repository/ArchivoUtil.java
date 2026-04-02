package repository;

import java.io.*;

public class ArchivoUtil {

    public static void escribirLinea(String ruta, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(contenido);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}