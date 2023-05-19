package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByNameAndPassword(String username, String password);
    User findByName(String username);
    Optional<User> findAllByName(String name);
}
