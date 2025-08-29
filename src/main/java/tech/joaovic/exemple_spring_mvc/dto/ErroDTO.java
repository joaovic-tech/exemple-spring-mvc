package tech.joaovic.exemple_spring_mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Classe padronizada para respostas de erro da API.
 * Garante que todos os erros retornem o formato: "errors": [ "Erro1", "Erro1", ]
 */
@Data
@AllArgsConstructor
public class ErroDTO {
    private List<String> errors;
}
