package uvg.edu.gt;

public class Interprete {
    private StackVector<String> stackVector = new StackVector<String>();
    private StackVector<String> stackRecursivo = new StackVector<String>();
    private StackVector<String> codigoRecursivo = new StackVector<String>();
    private Aritmeticas aritmeticas = new Aritmeticas();
    private SETQ setq = new SETQ();
    private Quote quote = new Quote();
    private Predicados predicados = new Predicados();
    private StackArrayList<Defun> stackArraylist = new StackArrayList<Defun>();

    public Interprete() {
    }

    public void interpretar(String codigo) {

        verificarSintaxis(codigo);

        String nombreFuncion = "";
        String valorFuncion = "";
        int cantidadCiclo = -2;
        int ciclo = -1;
        boolean recursivo = false;
        boolean condicional = true;

        boolean var = false;
        while (var == false) {

            char[] characters = codigo.toCharArray();
            char character = characters[0];
            String clave2 = Character.toString(character);

            if (recursivo && condicional == true) {
                stackVector = codigoRecursivo;
            }

            if (recursivo == true && condicional == false) {
                String parametro = regresarParametro(nombreFuncion);
                String valor = String.valueOf(ciclo);
                stackVector = cambiarParametro(stackVector, parametro, valor);
                int variablecantidad = 0;
                StackVector<String> valores = new StackVector<String>();
                int posicionfuncion = -5;
                for (int i = 0; i < stackVector.size(); i++) {
                    String llave = stackVector.get(i);
                    if (llave.equals(nombreFuncion)) {
                        valores.push(stackRecursivo.get(variablecantidad));
                        posicionfuncion = i;
                        variablecantidad++;
                    }
                    if (i != (posicionfuncion + 1) && i != (posicionfuncion + 2) && i != (posicionfuncion + 3)
                            && i != posicionfuncion) {
                        valores.push(llave);
                    }

                }
                stackVector = valores;
            }

            String clave = stackVector.get(0);

            if (clave.equals("-") || clave.equals("+") || clave.equals("*") || clave.equals("/")) {

                stackVector = setq.buscarValor(stackVector);

                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }

                if (expresion.contains(" ")) {
                    String resultado = aritmeticas.calculadoraEspaciada(expresion);

                    if (recursivo) {
                        stackRecursivo.removeFirst();
                        stackRecursivo.push(resultado);
                        condicional = true;
                        if (ciclo == cantidadCiclo) {
                            System.out.println(resultado);
                        }
                    } else if (!recursivo) {
                        System.out.println(resultado);
                    }
                    var = true;
                } else {
                    String resultado = aritmeticas.calculadoraPrefix(expresion);

                    if (recursivo) {
                        stackRecursivo.removeFirst();
                        stackRecursivo.push(resultado);
                        condicional = true;
                        if (ciclo == cantidadCiclo) {
                            System.out.println(resultado);
                        }
                    } else if (!recursivo) {
                        System.out.println(resultado);
                    }
                    var = true;
                }
            } else if (clave.equalsIgnoreCase("setq")) {

                if (stackVector.size() > 3) {
                    System.out.println("ERROR, Operacion Invalida");
                } else {
                    setq.agregarValor(stackVector.get(1), stackVector.get(2));
                }
                var = true;
            } else if (clave.equals("defun")) {

                String codigoFuncion = codigo;
                if (!(stackVector.size() >= 4)) {
                    System.out.println("ERROR, Operacion Invalida");
                } else {
                    String expresion = "";
                    for (int i = 3; i < stackVector.size(); i++) {
                        expresion += stackVector.get(i);
                        expresion += " ";
                    }
                    Defun nuevaFuncion = new Defun(stackVector.get(1), stackVector.get(2), expresion,
                            codigoFuncion);
                    stackArraylist.push(nuevaFuncion);
                }
                var = true;
            } else if (clave2.equals("'")) {

                char[] charac = codigo.toCharArray();
                String expresion = "";
                for (char c : charac) {
                    String caracter = Character.toString(c);
                    if (!caracter.equals("'")) {
                        expresion += c;
                    }
                }
                quote.returnQuote(expresion);
                var = true;
            } else if (clave.equalsIgnoreCase("QUOTE")) {

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
                quote.returnQuote(expresion);
                var = true;
            } else if (clave.equalsIgnoreCase("cond")) {

                if (recursivo) {
                    String parametro = regresarParametro(nombreFuncion);
                    stackVector = cambiarParametro(stackVector, parametro, String.valueOf(ciclo));
                }
                stackVector = setq.buscarValor(stackVector);
                String expresion = "";
                for (int i = 1; i <= 3; i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                String respuesta = predicados.evaluarPredicado(expresion);

                if (stackVector.size() == 4) {
                    System.out.println(respuesta);
                    var = true;
                } else {
                    if (respuesta.equals("true")) {
                        cambiarTrue(codigoFuncion(nombreFuncion));
                        if (stackVector.size() == 1) {
                            String respuestafinal = stackVector.get(0);
                            if (esNumero(respuestafinal)) {
                                if (recursivo) {
                                    if (cantidadCiclo == ciclo) {
                                        System.out.println(respuestafinal);
                                    }
                                    stackRecursivo.push(respuestafinal);
                                    var = true;
                                } else {
                                    System.out.println("La respuesta es: " + respuestafinal);
                                    var = true;
                                }
                            } else {
                                if (respuestafinal.equals(regresarParametro(nombreFuncion))) {
                                    if (recursivo) {
                                        if (ciclo == cantidadCiclo) {
                                            System.out.println(ciclo);
                                        }
                                        stackRecursivo.push(String.valueOf(ciclo));
                                        var = true;
                                    } else {
                                        System.out.println("La respuesta es: " + valorFuncion);
                                        var = true;
                                    }
                                }
                            }
                        }
                    } else if (respuesta.equals("false")) {
                        cambiarFalse(codigoFuncion(nombreFuncion));
                        condicional = false;
                    }
                }
            } else if (clave.equalsIgnoreCase("list")) {

                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                String respuesta = predicados.evaluarPredicado(expresion);
                System.out.println(respuesta);
                var = true;
            } else if (clave.equalsIgnoreCase("atom")) {

                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                String respuesta = predicados.evaluarPredicado(expresion);
                System.out.println(respuesta);
                var = true;
            } else if (setq.encontrarValor(clave)) {

                stackVector = setq.buscarValor(stackVector);
                String expresion = "";
                for (int i = 0; i < stackVector.size(); i++) {
                    expresion += stackVector.get(i);
                    expresion += " ";
                }
                System.out.println(expresion);
                var = true;
            } else if (!verificarFuncion(clave).equals("null")) {
                nombreFuncion = clave;
                String valor = stackVector.get(1);
                valorFuncion = valor;
                String nuevocodigo = verificarFuncion(clave);
                stackVector.clear();
                verificarSintaxis(nuevocodigo);
                if (!stackVector.isInStack(nombreFuncion)) {
                    String parametro = regresarParametro(clave);
                    stackVector = cambiarParametro(stackVector, parametro, valor);
                } else {
                    recursivo = true;
                    codigoRecursivo = stackVector;
                    cantidadCiclo = Integer.parseInt(valor);
                }
            } else {
                System.out.println("Codigo incorrecto, operaciones no validas");
                var = true;
            }

            if (recursivo && (cantidadCiclo != ciclo) && (cantidadCiclo != -2)) {
                var = false;
            }

            if (recursivo && (cantidadCiclo != ciclo) && (cantidadCiclo != -2) && condicional == true) {
                ciclo++;
            }
        }
        stackVector.clear();
        stackRecursivo.clear();
        codigoRecursivo.clear();
    }

    public void verificarSintaxis(String linea) {
        String nuevaLinea = linea.replace("(", "");
        nuevaLinea = nuevaLinea.replace(")", "");
        String[] lineasplit = nuevaLinea.split(" ");
        for (int i = 0; i < lineasplit.length; i++) {
            stackVector.push(lineasplit[i]);
        }
    }

    public boolean verificarParentesis(String codigo) {
        int contadora = 0;
        int contadorc = 0;
        for (int i = 0; i < codigo.length(); i++) {
            if (codigo.charAt(i) == '(') {
                contadora++;
            } else if (codigo.charAt(i) == ')') {
                contadorc++;
            }
        }
        if (contadora == contadorc) {
            return true;
        }
        return false;
    }

    public String verificarFuncion(String nombrefuncion) {
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getInstruccion();
            }
        }
        return "null";
    }

    public int cantidadFuncion(String nombrefuncion) {
        int cantidad = 0;
        for (int i = 0; i < stackVector.size(); i++) {
            if (nombrefuncion.equals(stackVector.get(i))) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public String codigoFuncion(String nombrefuncion) {
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getCodigo();
            }
        }
        return "null";
    }

    public String regresarParametro(String nombrefuncion) {
        for (int i = 0; i < stackArraylist.size(); i++) {
            if (nombrefuncion.equals(stackArraylist.get(i).getNombre())) {
                return stackArraylist.get(i).getParametro();
            }
        }
        return "null";
    }

    public StackVector<String> cambiarParametro(StackVector<String> stackVector, String parametro, String valor) {
        StackVector<String> valores = new StackVector<String>();
        for (int i = 0; i < stackVector.size(); i++) {
            String llave = stackVector.get(i);
            if (llave.equals(parametro)) {
                valores.push(valor);
            } else {
                valores.push(llave);
            }
        }
        return valores;
    }

    public boolean esNumero(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void cambiarTrue(String lineacodigo) {
        stackVector.clear();
        String nuevoCodigo = "";
        String nuevaLinea = lineacodigo.replace("(", "( ");
        nuevaLinea = nuevaLinea.replace(")", " )");
        String[] lineasplit = nuevaLinea.split(" ");
        StackVector<String> nuevoVector = new StackVector<String>();
        for (int i = 0; i < lineasplit.length; i++) {
            nuevoVector.push(lineasplit[i]);
        }
        int contadorParentesisAbierto = 0;
        int contadorParentesisCerrado = 0;
        for (int i = 0; i < nuevoVector.size(); i++) {
            String llave = nuevoVector.get(i);
            if (llave.equals("(")) {
                contadorParentesisAbierto++;
            } else if (llave.equals(")")) {
                contadorParentesisCerrado++;
            }
            if (contadorParentesisAbierto == 5 && contadorParentesisCerrado != 5 && (llave.equals("(") == false)
                    && (llave.equals(")") == false)) {
                nuevoCodigo += llave;
                nuevoCodigo += " ";
            }
        }
        verificarSintaxis(nuevoCodigo);
    }

    public void cambiarFalse(String lineacodigo) {
        stackVector.clear();
        String nuevoCodigo = "";
        String nuevaLinea = lineacodigo.replace("(", "( ");
        nuevaLinea = nuevaLinea.replace(")", " )");
        String[] lineasplit = nuevaLinea.split(" ");
        StackVector<String> nuevoVector = new StackVector<String>();
        for (int i = 0; i < lineasplit.length; i++) {
            nuevoVector.push(lineasplit[i]);
        }
        int contadorParentesisAbierto = 0;
        int contadorParentesisCerrado = 0;
        for (int i = 0; i < nuevoVector.size(); i++) {
            String llave = nuevoVector.get(i);
            if (llave.equals("(")) {
                contadorParentesisAbierto++;
            } else if (llave.equals(")")) {
                contadorParentesisCerrado++;
            }
            if (contadorParentesisAbierto >= 6 && contadorParentesisCerrado != 6 && (llave.equals("(") == false)
                    && (llave.equals(")") == false)) {
                nuevoCodigo += llave;
                nuevoCodigo += " ";
            }
        }
        verificarSintaxis(nuevoCodigo);
    }

    public StackArrayList<Defun> getStackArraylist() {
        return stackArraylist;
    }
}