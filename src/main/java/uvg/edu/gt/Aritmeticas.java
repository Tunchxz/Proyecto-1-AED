package uvg.edu.gt;

public class Aritmeticas {

    public Aritmeticas() {
    }

    public String calculadoraPrefix(String expresion) {
        StackVector<Double> pila = new StackVector<Double>();
        String resultado = "";
        String nuevoPrefijo = expresion.replace("(", "").replace(")", "");

        for (int i = nuevoPrefijo.length() - 1; i >= 0; i--) {
            String aString = Character.toString(nuevoPrefijo.charAt(i));
            try {
                pila.push(Double.parseDouble(aString));
            } catch (Exception e) {
                double num1 = 0;
                double num2 = 0;
                try {
                    num1 = pila.pop();
                    num2 = pila.pop();
                } catch (Exception exception) {
                    return "ERROR, Operación Inválida";
                }

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

        double peek = pila.peek();
        resultado = Double.toString(peek);
        return resultado;
    }
}