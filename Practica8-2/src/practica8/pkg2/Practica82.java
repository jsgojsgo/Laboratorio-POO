/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8.pkg2;

public class Practica82{
    public static void main(String[] args) {
        
        Automovil auto = new Automovil("Nuevo", "Tesla", 80000);
        
        System.out.println("--------- Propiedades del automóvil ---------");
        System.out.println("Modelo: " + auto.getModelo() + " (@Normal)");
        System.out.println("Marca: " + auto.getMarca() + " (@Normal)");
        System.out.println("Precio: $" + auto.getPrecio() + " (@Normal)");

        System.out.println("\n-------- Métodos del automóvil ----------");
        auto.encender(50);                  // @Override
        auto.avanzar(30);                   // @Normal
        System.out.println(auto.apagar());  // @Override @Deprecated

       
        Class<?> cls = auto.getClass().getSuperclass();
        if (cls.isAnnotationPresent(InfoClase.class)) {
            InfoClase info = cls.getAnnotation(InfoClase.class);
            System.out.println("\nAnotación personalizada en la clase padre:");
            System.out.println("Autor: " + info.autor());
            System.out.println("Versión: " + info.version() + " (@Custom Annotation)");
        }
    }
}