package dev.adsa.exceptions;


/**
 * Excepción que se lanza cuando no se han proporcionado suficientes
 * datos para realizar una operación.
 */
public class InsufficientDataException extends Exception {

    /**
     * Constructor que crea una instancia de InsufficientDataException con el mensaje
     * especificado.
     *
     * @param message El mensaje de la excepción.
     */
    public InsufficientDataException(String message) {
        super(message);
    }
}