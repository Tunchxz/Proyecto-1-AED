package uvg.edu.gt;

import java.util.EmptyStackException;

/**
 * Esta interfaz define los métodos para un Stack (pila).
 * Un Stack es una estructura de datos que sigue el principio de LIFO (Last In,
 * First Out). Es decir, el último elemento agregado es el primero en ser
 * removido.
 *
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 * 
 * @param <E> el tipo de elementos que serán almacenados en el Stack
 */
public interface IStack<E> {

    /**
     * Inserta un elemento en la cima del stack.
     *
     * @param data el elemento a insertar
     */
    public void push(E data);

    /**
     * Elimina y devuelve el elemento en la cima del stack.
     *
     * @return el elemento en la cima del stack
     */
    public E pop();

    /**
     * Devuelve el elemento en la cima del stack sin eliminarlo.
     *
     * @return el elemento en la cima del stack
     * @throws EmptyStackException si el stack está vacío
     */
    public E peek() throws EmptyStackException;

    /**
     * Obtiene el elemento en la posición especificada del stack.
     *
     * @param i la posición del elemento
     * @return el elemento en la posición especificada
     */
    public E get(int i);

    /**
     * Verifica si el stack está vacío.
     *
     * @return true si el stack está vacío, false en caso contrario
     */
    public boolean empty();

    /**
     * Obtiene el número de elementos en el stack.
     *
     * @return el número de elementos en el stack
     */
    public int size();

    /**
     * Elimina todos los elementos del stack.
     */
    public void clear();

    /**
     * Elimina el elemento en la posición especificada del stack.
     *
     * @param i la posición del elemento a eliminar
     */
    public void remove(int i);

    /**
     * Elimina el elemento en la posición 0 del stack.
     */
    public void removeFirst();

}