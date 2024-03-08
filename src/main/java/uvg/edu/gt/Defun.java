package uvg.edu.gt;

public class Defun {
    private String nombre;
    private String parametro;
    private String instruccion;
    private String codigo;

    public Defun(String nombre, String parametro, String instruccion, String codigo) {
        this.nombre = nombre;
        this.parametro = parametro;
        this.instruccion = instruccion;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getParametro() {
        return parametro;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public String getCodigo() {
        return codigo;
    }
}