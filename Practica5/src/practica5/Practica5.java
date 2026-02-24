package practica5;

import java.util.Scanner;

public class Practica5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringClass utils = new StringClass();
        
        //regresar longitud
        System.out.print("Ingrese un string: ");
        String texto = sc.nextLine();
        System.out.println("Longitud: " + utils.longitud(texto));
        
        //comparación
        System.out.print("Ingrese otro string para comparar: ");
        String texto2 = sc.nextLine();
        System.out.println("¿Son iguales?: " + utils.sonIguales(texto, texto2));
        
        //ordenar arreglo
        String[] arreglo = {"pera", "kiwi", "banana", "fresa", "manzana", "uva"};
        System.out.print("Arreglo original: ");
        for(String s : arreglo) System.out.print(s + " ");
        System.out.println();
        utils.ordenarArreglo(arreglo);
        System.out.print("Arreglo ordenado: ");
        for(String s : arreglo) System.out.print(s + " ");
        System.out.println();

        //mayor de 2 
        System.out.print("Ingrese el primer número para comparar: ");
        float num1 = sc.nextFloat();
        System.out.print("Ingrese el segundo número: ");
        float num2 = sc.nextFloat();
        System.out.println("Mayor: " + utils.mayorDeDos(num1, num2));

        //mayor de 3 
        System.out.print("Ingrese el primer número para comparar: ");
        float n1 = sc.nextFloat();
        System.out.print("Ingrese el segundo número: ");
        float n2 = sc.nextFloat();
        System.out.print("Ingrese el tercer número: ");
        float n3 = sc.nextFloat();
        System.out.println("Mayor: " + utils.mayorDeTres(n1, n2, n3));
        
        //cálculo iva
        System.out.print("Cantidad de producto: ");
        double cantidad = sc.nextDouble();
        System.out.print("Precio unitario: ");
        double precio = sc.nextDouble();
        System.out.println("IVA (16%): " + utils.calcularIVA(cantidad, precio));

        sc.nextLine();//limpiar buffer
        
        //dividir string
        System.out.print("Ingrese la cadena para dividir: ");
        String paraDividir = sc.nextLine();
        String caracter;
        do {
            System.out.print("Ingrese el caracter que separará la cadena: ");
            caracter = sc.nextLine();

            if (!paraDividir.contains(caracter)) {
                System.out.println("El separador '" + caracter + "' no se encuentra en la cadena. Intente de nuevo.");
            }
        } while (!paraDividir.contains(caracter));
        
        String[] partes = utils.dividirString(paraDividir, caracter);
        System.out.print("Partes: ");
        for (String p : partes) System.out.print(p + " ");
        System.out.println();
        
        //existencia string
        System.out.print("Ingrese cadena para buscar: ");
        String buscar = sc.nextLine();
        System.out.println("¿Contiene?: " + utils.contiene(paraDividir, buscar));

        sc.close();
    }
}