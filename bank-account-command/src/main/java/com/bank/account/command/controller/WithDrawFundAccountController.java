package com.bank.account.command.controller;

import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.model.WithDrawFundAccountDto;
import com.bank.account.command.service.WithDrawFundAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class WithDrawFundAccountController {

    private final WithDrawFundAccountService withDrawFundAccountService;

    @Autowired
    public WithDrawFundAccountController(WithDrawFundAccountService withDrawFundAccountService) {
        this.withDrawFundAccountService = withDrawFundAccountService;
    }

    @PutMapping(value = "/{id}/withdraw")
    public ResponseEntity<BankAccountResponse> withDrawFundAccount(@PathVariable String id, @RequestBody WithDrawFundAccountDto withDrawFundAccountDto) {
        final BankAccountResponse openBankAccountResponse = withDrawFundAccountService.process(id, withDrawFundAccountDto.getAmount());
        return ResponseEntity.ok(openBankAccountResponse);
    }
}
