package com.bank.account.command.event;

import com.bank.account.command.constant.EventType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositFundAccountEvent {
    private EventType eventType;
    private String accountId;
    private double amount;
}
