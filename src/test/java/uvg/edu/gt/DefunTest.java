package uvg.edu.gt;

/**
 * Esta clase proporciona pruebas unitarias para la clase Defun.
 * Se utiliza la biblioteca JUnit para realizar pruebas.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DefunTest {

    /**
     * Prueba para verificar la creación de una instancia de Defun y la obtención de
     * sus atributos.
     * Se comprueba si los atributos de nombre, parámetro, instrucción y código se
     * establecen correctamente.
     */
    @Test
    public void testConstructorAndGetters() {
        String nombre = "miFuncion";
        String parametro = "x";
        String instruccion = "* x x";
        String codigo = "defun miFuncion (x) (* x x)";

        Defun defun = new Defun(nombre, parametro, instruccion, codigo);

        assertEquals(nombre, defun.getNombre());
        assertEquals(parametro, defun.getParametro());
        assertEquals(instruccion, defun.getInstruccion());
        assertEquals(codigo, defun.getCodigo());
    }

    /**
     * Prueba para verificar el almacenamiento de funciones en el intérprete.
     * Se comprueba si las funciones definidas se almacenan correctamente en el
     * intérprete.
     */
    @Test
    public void testDefun() {
        Interprete interprete = new Interprete();

        interprete.interpretar("(defun sumar (a b) (+ a b))");
        assertEquals("sumar", interprete.getStackArraylist().get(0).getNombre());

        interprete.interpretar("(defun cuadrado (x) (* x x))");
        assertEquals("cuadrado", interprete.getStackArraylist().get(1).getNombre());
    }
}