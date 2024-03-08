package uvg.edu.gt;

/**
 * La Clase Defun es encargada de almacenar y manipular datos de funciones
 * definidas por el usuario.
 * 
 * Este interprete utiliza el Java Collections Framework para el desarrollo de
 * sus operaciones.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
public class Defun {
    private String nombre; // Nombre de la función
    private String parametro; // Parámetro de la función
    private String instruccion; // Código de la función a ejecutar
    private String codigo; // Código completo de la función definida

    /**
     * Constructor de la clase Defun.
     * 
     * @param nombre      Nombre de la función.
     * @param parametro   Parámetro de la función.
     * @param instruccion Código de la función a ejecutar.
     * @param codigo      Código completo de la función definida.
     */
    public Defun(String nombre, String parametro, String instruccion, String codigo) {
        this.nombre = nombre;
        this.parametro = parametro;
        this.instruccion = instruccion;
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre de la función.
     * 
     * @return Nombre de la función.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el parámetro de la función.
     * 
     * @return Parámetro de la función.
     */
    public String getParametro() {
        return parametro;
    }

    /**
     * Obtiene el código de la función a ejecutar.
     * 
     * @return Código de la función a ejecutar.
     */
    public String getInstruccion() {
        return instruccion;
    }

    /**
     * Obtiene el código completo de la función definida.
     * 
     * @return Código completo de la función definida.
     */
    public String getCodigo() {
        return codigo;
    }
}