package com.vibratorystudios.demo.user.controller;

import com.vibratorystudios.demo.user.service.UserService;
import com.vibratorystudios.demo.user.exceptions.UserException;
import com.vibratorystudios.demo.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/User")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;
    }

    @PostMapping("/User")
    public ResponseEntity<User> create(@RequestBody User user) throws UserException{
        user = userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/GetUser")
    public ResponseEntity<User> getByUser(@RequestParam String userName)throws UserException{
        User user = userService.getUser(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> user = userService.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
