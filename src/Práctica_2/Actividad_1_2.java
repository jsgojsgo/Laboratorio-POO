package Práctica_2;
import java.util.Scanner;

public class Actividad_1_2 {
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int age;
        long phone;
        
        System.out.println("\nIngrese su nombre: ");
        String name = input.nextLine();
        do{ 
            System.out.println("\nIngrese su edad: ");
            age = input.nextInt();
        }while(age<0||age>100);
        
        do{
            System.out.println("\nIngrese su teléfono (10 dígitos): ");
            phone = input.nextLong();
        }while(phone<0||String.valueOf(phone).length()!=10);
        
        System.out.println("\nHola "+name+", tu edad es "+age+", y tu teléfono es "+phone);
    }
}
