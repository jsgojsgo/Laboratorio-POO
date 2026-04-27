package util;

import java.io.File;

public class RutaArchivos {

    private RutaArchivos() {
    }

    public static File archivoEnResources(String nombreArchivo) {
        File directo = new File("src/resources", nombreArchivo);
        if (directo.exists()) {
            return directo;
        }

        File relativo = new File(nombreArchivo);
        if (relativo.exists()) {
            return relativo;
        }

        return directo;
    }
}