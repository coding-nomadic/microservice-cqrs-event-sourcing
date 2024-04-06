package com.bank.account.command.service;

import com.bank.account.command.constant.EventType;
import com.bank.account.command.entity.BankAccount;
import com.bank.account.command.event.DepositFundAccountEvent;
import com.bank.account.command.exception.OpenAccountProcessingException;
import com.bank.account.command.model.BankAccountResponse;
import com.bank.account.command.producer.DepositFundAccountProducer;
import com.bank.account.command.repository.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class DepositFundAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final DepositFundAccountProducer depositFundAccountProducer;

    @Autowired
    public DepositFundAccountService(BankAccountRepository bankAccountRepository, DepositFundAccountProducer depositFundAccountProducer) {
        this.bankAccountRepository = bankAccountRepository;
        this.depositFundAccountProducer = depositFundAccountProducer;
    }

    @Transactional
    public BankAccountResponse process(String id, double amount) throws OpenAccountProcessingException {
        log.info("Processing deposit funds request for account ID: {}, Amount: {}", id, amount);
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(id);
        BankAccount bankAccount = optionalBankAccount.orElseThrow(() -> new OpenAccountProcessingException("Bank account not found", "404"));

        double currentBalance = bankAccount.getOpeningBalance();
        bankAccount.setOpeningBalance(currentBalance + amount);
        bankAccountRepository.save(bankAccount);

        DepositFundAccountEvent depositFundAccountEvent = DepositFundAccountEvent.builder().eventType(EventType.DEPOSIT_FUND).accountId(id).amount(amount).build();
        depositFundAccountProducer.publish(depositFundAccountEvent);

        log.info("Deposit funds request completed successfully for account ID: {}, Amount: {}", id, amount);
        return BankAccountResponse.builder().id(id).message("Deposit funds request completed successfully!").build();
    }
}
