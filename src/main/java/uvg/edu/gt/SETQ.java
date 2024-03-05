package uvg.edu.gt;

import java.util.HashMap;

public class SETQ {
    HashMap<String, String> mapaLisp;

    public SETQ() {
        mapaLisp = new HashMap<String, String>();
    }

    public void agregarValor(String llave, String valor) {
        if (mapaLisp.containsKey(llave)) {
            mapaLisp.remove(llave);
            mapaLisp.put(llave, valor);
        } else {
            mapaLisp.put(llave, valor);
        }
    }

    public StackVector<String> buscarValor(StackVector<String> stackVector) {
        StackVector<String> valores = new StackVector<String>();
        String llave = "";

        for (int i = 0; i < stackVector.size(); i++) {
            llave = stackVector.get(i);
            if (mapaLisp.containsKey(llave)) {
                valores.push(mapaLisp.get(llave));
            } else {
                valores.push(llave);
            }
        }

        return valores;
    }

    public boolean encontrarValor(String clave) {
        if (mapaLisp.containsKey(clave)) {
            return true;
        }
        return false;
    }
}