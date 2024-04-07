package com.bank.account.query.model;

import com.bank.account.query.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountEvent {
    private String accountId;
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
