package uvg.edu.gt;

/**
 * La clase Interprete representa un intérprete para lenguaje de código common
 * Lisp. Interpreta el código dado y ejecuta las operaciones correspondientes.
 * 
 * @author Cristian Túnchez, Daniel Chet
 * @version 1.0
 * @since 24/03/2024
 */
public class Interprete {
    private StackVector<String> stackVector = new StackVector<String>();
    private StackVector<String> stackRecursivo = new StackVector<String>();
    private StackVector<String> codigoRecursivo = new StackVector<String>();
    private Aritmeticas aritmeticas = new Aritmeticas();
    private SETQ setq = new SETQ();
    private Quote quote = new Quote();
    private Predicados predicados = new Predicados();
    private StackArrayList<Defun> stackArraylist = new StackArrayList<Defun>();

    /**
     * Constructor predeterminado para la clase Interprete.
     */
    public Interprete() {
    }

    /**
     * Interpreta el código dado.
     *
     * @param codigo El código a interpretar
     */
    public void interpretar(String codigo) {

        verificarSintaxis(codigo);// Se verifica el codigo y pasa al Stack

        // Variables para poder hacer la recursividad
        String nombreFuncion = "";
        String valorFuncion = "";
        int cantidadCiclo = -2;
        int ciclo = -1;
        boolean recursivo = false;
        boolean condicional = true;

        // Ciclo
        boolean var = false;
        while (var == false) {

            // Se ve el primer caracter para la funcion de Quote(')
            char[] characters = codigo.toCharArray();
            char character = characters[0];
            String clave2 = Character.toString(character);

            // Si es recursivo y la variable se encuentra verdadera
            if (recursivo && condicional == true) {
                stackVector = codigoRecursivo;// El codigo de recursividad se coloca en el Stack Principal
            }

            if (recursivo == true && condicional == false) {// Si es recursivo y es falso el condicional
                String parametro = regresarParametro(nombreFuncion);// Encontrar el nombre del parametro
                String valor = String.valueOf(ciclo);// Encontrar el valor del parametro por medio del ciclo
                stackVector = cambiarParametro(stackVector, parametro, valor);// Cambiar el parametro por el valor
                int variablecantidad = 0;// Cantidad de variables a cambiar
                StackVector<String> valores = new StackVector<String>();// Se inicia nuevo stack
                int posicionfuncion = -5;// Iniciar posicion
                // For para recorrer todos los elementos en el stack
                for (int i = 0; i < stackVector.size(); i++) {// Se itera en stack vector
                    String llave = stackVector.get(i);// Se toma el valor de i
                    // Ver si uno de los elementos ya es una llave y remplazar la llave por el valor
                    if (llave.equals(nombreFuncion)) {// Buscar funcion
                        valores.push(stackRecursivo.get(variablecantidad));// Sacar e ingresa el valor del stack
                                                                           // recursivo
                        posicionfuncion = i;
                        variablecantidad++;
                    }
                    // Si es lo siguiente al nombre de la funcion no ingresa al nuevo Stack
                    if (i != (posicionfuncion + 1) && i != (posicionfuncion + 2) && i != (posicionfuncion + 3)
                            && i != posicionfuncion) {
                        valores.push(llave);
                    }

                }
                stackVector = valores;// El nuevo Stack se convierte en el Stack principal
            }

            // Se toma el valor 0 para saber el tipo de operacion
            String clave = stackVector.get(0);

            // ------------------------- Aritmeticas -------------------------
            if (clave.equals("-") || clave.equals("+") || clave.equals("*") || clave.equals("/")) {

                // Se busca si hay algun Setq y se intercambia
                stackVector = setq.buscarValor(stackVector);
                // Se forma la expresion que se encuentra en el Stack
                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }

                // Si tiene espacios se utiliza Calculadora Espaciada
                if (expresion.contains(" ")) {
                    String resultado = aritmeticas.calculadoraEspaciada(expresion);

                    if (recursivo) {// Si es recursivo
                        stackRecursivo.removeFirst();// Se remueve el primer valor del Stack de los resultados de
                                                     // recursividad
                        stackRecursivo.push(resultado);// Se ingresa el resultado nuevo al Stack de recursividad
                        condicional = true;// La condicional se vuelve verdadera
                        if (ciclo == cantidadCiclo) {
                            System.out.println(resultado);// Se regresa el resultado
                        }
                    } else if (!recursivo) {
                        System.out.println(resultado);// Se regresa el resultado
                    }
                    var = true;// Se vuelve verdadera la variable del ciclo
                }
                // Si no tiene espacios se usa CalculadoraPrefix
                else {
                    String resultado = aritmeticas.calculadoraPrefix(expresion);

                    if (recursivo) {// Si es recursivo
                        stackRecursivo.removeFirst();// Se remueve el primer valor del Stack de los resultados de
                                                     // recursividad
                        stackRecursivo.push(resultado);// Se ingresa el resultado nuevo al Stack de recursividad
                        condicional = true;// La condicional se vuelve verdadera
                        if (ciclo == cantidadCiclo) {
                            System.out.println(resultado);// Se regresa el resultado
                        }
                    } else if (!recursivo) {
                        System.out.println(resultado);// Se regresa el resultado
                    }
                    var = true;// Se vuelve verdadera la variable del ciclo
                }
            }

            // ------------------------- SETQ -------------------------
            else if (clave.equalsIgnoreCase("setq")) {
                // Se agregan los valores al Hashmap de Setq
                if (stackVector.size() > 3) {// Condicion para evitar errores
                    System.out.println("[Sistema]: Error. La operación no es válida.");
                } else {
                    setq.agregarValor(stackVector.get(1), stackVector.get(2));
                }
                var = true;// Variable del ciclo verdadera
            }

            // ------------------------- Defun -------------------------
            else if (clave.equals("defun")) {
                // Se guarda el codigo completo de la funcion
                String codigoFuncion = codigo;
                // Se toma el codigo de la funcion
                if (!(stackVector.size() >= 4)) {
                    System.out.println("[Sistema]: Error. La operación no es válida.");
                } else {
                    String expresion = "";
                    for (int i = 3; i < stackVector.size(); i++) {
                        expresion += stackVector.get(i);
                        expresion += " ";
                    }
                    // Se guardan todas las variables de la clase funciones
                    Defun nuevaFuncion = new Defun(stackVector.get(1), stackVector.get(2), expresion,
                            codigoFuncion);
                    // Se guarda la instancia de la Funcion en el Stack
                    stackArraylist.push(nuevaFuncion);
                }
                // Variable se hace verdadera
                var = true;

            }
            // ------------------------- Quote (') -------------------------
            else if (clave2.equals("'")) {
                // Se convierte en caracteres
                char[] charac = codigo.toCharArray();
                // Se toma la expresion de caracteres
                String expresion = "";
                for (char c : charac) {
                    String caracter = Character.toString(c);
                    if (!caracter.equals("'")) {
                        expresion += c;
                    }
                }
                // Se usa el Quote
                quote.returnQuote(expresion);
                var = true;
            }

            // ------------------------- Quote -------------------------
            else if (clave.equalsIgnoreCase("QUOTE")) {
                // Se toma la expresion
                String expresion = "";
                String nuevaLinea = codigo.replace("(", "( ");
                nuevaLinea = nuevaLinea.replace(")", " )");
                String[] lineasplit = nuevaLinea.split(" ");
                for (int i = 0; i < lineasplit.length; i++) {
                    if (!lineasplit[i].equalsIgnoreCase("QUOTE") && i != 0 && i != lineasplit.length - 1) {
                        expresion += lineasplit[i];
                        expresion += " ";
                    }
                }
                // Se usa el Quote
                quote.returnQuote(expresion);
                var = true;
            }

            // ------------------------- Condicional -------------------------
            else if (clave.equalsIgnoreCase("cond")) {
                // Si es recursivo
                if (recursivo) {
                    String parametro = regresarParametro(nombreFuncion);// Se revisa el parametro
                    stackVector = cambiarParametro(stackVector, parametro, String.valueOf(ciclo));// Se cambia por el
                                                                                                  // ciclo
                }
                stackVector = setq.buscarValor(stackVector);// Buscar si hay alguna variable de SetQ
                // Se toma la expresion condicional
                String expresion = "";
                for (int i = 1; i <= 3; i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                // Se hace la comparacion
                String respuesta = predicados.evaluarPredicado(expresion);

                if (stackVector.size() == 4) {// verificar si no hay codigo que correr
                    System.out.println(respuesta);
                    var = true;
                } else {// Si hay, se corre el codigo
                    if (respuesta.equals("true")) {// Si es verdadero
                        cambiarTrue(codigoFuncion(nombreFuncion));// Se cambia por el codigo de true
                        if (stackVector.size() == 1) {// Si es solo una variable se hace la operacion
                            String respuestafinal = stackVector.get(0);// Se toma la variable
                            if (esNumero(respuestafinal)) {// Se revisa si es un numero
                                if (recursivo) {// Si es recursivo
                                    if (cantidadCiclo == ciclo) {
                                        System.out.println(respuestafinal);// Se imprime
                                    }
                                    stackRecursivo.push(respuestafinal);// Y se ingresa al Stack de recursividad
                                    var = true;
                                } else {
                                    System.out.println("La respuesta es: " + respuestafinal);// Si no, solo se imprime
                                                                                             // la respuesta
                                    var = true;
                                }
                            } else {// Si es una variable o letra
                                if (respuestafinal.equals(regresarParametro(nombreFuncion))) {// Se verifica que sea un
                                                                                              // parametro
                                    if (recursivo) {// Si es recursivo se cambia el valor, por el valor del ciclo
                                        if (ciclo == cantidadCiclo) {
                                            System.out.println(ciclo);
                                        }
                                        stackRecursivo.push(String.valueOf(ciclo));
                                        var = true;
                                    } else {// Si no, solo se imprime
                                        System.out.println("La respuesta es: " + valorFuncion);
                                        var = true;// La variable se hace verdadera
                                    }
                                }
                            }
                        }
                    } else if (respuesta.equals("false")) {// Si es falso
                        cambiarFalse(codigoFuncion(nombreFuncion));// Se cambia el codigo por el codigo de False
                        condicional = false;// Se vuelve la condicional falsa
                    }
                }
            }

            // ------------------------- List -------------------------
            else if (clave.equalsIgnoreCase("list")) {
                // Se toma la expresion
                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                // Se usa el metodo del predicado
                String respuesta = predicados.evaluarPredicado(expresion);
                System.out.println(respuesta);// Se imprime
                var = true;// La variable se hace verdadera
            }

            // ------------------------- Atom -------------------------
            else if (clave.equalsIgnoreCase("atom")) {
                // Se toma la expresion
                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                // Se usa el metodo del predicado
                String respuesta = predicados.evaluarPredicado(expresion);
                System.out.println(respuesta);// Se imprime
                var = true;// La variable se hace verdadera
            }

            // ------------------------- Variables -------------------------
            else if (setq.encontrarValor(clave)) {
                // Se cambia por su valor
                stackVector = setq.buscarValor(stackVector);
                // Se toma la expresion
                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                // Se imprime la expresion
                System.out.println(expresion);
                var = true;// La variable se hace verdadera
            }

            // ------------------------- Funciones -------------------------
            else if (!verificarFuncion(clave).equals("null")) {
                nombreFuncion = clave;// El nombre de la funcion se guarda
                String valor = stackVector.get(1);// Se toma el parametro
                valorFuncion = valor;// Se guarda el parametro
                String nuevocodigo = verificarFuncion(clave);// Se obtiene el codigo de la funciom
                stackVector.clear();// Se limpia el Stack
                verificarSintaxis(nuevocodigo);// Se ingresa el nuevo codigo al Stack
                if (!stackVector.isInStack(nombreFuncion)) {// Si el nombre de la funcion no se encuentra en el Stack
                    String parametro = regresarParametro(clave);// Se cambia el parametro
                    stackVector = cambiarParametro(stackVector, parametro, valor);// Y se pasa el codigo
                } else {// Si el nombre de la funcion se encuentra en el Stack
                    recursivo = true;// Es recursivo
                    codigoRecursivo = stackVector;// El codigo se almacena en el Stack de codigoRecursivo
                    cantidadCiclo = Integer.parseInt(valor);// Y se inicia la variable de la cantidad de ciclo
                }
            } else {
                System.out.println("[Sistema]: Error. Existen operaciones no válidas.");// Si no es ninguna el codigo es
                // incorrecto
                var = true;
            }

            if (recursivo && (cantidadCiclo != ciclo) && (cantidadCiclo != -2)) {// Si es recursivo y el ciclo no ha
                                                                                 // terminado, sigue haciendo
                                                                                 // operaciones
                var = false;
            }

            if (recursivo && (cantidadCiclo != ciclo) && (cantidadCiclo != -2) && condicional == true) {// Si es
                                                                                                        // recursivo, el
                                                                                                        // ciclo no
                                                                                                        // termina y
                                                                                                        // esta en el
                                                                                                        // cond, se suma
                                                                                                        // uno al ciclo
                // Sumar el ciclo del parametro
                ciclo++;
            }
        }
        stackVector.clear();// Se limpia el Stack Prinicipal
        stackRecursivo.clear();// Se limpia el Stack Recursivo
        codigoRecursivo.clear();// Se limpia el Almacenamiento del Codigo Recursivo
    }

    /**
     * Verifica la sintaxis de la línea de código dada.
     *
     * @param linea La línea de código a verificar
     */
    public void verificarSintaxis(String linea) {
        // Se reemplazan los parentesis por espacios en blanco
        String nuevaLinea = linea.replace("(", "");
        nuevaLinea = nuevaLinea.replace(")", "");

        // Se separa el codigo en un arry
        String[] lineasplit = nuevaLinea.split(" ");
        // Se itera en el array y se guarda en el Stack
        for (int i = 0; i < lineasplit.length; i++) {
            stackVector.push(lineasplit[i]);
        }
    }

    /**
     * Verifica si los paréntesis en el código están balanceados.
     *
     * @param codigo El código a verificar para paréntesis balanceados
     * @return Verdadero si los paréntesis están balanceados, de lo contrario, falso
     */
    public boolean verificarParentesis(String codigo) {
        // Se inician los contadores de parentesis abiertos y cerrados
        int contadora = 0;
        int contadorc = 0;
        // Se cuenta la cantidad de parentesis
        for (int i = 0; i < codigo.length(); i++) {
            if (codigo.charAt(i) == '(') {
                contadora++;
            } else if (codigo.charAt(i) == ')') {
                contadorc++;
            }
        }
        // Si el contador es igual de los dos parentesis se termina la espera del
        // ingreso
        if (contadora == contadorc) {
            return true;
        }
        // Si no, se sigue esperando a que sean iguales
        return false;
    }

    /**
     * Verifica si una función dada existe en la pila de funciones.
     *
     * @param nombrefuncion El nombre de la función a verificar
     * @return La instrucción correspondiente a la función si se encuentra, de lo
     *         contrario "null"
     */
    public String verificarFuncion(String nombrefuncion) {
        // Se itera en el Arraylist de las funcions, buscando el nombre de la funcions
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getInstruccion();// Si se encuentra, se regresa el codigo de la funcion
            }
        }
        // Si no se encuentra se regresa null
        return "null";
    }

    /**
     * Cuenta las ocurrencias de una función en el vector de pila.
     *
     * @param nombrefuncion El nombre de la función a contar
     * @return La cantidad de ocurrencias de la función
     */
    public int cantidadFuncion(String nombrefuncion) {
        // Se inicializa la cantidad
        int cantidad = 0;
        // Se itera en el stack
        for (int i = 0; i < stackVector.size(); i++) {
            if (nombrefuncion.equals(stackVector.get(i))) {
                cantidad++;// Si se encuentra el nombre de la funcion, se suma 1
            }
        }
        // Se regresa la cantidad
        return cantidad;
    }

    /**
     * Devuelve el código correspondiente a una función.
     *
     * @param nombrefuncion El nombre de la función
     * @return El código de la función si se encuentra, de lo contrario "null"
     */
    public String codigoFuncion(String nombrefuncion) {
        // Se itera en el Arraylist de las funcions
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getCodigo();// Si se encuentra el nombre de la funcion, se regresa
                                                         // el codigo completo de toda la funcion
            }
        }
        // Si no, se regresa null
        return "null";
    }

    /**
     * Devuelve el parámetro de una función.
     *
     * @param nombrefuncion El nombre de la función
     * @return El parámetro de la función si se encuentra, de lo contrario "null"
     */
    public String regresarParametro(String nombrefuncion) {
        // Se itera en el Arraylist de las funcions
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getParametro();// Si se encuentra el nombre de la funcion, se regresa el
                                                            // nombre del parametro
            }
        }
        // Si no, se regresa null
        return "null";
    }

    /**
     * Reemplaza un parámetro con un valor en el vector de pila.
     *
     * @param stackVector El vector de pila a ser modificado
     * @param parametro   El parámetro a ser reemplazado
     * @param valor       El valor para reemplazar el parámetro
     * @return El vector de pila modificado
     */
    public StackVector<String> cambiarParametro(StackVector<String> stackVector, String parametro, String valor) {
        // Se inicia un nuevo Stack
        StackVector<String> valores = new StackVector<String>();
        // For para recorrer todos los elementos en el stack
        for (int i = 0; i < stackVector.size(); i++) {
            // Se toma cada elemento del Stack
            String llave = stackVector.get(i);

            // Encontrar si uno de los elementos ya es una llave y remplazar la llave por el
            // valor
            if (llave.equals(parametro)) {
                valores.push(valor);// Si se encuentra el parametro, se cambia por el valor de este
            } else {
                valores.push(llave);// Si no, solo se regresa el elemento del Stack
            }
        }
        // Se regresa el Stack de los valores
        return valores;
    }

    /**
     * Comprueba si una cadena es un número.
     *
     * @param string La cadena a ser comprobada
     * @return Verdadero si la cadena es un número, de lo contrario falso
     */
    public boolean esNumero(String string) {
        // Si el elemento es numero se regresa true
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;// Si no, se regresa false
        }
    }

    /**
     * Modifica el código cuando una condición es verdadera.
     *
     * @param lineacodigo El código a ser modificado
     */
    public void cambiarTrue(String lineacodigo) {
        // Se limpia el Stack principal y se hace un nuevo codigo
        stackVector.clear();
        String nuevoCodigo = "";

        // Se agregan espacios a los parentesis
        String nuevaLinea = lineacodigo.replace("(", "( ");
        nuevaLinea = nuevaLinea.replace(")", " )");

        // Se separa el string en un array
        String[] lineasplit = nuevaLinea.split(" ");
        // Se hace un nuevo stack
        StackVector<String> nuevoVector = new StackVector<String>();
        // Se itera en el array del codigo ingresado
        for (int i = 0; i < lineasplit.length; i++) {
            nuevoVector.push(lineasplit[i]);// El codigo se ingresa al stack
        }
        // Se inician las variables de los parentesis
        int contadorParentesisAbierto = 0;
        int contadorParentesisCerrado = 0;
        // Se itera en el nuevo stack
        for (int i = 0; i < nuevoVector.size(); i++) {
            // Se toma el valor del stack
            String llave = nuevoVector.get(i);

            // Encontrar si hay parentesis y agregar la cuenta de los parentesis
            if (llave.equals("(")) {
                contadorParentesisAbierto++;
            } else if (llave.equals(")")) {
                contadorParentesisCerrado++;
            }
            // Revisar la posicion de los parentesis y tomar el codigo de esa posicion
            // especifica
            if (contadorParentesisAbierto == 5 && contadorParentesisCerrado != 5 && (llave.equals("(") == false)
                    && (llave.equals(")") == false)) {
                nuevoCodigo += llave;
                nuevoCodigo += " ";
            }
        }
        // Usar el metodo verificarSintaxis y asi ingresa el codigo al stack principal
        verificarSintaxis(nuevoCodigo);
    }

    /**
     * Modifica el código cuando una condición es falsa.
     *
     * @param lineacodigo El código a ser modificado
     */
    public void cambiarFalse(String lineacodigo) {
        // Limpiar el stack principal y crear un nuevo codigo
        stackVector.clear();
        String nuevoCodigo = "";
        // Agregar espacios a los parentesis del codigo ingresado
        String nuevaLinea = lineacodigo.replace("(", "( ");
        nuevaLinea = nuevaLinea.replace(")", " )");
        // Separar todo en un array
        String[] lineasplit = nuevaLinea.split(" ");
        // Crear un nuevo stack
        StackVector<String> nuevoVector = new StackVector<String>();
        // Iterar en el array del codigo ingresado
        for (int i = 0; i < lineasplit.length; i++) {
            nuevoVector.push(lineasplit[i]);// Ingresar el codigo al stack
        }
        // Iniciar las variables de los parentesis
        int contadorParentesisAbierto = 0;
        int contadorParentesisCerrado = 0;
        // Se itera en el nuevo stack
        for (int i = 0; i < nuevoVector.size(); i++) {
            // Tomar el valor de cada elemento del stack
            String llave = nuevoVector.get(i);

            // Encontrar si hay un parentesis y aumentar la cuenta
            if (llave.equals("(")) {
                contadorParentesisAbierto++;
            } else if (llave.equals(")")) {
                contadorParentesisCerrado++;
            }
            // Si se llega a la posicion especifica, tomar el codigo de esa posicion
            if (contadorParentesisAbierto >= 6 && contadorParentesisCerrado != 6 && (llave.equals("(") == false)
                    && (llave.equals(")") == false)) {
                nuevoCodigo += llave;
                nuevoCodigo += " ";
            }
        }
        // Por medio del metodo de verificarSintaxis, ingresar el codigo tomado al stack
        // principal
        verificarSintaxis(nuevoCodigo);
    }

    /**
     * Getter para la pila de funciones definidas.
     *
     * @return La pila de funciones definidas
     */
    public StackArrayList<Defun> getStackArraylist() {
        return stackArraylist;
    }
}