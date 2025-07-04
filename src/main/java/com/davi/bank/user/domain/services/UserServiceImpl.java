package com.davi.bank.user.domain.services;

import com.davi.bank.user.UserService;
import com.davi.bank.user.domain.dtos.RequestUser;
import com.davi.bank.user.domain.dtos.ResponseUser;
import com.davi.bank.user.domain.exceptions.DuplicatedUniqueFields;
import com.davi.bank.user.domain.exceptions.NotFoundException;
import com.davi.bank.user.domain.mappers.UserMapper;
import com.davi.bank.user.domain.models.Users;
import com.davi.bank.user.domain.repositories.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<ResponseUser> findAll() {
        List<ResponseUser> responsesUsers = new ArrayList<>();
        List<Users> allUsers = this.userRepository.findAll();
        for(Users user : allUsers) {
            ResponseUser responseUser = UserMapper.toDto(user);
            responsesUsers.add(responseUser);
        }
        return responsesUsers;
    }

    @Override
    public ResponseUser findById(String id) {
        Optional<Users> userOptional = this.userRepository.findById(id);
        if(userOptional.isEmpty()) throw new NotFoundException("usuário não existe");
        return UserMapper.toDto(userOptional.get());
    }

    @Override
    public ResponseUser save(RequestUser userDto) {
        try {
            Users user = UserMapper.toEntity(userDto);
            Users createdUser = this.userRepository.save(user);
            return UserMapper.toDto(createdUser);
        } catch(DataIntegrityViolationException integrityViolationException) {
            String message = customizedDuplicatedExceptionMessage(userDto);
            throw new DuplicatedUniqueFields(message);
        }
    }

    @Override
    public ResponseUser update(String id, RequestUser userDto) {
        try{
            boolean userExist = this.userRepository.existsById(id);
            if(!userExist) throw new NotFoundException("usuário não existe");
            Users updatedUser = UserMapper.toEntity(userDto);
            updatedUser.setId(id);
            return UserMapper.toDto(this.userRepository.save(updatedUser));
        } catch(DataIntegrityViolationException integrityViolationException) {
            String message = customizedDuplicatedExceptionMessage(userDto);
            throw new DuplicatedUniqueFields(message);
        }
    }

    @Override
    public void delete(String id) {
        boolean userExists = this.userRepository.existsById(id);
        if(!userExists) throw new NotFoundException("usuário não existe");
        this.userRepository.deleteById(id);
    }

    private String customizedDuplicatedExceptionMessage(RequestUser userDto) {
        String message = "erro de integridade";

        boolean existsByCPF = this.userRepository.existsByCpf(userDto.cpf());
        boolean existsByEmail = this.userRepository.existsByEmail(userDto.email());

        if(existsByCPF && existsByEmail) {
            message = "já existem usuários cadastrados com os mesmos campos CPF e Email";
        }
        else if(existsByCPF) {
            message = "já existe um usuário cadastrado com o CPF enviado";
        }
        else if(existsByEmail) {
            message = "já existe um usuário cadastrado com o Email enviado";
        }
        return message;
    }
}
