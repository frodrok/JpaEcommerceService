package se.fredrikandthenurses.exception;

public class ProductException extends Exception {
    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductException(Throwable cause) {
        super(cause);
    }
}
