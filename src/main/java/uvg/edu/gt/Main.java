/**
 * Clase principal que proporciona una interfaz para probar el intérprete de Common Lisp a partir de un archivo.
 * 
 * Este intérprete utiliza el Java Collections Framework para sus operaciones.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        // Iniciar la instancia del intérprete
        Interprete interprete = new Interprete();

        // Intentar leer el archivo codigo.lisp
        try {
            // Ruta del archivo
            File file = new File("src\\main\\java\\uvg\\edu\\gt\\Codigo.lisp");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            // Mensaje de bienvenida
            System.out.println(
                    "\n--------------------| Intérprete de Common Lisp |--------------------");

            // Ciclo para leer el código línea por línea
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println("\n>>> " + linea);
                interprete.interpretar(linea);
            }

            // Cerrar el flujo de lectura
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("[Sistema]: Error al leer el archivo: " + e.getMessage());
        }
    }
}