package com.bank.account.query.service;

import com.bank.account.query.entity.BankAccount;
import com.bank.account.query.exception.OpenAccountProcessingException;
import com.bank.account.query.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> findAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public BankAccount findAccountById(String id) {
        return bankAccountRepository.findByAccountId(id);
    }

    public List<BankAccount> findAccountByAccountHolder(String accountHolder) {
        List<BankAccount> bankAccounts = bankAccountRepository.findByAccountHolder(accountHolder);
        if (bankAccounts.isEmpty()) {
            throw new OpenAccountProcessingException("Accounts not found", "102");
        }
        return bankAccounts;
    }
}
