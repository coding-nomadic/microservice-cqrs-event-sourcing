package com.bank.account.command.service;

import com.bank.account.command.constant.EventType;
import com.bank.account.command.entity.BankAccount;
import com.bank.account.command.event.WithDrawFundAccountEvent;
import com.bank.account.command.exception.OpenAccountProcessingException;
import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.producer.WithDrawFundAccountProducer;
import com.bank.account.command.repository.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class WithDrawFundAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final WithDrawFundAccountProducer withDrawFundAccountProducer;

    @Autowired
    public WithDrawFundAccountService(BankAccountRepository bankAccountRepository, WithDrawFundAccountProducer withDrawFundAccountProducer) {
        this.bankAccountRepository = bankAccountRepository;
        this.withDrawFundAccountProducer = withDrawFundAccountProducer;
    }

    @Transactional
    public BankAccountResponse process(String id, double amount) throws OpenAccountProcessingException {
        log.info("Processing withdraw funds request for account ID: {}, Amount: {}", id, amount);
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);
        BankAccount bankAccount = optionalBankAccount.orElseThrow(() -> new OpenAccountProcessingException("Bank account not found", "404"));

        double currentBalance = bankAccount.getOpeningBalance();
        bankAccount.setOpeningBalance(currentBalance - amount);
        bankAccountRepository.save(bankAccount);

        WithDrawFundAccountEvent depositFundAccountEvent = WithDrawFundAccountEvent.builder().eventType(EventType.WITHDRAW_FUND).accountId(id).amount(amount).build();
        withDrawFundAccountProducer.publish(depositFundAccountEvent);

        log.info("Withdraw funds request completed successfully for account ID: {}, Amount: {}", id, amount);
        return BankAccountResponse.builder().id(id).message("Withdraw funds request completed successfully!").build();
    }
}
