package com.davi.bank.user.domain.services;

import com.davi.bank.user.AddressService;
import com.davi.bank.user.domain.dtos.RequestAddress;
import com.davi.bank.user.domain.models.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public List<Address> findAll() {
        return List.of();
    }

    @Override
    public Optional<Address> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Address user, RequestAddress addressDto) {

    }

    @Override
    public Address update(String id, RequestAddress addressDto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
