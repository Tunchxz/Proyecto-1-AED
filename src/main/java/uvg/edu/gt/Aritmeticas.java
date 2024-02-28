package uvg.edu.gt;

/**
 * La Clase Aritmeticas proporciona métodos para realizar operaciones
 * aritméticas utilizando notación prefija.
 * 
 * Soporta dos métodos de cálculo diferentes.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
public class Aritmeticas {

    /**
     * Constructor por defecto de la clase Aritmeticas.
     */
    public Aritmeticas() {
    }

    /**
     * Realiza operaciones aritméticas en notación prefija.
     * 
     * @param expresion Expresión aritmética en notación prefija.
     * @return El resultado de evaluar la expresión.
     */
    public String calculadoraPrefix(String expresion) {
        // StackVector es una clase no proporcionada aquí, pero asumiremos que funciona
        // como una pila estándar.
        StackVector<Double> pila = new StackVector<Double>(); // Creamos una pila para almacenar los números
        String resultado = "";
        // Eliminamos los paréntesis de la expresión
        String nuevoPrefijo = expresion.replace("(", "").replace(")", "");

        // Recorremos la expresión desde el final hacia el principio
        for (int i = nuevoPrefijo.length() - 1; i >= 0; i--) {
            String aString = Character.toString(nuevoPrefijo.charAt(i));
            try {
                // Intentamos convertir el carácter a un número
                pila.push(Double.parseDouble(aString));
            } catch (Exception e) {
                double num1 = 0;
                double num2 = 0;
                try {
                    // Si no es un número, realizamos operaciones
                    num1 = pila.pop();
                    num2 = pila.pop();
                } catch (Exception exception) {
                    return "ERROR, Operación Inválida";
                }

                // Realizamos la operación dependiendo del operador
                if (aString.equals("+")) {
                    pila.push(num1 + num2);
                } else if (aString.equals("*")) {
                    pila.push(num1 * num2);
                } else if (aString.equals("-")) {
                    pila.push(num1 - num2);
                } else if (aString.equals("/")) {
                    pila.push(num1 / num2);
                } else {
                    return "ERROR, Operación Inválida";
                }
            }
        }

        // Obtenemos el resultado de la cima de la pila
        double peek = pila.peek();
        resultado = Double.toString(peek);
        return resultado;
    }

    /**
     * Realiza operaciones aritméticas en notación prefija (otra implementación).
     * 
     * @param expresion Expresión aritmética en notación prefija.
     * @return El resultado de evaluar la expresión.
     */
    public String calculadoraEspaciada(String expresion) {
        StackVector<Double> pila = new StackVector<Double>();
        String resultado = "";
        String nuevoPrefijo = expresion.replace("(", "").replace(")", "");
        String[] lista = nuevoPrefijo.split(" ");

        // Recorremos la expresión desde el final hacia el principio
        for (int i = lista.length - 1; i >= 0; i--) {
            String aString = lista[i];
            try {
                // Intentamos convertir el carácter a un número
                pila.push(Double.parseDouble(aString));
            } catch (Exception e) {
                double num1 = 0;
                double num2 = 0;
                try {
                    // Si no es un número, realizamos operaciones
                    num1 = pila.pop();
                    num2 = pila.pop();
                } catch (Exception exception) {
                    return "ERROR, Operación Inválida";
                }

                // Realizamos la operación dependiendo del operador
                if (aString.equals("+")) {
                    pila.push(num1 + num2);
                } else if (aString.equals("*")) {
                    pila.push(num1 * num2);
                } else if (aString.equals("-")) {
                    pila.push(num1 - num2);
                } else if (aString.equals("/")) {
                    pila.push(num1 / num2);
                }
            }
        }

        // Obtenemos el resultado de la cima de la pila
        double peek = pila.peek();
        resultado = Double.toString(peek);
        return resultado;
    }
}