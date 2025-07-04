package com.davi.bank.user.domain.repositories;

import com.davi.bank.user.domain.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @Override
    List<Users> findAll();

    @Override
    Optional<Users> findById(String s);

    @Override
    Users save(Users u);

    @Override
    void deleteById(String id);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
