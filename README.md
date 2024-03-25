# Proyecto 1 - Interprete de LISP

Desarrollo de un intérprete LISP para un subconjunto sencillo de instrucciones de alguno de los dos dialectos principales (Common LISP y Scheme).

## Información

Universidad del Valle de Guatemala  
Facultad de Ingeniería  
Departamento de Ciencias de la Computación  
CC2016 - Algoritmos y Estructura de Datos  
Guatemala, 24 de marzo de 2024

## Colaboradores

- Cristian Túnchez (231359)
- Daniel Chet (231177)

## Controlador de Versiones

[Repositorio Proyecto-1-AED](https://github.com/Tunchxz/Proyecto-1-AED "Enlace a GitHub")

## Video Tutorial

[¿Cómo usar el interprete?](https://youtu.be/wvS4_JqmGAc "Enlace a YouTube")

## Diagramas UML (Clases, Casos de Uso, Secuencial)

[Ver los diagramas del Interprete](https://www.canva.com/design/DAGAR5kPbSE/bUVoJh0IDMsjDebSzFNBOw/view?utm_content=DAGAR5kPbSE&utm_campaign=designshare&utm_medium=link&utm_source=editor "Enlace a Canva")

## Guía de Cómo Usar el Interprete

1. Respetar espacios entre caracteres, por ejemplo: (+ 3 2), existe un espacio entre el operador y entre ambos operandos. Lo anterior no aplica a las funcionalidades que requieren de palabras enteras, entiendase: defun, setq, cond, etc.

2. La operación a realizar deberá estar escrita en una sola línea de código. Por ejemplo: (defun cubo (n) (_ (_ n n) n)). Importante tomar en cuenta esto, pues en common LISP las funciones pueden ser descritas a lo largo de varias lineas de código.

3. Utilizar misma cantidad de parentesis abiertos que cerrados.

4. Debido a la complejidad de la recursividad, algunas funcionalidades del interprete no aceptan recursividad. Ejemplo de lo anterior es, SETQ. El interprete es incapaz de poder crear un variable a partir de algo como esto: (setq x (+ 1 2)), en su lugar, solo regresara un error al usuario. Sin embargo, funcionalidades importantes como defun si aceptan este tipo de recursividad y como ejemplo tenemos las funciones fibonacci y conversion dentro del archivo "Codigo.lisp" que se incluye en el proyecto.
