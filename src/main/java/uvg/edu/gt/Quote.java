package uvg.edu.gt;

/**
 * Clase QUOTE o ‘ (single quote, para interrumpir el proceso de evaluación de
 * expresiones).
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
public class Quote {

    /**
     * Constructor vacío de la clase Quote.
     */
    public Quote() {
    }

    /**
     * Método que recibe una instrucción y la devuelve como una cadena.
     * 
     * @param instruccion La instrucción que se va a imprimir y devolver.
     * @return La instrucción dada como cadena.
     */
    public String returnQuote(String quote) {
        // Imprimir la instrucción en la consola
        System.out.println(quote);
        // Devolver la instrucción
        return quote;
    }
}