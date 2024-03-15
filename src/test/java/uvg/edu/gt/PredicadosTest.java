package uvg.edu.gt;

/**
 * Esta clase proporciona pruebas unitarias para la clase Predicados.
 * Se utiliza la biblioteca JUnit para realizar pruebas.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PredicadosTest {
    private Predicados predicados;

    /**
     * Método que se ejecuta antes de cada prueba para inicializar el objeto
     * Predicados.
     */
    @Before
    public void setUp() {
        predicados = new Predicados();
    }

    /**
     * Prueba para verificar la funcionalidad del predicado 'atom'.
     * Se comprueba si la evaluación del predicado atom se realiza correctamente.
     */
    @Test
    public void testAtom() {
        assertEquals("true", predicados.evaluarPredicado("atom 5"));
        assertEquals("false", predicados.evaluarPredicado("atom (+ 2 3)"));
        assertEquals("false", predicados.evaluarPredicado("atom (list 1 2 3)"));
    }

    /**
     * Prueba para verificar la funcionalidad del predicado '=' (igual).
     * Se comprueba si la evaluación del predicado igual se realiza correctamente.
     */
    @Test
    public void testEqual() {
        assertEquals("true", predicados.evaluarPredicado("= 5 5"));
        assertEquals("false", predicados.evaluarPredicado("equals 2 3"));
    }

    /**
     * Prueba para verificar la funcionalidad del predicado 'list'.
     * Se verifica si se lanza alguna excepción al evaluar el predicado list.
     */
    @Test
    public void testList() {
        try {
            predicados.evaluarPredicado("list 1 2 3");
        } catch (Exception e) {
            fail("Se lanzó una excepción: " + e.getMessage());
        }
    }

    /**
     * Prueba para verificar la funcionalidad del predicado '>' (mayor que).
     * Se comprueba si la evaluación del predicado mayor que se realiza
     * correctamente.
     */
    @Test
    public void testMayorQue() {
        assertEquals("true", predicados.evaluarPredicado("> 5 3"));
        assertEquals("false", predicados.evaluarPredicado("> 2 3"));
        assertEquals("false", predicados.evaluarPredicado("> 3 5"));
    }

    /**
     * Prueba para verificar la funcionalidad del predicado '<' (menor que).
     * Se comprueba si la evaluación del predicado menor que se realiza
     * correctamente.
     */
    @Test
    public void testMenorQue() {
        assertEquals("true", predicados.evaluarPredicado("< 3 5"));
        assertEquals("false", predicados.evaluarPredicado("< 5 3"));
        assertEquals("false", predicados.evaluarPredicado("< 3 3"));
    }
}