package com.bank.account.command.service;

import com.bank.account.command.constant.EventType;
import com.bank.account.command.entity.BankAccount;
import com.bank.account.command.event.OpenBankAccountEvent;
import com.bank.account.command.exception.OpenAccountProcessingException;
import com.bank.account.command.model.OpenBankAccountDto;
import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.producer.OpenBankAccountProducer;
import com.bank.account.command.repository.BankAccountRepository;
import com.bank.account.command.util.TransformerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenBankAccountService {

    private final BankAccountRepository openBankAccountRepository;
    private final OpenBankAccountProducer messagePublisher;

    @Autowired
    public OpenBankAccountService(BankAccountRepository openBankAccountRepository, OpenBankAccountProducer messagePublisher) {
        this.openBankAccountRepository = openBankAccountRepository;
        this.messagePublisher = messagePublisher;
    }

    @Transactional
    public BankAccountResponse process(OpenBankAccountDto openBankAccountDto) throws OpenAccountProcessingException {
        try {
            BankAccount openBankAccount = openBankAccountRepository.save(TransformerUtils.getBankAccount(openBankAccountDto));
            OpenBankAccountEvent openBankAccountEvent = OpenBankAccountEvent.builder().eventType(EventType.OPEN_ACCOUNT).openBankAccount(openBankAccount).build();
            messagePublisher.publish(openBankAccountEvent);
            return BankAccountResponse.builder().id(openBankAccount.getAccountId()).message("Open Account request sent successfully").build();
        } catch (Exception e) {
            throw new OpenAccountProcessingException("Error processing open bank account request", e);
        }
    }
}
