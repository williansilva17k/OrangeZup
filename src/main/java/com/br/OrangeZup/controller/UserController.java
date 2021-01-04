package com.br.OrangeZup.controller;


import com.br.OrangeZup.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {
    @GetMapping(value = "/users")
    ResponseEntity getUsers();

    @GetMapping(value = "/user/{cpf}")
    ResponseEntity getUserByCpf(@PathVariable String cpf);

    @PostMapping(value = "/user")
    ResponseEntity saveUser(@RequestBody User user);

    @PutMapping(value = "/user/{cpf}")
    ResponseEntity updateUserByCpf(@RequestBody User user, @PathVariable String cpf);

    @DeleteMapping(value = "/user/{cpf}")
    ResponseEntity deleteUserByCpf(@PathVariable String cpf);

}