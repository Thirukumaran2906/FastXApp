package com.hexaware.fastx.service;

import com.hexaware.fastx.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();

    User getUserByEmail(String email);

    // 🔹 Add this new login method
    User login(String email, String password);
}
