package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializacionUtil {
    
    private SerializacionUtil() {
    }
    public static <T extends Serializable> void guardarLista(List<T> lista, File archivo) {
        if (lista == null || archivo == null) {
            return;
        }
        File padre = archivo.getParentFile();
        if (padre != null && !padre.exists()) {
            padre.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(new ArrayList<T>(lista));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked") //IMPORTANTE EN BITACORA IA ANTES DE SUBIR
    public static <T> List<T> cargarLista(File archivo) {
        if (archivo == null || !archivo.exists() || archivo.length() == 0L) {
            return new ArrayList<T>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return (List<T>) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<T>();
    }
}