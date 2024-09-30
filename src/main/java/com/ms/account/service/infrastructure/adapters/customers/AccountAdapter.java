package com.ms.account.service.infrastructure.adapters.customers;

import com.ms.account.service.application.ports.out.AccountOutPort;
import com.ms.account.service.domain.models.Account;
import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import com.ms.account.service.infrastructure.adapters.customers.repository.AccountRepository;
import com.ms.account.service.infrastructure.adapters.customers.repository.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


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
}
