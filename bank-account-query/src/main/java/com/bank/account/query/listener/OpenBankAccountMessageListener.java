package com.bank.account.query.listener;

import com.bank.account.query.constant.AccountQueryConstant;
import com.bank.account.query.entity.BankAccount;
import com.bank.account.query.event.OpenBankAccountEvent;
import com.bank.account.query.repository.BankAccountRepository;
import com.bank.account.query.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OpenBankAccountMessageListener {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public OpenBankAccountMessageListener(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @KafkaListener(topics = AccountQueryConstant.OPEN_BANK_ACCOUNT, groupId = AccountQueryConstant.CONSUMER_GROUP)
    public void receiveMessage(String message) {
        try {
            OpenBankAccountEvent openBankAccountEvent = JsonUtils.convertWithClass(message, OpenBankAccountEvent.class);
            BankAccount bankAccount = buildBankAccount(openBankAccountEvent);
            bankAccountRepository.save(bankAccount);
            log.info("Bank account created: {}", bankAccount);
        } catch (Exception exception) {
            log.error("Error occurred while processing message: {}", exception.getMessage());
            throw new RuntimeException("Exception occurred in Message Listener", exception);
        }
    }


    private BankAccount buildBankAccount(OpenBankAccountEvent openBankAccountEvent) {
        return BankAccount.builder().accountId(openBankAccountEvent.getOpenBankAccount().getAccountId()).accountHolder(openBankAccountEvent.getOpenBankAccount().getAccountHolder()).accountType(openBankAccountEvent.getOpenBankAccount().getAccountType()).openingBalance(openBankAccountEvent.getOpenBankAccount().getOpeningBalance()).build();
    }
}
