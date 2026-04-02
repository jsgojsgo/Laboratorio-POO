package repository;

import model.Staff;

import java.io.*;
import java.util.*;

public class StaffRepository {

    public List<Staff> cargarStaff() {
        List<Staff> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/resources/BaseDatosStaff.csv")
                )
        )) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {

                String[] d = linea.split(",");

                lista.add(new Staff(d[0], d[1], d[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}