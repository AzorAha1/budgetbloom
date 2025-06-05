package com.budgetbloom.app.model;

import java.time.LocalDate;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Transaction {
    private String id; // id is gotten from plaid id
    private String merchant;
    private LocalDate date;
    private String category;
    private boolean isPending;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Transaction() {}

    public Transaction(String id, String merchant, LocalDate date, String category, boolean isPending, User user) {
        this.id = id;
        this.merchant = merchant;
        this.date = date;
        this.category = category;
        this.isPending = isPending;
        this.user = user;
    }
    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMerchant() {
        return merchant;
    }
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean isPending() {
        return isPending;
    }
    public void setPending(boolean isPending) {
        this.isPending = isPending;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
