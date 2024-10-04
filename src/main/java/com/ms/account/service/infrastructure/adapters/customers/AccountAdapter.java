package com.ms.account.service.infrastructure.adapters.customers;

import com.ms.account.service.application.ports.out.AccountOutPort;
import com.ms.account.service.domain.models.Account;
import com.ms.account.service.domain.models.Customer;
import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import com.ms.account.service.infrastructure.adapters.customers.repository.AccountRepository;
import com.ms.account.service.infrastructure.adapters.customers.repository.CustomerRepository;
import com.ms.account.service.infrastructure.adapters.customers.repository.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor

public class AccountAdapter implements AccountOutPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CustomerRepository customerRepository;


   @Override
public Account save(Account account) {
    Customer customer =
            customerRepository.getCustomerById("asd", "asd", account.getCustomerId()).getBody();
    if (Objects.isNull(customer)) {
        return null;
    }
    AccountEntity customerEntity = accountMapper.toCustomerEntity(account);
    return accountMapper.toAccount(accountRepository.save(customerEntity));
}

    @Override
    public Account findById(Integer accountId) {
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);
       if (Objects.isNull(optionalAccountEntity)){
            return null;
        }else{
           return accountMapper.toAccountOptional(accountRepository.findById(accountId));
       }

    }

    public List<Account> findAccountsByCustomerId(Integer customerId) {
        List<AccountEntity> accountEntityList = accountRepository.findByCustomerId(customerId);
        if (Objects.isNull(accountEntityList) || accountEntityList.isEmpty()){
            return null;
        }else{
            return accountMapper.toAccountList(accountEntityList);
        }
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
