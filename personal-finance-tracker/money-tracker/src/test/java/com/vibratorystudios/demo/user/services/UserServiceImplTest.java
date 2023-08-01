package com.vibratorystudios.demo.user.services;


import com.vibratorystudios.demo.user.exceptions.UserException;
import com.vibratorystudios.demo.user.model.User;
import com.vibratorystudios.demo.user.repo.UserRepo;
import com.vibratorystudios.demo.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser(){
        User userToSave = new User(null,"sampleUser","samplePassword");
        User savedUser = new User(1L,"sampleUser", "samplePassword");

        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        User createdUser = userService.create(userToSave);

        assertNotNull(createdUser);
        assertEquals(savedUser.getId(), createdUser.getId());
        assertEquals(savedUser.getUserName(), createdUser.getUserName());
        assertEquals(savedUser.getPassword(), createdUser.getPassword());

        verify(userRepo, times(1)).save(any(User.class));

    }

    @Test
    public void testGetUserById() throws UserException{
        //Given
        Long userId = 1L;
        User user = new User(userId, "randomUser", "randomPassword");
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        //When
        User gottenUser = userService.getUserById(userId);



        //Then
        assertNotNull(gottenUser);
        assertEquals(userId, gottenUser.getId());
        assertEquals("randomUser",gottenUser.getUserName());
        assertEquals("randomPassword", gottenUser.getPassword());
    }

    @Test
    public void testUser_IdNotFound(){

        //Given
        Long idNotFound = 123L;
        when(userRepo.findById(idNotFound)).thenReturn(Optional.empty());

        //When and then
        assertThrows(UserException.class, ()-> userService.getUserById(idNotFound));
    }


    @Test
    public void testGetAllUsers(){
        //Given
        List<User> userList = new ArrayList<>();

        userList.add(new User(1L, "user1", "password1"));
        userList.add(new User(2L, "user2", "password2"));
        when(userRepo.findAll()).thenReturn(userList);


        //When
        List<User> allUsers = userService.getAllUsers();

        //Then

        assertEquals(2, allUsers.size());
    }

    @Test
    public void testDeleteUser() throws UserException {
        // Given
        Long userId = 1L;
        User existingUser = new User(userId, "existingUser", "existingPassword");
        when(userRepo.findById(userId)).thenReturn(Optional.of(existingUser));

        // When
        boolean isDeleted = userService.deleteUser(userId);

        // Then
        assertTrue(isDeleted);
        verify(userRepo, times(1)).delete(existingUser);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        // Given
        Long nonExistentUserId = 123L;
        when(userRepo.findById(nonExistentUserId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(UserException.class, () -> userService.deleteUser(nonExistentUserId));
    }


}
