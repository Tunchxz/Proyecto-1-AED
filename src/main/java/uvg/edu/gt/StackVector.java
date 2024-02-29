package uvg.edu.gt;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * Implementación de una pila utilizando un vector.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 * 
 * @param <E> tipo de elementos que contendrá la pila.
 */
public class StackVector<E> implements IStack<E> {
    private Vector<E> data;

    /**
     * Constructor que inicializa el Vector interno del stack.
     */
    public StackVector() {
        data = new Vector<E>();
    }

    @Override
    public void push(E item) {
        // Agrega un elemento a la parte superior del stack
        data.add(item);
    }

    @Override
    public E pop() {
        // Elimina y devuelve el elemento en la parte superior del stack
        return data.remove(size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException {
        // Devuelve el elemento en la parte superior del stack sin eliminarlo
        if (data.isEmpty()) {
            throw new EmptyStackException();
        }
        return data.elementAt(data.size() - 1);
    }

    @Override
    public E get(int i) {
        // Obtiene el elemento en la posición especificada del stack
        return data.get(i);
    }

    @Override
    public boolean empty() {
        // Verifica si el stack está vacío
        return data.isEmpty();
    }

    @Override
    public int size() {
        // Devuelve el número de elementos en el stack
        return data.size();
    }

    @Override
    public void clear() {
        // Elimina todos los elementos del stack
        data.clear();
    }

    @Override
    public void remove(int i) {
        // Elimina el elemento en la posición especificada del stack
        data.remove(i);
    }

    @Override
    public void removeFirst() {
        // Elimina el elemento en la posición 0 del stack
        data.remove(0);
    }

    /**
     * Verifica si un valor dado está presente en el stack.
     *
     * @param value el valor a buscar
     * @return true si el valor está en el stack, false de lo contrario
     */
    public boolean isInStack(String value) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el índice de la primera ocurrencia de un valor dado en el stack.
     *
     * @param value el valor a buscar
     * @return el índice del valor en el stack, o -1 si no se encuentra
     */
    public int indexOf(String value) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }
}