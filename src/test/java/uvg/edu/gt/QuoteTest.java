package uvg.edu.gt;

/**
 * Esta clase proporciona pruebas unitarias para la clase Quote.
 * Se utiliza la biblioteca JUnit para realizar pruebas.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class QuoteTest {

    /**
     * Prueba para verificar el método returnQuote() en la clase Quote.
     * Se verifica si el método devuelve la cadena de entrada sin modificar.
     */
    @Test
    public void testReturnQuote() {
        // Crear una instancia de la clase Quote
        Quote quoteInstance = new Quote();

        // Definir una cadena de prueba
        String inputQuote = "(+ 1 2)";

        // Llamar al método returnQuote con la cadena de prueba
        String returnedQuote = quoteInstance.returnQuote(inputQuote);

        // Verificar si la cadena devuelta es igual a la cadena de entrada
        assertEquals(inputQuote, returnedQuote);
    }
}