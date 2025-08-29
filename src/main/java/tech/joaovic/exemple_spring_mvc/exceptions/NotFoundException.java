package tech.joaovic.exemple_spring_mvc.exceptions;

/**
 * Exception para quando um recurso não é encontrado (HTTP 404).
 * Exemplos: usuário não encontrado, CEP não encontrado, etc.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
