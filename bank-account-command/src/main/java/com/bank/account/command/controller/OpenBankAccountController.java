package com.bank.account.command.controller;

import com.bank.account.command.model.OpenBankAccountDto;
import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.service.OpenBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class OpenBankAccountController {

    private final OpenBankAccountService openBankAccountService;

    @Autowired
    public OpenBankAccountController(OpenBankAccountService openBankAccountService) {
        this.openBankAccountService = openBankAccountService;
    }
    
    @PostMapping
    public ResponseEntity<BankAccountResponse> openBankAccount(@RequestBody OpenBankAccountDto openBankAccountDto) {
        final BankAccountResponse openBankAccountResponse = openBankAccountService.process(openBankAccountDto);
        return ResponseEntity.ok(openBankAccountResponse);
    }
}
