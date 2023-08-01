package com.vibratorystudios.demo.budget.model;

import com.vibratorystudios.demo.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Default constructor
    public Budget() {
    }

    public Budget(Long id, String name, double amount, User user) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
