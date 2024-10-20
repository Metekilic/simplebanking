package com.eteration.simplebanking.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String owner;
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        balance += amount;
        DepositTransaction transaction = new DepositTransaction(amount);
        transactions.add(transaction);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (balance >= amount) {
            balance -= amount;
            WithdrawalTransaction transaction = new WithdrawalTransaction(amount);
            transactions.add(transaction);
        } else {
            throw new InsufficientBalanceException("Yetersiz bakiye");
        }
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        if (transaction instanceof DepositTransaction) {
            deposit(transaction.getAmount());
        } else if (transaction instanceof WithdrawalTransaction) {
            withdraw(transaction.getAmount());
        }
    }
}
