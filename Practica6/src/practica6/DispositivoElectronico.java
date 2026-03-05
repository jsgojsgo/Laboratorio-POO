package practica6;

public class DispositivoElectronico {
    private String marca, modelo;

    public DispositivoElectronico(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }
    public void encender() {
        System.out.println("\nEncender");
    }
    public String apagar() {
        return "\nApagar";
    }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
}