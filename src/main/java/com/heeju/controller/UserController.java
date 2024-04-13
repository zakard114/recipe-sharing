package com.heeju.controller;

import com.heeju.dto.UserDto;
import com.heeju.model.User;
import com.heeju.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) throws Exception {

        User isExisted = userServiceImpl.findByEmail(userDto.getEmail());
        if(isExisted!=null){
            throw new Exception("user not found with email "+userDto.getEmail());
        }

        User savedUser = userServiceImpl.addUser(userDto);
        return ResponseEntity.ok(savedUser);
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long userId) {

        userServiceImpl.deleteById(userId);

        return "User deleted successfully";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Map<Long, User>> getAllUsers() {
        Map<Long, User> users = userServiceImpl.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(users);
    }


}
