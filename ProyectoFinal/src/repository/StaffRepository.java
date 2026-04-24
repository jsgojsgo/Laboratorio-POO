package repository;

import model.Staff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaffRepository {

    public List<Staff> cargarStaff() {
        List<Staff> lista = new ArrayList<Staff>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/resources/BaseDatosStaff.csv"), "UTF-8"))) {

            String linea;
            br.readLine(); //primera linea no

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] d = linea.split(",");
                if (d.length < 3) {
                    continue;
                }

                lista.add(new Staff(d[0].trim(), d[1].trim(), d[2].trim()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}