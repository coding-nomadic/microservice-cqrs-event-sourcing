package com.bank.account.query.listener;

import com.bank.account.query.constant.AccountQueryConstant;
import com.bank.account.query.event.CloseBankAccountEvent;
import com.bank.account.query.repository.BankAccountRepository;
import com.bank.account.query.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CloseBankAccountMessageListener {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public CloseBankAccountMessageListener(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @KafkaListener(topics = AccountQueryConstant.CLOSE_BANK_ACCOUNT, groupId = AccountQueryConstant.CONSUMER_GROUP)
    public void receiveMessage(String message) {
        try {
            CloseBankAccountEvent closeBankAccountEvent = JsonUtils.convertWithClass(message, CloseBankAccountEvent.class);
            bankAccountRepository.deleteByAccountId(closeBankAccountEvent.getAccountId());
            log.info("Bank account closed: {}", closeBankAccountEvent.getAccountId());
        } catch (Exception exception) {
            log.error("Error occurred while processing message: {}", exception.getMessage());
            throw new RuntimeException("Exception occurred in Message Listener", exception);
        }
    }
}
