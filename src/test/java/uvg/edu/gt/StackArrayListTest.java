package uvg.edu.gt;

/**
 * Esta clase proporciona pruebas unitarias para la implementación de la clase StackArrayList.
 * Se utiliza la biblioteca JUnit para realizar pruebas.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class StackArrayListTest {

    private StackArrayList<Integer> stack;

    /**
     * Método que se ejecuta antes de cada prueba para inicializar el objeto stack.
     */
    @Before
    public void setUp() {
        stack = new StackArrayList<>();
    }

    /**
     * Prueba para verificar la funcionalidad de push() en la pila.
     * Se comprueba que el tamaño de la pila sea correcto después de agregar un
     * elemento.
     */
    @Test
    public void testPush() {
        stack.push(5);
        assertEquals(1, stack.size());
        assertFalse(stack.empty());
    }

    /**
     * Prueba para verificar la funcionalidad de pop() en la pila.
     * Se comprueba que el elemento devuelto por pop() sea el esperado y que la pila
     * esté vacía después de la operación.
     */
    @Test
    public void testPop() {
        stack.push(5);
        assertEquals(Integer.valueOf(5), stack.pop());
        assertTrue(stack.empty());
    }

    /**
     * Prueba para verificar la funcionalidad de peek() en la pila.
     * Se comprueba que el elemento devuelto por peek() sea el esperado y que el
     * tamaño de la pila sea correcto después de la operación.
     */
    @Test
    public void testPeek() {
        stack.push(5);
        assertEquals(Integer.valueOf(5), stack.peek());
        assertEquals(1, stack.size());
        assertFalse(stack.empty());
    }

    /**
     * Prueba para verificar la funcionalidad de isEmpty() en la pila.
     * Se comprueba que la pila esté vacía inicialmente y que no lo esté después de
     * agregar un elemento.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(stack.empty());
        stack.push(5);
        assertFalse(stack.empty());
    }
}