package com.davi.bank.user;

import com.davi.bank.user.domain.dtos.RequestAddress;
import com.davi.bank.user.domain.models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAll();
    Optional<Address> findById(String id);
    void save(Address user, RequestAddress addressDto);
    Address update(String id,RequestAddress addressDto);
    void delete(String id);
}
