package practica8.pkg2;

class Automovil extends TransporteTerrestre{

    private double precio;
    public Automovil(String modelo, String marca, double precio) {
        super(modelo, marca);
        this.precio = precio;
    }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void avanzar(int gas) {
        System.out.println("El automóvil avanza con " + gas + " unidades de combustible. (@Normal)");
    }
    @Override
    public void encender(int gas) {
        System.out.println("Automóvil encendido con " + gas + " unidades de combustible. (@Override)");
    }
    @Override
    @Deprecated
    public String apagar() {
        return "Automóvil apagado. (@Override @Deprecated)";
    }
}