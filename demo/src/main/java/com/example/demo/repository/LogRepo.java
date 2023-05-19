package com.example.demo.repository;

import com.example.demo.model.LoginLogout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends JpaRepository<LoginLogout,Integer> {
    LoginLogout findByUserId(int id);
}
