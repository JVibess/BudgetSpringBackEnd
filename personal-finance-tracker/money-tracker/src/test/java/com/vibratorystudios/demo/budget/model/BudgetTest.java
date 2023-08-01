package com.vibratorystudios.demo.budget.model;
import com.vibratorystudios.demo.user.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class BudgetTest {

    private Budget budget;
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User(1L, "justin_kani", "password");
        budget = new Budget(1L,"Light_Bill",1000.0, user);
    }

    @Test
    public void testBudgetIdGeneration(){
        assertNotNull(budget.getId());
    }

}
