
package actividad4;

public class DispositivoElectronico{

    private String marca;
    private String modelo;
    private boolean encendido;
    
    public DispositivoElectronico(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.encendido = false;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public boolean isEncendido() {
        return encendido;
    }
    public void encender() {
        encendido = true;
        System.out.println("Dispositivo Encendido");
    }
    public void apagar() {
        encendido = false;
        System.out.println("Dispositivo Apagado");
    }
}