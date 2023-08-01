package com.vibratorystudios.demo.budget.repo;

import com.vibratorystudios.demo.budget.model.Budget;
import com.vibratorystudios.demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepo extends JpaRepository<Budget, Long> {
    List<Budget> findById(long id);
    List<Budget> findByName(String name);
    List<Budget> findByuser(User user);
    List<Budget> findByAmountGreaterThan(double amount);
}
