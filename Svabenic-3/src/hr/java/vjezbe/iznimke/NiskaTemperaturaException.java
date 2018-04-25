package hr.java.vjezbe.iznimke;

/**
 * Predstavlja iznimku koja se baca prilikom preniske temperature
 *
 * @author Marko
 * @version 1.0
 */
public class NiskaTemperaturaException extends RuntimeException {
    public NiskaTemperaturaException() {
    }

    public NiskaTemperaturaException(String message) {
        super(message);
    }

    public NiskaTemperaturaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NiskaTemperaturaException(Throwable cause) {
        super(cause);
    }
}
