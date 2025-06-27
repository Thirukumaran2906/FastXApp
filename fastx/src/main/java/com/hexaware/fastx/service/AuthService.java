package com.hexaware.fastx.service;

import com.hexaware.fastx.dtos.LoginRequest;
import com.hexaware.fastx.dtos.LoginResponse;
import com.hexaware.fastx.entity.User;

public interface AuthService {

    User registerUser(User user);

    LoginResponse loginUser(LoginRequest request);

}
