package practica6;

public class Telefono extends DispositivoElectronico implements IWifiConexion {

    private String precio;

    public Telefono(String marca, String modelo, String precio) {
        super(marca, modelo);
        this.precio = precio;
    }
    public void iniciarLlamada(String numero) {
        System.out.println("Iniciar Llamada " + numero);
    }
    public String iniciarLlamada(Contacto contacto) {
        return "\nIniciar Llamada " + contacto.getNombre();
    }
    public String finalizarLlamada() {
        return "Finalizar Llamada";
    }
    @Override
    public String cambioCanal() {
        return "Conexion WIFI";
    }
    @Override
    public String volumen() {
        return "Volumen";
    }
}