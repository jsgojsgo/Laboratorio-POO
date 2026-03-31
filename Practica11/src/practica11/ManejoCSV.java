package practica11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejoCSV {

    //leer productos csv
    public static List<Producto> leerProductos(String ruta) {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); // saltar primera linea
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    double precio = Double.parseDouble(partes[1]);
                    productos.add(new Producto(partes[0], precio, partes[2]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    //guardar venta en csv
    public static void guardarVenta(String ruta, List<String> venta) {
        boolean existe = new File(ruta).exists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            if (!existe) {
                //escribir encabezado solo si no existe
                bw.write("total,platillo1,platillo2,platillo3,...");
                bw.newLine();
            }
            bw.write(String.join(",", venta));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //leer ventas csv
    public static List<String[]> leerVentas(String ruta) {
        List<String[]> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); //saltar primera linea
            while ((linea = br.readLine()) != null) {
                registros.add(linea.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registros;
    }

    //sobrescribir ventas
    public static void guardarTodasVentas(String ruta, List<String[]> ventas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("total,platillo1,platillo2,platillo3,...");
            bw.newLine();
            for (String[] v : ventas) {
                bw.write(String.join(",", v));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}