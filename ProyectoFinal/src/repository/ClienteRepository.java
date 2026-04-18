package repository;

import model.Cliente;
import model.Membresia;
import model.TipoMembresia;
import util.RutaArchivos;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private static final String ARCHIVO = "BaseDatos.csv";

    public List<Cliente> cargarClientes() {
        File archivo = RutaArchivos.archivoEnResources(ARCHIVO);

        if (!archivo.exists() || archivo.length() == 0L) {
            return new ArrayList<Cliente>();
        }

        return cargarDesdeArchivo(archivo);
    }

    public Cliente buscarPorMatricula(String matricula) {
        if (matricula == null) {
            return null;
        }

        for (Cliente c : cargarClientes()) {
            if (c.getMatricula() != null && c.getMatricula().trim().equalsIgnoreCase(matricula.trim())) {
                return c;
            }
        }
        return null;
    }

    public boolean agregarCliente(Cliente cliente) {
        List<Cliente> lista = cargarClientes();

        for (Cliente c : lista) {
            if (c.getMatricula() != null && c.getMatricula().trim().equalsIgnoreCase(cliente.getMatricula().trim())) {
                return false;
            }
        }

        lista.add(cliente);
        guardarClientes(lista);
        return true;
    }

    public boolean actualizarCliente(Cliente original, Cliente nuevo) {
        List<Cliente> lista = cargarClientes();
        int indice = indicePorMatricula(lista, original == null ? null : original.getMatricula());

        if (indice < 0) {
            return false;
        }

        lista.set(indice, nuevo);
        guardarClientes(lista);
        return true;
    }

    public boolean eliminarCliente(Cliente cliente) {
        List<Cliente> lista = cargarClientes();
        int indice = indicePorMatricula(lista, cliente == null ? null : cliente.getMatricula());

        if (indice < 0) {
            return false;
        }

        lista.remove(indice);
        guardarClientes(lista);
        return true;
    }

    public void guardarClientes(List<Cliente> clientes) {
        File archivo = RutaArchivos.archivoEnResources(ARCHIVO);

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            bw.write("nombre,matricula,tipo_membresia,renovacion_automatica,dia_renovacion,fecha_registro,ultimo_acceso,frecuente");
            bw.newLine();

            for (Cliente c : clientes) {
                bw.write(
                        limpio(c.getNombre()) + "," +
                        limpio(c.getMatricula()) + "," +
                        tipo(c) + "," +
                        c.isRenovacionAutomatica() + "," +
                        limpio(c.getDiaRenovacion()) + "," +
                        fecha(c.getFechaRegistro()) + "," +
                        fecha(c.getUltimoAcceso()) + "," +
                        c.isFrecuente()
                );
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int indicePorMatricula(List<Cliente> lista, String matricula) {
        if (matricula == null) {
            return -1;
        }

        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            if (c.getMatricula() != null && c.getMatricula().trim().equalsIgnoreCase(matricula.trim())) {
                return i;
            }
        }
        return -1;
    }

    private List<Cliente> cargarDesdeArchivo(File archivo) {
        List<Cliente> lista = new ArrayList<Cliente>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] d = linea.split(",");
                if (d.length < 8) {
                    continue;
                }

                Cliente c = new Cliente(d[0].trim(), d[1].trim());
                c.setMembresia(new Membresia(parseTipo(d[2].trim())));
                c.setRenovacionAutomatica(parseBooleanFlexible(d[3]));
                c.setDiaRenovacion(limpio(d[4]));
                c.setFechaRegistro(parseFecha(d[5]));
                c.setUltimoAcceso(parseFecha(d[6]));
                c.setFrecuente(parseBooleanFlexible(d[7]));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private TipoMembresia parseTipo(String texto) {
        try {
            return TipoMembresia.valueOf(texto.trim());
        } catch (Exception e) {
            return TipoMembresia.STANDARD;
        }
    }

    private boolean parseBooleanFlexible(String texto) {
        if (texto == null) return false;
        String t = texto.trim().toLowerCase();
        return t.equals("true") || t.equals("sí") || t.equals("si") || t.equals("1") || t.equals("yes");
    }

    private LocalDate parseFecha(String texto) {
        try {
            String t = limpio(texto);
            return t.isEmpty() ? null : LocalDate.parse(t);
        } catch (Exception e) {
            return null;
        }
    }

    private String tipo(Cliente c) {
        if (c.getMembresia() == null || c.getMembresia().getTipo() == null) {
            return TipoMembresia.STANDARD.name();
        }
        return c.getMembresia().getTipo().name();
    }

    private String fecha(LocalDate fecha) {
        return fecha == null ? "" : fecha.toString();
    }

    private String limpio(String texto) {
        return texto == null ? "" : texto.trim();
    }
}