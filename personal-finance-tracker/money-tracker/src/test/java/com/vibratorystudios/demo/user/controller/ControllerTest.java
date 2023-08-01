package com.vibratorystudios.demo.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibratorystudios.demo.user.exceptions.UserException;
import com.vibratorystudios.demo.user.model.User;
import com.vibratorystudios.demo.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception{
        User user = new User(1L, "justin", "password");
        when(userService.create(any(User.class))).thenReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUser = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/User/User")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), statusCode);

        verify(userService, times(1)).create(any(User.class));
    }



    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "john_doe", "password");
        User user2 = new User(2L, "jane_smith", "password2");
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/User"))
                .andReturn();

        verify(userService, times(1)).getAllUsers();
        // Add further assertions for status code, response body, etc.
    }
}
