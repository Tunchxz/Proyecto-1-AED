package uvg.edu.gt;

/**
 * Esta clase proporciona pruebas unitarias para la implementación de la clase StackVector.
 * Se utiliza la biblioteca JUnit para realizar pruebas.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
import static org.junit.Assert.*;

import org.junit.*;

public class StackVectorTest {

    private StackVector<Integer> stack;

    /**
     * Método que se ejecuta antes de cada prueba para inicializar el objeto stack.
     */
    @Before
    public void setUp() {
        stack = new StackVector<>();
    }

    /**
     * Prueba para verificar la funcionalidad de push() y pop() en la pila.
     */
    @Test
    public void testPushAndPop() {
        assertTrue(stack.empty());
        stack.push(1);
        stack.push(2);
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
        assertTrue(stack.empty());
    }

    /**
     * Prueba para verificar la funcionalidad de peek() en la pila.
     */
    @Test
    public void testPeek() {
        assertTrue(stack.empty());
        stack.push(1);
        stack.push(2);
        assertEquals(Integer.valueOf(2), stack.peek());
        assertEquals(Integer.valueOf(2), stack.peek()); // Ensure peek doesn't remove the element
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.peek());
    }

    /**
     * Prueba para verificar la funcionalidad de size() en la pila.
     */
    @Test
    public void testSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    /**
     * Prueba para verificar la funcionalidad de clear() en la pila.
     */
    @Test
    public void testClear() {
        stack.push(1);
        stack.push(2);
        assertFalse(stack.empty());
        stack.clear();
        assertTrue(stack.empty());
        assertEquals(0, stack.size());
    }
}