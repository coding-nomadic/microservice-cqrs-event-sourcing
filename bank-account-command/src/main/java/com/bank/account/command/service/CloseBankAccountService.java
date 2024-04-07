package com.bank.account.command.service;


import com.bank.account.command.constant.EventType;
import com.bank.account.command.event.CloseBankAccountEvent;
import com.bank.account.command.exception.OpenAccountProcessingException;
import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.producer.CloseBankAccountProducer;
import com.bank.account.command.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CloseBankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CloseBankAccountProducer deleteBankAccountProducer;

    @Transactional
    public BankAccountResponse process(String id) throws OpenAccountProcessingException {
        bankAccountRepository.deleteById(String.valueOf(id));
        CloseBankAccountEvent closeBankAccountEvent = CloseBankAccountEvent.builder().accountId(id).eventType(EventType.CLOSE_ACCOUNT).build();
        deleteBankAccountProducer.publish(closeBankAccountEvent);
        return BankAccountResponse.builder().id(id).message("Delete Account request sent successfully").build();
    }
}
