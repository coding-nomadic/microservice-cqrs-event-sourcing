package com.bank.account.query.listener;

import com.bank.account.query.constant.AccountQueryConstant;
import com.bank.account.query.entity.BankAccount;
import com.bank.account.query.event.DepositFundAccountEvent;
import com.bank.account.query.exception.OpenAccountProcessingException;
import com.bank.account.query.repository.BankAccountRepository;
import com.bank.account.query.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class DepositFundAccountMessageListener {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public DepositFundAccountMessageListener(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @KafkaListener(topics = AccountQueryConstant.DEPOSIT_FUND_ACCOUNT, groupId = AccountQueryConstant.CONSUMER_GROUP)
    public void receiveMessage(String message) {
        try {
            DepositFundAccountEvent depositFundAccountEvent = JsonUtils.convertWithClass(message, DepositFundAccountEvent.class);
            BankAccount bankAccount = buildBankAccount(depositFundAccountEvent);
            bankAccountRepository.save(bankAccount);
            log.info("Bank account deposited: {}", bankAccount);
        } catch (Exception exception) {
            log.error("Error occurred while processing message: {}", exception.getMessage());
            throw new RuntimeException("Exception occurred in Message Listener", exception);
        }
    }

    private BankAccount buildBankAccount(DepositFundAccountEvent depositFundAccountEvent) {
        BankAccount bankAccount = bankAccountRepository.findByAccountId(depositFundAccountEvent.getAccountId());
        if (bankAccount != null) {
            double currentBalance = bankAccount.getOpeningBalance();
            double newBalance = currentBalance + depositFundAccountEvent.getAmount();
            bankAccount.setOpeningBalance(newBalance);
            return bankAccount;
        } else {
            throw new OpenAccountProcessingException("Account Id not found", "102");
        }
    }
}
