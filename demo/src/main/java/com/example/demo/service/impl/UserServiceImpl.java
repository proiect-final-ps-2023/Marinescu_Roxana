package com.example.demo.service.impl;

import com.example.demo.DTO.UserDTO;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
        userMapper = new UserMapper();
    }

    @Override
    public User updateUser(User user) {
        User updateUser = userRepo.findById(user.getId()).get();
        updateUser.setName(user.getName());
        updateUser.setId(user.getId());
        updateUser.setAuth(user.getAuth());
        updateUser.setCart(user.getCart());
        updateUser.setPassword(user.getPassword());
        updateUser.setMail(user.getMail());
        updateUser.setWishlist(user.getWishlist());
        userRepo.save(updateUser);
        return updateUser;
    }
    @Override
    public User createUser(User user) {
         userRepo.save(user);
        return user;
    }

    @Override
    public User deleteUser(User user) {
        User deleteUser = userRepo.findById(user.getId()).get();
        userRepo.delete(deleteUser);
        return user;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepo.findAll().
                stream().
                map(UserMapper::mapModelToDto).
                collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(()
                        ->
                        new IllegalArgumentException("Id invalid"));
        return userMapper.mapModelToDto(user);
    }

    @Override
    public UserDTO findByName(String name) {
        final User user = userRepo.findAllByName(name).orElseThrow(()
                -> {
            throw new EntityNotFoundException("Can not find user ");
        });
        return userMapper.mapModelToDto(user);
    }

    @Override
    public UserDTO login(String username, String password) {
        try {
            UserDTO user1 = userMapper.mapModelToDto(userRepo.findByName(username));
            System.out.println("User 1" + user1.getName());
            System.out.println(username + password);
            if (user1.getName().equals(username) && user1.getPassword().equals(password)) {
                return user1;
            }
            final User user = userRepo.findByNameAndPassword(username, password)
                    .<EntityNotFoundException>orElseThrow(()
                            ->
                    {
                        throw new EntityNotFoundException("Cannot find");
                    });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       return null;
    }
    }
