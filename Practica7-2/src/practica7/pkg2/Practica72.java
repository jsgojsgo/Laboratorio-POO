package practica7.pkg2;

public class Practica72{

    public static void main(String[] args) {

        Automovil auto = new Automovil();
        auto.setMarca("Toyota");
        auto.setModelo("Corolla");
        auto.setColor("Rojo");
        auto.setPrecio("$300,000");

        auto.mostrar();
        auto.encender();
        System.out.println(auto.avanzar(10));
        auto.vuelta("Izquierda");
        auto.frenar();
        auto.apagar();

        System.out.println();

        Transporte tr = new Automovil();
        tr.encender();
        tr.listaMetodos();
        tr.apagar();

        System.out.println();
        
        IAutomovil iAuto = new Automovil();
        iAuto.mostrar();
    }
}
