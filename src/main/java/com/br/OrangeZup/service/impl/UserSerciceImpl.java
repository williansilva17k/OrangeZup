package com.br.OrangeZup.service.impl;

import com.br.OrangeZup.model.User;
import com.br.OrangeZup.repository.UserRepository;
import com.br.OrangeZup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSerciceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity findAllUsers() {

        try {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity findByCpf(String cpf) {
        try {
            if (cpf == null) {
                return new ResponseEntity<>("Cpf is not null", HttpStatus.PRECONDITION_FAILED);
            }
            User user = userRepository.findByCpf(cpf);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity saveUser(User user) {
        try {
            if (user == null) {
                return new ResponseEntity<>("User is not null", HttpStatus.PRECONDITION_FAILED);
            }
            User userS = userRepository.save(user);
            return new ResponseEntity<>(userS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity updateUserByCpf(User user, String cpf) {
        try {
            if (cpf == null) {
                return new ResponseEntity<>("Cpf is not null", HttpStatus.PRECONDITION_FAILED);
            }
            User userU = userRepository.findByCpf(cpf);
            if (userU == null) {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            userU.setCpf(user.getCpf() == null ? userU.getCpf() : user.getCpf());
            userU.setDate(user.getDate() == null ? userU.getDate() : user.getDate());
            userU.setEmail(user.getEmail() == null ? userU.getEmail() : user.getEmail());
            userU.setName(user.getName() == null ? userU.getName() : user.getName());
            userRepository.save(userU);
            return new ResponseEntity<>(userU, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity deleteUserByCpf(String cpf) {
        try {
            if (cpf == null) {
                return new ResponseEntity<>("Cpf is not null", HttpStatus.PRECONDITION_FAILED);
            }
            Long status = userRepository.deleteByCpf(cpf);
            if (status == 1) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
