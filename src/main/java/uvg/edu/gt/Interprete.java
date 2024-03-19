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
}