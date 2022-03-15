package com.example.spring_api_nhansu.repository;

import com.example.spring_api_nhansu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserById(Integer id);

    void deleteById(Integer id);

    User findByUsername(String username);

    Optional<User> findUserByEmail(String email);
}
