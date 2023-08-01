package com.vibratorystudios.demo.user.service;

import com.vibratorystudios.demo.user.exceptions.UserException;
import com.vibratorystudios.demo.user.model.User;
import com.vibratorystudios.demo.user.repo.UserRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepo userRepo;

    @Autowired
   public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    @Override
    public User create(User user) {
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty()){
            logger.error("  id {} does not exist", id);
            throw new UserException("User Not Found");
        }
        return userOptional.get();
    }

    @Override
    public User getUser(String userName) throws UserException {
        Optional<User> userOptional = userRepo.findByUserName(userName);
        if(userOptional.isEmpty()){
            logger.error("user {} not found", userName);
            throw new UserException("userName not found");
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUsers() {
        return (List) userRepo.findAll();
    }

    @Override
    public User updateUser(Long id, User user) throws UserException {
       Optional<User> userOptional = userRepo.findById(id);
       if(userOptional.isEmpty()){
           throw new UserException("User not found");
       }
       User savedUser = userOptional.get();
       savedUser.setUserName(user.getUserName());
       savedUser.setPassword(user.getPassword());

       return userRepo.save(savedUser);
    }

    @Override
    public boolean deleteUser(Long id) throws UserException {
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isEmpty()){
            throw new UserException("user does not exist, can not delete");
        }
        User userToDelete = userOptional.get();
        userRepo.delete(userToDelete);
        return true;
    }
}
