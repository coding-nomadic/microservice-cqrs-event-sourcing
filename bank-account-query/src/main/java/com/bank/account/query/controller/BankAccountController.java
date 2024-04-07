package com.bank.account.query.controller;

import com.bank.account.query.entity.BankAccount;
import com.bank.account.query.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> findAllAccounts() {
        List<BankAccount> bankAccountList = bankAccountService.findAllAccounts();
        return ResponseEntity.ok(bankAccountList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BankAccount> findByAccountId(@PathVariable String id) {
        BankAccount bankAccount = bankAccountService.findAccountById(id);
        return ResponseEntity.ok(bankAccount);
    }

    @GetMapping(value = "/{accountHolder}")
    public ResponseEntity<List<BankAccount>> findByAccountHolder(@PathVariable String accountHolder) {
        List<BankAccount> bankAccounts = bankAccountService.findAccountByAccountHolder(accountHolder);
        return ResponseEntity.ok(bankAccounts);
    }
}
