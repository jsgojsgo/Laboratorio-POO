package practica7.pkg2;
public abstract class Transporte {

    public abstract void encender();
    
    public String avanzar(int gasolina) {
        return "Avanza desde Transporte";
    }
    public void apagar() {
        System.out.println("Apagado");
    }
    public void listaMetodos() {
        System.out.println("Métodos de Transporte");
    }
}