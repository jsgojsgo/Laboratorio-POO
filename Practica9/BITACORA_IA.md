# BITACORA IA

**Prompt 1:** explícame y dame un ejemplo de cómo hacer manejo de archivos en java

**Respuesta 1:**
Tipos básicos de manejo de archivos en Java

En Java, los archivos se pueden manejar de varias formas dependiendo de si quieres leer/escribir texto o bytes:

a) Archivos de texto
Clases comunes: FileReader, FileWriter, BufferedReader, BufferedWriter
Permiten leer o escribir texto línea por línea o carácter por carácter.
b) Archivos binarios
Clases comunes: FileInputStream, FileOutputStream, DataInputStream, DataOutputStream
Permiten leer o escribir datos en bytes, útil para imágenes, audio o datos binarios.

Ejemplo práctico:
```
import java.io.*;

public class ManejarArchivo{
    public static void main(String[] args) {
        String nombreArchivo = "ejemplo.txt";

        // Escribir en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            bw.write("Hola, este es un ejemplo de manejo de archivos en Java.");
            bw.newLine();
            bw.write("Esta es la segunda línea.");
            System.out.println("Archivo escrito exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }

        // Leer del archivo
        try (BufferedReader bf = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            System.out.println("\nContenido del archivo:");
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
```
**Prompt 2:** y directorios?

**Respuesta 2:**
1. Usando java.io.File: La clase File no solo representa archivos, también representa directorios.
2. Usando java.nio.file (moderno y recomendado)

Ejemplo:
```
import java.nio.file.*;
import java.io.IOException;

public class ManejoDirectoriosNIO {
    public static void main(String[] args) {
        Path dir = Paths.get("MiDirectorioNIO");

        // Crear directorio
        try {
            if (!Files.exists(dir)) {
                Files.createDirectory(dir); // o createDirectories() si quieres padres también
                System.out.println("Directorio creado: " + dir);
            }
        } catch (IOException e) {
            System.out.println("Error al crear directorio: " + e.getMessage());
        }

        // Listar contenido
        System.out.println("\nContenido del directorio:");
        try {
            Files.list(dir).forEach(p -> System.out.println(p.getFileName()));
        } catch (IOException e) {
            System.out.println("Error al listar directorio: " + e.getMessage());
        }

        // Eliminar directorio
        try {
            Files.delete(dir); // solo si está vacío
            System.out.println("\nDirectorio eliminado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al eliminar directorio: " + e.getMessage());
        }
    }
}
```
**Prompt 3:** qué es una clase serializable?
**Respuesta 3:**
En Java, serializar un objeto significa convertirlo en una secuencia de bytes para poder guardarlo en un archivo, enviarlo por red, o almacenarlo de alguna manera.
Ejemplo:
```
import java.io.Serializable;

public class Persona implements Serializable {
    // Identificador de versión para serialización
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;

    public Persona(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y setters...
}
```
**Prompt 4:** cómo se exportan datos en java, de un CSV hacia un txt?
**Respuesta 4:** 
```
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvATxt {
    public static void main(String[] args) {
        String rutaCsv = "numeros.csv"; // tu archivo CSV
        String rutaTxt = "numeros.txt"; // archivo TXT de salida

        try {
            // Abrimos CSV para leer
            BufferedReader br = new BufferedReader(new FileReader(rutaCsv));
            // Abrimos TXT para escribir
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaTxt));

            String linea;
            while ((linea = br.readLine()) != null) {
                // Opcional: reemplazar comas por espacios o tabs
                linea = linea.replace(",", "\t"); // aquí separo columnas con tab
                bw.write(linea);
                bw.newLine();
            }

            br.close();
            bw.close();
            System.out.println("CSV convertido a TXT correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
**Prompt 5:** Dame una lista de 100 datos aleatorios enteros en formato CSV
**Respuesta 5:**
532
87
412
294
678
159
743
905
321
88
412
765
234
678
901
56
345
789
123
456
987
210
333
777
654
432
289
100
543
876
234
567
890
345
678
901
234
567
890
123
456
789
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890
345
678
901
234
567
890