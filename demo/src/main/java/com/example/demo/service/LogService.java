package com.example.demo.service;

import com.example.demo.DTO.CartDTO;
import com.example.demo.DTO.LogDTO;
import com.example.demo.model.LoginLogout;
import org.springframework.stereotype.Component;

@Component
public interface LogService {
    public LoginLogout updateLoginLogout(LoginLogout loginLogout);
    public LoginLogout deleteLoginLogout(LoginLogout loginLogout);
    public LoginLogout createLoginLogout(LoginLogout loginLogout);
    LogDTO findByUserId(int id);
}
