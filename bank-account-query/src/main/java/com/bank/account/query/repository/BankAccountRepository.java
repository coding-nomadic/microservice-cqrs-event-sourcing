package com.bank.account.query.repository;

import com.bank.account.query.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

    public List<BankAccount>findByAccountHolder(String accountHolder);

    public BankAccount findByAccountId(String accountId);

    @Transactional
    public void deleteByAccountId(String accountId);

}
