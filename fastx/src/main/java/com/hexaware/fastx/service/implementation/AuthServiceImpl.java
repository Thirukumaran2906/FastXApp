package com.hexaware.fastx.service.implementation;


import com.hexaware.fastx.dtos.LoginRequest;
import com.hexaware.fastx.dtos.LoginResponse;
import com.hexaware.fastx.entity.User;
import com.hexaware.fastx.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        return null;
    }
}
