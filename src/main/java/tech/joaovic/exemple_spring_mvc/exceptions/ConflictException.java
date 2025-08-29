package tech.joaovic.exemple_spring_mvc.exceptions;

/**
 * Exception para quando há conflito de dados (HTTP 409).
 * Exemplos: email já existe, dados duplicados, etc.
 */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
