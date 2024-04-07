package com.bank.account.command.entity;

import com.bank.account.command.constant.AccountType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bank_account")
@Builder
public class BankAccount {
    @Id
    private String accountId;
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
