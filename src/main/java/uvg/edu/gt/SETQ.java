package uvg.edu.gt;

import java.util.HashMap;

/**
 * La clase SETQ representa un diccionario donde se almacenan las variables
 * declaradas por el usuario para su posterior recursión.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
public class SETQ {
    // HashMap para almacenar pares clave-valor
    HashMap<String, String> mapaLisp;

    /**
     * Constructor que inicializa el HashMap.
     */
    public SETQ() {
        mapaLisp = new HashMap<String, String>();
    }

    /**
     * Agrega un valor al HashMap. Si la clave ya existe, se actualiza el valor
     * asociado.
     * 
     * @param llave Clave del valor a agregar o actualizar.
     * @param valor Valor asociado a la clave.
     */
    public void agregarValor(String llave, String valor) {
        if (mapaLisp.containsKey(llave)) {
            mapaLisp.remove(llave); // Elimina la entrada previa para actualizar el valor
            mapaLisp.put(llave, valor);
        } else {
            mapaLisp.put(llave, valor);
        }
    }

    /**
     * Busca los valores correspondientes a las claves en un StackVector y los
     * devuelve en otro StackVector.
     * Si una clave no está presente, se mantiene en el StackVector de salida sin
     * cambios.
     * 
     * @param stackVector StackVector que contiene las claves a buscar.
     * @return StackVector con los valores correspondientes a las claves encontradas
     *         en el HashMap.
     */
    public StackVector<String> buscarValor(StackVector<String> stackVector) {

        StackVector<String> valores = new StackVector<String>();
        String llave = "";

        // Itera sobre los elementos del stackVector
        for (int i = 0; i < stackVector.size(); i++) {

            llave = stackVector.get(i);

            // Verifica si la clave existe en el mapaLisp y agrega el valor correspondiente
            if (mapaLisp.containsKey(llave)) {
                valores.push(mapaLisp.get(llave));
            } else {
                // Si la clave no está presente, agrega la misma clave al StackVector de salida
                valores.push(llave);
            }
        }

        // Retorna el StackVector con los valores encontrados
        return valores;
    }

    /**
     * Verifica si una clave está presente en el HashMap.
     * 
     * @param clave Clave a verificar.
     * @return true si la clave está presente, false de lo contrario.
     */
    public boolean encontrarValor(String clave) {
        // Verifica si la clave está presente en el HashMap
        if (mapaLisp.containsKey(clave)) {
            return true; // Retorna true si la clave está presente
        }
        // Retorna false si la clave no está presente
        return false;
    }
}