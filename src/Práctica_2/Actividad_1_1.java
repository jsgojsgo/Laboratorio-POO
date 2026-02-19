package Práctica_2;
import java.util.Scanner;

public class Actividad_1_1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("\nIngresa un byte (ejemplo: 100): ");
        byte b = input.nextByte();
        
        System.out.print("\nIngresa un short (ejemplo: 32000): ");
        short s = input.nextShort();

        System.out.print("\nIngresa un int (ejemplo: 1000): ");
        int i = input.nextInt();
        
        System.out.print("\nIngresa un long (ejemplo: 10000000000): ");
        long l = input.nextLong();
        
        System.out.print("\nIngresa un float (ejemplo: 3.14): ");
        float f = input.nextFloat();
        
        System.out.print("Ingresa un double (ejemplo: 2.71828): ");
        double d = input.nextDouble();
        
        System.out.print("Ingresa un char (ejemplo: A): ");
        char c = input.next().charAt(0);
        
        System.out.print("Ingresa un boolean (ejemplo: true): ");
        boolean bo = input.nextBoolean();

        System.out.println("\nLos valores ingresados son:");
        System.out.println("\nbyte: " + b);
        System.out.println("\nshort: " + s);
        System.out.println("\nint: " + i);
        System.out.println("\nlong: " + l);
        System.out.println("\nfloat: " + f);
        System.out.println("\ndouble: " + d);
        System.out.println("\nchar: " + c);
        System.out.println("\nboolean: " + bo);

        input.close();
    }
}


