package com.davi.bank.user.domain.mappers;

import com.davi.bank.user.domain.dtos.RequestUser;
import com.davi.bank.user.domain.dtos.ResponseUser;
import com.davi.bank.user.domain.models.Users;

public class UserMapper {

    public static Users toEntity(RequestUser userDto) {
         return Users.builder()
                .nomeCompleto(userDto.nomeCompleto())
                .cpf(userDto.cpf())
                .email(userDto.email())
                .senha_hash(userDto.senha())
                .telefone(userDto.telefone())
                .dataNascimento(userDto.dataNascimento()).build();
    }

    public static ResponseUser toDto(Users entity) {
        return ResponseUser.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nomeCompleto(entity.getNomeCompleto())
                .dataNascimento(entity.getDataNascimento())
                .email(entity.getEmail())
                .telefone(entity.getTelefone())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
