package com.bank.account.command.event;

import com.bank.account.command.constant.EventType;
import com.bank.account.command.entity.BankAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpenBankAccountEvent {
    private EventType eventType;
    private BankAccount openBankAccount;
}
