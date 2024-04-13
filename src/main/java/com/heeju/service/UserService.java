package com.heeju.service;

import com.heeju.dto.UserDto;
import com.heeju.model.User;

import java.util.Map;

public interface UserService {
    User addUser(UserDto userDto);

    User findByEmail(String email);

    void deleteById(Long userId);

    Map<Long, User> findAll();

    User findUserById(Long userId);
}
