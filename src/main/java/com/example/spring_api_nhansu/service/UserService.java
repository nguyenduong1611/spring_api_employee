package com.example.spring_api_nhansu.service;

import com.example.spring_api_nhansu.entity.Level;
import com.example.spring_api_nhansu.entity.User;
import com.example.spring_api_nhansu.exception.UserNotException;
import com.example.spring_api_nhansu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String generatedpassword(){
       String random =  UUID.randomUUID().toString();
        return random;
    }
    public User addUser(User user) throws IOException {
        return userRepository.save(user);
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Integer id){
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotException("User by id " + id + " was not found"));
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotException("User by Email " + email + " was not found"));
    }
}
