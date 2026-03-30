package practica9;
import java.util.Scanner;

public class Practica9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        ManejoArchivos archivo = new ManejoArchivos(); // objeto para archivos de texto

        do {
            System.out.print("\n\n\tMenu:\n1. Crear archivo\n2. Leer archivo\n3. Escribir en archivo\n4. Crear archivo binario\n5. Leer y procesar datos de archivo CSV\n6. Crear directorio\n7. Eliminar directorio\n 8.Ver historial\n0. Salir\n>> ");
            while (!sc.hasNextInt()) {
                System.out.print("\nTipo de dato incorrecto. Intente de nuevo: ");
                sc.next();
            }
            n = sc.nextInt();
            sc.nextLine();
            String nombreArchivo;
            switch(n){
                case 1:
                    System.out.print("\nIngrese el nombre del archivo a crear: ");
                    nombreArchivo = sc.nextLine();
                    archivo.crearArchivo(nombreArchivo);
                    HistorialArchivos.registrar(nombreArchivo, "CREADO");
                    break;
                case 2:
                    System.out.print("\nIngrese el nombre del archivo a leer: ");
                    nombreArchivo = sc.nextLine();
                    archivo.leerArchivo(nombreArchivo);
                    HistorialArchivos.registrar(nombreArchivo, "LEIDO");
                    break;
                case 3:
                    System.out.print("\nIngrese el nombre del archivo a modificar: ");
                    nombreArchivo = sc.nextLine();
                    System.out.print("\nIngrese el texto a agregar: ");
                    String texto = sc.nextLine();
                    archivo.escribirArchivo(nombreArchivo, texto);
                    HistorialArchivos.registrar(nombreArchivo, "MODIFICADO");
                    break;
                case 4:
                    System.out.print("\nIngrese el nombre del archivo binario a crear: ");
                    nombreArchivo = sc.nextLine();
                    System.out.print("\nIngrese texto a convertir a binario: ");
                    String binTexto = sc.nextLine();
                    ManejoArchivosBinarios.escribirBinario(nombreArchivo, binTexto.getBytes());
                    HistorialArchivos.registrar(nombreArchivo, "BINARIO CREADO");
                    break;
                case 5:
                    System.out.print("\nIngrese el archivo CSV a leer: ");
                    nombreArchivo = sc.nextLine();
                    archivo.procesarCSV(nombreArchivo);
                    HistorialArchivos.registrar(nombreArchivo, "CSV LEIDO");
                    break;
                case 6:
                    System.out.print("\nIngrese el nombre del directorio a crear: ");
                    nombreArchivo = sc.nextLine();
                    ManejarDirectorios.crearDirectorio(nombreArchivo);
                    HistorialArchivos.registrar(nombreArchivo, "DIRECTORIO CREADO");
                    break;

                case 7:
                    System.out.print("\nIngrese el nombre del directorio a eliminar: ");
                    nombreArchivo = sc.nextLine();
                    ManejarDirectorios.eliminarDirectorio(nombreArchivo);
                    HistorialArchivos.registrar(nombreArchivo, "DIRECTORIO ELIMINADO");
                    break;
                case 8:
                    HistorialArchivos.mostrarHistorial();
                    break;
                case 0:
                    break;
                default:
                    System.out.print("\nOpción inválida. Intente de nuevo.");
            }
        } while(n!= 0);

        sc.close();
    }
}