package com.vibratorystudios.demo.user.service;

import com.vibratorystudios.demo.user.exceptions.UserException;
import com.vibratorystudios.demo.user.model.User;

import java.util.List;
public interface UserService {

    User create(User user);

    User getUserById(Long id) throws UserException;

    User getUser(String userName)throws UserException;
    List<User> getAllUsers();

    User updateUser(Long id,User user) throws UserException;

    boolean deleteUser(Long id) throws UserException;

}
