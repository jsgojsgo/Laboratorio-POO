    package practica3.pkg1;

    import java.util.Scanner;

    class Calculadora {
        private double x;
        private double y;

        public Calculadora(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public double sumar() {
            return x + y;
        }
        
        public double restar() {
            return x - y;
        }
        
        public double multiplicar() {
            return x * y;
        }
        
        public double dividir() {
            if(y != 0) {
                return x / y;
            } else {
                System.out.println("División: No se puede dividir entre 0");
                return 0;
            }
        }
    }

    public class Practica31 {

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            double x, y;

            System.out.print("x = ");
            x = input.nextDouble();
            System.out.print("y = ");
            y = input.nextDouble();

            Calculadora calculadora = new Calculadora(x, y);

            System.out.println("Suma: " + calculadora.sumar());
            System.out.println("Resta: " + calculadora.restar());
            System.out.println("Multiplicación: " + calculadora.multiplicar());
            System.out.println("División: " + calculadora.dividir());
        }
    }