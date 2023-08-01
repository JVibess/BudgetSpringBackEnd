package com.vibratorystudios.demo.budget.controller;

import com.vibratorystudios.demo.budget.exception.BudgetException;
import com.vibratorystudios.demo.budget.model.Budget;
import com.vibratorystudios.demo.budget.service.BudgetService;
import com.vibratorystudios.demo.user.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Budget")
public class BudgetController {

    private BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService){
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestParam Long userId, @RequestBody Budget budget) throws UserException {
        Budget createdBudget = budgetService.create(userId, budget);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    @GetMapping("/id")
    public ResponseEntity<Budget> getByBudgetName(@RequestParam Long id ) throws BudgetException{
        Budget budget = budgetService.getBudgetById(id);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @GetMapping("/budgetname")
    public ResponseEntity<Budget> getBudgetByName(@RequestParam String name) throws BudgetException {
        Budget budget = budgetService.getBudgetName(name);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) throws BudgetException {
        Budget updatedBudget = budgetService.updateBudget(id, budget);
        return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
