package com.bank.account.query.event;


import com.bank.account.query.constant.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithDrawFundAccountEvent {
    private EventType eventType;
    private String accountId;
    private double amount;
}
