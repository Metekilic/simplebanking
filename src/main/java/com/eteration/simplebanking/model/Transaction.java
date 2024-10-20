package com.eteration.simplebanking.model;

import java.time.LocalDateTime;

public abstract class Transaction {

    protected LocalDateTime date;
    protected double amount;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
