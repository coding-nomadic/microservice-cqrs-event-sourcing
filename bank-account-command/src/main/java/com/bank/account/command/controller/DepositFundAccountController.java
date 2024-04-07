package com.bank.account.command.controller;

import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.model.DepositFundAccountDto;
import com.bank.account.command.service.DepositFundAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class DepositFundAccountController {

    private final DepositFundAccountService depositFundAccountService;

    @Autowired
    public DepositFundAccountController(DepositFundAccountService depositFundAccountService) {
        this.depositFundAccountService = depositFundAccountService;
    }

    @PutMapping(value = "/{id}/deposit")
    public ResponseEntity<BankAccountResponse> depositFundAccount(@PathVariable String id, @RequestBody DepositFundAccountDto depositFundAccountDto) {
        final BankAccountResponse openBankAccountResponse = depositFundAccountService.process(id,depositFundAccountDto.getAmount());
        return ResponseEntity.ok(openBankAccountResponse);
    }
}
