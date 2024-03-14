package uvg.edu.gt;

/**
 * Esta clase proporciona pruebas unitarias para la clase SETQ.
 * Se utiliza la biblioteca JUnit para realizar pruebas.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SETQTest {

    private SETQ setq;

    /**
     * Método que se ejecuta antes de cada prueba para inicializar el objeto SETQ.
     */
    @Before
    public void setUp() {
        setq = new SETQ();
    }

    /**
     * Prueba para verificar la funcionalidad de agregarValor() en la clase SETQ.
     * Se comprueba si se pueden agregar valores correctamente y si se pueden buscar
     * después.
     */
    @Test
    public void testAgregarValor() {
        setq.agregarValor("a", "1");
        setq.agregarValor("b", "2");

        assertTrue(setq.encontrarValor("a"));
        assertTrue(setq.encontrarValor("b"));
        assertFalse(setq.encontrarValor("c")); // "c" no ha sido agregado aún

        setq.agregarValor("b", "3"); // Actualizar el valor de "b"

        // Crear un StackVector con las llaves "a" y "b"
        StackVector<String> llaves = new StackVector<>();
        llaves.push("a");
        llaves.push("b");

        // Buscar los valores correspondientes a las llaves
        StackVector<String> valores = setq.buscarValor(llaves);

        // Verificar que los valores obtenidos son correctos
        assertEquals("3", valores.pop());
        assertEquals("1", valores.pop());
    }

    /**
     * Prueba para verificar la funcionalidad de buscarValor() en la clase SETQ.
     * Se comprueba si se pueden buscar valores correspondientes a las llaves dadas.
     */
    @Test
    public void testBuscarValores() {
        setq.agregarValor("a", "1");
        setq.agregarValor("b", "2");

        StackVector<String> llaves = new StackVector<>();
        llaves.push("a");
        llaves.push("b");
        llaves.push("c"); // "c" no existe en el conjunto

        StackVector<String> valores = setq.buscarValor(llaves);

        assertEquals("c", valores.pop()); // el valor de "b"
        assertEquals("2", valores.pop()); // el valor de "a"
        assertEquals("1", valores.pop()); // "c" no existe, así que se espera que regrese la llave misma
    }

    /**
     * Prueba para verificar la funcionalidad de encontrarValor() en la clase SETQ.
     * Se comprueba si se puede verificar si una llave existe en el conjunto de
     * SETQ.
     */
    @Test
    public void testLlaveExiste() {
        setq.agregarValor("a", "1");

        assertTrue(setq.encontrarValor("a"));
        assertFalse(setq.encontrarValor("b")); // "b" no ha sido agregado aún
    }
}