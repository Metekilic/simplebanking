package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    private Map<String, Account> accountRepository = new HashMap<>();

    public AccountService() {
        // Başlangıç hesabı eklemek için bu şekilde yaptım.
        Account account = new Account("Kerem Karaca", "17892");
        accountRepository.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        return accountRepository.get(accountNumber);
    }

    public void saveAccount(Account account) {
        accountRepository.put(account.getAccountNumber(), account);
    }
}
