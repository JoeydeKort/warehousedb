package be.intecbrussel.exceptions;

public class CustomProductException extends RuntimeException {

    public CustomProductException(String message) {
        super(message);
    }

    public CustomProductException(String message, Throwable cause) {
        super(message, cause);
    }

}
