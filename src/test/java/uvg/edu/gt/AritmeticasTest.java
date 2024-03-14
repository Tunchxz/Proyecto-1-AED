package uvg.edu.gt;

/**
 * Esta clase proporciona pruebas unitarias para la clase Aritmeticas.
 * Se utiliza la biblioteca JUnit para realizar pruebas.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AritmeticasTest {

    /**
     * Prueba para verificar la funcionalidad de calculadoraEspaciada() en la clase
     * Aritmeticas.
     * Se comprueba si la expresión dada se calcula correctamente cuando está
     * espaciada y contiene paréntesis.
     */
    @Test
    public void testCalculadoraEspaciada() {
        Aritmeticas aritmetica = new Aritmeticas();
        String expresion = "(- (* 10 2) (+ 8 2))";
        String resultadoEsperado = "10.0";
        assertEquals(resultadoEsperado, aritmetica.calculadoraEspaciada(expresion));
    }

    /**
     * Prueba para verificar la funcionalidad de calculadoraEspaciada() en la clase
     * Aritmeticas.
     * Se comprueba si la expresión dada se calcula correctamente cuando no contiene
     * paréntesis.
     */
    @Test
    public void testCalculadoraEspaciadaSinParentesis() {
        Aritmeticas aritmetica = new Aritmeticas();
        String expresion = "- 10 2";
        String resultadoEsperado = "8.0";
        assertEquals(resultadoEsperado, aritmetica.calculadoraEspaciada(expresion));
    }

    /**
     * Prueba para verificar la detección de error en operaciones inválidas en la
     * clase Aritmeticas.
     * Se comprueba si se devuelve un mensaje de error cuando la expresión contiene
     * una operación inválida.
     */
    @Test
    public void testErrorOperacionInvalida() {
        Aritmeticas aritmetica = new Aritmeticas();
        String expresion = "+ 5 *";
        String resultadoEsperado = "ERROR, Operación Inválida";
        assertEquals(resultadoEsperado, aritmetica.calculadoraPrefix(expresion));
    }
}