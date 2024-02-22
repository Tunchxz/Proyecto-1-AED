package uvg.edu.gt;

import java.util.ArrayList;

/**
 * Esta clase implementa una pila utilizando un ArrayList.
 *
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 * 
 * @param <E> el tipo de elementos que contendrá la pila
 */
public class StackArrayList<E> implements IStack<E> {

    // Lista para almacenar los elementos del stack
    protected ArrayList<E> data;

    /**
     * Constructor que inicializa la lista interna del stack.
     */
    public StackArrayList() {
        data = new ArrayList<E>();
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
    public E peek() {
        // Devuelve el elemento en la parte superior del stack sin eliminarlo
        return data.get(size() - 1);
    }

    @Override
    public int size() {
        // Devuelve el número de elementos en el stack
        return data.size();
    }

    @Override
    public boolean empty() {
        // Verifica si el stack está vacío
        return size() == 0;
    }

    @Override
    public E get(int i) {
        // Obtiene el elemento en la posición especificada del stack
        return data.get(i);
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
}