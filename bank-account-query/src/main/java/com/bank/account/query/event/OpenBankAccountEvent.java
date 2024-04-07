package com.bank.account.query.event;

import com.bank.account.query.constant.EventType;
import com.bank.account.query.entity.BankAccount;
import com.bank.account.query.model.BankAccountEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenBankAccountEvent {
    private EventType eventType;
    private BankAccountEvent openBankAccount;
}
