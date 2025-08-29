package tech.joaovic.exemple_spring_mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.joaovic.exemple_spring_mvc.dto.ErroDTO;
import tech.joaovic.exemple_spring_mvc.exceptions.ConflictException;
import tech.joaovic.exemple_spring_mvc.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manipula erros de recursos não encontrados (404)
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroDTO> handleNotFoundException(NotFoundException ex) {
        ErroDTO error = new ErroDTO(List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Manipula erros de conflito de dados (409)
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErroDTO> handleConflictException(ConflictException ex) {
        ErroDTO error = new ErroDTO(List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Manipula erros de validação de campos (400)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErroDTO error = new ErroDTO(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Manipula erros gerais de IllegalArgumentException (400)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErroDTO error = new ErroDTO(List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Manipula erros não mapeados (500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDTO> handleGenericException(Exception ex) {
        ErroDTO error = new ErroDTO(List.of("Erro interno do servidor"));
        System.err.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
