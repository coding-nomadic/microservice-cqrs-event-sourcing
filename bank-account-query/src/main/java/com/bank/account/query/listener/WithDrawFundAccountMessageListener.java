package com.bank.account.query.listener;

import com.bank.account.query.constant.AccountQueryConstant;
import com.bank.account.query.entity.BankAccount;
import com.bank.account.query.event.WithDrawFundAccountEvent;
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
public class WithDrawFundAccountMessageListener {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public WithDrawFundAccountMessageListener(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @KafkaListener(topics = AccountQueryConstant.WITHDRAW_FUND_ACCOUNT, groupId = AccountQueryConstant.CONSUMER_GROUP)
    public void receiveMessage(String message) {
        try {
            WithDrawFundAccountEvent withDrawFundAccountEvent = JsonUtils.convertWithClass(message, WithDrawFundAccountEvent.class);
            BankAccount bankAccount = buildBankAccount(withDrawFundAccountEvent);
            bankAccountRepository.save(bankAccount);
            log.info("Bank account withdrawn: {}", bankAccount);
        } catch (Exception exception) {
            log.error("Error occurred while processing message: {}", exception.getMessage());
            throw new RuntimeException("Exception occurred in Message Listener", exception);
        }
    }

    /**
     * @param withDrawFundAccountEvent
     * @return
     */
    private BankAccount buildBankAccount(WithDrawFundAccountEvent withDrawFundAccountEvent) {
        BankAccount bankAccount = bankAccountRepository.findByAccountId(withDrawFundAccountEvent.getAccountId());
        if (bankAccount != null) {
            double currentBalance = bankAccount.getOpeningBalance();
            double newBalance = currentBalance - withDrawFundAccountEvent.getAmount();
            if (newBalance < 0) {
                throw new OpenAccountProcessingException("Insufficient funds", "103");
            }
            bankAccount.setOpeningBalance(newBalance);
            return bankAccount;
        } else {
            throw new OpenAccountProcessingException("Account Id not found", "102");
        }
    }
}
