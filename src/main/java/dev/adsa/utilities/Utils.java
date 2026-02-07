package dev.adsa.utilities;

import java.util.Scanner;

/**
 * Clase que contiene los metodos para interactuar con el usuario
 * 
 * @author Aitor de Santos
 * @version 1.0
 */
public class Utils {

    /**
     * Funcion para leer un numero entero
     * 
     * @param sc Objeto Scaner para leer por consola
     * @return Devuelve el numero leido
     */
    public static int readInt(Scanner sc) {
        int numero = 0;
        boolean continuar = true;
        do {
            if (sc.hasNextInt()) {
                numero = sc.nextInt();
                continuar = false;
                sc.nextLine();
            } else {
                System.out.println("Porfavor introduzca un numero");
                sc.nextLine();
            }
        } while (continuar);
        return numero;
    }

    /**
     * Funcion para leer un numero double mayor o igual a un minimo y menor o igual
     * a un maximo
     * 
     * @param sc  Objeto Scanner para leer por consola
     * @param min Numero minimo permitido
     * @param max Numero maximo permitido
     * @return Devuelve el numero leido
     */
    public static double readPositiveDouble(Scanner sc, double min, double max) {
        double numero = 0;
        boolean continuar = true;
        do {
            if (sc.hasNextDouble()) {
                numero = sc.nextDouble();
                if (numero >= min && numero <= max) {
                    continuar = false;
                    sc.nextLine();
                } else {
                    System.out.println("Introzuca un numero mayor que " + min);
                }
            } else {
                System.out.println("Porfavor introduzca un numero utilizando si fuese necesario \",\"");
                sc.nextLine();
            }
        } while (continuar);
        return numero;
    }

    /**
     * Funcion para leer un numero entero mayor o igual a un minimo y menor o igual
     * a un maximo
     * 
     * @param sc  Objeto Scanner para leer por consola
     * @param min Numero minimo permitido
     * @param max Numero maximo permitido
     * @return Devuelve el numero leido
     */
    public static int readPositiveInt(Scanner sc, int min, int max) {
        int numero = 0;
        boolean continuar = true;
        do {
            if (sc.hasNextInt()) {
                numero = sc.nextInt();
                if (numero >= min && numero <= max) {
                    continuar = false;
                    sc.nextLine();
                } else {
                    System.out.printf("Introzuca un numero mayor o igual a %d y menor o igual a %d %n", min, max);
                }
            } else {
                System.out.println("Porfavor introduzca un numero");
                sc.nextLine();
            }
        } while (continuar);
        return numero;
    }

    /**
     * Funcion para leer un numero long
     * 
     * @param sc Objeto Scaner para leer por consola
     * @return Devuelve el numero leido
     */
    public static long readLong(Scanner sc) {
        long numero = 0;
        if (sc.hasNextLong()) {
            numero = sc.nextLong();
            sc.nextLine();
        } else {
            System.out.println("Porfavor introduzca un numero");
            sc.nextLine();
        }
        return numero;
    }

    /**
     * Metodo para leer una String
     * 
     * @param sc Objeto Scaner para leer por consola
     * @return Devuelve la String leido
     */
    public static String readString(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Funcion para leer un String con longitud entre min y max
     * 
     * @param sc  Objeto Scanner para leer por consola
     * @param min Longitud minima del String
     * @param max Longitud maxima del String
     * @return Devuelve el String leido
     */
    public static String readString(Scanner sc, int min, int max) {
        String frase = "";
        boolean stop = false;
        do {
            frase = sc.nextLine();
            if (frase.length() >= min && frase.length() <= max) {
                stop = true;
            } else {
                System.out.printf("La cadena de texto tiene que ser mayor o igual a %d y menor o igual a %d %n", min,
                        max);
            }
        } while (!stop);
        return frase;
    }
}