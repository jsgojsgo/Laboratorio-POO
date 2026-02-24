package actividad4;
public class Television extends DispositivoElectronico implements Ifunciones {

    private String color;

    public Television(String marca, String modelo, String color) {
        super(marca, modelo);
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    @Override
    public String cambioCanal() {
        return "Cambio de canal exitoso";
    }

    @Override
    public String volumen() {
        return "Volumen ajustado";
    }

    public String confMenu() {
        return "Configuración de Menú";
    }
    public void mostrarInformacion() {
        System.out.println("\nTelevisión: "+getMarca());
        System.out.println("Modelo: "+getModelo());
        System.out.println("Color: "+color);
        boolean estabaEncendido = isEncendido();
        if (!estabaEncendido) {
            encender();
        } else {
            System.out.println("Dispositivo Encendido");
        }
        System.out.println(cambioCanal());
        System.out.println(confMenu());
        apagar();
      }
}