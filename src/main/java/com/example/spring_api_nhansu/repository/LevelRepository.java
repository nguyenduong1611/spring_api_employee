package com.example.spring_api_nhansu.repository;

import com.example.spring_api_nhansu.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    Level findByName(String name);
}
