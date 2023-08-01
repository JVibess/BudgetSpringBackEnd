package com.vibratorystudios.demo.budget.controller;

import com.vibratorystudios.demo.budget.model.Budget;
import com.vibratorystudios.demo.budget.service.BudgetService;
import com.vibratorystudios.demo.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.mockito.ArgumentMatchers.any;

public class BudgetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BudgetService budgetService;

    @InjectMocks
    private BudgetController budgetController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(budgetController).build();
    }

    @Test
    public void testCreateBudget()throws Exception{
        Long userId = 1L;
        User fakeUser = new User(userId,"randomuser","password");
        Budget budget = new Budget(1L,"budgetName",2000,fakeUser);

        when(budgetService.create(1L, budget)).thenReturn(budget);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBudget = objectMapper.writeValueAsString(budget);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/Budget")
                        .param("userId","1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBudget))
                .andReturn();

        int statusCode = result.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(),statusCode);

        verify(budgetService, times(1)).create(eq(1L), ArgumentMatchers.any(Budget.class));


    }




}
