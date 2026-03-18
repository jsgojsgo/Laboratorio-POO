package practica8.pkg2;

@InfoClase(autor="Alumno de POO", version="1.0")

abstract class TransporteTerrestre {
    private String modelo, marca;
    public TransporteTerrestre(String modelo, String marca) {
        this.modelo = modelo;
        this.marca = marca;
    }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public void encender(int gas) {
        System.out.println("Encendiendo con " + gas + " unidades de combustible. (@Normal)");
    }

    @Deprecated
    public String apagar() {
        return "Transporte apagado (@Deprecated)";
    }
}