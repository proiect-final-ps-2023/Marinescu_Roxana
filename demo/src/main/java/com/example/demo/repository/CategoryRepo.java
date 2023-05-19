package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Optional<Category> findAllByName(String name);
    Category findByName(String name);
}
