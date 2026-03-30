package practica9;

import java.io.*;

public class ManejoArchivosBinarios{
    
    public static void escribirBinario(String ruta, byte[] datos) {
        try(FileOutputStream escribir = new FileOutputStream(ruta)) {
            escribir.write(datos);
        } catch (IOException e) {
            System.out.println("\nError escribiendo binario: " + e.getMessage());
        }
    }

public static byte[] leerBinario(String ruta) {
    File archivo = new File(ruta);
        try (FileInputStream escribir = new FileInputStream(archivo);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            byte[] temp = new byte[1024]; // buffer temporal
            int bytesLeidos;
            while ((bytesLeidos = escribir.read(temp)) != -1) {
                buffer.write(temp, 0, bytesLeidos);
            }
            return buffer.toByteArray();
        }catch(IOException e) {
            System.out.println("\nError leyendo binario: " + e.getMessage());
            return null;
        }
       }

}