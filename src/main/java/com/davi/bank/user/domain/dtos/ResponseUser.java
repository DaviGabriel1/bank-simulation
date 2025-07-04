package com.davi.bank.user.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Builder
public record ResponseUser(
        String id,
        @JsonProperty("nome_completo")
        String nomeCompleto,
        String cpf,
        String email,
        String telefone,
        @JsonProperty("data_de_nascimento")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        @JsonProperty("criado_em")
        Instant createdAt,
        @JsonProperty("atualizado_em")
        Instant updatedAt
) {
}
