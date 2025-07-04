package com.davi.bank.user.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record RequestUser (
        @NotBlank(message = "o campo nome é obrigatório")
        @Size(min = 1, message = "nome muito curto")
        @JsonProperty("nome_completo")
        String nomeCompleto,
        @NotBlank(message = "o campo cpf é obrigatório")
        @CPF(message = "o campo CPF está invalido")
        String cpf,
        @NotBlank(message = "o campo email é obrigatório")
        @Email(message = "o email está invalido")
        String email,
        @NotBlank(message = "o campo senha é obrigatório")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&(){}\\[\\]#^~;:.,<>+=_|-])[A-Za-z\\d@$!%*?&(){}\\[\\]#^~;:.,<>+=_|-]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, com pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial"
        )
        String senha,
        @NotBlank(message = "o campo telefone é obrigatório")
        @Pattern(
                regexp = "^\\(?[1-9]{2}\\)? ?9?[0-9]{4}-?[0-9]{4}$",
                message = "Número de telefone inválido. Ex: (11) 91234-5678"
        )
        String telefone,
        @NotNull(message = "o campo data de nascimento é obrigatório")
        @JsonProperty("data_de_nascimento")
        LocalDate dataNascimento
){}
