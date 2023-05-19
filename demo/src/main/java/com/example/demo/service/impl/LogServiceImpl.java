package com.example.demo.service.impl;

import com.example.demo.DTO.LogDTO;
import com.example.demo.mapper.LogMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.LoginLogout;
import com.example.demo.model.Order;
import com.example.demo.repository.LogRepo;
import com.example.demo.repository.OrderRepo;
import com.example.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepo logRepo;
    private LogMapper logMapper;

    public LogServiceImpl(LogRepo logRepo){
        this.logRepo=logRepo;
        logMapper=new LogMapper();
    }

    @Override
    public LoginLogout updateLoginLogout(LoginLogout loginLogout) {
        LoginLogout updateLoginLogout = new LoginLogout();
        logRepo.save(updateLoginLogout);
        return updateLoginLogout;
    }

    @Override
    public LoginLogout deleteLoginLogout(LoginLogout loginLogout) {
        LoginLogout deleteLog = logRepo.findById(loginLogout.getId()).get();
        logRepo.delete(deleteLog);
        return loginLogout;
    }

    @Override
    public LoginLogout createLoginLogout(LoginLogout loginLogout) {
        logRepo.save(loginLogout);
        return loginLogout;
    }

    @Override
    public LogDTO findByUserId(int id) {
        final LoginLogout loginLogout=logRepo.findByUserId(id);
        return logMapper.mapModelToDto(loginLogout);
    }
}
