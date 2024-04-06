package com.bank.account.command.model;

import com.bank.account.command.constant.AccountType;
import lombok.Data;

@Data
public class OpenBankAccountDto {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
