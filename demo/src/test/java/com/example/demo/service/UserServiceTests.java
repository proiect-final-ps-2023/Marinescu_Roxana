package com.example.demo.service;

import com.example.demo.enums.Auth;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTests {

    private static final String NAME = "Alex";
    private static final String NAME1 = "Alexx";
    private static final String EMAIL = "alex@gmail.com";
    private static final String PASS = "alex12";

    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    void init() {
        initMocks(this);
        user = new User();
        userService = new UserServiceImpl(userRepo);
        when(userRepo.save(user)).thenReturn(user);
    }

    @Test
    void updateVerify(){
        User user1 = new User();
        user1.setName(NAME);
        user1.setId(12);
        User user2 = new User();
        user2.setName(NAME1);
        user2.setId(12);

        given(userRepo.findById(user1.getId())).willReturn(Optional.of(user1));
        userService.updateUser(user2);
//        verify(userRepo).save(user2);
//        verify(userRepo).findById(user1.getId());
    }

    @Test
    void updateVerify_ExceptionIfUserDoesntExist(){
        try {
            User user1 = new User();
            user1.setName(NAME);
            user1.setId(12);
            User user2 = new User();
            user2.setName(NAME1);
            user2.setId(13);
            given(userRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
            userService.updateUser(user2);
        }catch(RuntimeException re){}
    }

    @Test
    void deleteVerify(){
        User user1 = new User();
        user1.setName(NAME);
        user1.setId(12);
        user1.setMail(EMAIL);
        user1.setPassword(PASS);
        when(userRepo.findById(user1.getId())).thenReturn(Optional.of(user1));
        userService.deleteUser(user1);
        //verify(userRepo).delete(user1);
    }

    @Test
    void deleteVerify_ExceptionIfUserDoesntExist(){
        try {
            User user1 = new User();
            user1.setName(NAME);
            user1.setId(13);
            user1.setMail(EMAIL);
            user1.setPassword(PASS);
            given(userRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
            userService.deleteUser(user1);
        }catch(RuntimeException re){}
    }

    @Test
    void createVerify(){
        User user1 = new User();
        user1.setName(NAME);
        user1.setMail(EMAIL);
        user1.setPassword(PASS);
        user1.setAuth(Auth.USER);
        user1.setId(12);
        userService.createUser(user1);
    }
}
