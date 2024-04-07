package com.bank.account.query.entity;

import com.bank.account.query.constant.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "account_id", nullable = false)
    private String accountId;
    @Column(name = "account_holder", nullable = false)
    private String accountHolder;
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;
    @Column(name = "opening_balance", nullable = false)
    private double openingBalance;
}
