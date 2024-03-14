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
}