package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Interprete interprete = new Interprete();

        try {
            File file = new File("src\\main\\java\\uvg\\edu\\gt\\Codigo.lisp");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            System.out.println(
                    "--------------- Intérprete de Common Lisp (Por: Daniel Chet y Cristian Túnchez) ---------------");

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println("\n>>> " + linea);
                interprete.interpretar(linea);
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}