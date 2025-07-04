package com.davi.bank.user;

import com.davi.bank.user.domain.dtos.RequestUser;
import com.davi.bank.user.domain.dtos.ResponseUser;
import com.davi.bank.user.domain.models.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<ResponseUser> findAll();
    ResponseUser findById(String id);
    ResponseUser save(RequestUser userDto);
    ResponseUser update(String id,RequestUser userDto);
    void delete(String id);
}
