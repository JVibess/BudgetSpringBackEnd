package com.vibratorystudios.demo.budget.service;

import com.vibratorystudios.demo.budget.exception.BudgetException;
import com.vibratorystudios.demo.budget.model.Budget;
import com.vibratorystudios.demo.user.exceptions.UserException;

import java.util.List;

public interface BudgetService {

    Budget create(Long userId, Budget budget) throws UserException;

    Budget create(Budget Budget);

    Budget createBudget(Long userId, Budget budget) throws UserException;

    Budget getBudgetById(Long id) throws BudgetException;

    Budget getBudgetName(String name) throws BudgetException;

    List<Budget> getAllBudgets();

    Budget updateBudget(Long id, Budget Budget) throws BudgetException;

    void deleteBudget (Long id);
}
