package practica11;

public class Producto {
    private String nombre, descripcion;
    private double precio;

    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return nombre + " ($" + precio + ")";
    }
}