package practica5;

public class StringClass {
    public int longitud(String str) {
        return str.length();
    }
    public boolean sonIguales(String str1, String str2) {
        return str1.equals(str2);
    }
    public void ordenarArreglo(String[] arr) {
        java.util.Arrays.sort(arr);
    }
    public float mayorDeDos(float a, float b) {
        return (a > b) ? a : b;
    }
    public float mayorDeTres(float a, float b, float c) {
        float mayor = a;
        if(b > mayor) mayor = b;
        if(c > mayor) mayor = c;
        return mayor;
    }
    public double calcularIVA(double cantidad, double precio) {
        double subtotal = cantidad * precio;
        double iva = subtotal * 0.16; //donde el iva es 16%
        return iva;
    }
    public String[] dividirString(String str, String separador) {
        return str.split(separador);
    }
    public boolean contiene(String cadena, String subcadena) {
        return cadena.contains(subcadena);
    }
}