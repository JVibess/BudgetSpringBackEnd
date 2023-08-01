package com.vibratorystudios.demo.budget.services;

import com.vibratorystudios.demo.budget.exception.BudgetException;
import com.vibratorystudios.demo.budget.model.Budget;
import com.vibratorystudios.demo.budget.repo.BudgetRepo;
import com.vibratorystudios.demo.budget.service.BudgetService;
import com.vibratorystudios.demo.budget.service.BudgetServiceImpl;
import com.vibratorystudios.demo.user.exceptions.UserException;
import com.vibratorystudios.demo.user.model.User;
import com.vibratorystudios.demo.user.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BudgetServiceImpTest {

    @Mock
    private BudgetRepo budgetRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private BudgetServiceImpl budgetService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBudget() throws UserException {
        Long userId = 1L;
        Long budgetId = 1L;
        User user = new User(userId, "Justin", "password");
        Budget budget = new Budget(budgetId, "bills", 200, user);
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(budgetRepo.save(budget)).thenReturn(budget);

        Budget createdBudget = budgetService.create(userId, budget);

        assertNotNull(createdBudget);
        assertEquals(user, createdBudget.getUser());
        assertEquals(budget.getName(), createdBudget.getName());
        verify(userRepo).findById(userId);
        verify(budgetRepo).save(budget);
    }
    @Test
    public void testGetBudgetById() throws BudgetException{
        Long userId = 1L;
        Long budgetId = 1L;
        User user = new User(userId, "justinKani","aword");
        Budget budget = new Budget(budgetId,"groceireis", 2000,user);

        when(budgetRepo.findById(budgetId)).thenReturn(Optional.of(budget));

    }

    @Test
    public void testGetBudgetName() throws BudgetException{
        Long userId = 1L;
        Long budgetId = 1L;
        String name = "bills";
        User user = new User(userId, "randomName", "password");
        Budget createdBudget = new Budget(budgetId, name,2080,user);

        List<Budget> budgetList = new ArrayList<>();

        when(budgetRepo.findByName(name)).thenReturn(budgetList);


    }

    @Test
    public void testUpdateBudgets() throws BudgetException{
        Long budgetId = 1L;
        Long userId = 1L;
        User user = new User(userId, "randomName", "password");
        Budget existingBudget = new Budget(budgetId,"bills", 200, user);
        Budget updatedBudget = new Budget(budgetId,"newBIll", 300,user);

        when(budgetRepo.findById(budgetId)).thenReturn(Optional.of(existingBudget));

        when(budgetRepo.save(existingBudget)).thenReturn(updatedBudget);

        Budget result = budgetService.updateBudget(budgetId,updatedBudget);

        assertNotNull(result);
        assertEquals(updatedBudget.getName(),result.getName());
        assertEquals(updatedBudget.getAmount(),result.getAmount());

    }

    @Test
    public void testDeleteBudgetById(){

        Long budgetId = 1L;

        budgetService.deleteBudget(budgetId);

        verify(budgetRepo, times(1)).deleteById(budgetId);

    }
}
