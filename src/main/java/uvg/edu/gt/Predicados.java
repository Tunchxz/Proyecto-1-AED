package uvg.edu.gt;

public class Predicados {

    public Predicados() {
    }

    public String evaluarPredicado(String instrucc) {
        String respuesta = "";
        boolean atom = false;
        boolean equal = false;
        boolean list = false;
        boolean mayorQue = false;
        boolean menorQue = false;

        String[] SaltoInstruccion = instrucc.split(" ");
        String expsplit = "";
        expsplit = String.join(" ", SaltoInstruccion);

        String letra = expsplit.substring(0, 1);

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
                System.out.println("No hay suficientes parametros");
            } else {
                if (SaltoEspacio[1].equals(SaltoEspacio[2])) {
                    respuesta = "true";
                } else {
                    respuesta = "false";
                }
            }
        }

        if (list == true) {
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
                System.out.println("No hay suficientes parametros");
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
                    System.out.println("No es un dato numerico");
                }
            }
        }

        if (menorQue == true) {
            String[] SaltoEspacio = expsplit.split(" ");
            if (SaltoEspacio.length <= 2) {
                System.out.println("No es un dato numerico");
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
                    System.out.println("No es un dato numerico");
                }
            }
        }
        return respuesta;
    }
}