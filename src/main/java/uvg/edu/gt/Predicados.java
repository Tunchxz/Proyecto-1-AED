package uvg.edu.gt;

/**
 * Clase Predicados contiene métodos para procesar operaciones basadas en
 * predicados.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
public class Predicados {

    /**
     * Constructor por defecto.
     */
    public Predicados() {
    }

    /**
     * Método que procesa la instrucción dada y devuelve el resultado de la
     * operación
     * según el predicado especificado.
     *
     * @param instrucc la instrucción que contiene la operación y sus operandos
     * @return el resultado de la operación según el predicado
     */
    public String evaluarPredicado(String instrucc) {
        String respuesta = "";
        boolean atom = false;
        boolean equal = false;
        boolean list = false;
        boolean mayorQue = false;
        boolean menorQue = false;

        // Separar la instrucción en partes y rearmarla
        String[] SaltoInstruccion = instrucc.split(" ");
        String expsplit = "";
        expsplit = String.join(" ", SaltoInstruccion);

        // Obtener el primer carácter de la instrucción para determinar el tipo de
        // operación
        String letra = expsplit.substring(0, 1);

        // Establecer los predicados según el primer carácter
        if (letra.equalsIgnoreCase("a")) {
            atom = true;
        }
        if (letra.equalsIgnoreCase("e") || letra.equals("=")) {
            equal = true;
        }
        if (letra.equalsIgnoreCase("l")) {
            list = true;
        }
        if (letra.equals(">")) {
            mayorQue = true;
        }
        if (letra.equals("<")) {
            menorQue = true;
        }

        // Realizar la operación correspondiente según el predicado

        if (atom == true) {
            String[] SaltoEspacio = expsplit.split(" ");
            if (SaltoEspacio.length <= 2) {
                respuesta = "true";
            } else {
                respuesta = "false";
            }
        }

        if (equal == true) {
            String[] SaltoEspacio = expsplit.split(" ");
            if (SaltoEspacio.length <= 2) {
                System.out.println("[Sistema]: Error. No hay suficientes parámetros.");
            } else {
                if (SaltoEspacio[1].equals(SaltoEspacio[2])) {
                    respuesta = "true";
                } else {
                    respuesta = "false";
                }
            }
        }

        if (list == true) {
            // Imprimir los elementos de la lista
            System.out.print("( ");
            for (int i = 1; i <= (SaltoInstruccion.length - 1); i++) {
                if (i < (SaltoInstruccion.length - 1)) {
                    System.out.print(SaltoInstruccion[i] + ", ");
                } else {
                    System.out.print(SaltoInstruccion[i]);
                }
            }
            System.out.print(" )");
        }

        if (mayorQue == true) {
            String[] SaltoEspacio = expsplit.split(" ");
            if (SaltoEspacio.length <= 2) {
                System.out.println("[Sistema]: Error. No hay suficientes parámetros.");
            } else {
                try {
                    int primerNumero = Integer.parseInt(SaltoEspacio[1]);
                    int segundoNumero = Integer.parseInt(SaltoEspacio[2]);
                    if (primerNumero > segundoNumero) {
                        respuesta = "true";
                    } else {
                        respuesta = "false";
                    }
                } catch (Exception e) {
                    System.out.println("[Sistema]: Error. Ingrese un valor que sea númerico.");
                }
            }
        }

        if (menorQue == true) {
            String[] SaltoEspacio = expsplit.split(" ");
            if (SaltoEspacio.length <= 2) {
                System.out.println("[Sistema]: Error. Ingrese un valor que sea númerico.");
            } else {
                try {
                    int primerNumero = Integer.parseInt(SaltoEspacio[1]);
                    int segundoNumero = Integer.parseInt(SaltoEspacio[2]);
                    if (primerNumero < segundoNumero) {
                        respuesta = "true";
                    } else {
                        respuesta = "false";
                    }
                } catch (Exception e) {
                    System.out.println("[Sistema]: Error. Ingrese un valor que sea númerico.");
                }
            }
        }
        return respuesta;
    }
}