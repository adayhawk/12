package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}