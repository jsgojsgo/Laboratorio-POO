package practica9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ManejoArchivos implements Serializable {
    private static final long serialVersionUID = 1L; //identficador de versión
    private List<String> contenido; //almacenar líneas
    public ManejoArchivos() {
        contenido = new ArrayList<>();
    }
    public void crearArchivo(String ruta) {
        File archivo = new File(ruta);
        try {
            if (archivo.createNewFile()) {
                System.out.println("\nArchivo creado correctamente: " + ruta);
            } else {
                System.out.println("\nEl archivo ya existe: " + ruta);
            }
        } catch (IOException e) {
            System.out.println("\nError al crear el archivo: " + e.getMessage());
        }
    }

    public void leerArchivo(String ruta){ 
        contenido.clear(); //borrar contenido anterior
        try(BufferedReader leer = new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = leer.readLine())!=null){
                contenido.add(linea);
                System.out.println(linea);
            }
        }catch(IOException e){
            System.out.println("\nError al leer el archivo: " + e.getMessage());
        }
    }

    public void escribirArchivo(String ruta, String texto) {
        try(BufferedWriter escribir = new BufferedWriter(new FileWriter(ruta, true))){ // append
            escribir.write(texto);
            escribir.newLine();
            contenido.add(texto);
        } catch(IOException e) {
            System.out.println("\nError al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public void guardarArchivo(String archivo){//.ser
        try(ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(archivo))) {
            escribir.writeObject(this);
            System.out.println("Objeto guardado correctamente en " + archivo);
        } catch(IOException e) {
            System.out.println("\nError al guardar el objeto: " + e.getMessage());
        }
    }

    public static ManejoArchivos cargarArchivo(String archivo){//.ser
        try(ObjectInputStream leer = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ManejoArchivos) leer.readObject(); //regresar objeto ManejoArchivos para poder continuar
        } catch(IOException|ClassNotFoundException e) {
            System.out.println("\nError al cargar el objeto: " + e.getMessage());
            return null;
        }
    }
    
    public void procesarCSV(String nombreArchivo) {
        String nombreTXT = nombreArchivo.replace(".csv", ".txt");

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(nombreTXT))) {
            String linea;
            double suma = 0, multiplicacion = 1;
            int cantidad = 0;
            bw.write("\nElementos: ");
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                bw.newLine();

                for (String valor : valores) {
                    try {
                        double numero = Double.parseDouble(valor.trim());
                        bw.write(numero + " ");
                        suma += numero;
                        multiplicacion *= numero;
                        cantidad++;
                    } catch (NumberFormatException e) {
                        bw.newLine();
                    }
                }
                bw.newLine();
            }

            if (cantidad > 0) {
                double promedio = suma / cantidad;
                bw.write("\nResultados del CSV:");
                bw.newLine();
                bw.write("Suma: " + suma);
                bw.newLine();
                bw.write("Promedio: " + promedio);
                bw.newLine();
                bw.write("Multiplicación: " + multiplicacion);
                bw.newLine();
            } else {
                bw.write("\nNo se encontraron números en el archivo CSV.");
                bw.newLine();
            }

            System.out.println("Archivo TXT creado: " + nombreTXT);

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
    
    public void backupArchivo(String rutaOriginal) {
           File archivo = new File(rutaOriginal);
           if (!archivo.exists()) {
               System.out.println("No existe el archivo para hacer backup: " + rutaOriginal);
               return;
           }

           String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
           String rutaBackup = rutaOriginal + "_backup_" + timestamp;

           try {
               Files.copy(Paths.get(rutaOriginal), Paths.get(rutaBackup));
               System.out.println("Backup creado: " + rutaBackup);
           } catch (IOException e) {
               System.out.println("Error al crear backup: " + e.getMessage());
           }
       }
  }