package com.ms.account.service.infrastructure.adapters.customers;

import com.ms.account.service.application.ports.out.AccountOutPort;
import com.ms.account.service.domain.models.Account;
import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import com.ms.account.service.infrastructure.adapters.customers.repository.AccountRepository;
import com.ms.account.service.infrastructure.adapters.customers.repository.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor

public class AccountAdapter implements AccountOutPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    @Override
    public Account save(Account account) {
        AccountEntity customerEntity = accountMapper.toCustomerEntity(account);
        return accountMapper.toAccount(accountRepository.save(customerEntity));
    }

    @Override
    public Account findById(Integer accountId) {
        return accountMapper.toAccountOptional(accountRepository.findById(accountId));
    }

    public List<Account> findAccountsByCustomerId(Integer customerId) {
        return accountMapper.toAccountList(accountRepository.findByCustomerId(customerId));
    }

    public Account updateAccountAmount(Integer accountId, BigDecimal newAmount) {

        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);


        if (!optionalAccountEntity.isPresent()) {
            return null;
        }

        AccountEntity accountEntity = optionalAccountEntity.get();

        if (accountEntity.getNumberAccount() == null || accountEntity.getNumberAccount().isEmpty()) {
            return null;
        }

        accountEntity.setAmount(newAmount);

        AccountEntity updatedAccountEntity = accountRepository.save(accountEntity);

        return accountMapper.toAccount(updatedAccountEntity);
    }




}
