package com.davi.bank.user.web.controllers;

import com.davi.bank.user.domain.dtos.RequestUser;
import com.davi.bank.user.domain.dtos.ResponseUser;
import com.davi.bank.user.domain.models.Users;
import com.davi.bank.user.domain.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseUser>> findAllUsers() {
        List<ResponseUser> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseUser> findOneUser(@PathVariable String id) {
        ResponseUser user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<ResponseUser> saveUser(@RequestBody @Valid RequestUser requestUser) {
        ResponseUser createdUser = this.userService.save(requestUser);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.id())
                .toUri();

        return ResponseEntity.created(uri).body(createdUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseUser> updateUser(@RequestBody @Valid RequestUser requestUser, @PathVariable String id) {
        ResponseUser updatedUser = this.userService.update(id, requestUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseUser> deleteUser(@PathVariable String id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}