package com.example.demo.controller;

import com.example.demo.DTO.CartDTO;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.mapper.LogMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Cart;
import com.example.demo.model.LoginLogout;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.LogService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogService logService;
    LogMapper logMapper;
    UserService userService;
    UserMapper userMapper;
    public LogController(LogService logService,LogMapper logMapper,UserService userService,
    UserMapper userMapper){
        this.logMapper=logMapper;
        this.logService=logService;
        this.userMapper=userMapper;
        this.userService=userService;
    }
    @RequestMapping(value="/addlogin")
    public void addLogin(@RequestBody int idu)
    {
        LoginLogout loginLogout= logMapper.mapDtoToModel(logService.findByUserId(idu));
        UserDTO user = userService.findById(idu);
        System.out.println(idu);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        loginLogout.setLogin(formatter.format(date));
        user.setLogin_logout_id(loginLogout.getId());
        loginLogout.setUser(userMapper.mapDtoToModel(user));
        System.out.println(user.getName());
        logService.createLoginLogout(loginLogout);
    }
    @RequestMapping(value="/addlogout")
    public void addLogout(@RequestBody String id)
    {
        String s = String.valueOf(id.charAt(1));
        int idu = Integer.parseInt(s);

        LoginLogout loginLogout= logMapper.mapDtoToModel(logService.findByUserId(idu));
        UserDTO user = userService.findById(idu);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        loginLogout.setLogout(formatter.format(date));
        user.setLogin_logout_id(loginLogout.getId());
        loginLogout.setUser(userMapper.mapDtoToModel(user));
        System.out.println(user.getName()+"out");
        logService.createLoginLogout(loginLogout);
    }
}
