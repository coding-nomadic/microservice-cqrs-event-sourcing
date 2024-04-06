package com.bank.account.command.util;


import com.bank.account.command.model.OpenBankAccountDto;
import com.bank.account.command.entity.BankAccount;

public class TransformerUtils {

    private TransformerUtils() {
    }

    public static BankAccount getBankAccount(OpenBankAccountDto openBankAccountDto) {
        return BankAccount.builder().accountHolder(openBankAccountDto.getAccountHolder()).openingBalance(openBankAccountDto.getOpeningBalance()).accountType(openBankAccountDto.getAccountType()).build();
    }
}
