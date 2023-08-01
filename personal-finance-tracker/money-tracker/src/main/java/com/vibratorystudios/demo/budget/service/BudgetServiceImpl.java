package com.vibratorystudios.demo.budget.service;

import com.vibratorystudios.demo.budget.exception.BudgetException;
import com.vibratorystudios.demo.budget.model.Budget;
import com.vibratorystudios.demo.budget.repo.BudgetRepo;
import com.vibratorystudios.demo.user.exceptions.UserException;
import com.vibratorystudios.demo.user.model.User;
import com.vibratorystudios.demo.user.repo.UserRepo;
import com.vibratorystudios.demo.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BudgetServiceImpl implements BudgetService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    private BudgetRepo budgetRepo;
    private UserRepo userRepo;

    @Autowired
    public BudgetServiceImpl(BudgetRepo budgetRepo, UserRepo userRepo) {
        this.budgetRepo = budgetRepo;
        this.userRepo = userRepo;
    }
    @Override
    public Budget create(Long userId, Budget budget) throws UserException {
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserException("User not found");
        }
        User user = userOptional.get();
        budget.setUser(user);
        return budgetRepo.save(budget);
    }

    @Override
    public Budget create(Budget budget) {
        return budgetRepo.save(budget);
    }

    @Override
    public Budget createBudget(Long userId, Budget budget) throws UserException {
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserException("User Not Found");
        }
        User user = userOptional.get();
        budget.setUser(user);
        return budgetRepo.save(budget);
    }

    @Override
    public Budget getBudgetById(Long id) throws BudgetException {
        Optional<Budget> budgetOptional = budgetRepo.findById(id);
        if (budgetOptional.isEmpty()){
            throw new BudgetException("Budget not found");
        }
        return budgetOptional.get();

    }

    @Override
    public Budget getBudgetName(String name) throws BudgetException {
        List<Budget> budgetOptional = budgetRepo.findByName(name);
        if(budgetOptional.isEmpty()){
            throw new BudgetException("Budget not found");
        }
        return budgetOptional.get(0);
    }

    @Override
    public List<Budget> getAllBudgets() {
        return budgetRepo.findAll();
    }

    @Override
    public Budget updateBudget(Long id, Budget budget) throws BudgetException {
        Optional<Budget> budgetOptional = budgetRepo.findById(id);
        if (budgetOptional.isEmpty()) {
            throw new BudgetException("Budget not found");
        }
        Budget existingBudget = budgetOptional.get();
        existingBudget.setName(budget.getName());
        existingBudget.setAmount(budget.getAmount());
        return budgetRepo.save(existingBudget);
    }


    @Override
    public void deleteBudget(Long id) {

        budgetRepo.deleteById(id);
    }
}
