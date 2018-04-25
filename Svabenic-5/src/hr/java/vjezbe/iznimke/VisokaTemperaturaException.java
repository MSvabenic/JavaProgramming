package hr.java.vjezbe.iznimke;

/**
 * Predstavlja iznimku koja se baca prilikom previsoke temperature
 *
 * @author Marko
 * @version 1.0
 */
public class VisokaTemperaturaException extends Exception{
    public VisokaTemperaturaException() {
    }

    public VisokaTemperaturaException(String message) {
        super(message);
    }

    public VisokaTemperaturaException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisokaTemperaturaException(Throwable cause) {
        super(cause);
    }
}
