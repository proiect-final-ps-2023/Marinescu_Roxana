package com.example.demo.mapper;

import com.example.demo.DTO.LogDTO;
import com.example.demo.model.LoginLogout;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class LogMapper {
    public static LogDTO mapModelToDto(LoginLogout loginLogout){
        LogDTO logDTO = new LogDTO();
        logDTO.setId(loginLogout.getId());
        logDTO.setLogin(loginLogout.getLogin());
        logDTO.setLogout(loginLogout.getLogout());
        logDTO.setUser_id(loginLogout.getUser().getId());
        return logDTO;
    }

    public static LoginLogout mapDtoToModel(LogDTO logDTO){
        LoginLogout loginLogout = new LoginLogout();
        loginLogout.setId(logDTO.getId());
        loginLogout.setLogin(logDTO.getLogin());
        loginLogout.setLogout(logDTO.getLogout());

        User user = new User();
        user.setId(logDTO.getUser_id());
        loginLogout.setUser(user);

        return loginLogout;
    }
}
