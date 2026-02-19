package practica3;

import java.util.Scanner;

public class Practica3 {

    public static void main(String[] args) {
        double x,y;
        Scanner input = new Scanner(System.in);
        
        System.out.println("x = ");
        x = input.nextDouble();
        System.out.println("y = ");
        y = input.nextDouble();
        
        System.out.println("Suma: " + (x+y));
        System.out.println("Resta: " + (x-y));
        System.out.println("Multiplicación: " + (x*y));
        if(y != 0) {
            System.out.println("División: " + (x/y));
        } else {
            System.out.println("División: No se puede dividir entre 0");
        }

    }
    
}
