package com.example.demo.service;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    public User updateUser(User user);
    public User createUser(User user);
    public User deleteUser(User user);
    List<UserDTO> findAll();
    UserDTO findById(int id);
    UserDTO findByName(String name);
    UserDTO login(String username, String password);
}
