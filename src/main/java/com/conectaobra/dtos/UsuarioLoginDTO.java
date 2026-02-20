package com.conectaobra.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioLoginDTO(
        @Length(min = 3, message = "Nome deve ter mais de 3 caracteres") @NotBlank(message = "Nome deu errado") String nome,
        @Length(min = 3, message = "Senha deve ter mais de 3 caracteres") String senha) {
}