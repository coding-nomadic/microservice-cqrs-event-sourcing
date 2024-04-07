package com.bank.account.command.controller;

import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.service.CloseBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class CloseBankAccountController {

    private final CloseBankAccountService deleteBankAccountService;

    @Autowired
    public CloseBankAccountController(CloseBankAccountService deleteBankAccountService) {
        this.deleteBankAccountService = deleteBankAccountService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BankAccountResponse> closeBankAccount(@PathVariable String id) {
        final BankAccountResponse bankAccountResponse = deleteBankAccountService.process(id);
        return new ResponseEntity<>(bankAccountResponse, HttpStatus.NO_CONTENT);
    }
}
