package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{accountNumber}/credit")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction transaction) {
        Account account = accountService.findAccount(accountNumber);
        if (account != null) {
            account.deposit(transaction.getAmount());
            TransactionStatus status = new TransactionStatus("OK");
            return ResponseEntity.ok(status);
        } else {
            TransactionStatus status = new TransactionStatus("Hesap bulunamadı");
            return ResponseEntity.badRequest().body(status);
        }
    }

    @PostMapping("/{accountNumber}/debit")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction transaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        if (account != null) {
            account.withdraw(transaction.getAmount());
            TransactionStatus status = new TransactionStatus("OK");
            return ResponseEntity.ok(status);
        } else {
            TransactionStatus status = new TransactionStatus("Hesap bulunamadı");
            return ResponseEntity.badRequest().body(status);
        }
    }
}