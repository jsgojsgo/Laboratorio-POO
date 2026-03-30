package practica9;
import java.nio.file.*;
import java.io.IOException;

public class ManejarDirectorios{
    public static void crearDirectorio(String directorio) {
        Path ruta = Paths.get(directorio);
        try {
            if (!Files.exists(ruta)) {
                Files.createDirectory(ruta); 
                System.out.println("Directorio creado: " + ruta);
            }
        }catch(IOException e){
            System.out.println("\nError al crear directorio: " + e.getMessage());
        }
    }
    
    public static void eliminarDirectorio(String directorio){
        Path ruta = Paths.get(directorio);
        try {
            Files.delete(ruta); //si está vacío
            System.out.println("\nDirectorio eliminado correctamente.");
        } catch(IOException e) {
            System.out.println("\nError al eliminar directorio: " + e.getMessage());
        }
    }
}
