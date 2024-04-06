package com.bank.account.command.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountResponse {
    private String id;
    private String message;
}
