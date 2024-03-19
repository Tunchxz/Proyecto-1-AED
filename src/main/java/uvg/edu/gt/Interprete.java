package uvg.edu.gt;

public class Interprete {
    StackVector<String> stackVector = new StackVector<String>();
    StackArrayList<Defun> stackArraylist = new StackArrayList<Defun>();

    public void verificarSintaxis(String linea) {
        String nuevaLinea = linea.replace("(", "");
        nuevaLinea = nuevaLinea.replace(")", "");
        String[] lineasplit = nuevaLinea.split(" ");
        for (int i = 0; i < lineasplit.length; i++) {
            stackVector.push(lineasplit[i]);
        }
    }

    public boolean verificarParentesis(String codigo) {
        int contadora = 0;
        int contadorc = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (codigo.charAt(i) == '(') {
                contadora++;
            } else if (codigo.charAt(i) == ')') {
                contadorc++;
            }
        }
        if (contadora == contadorc) {
            return true;
        }
        return false;
    }

    public String verificarFuncion(String nombrefuncion) {
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getInstruccion();
            }
        }
        return "null";
    }

    public int cantidadFuncion(String nombrefuncion) {
        int cantidad = 0;
        for (int i = 0; i < stackVector.size(); i++) {
            if (nombrefuncion.equals(stackVector.get(i))) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public String codigoFuncion(String nombrefuncion) {
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getCodigo();
            }
        }
        return "null";
    }

    public String regresarParametro(String nombrefuncion) {
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getParametro();
            }
        }
        return "null";
    }

    public StackVector<String> cambiarParametro(StackVector<String> stackVector, String parametro, String valor) {
        StackVector<String> valores = new StackVector<String>();
        for (int i = 0; i < stackVector.size(); i++) {
            String llave = stackVector.get(i);
            if (llave.equals(parametro)) {
                valores.push(valor);
            } else {
                valores.push(llave);
            }
        }
        return valores;
    }

    public boolean esNumero(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void cambiarTrue(String lineacodigo) {
        stackVector.clear();
        String nuevoCodigo = "";
        String nuevaLinea = lineacodigo.replace("(", "( ");
        nuevaLinea = nuevaLinea.replace(")", " )");
        String[] lineasplit = nuevaLinea.split(" ");
        StackVector<String> nuevoVector = new StackVector<String>();
        for (int i = 0; i < lineasplit.length; i++) {
            nuevoVector.push(lineasplit[i]);
        }
        int contadorParentesisAbierto = 0;
        int contadorParentesisCerrado = 0;
        for (int i = 0; i < nuevoVector.size(); i++) {
            String llave = nuevoVector.get(i);
            if (llave.equals("(")) {
                contadorParentesisAbierto++;
            } else if (llave.equals(")")) {
                contadorParentesisCerrado++;
            }
            if (contadorParentesisAbierto == 5 && contadorParentesisCerrado != 5 && (llave.equals("(") == false)
                    && (llave.equals(")") == false)) {
                nuevoCodigo += llave;
                nuevoCodigo += " ";
            }
        }
        verificarSintaxis(nuevoCodigo);
    }

    public void cambiarFalse(String lineacodigo) {
        stackVector.clear();
        String nuevoCodigo = "";
        String nuevaLinea = lineacodigo.replace("(", "( ");
        nuevaLinea = nuevaLinea.replace(")", " )");
        String[] lineasplit = nuevaLinea.split(" ");
        StackVector<String> nuevoVector = new StackVector<String>();
        for (int i = 0; i < lineasplit.length; i++) {
            nuevoVector.push(lineasplit[i]);
        }
        int contadorParentesisAbierto = 0;
        int contadorParentesisCerrado = 0;
        for (int i = 0; i < nuevoVector.size(); i++) {
            String llave = nuevoVector.get(i);
            if (llave.equals("(")) {
                contadorParentesisAbierto++;
            } else if (llave.equals(")")) {
                contadorParentesisCerrado++;
            }
            if (contadorParentesisAbierto >= 6 && contadorParentesisCerrado != 6 && (llave.equals("(") == false)
                    && (llave.equals(")") == false)) {
                nuevoCodigo += llave;
                nuevoCodigo += " ";
            }
        }
        verificarSintaxis(nuevoCodigo);
    }

    public StackArrayList<Defun> getStackArraylist() {
        return stackArraylist;
    }
}