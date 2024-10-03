package com.ms.account.service.application.service;

import com.ms.account.service.application.ports.in.AccountInPort;
import com.ms.account.service.application.ports.out.AccountOutPort;
import com.ms.account.service.domain.models.Account;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AccountService implements AccountInPort {

    private final AccountOutPort accountOutPort;

    @Override
    public Account saveAccount(Account account) {
        return accountOutPort.save(account);
    }

    @Override
    public Account findByIdAccount(Integer accountId) {
        return accountOutPort.findById(accountId);
    }

    @Override
    public List<Account> findAccountsByCustomerIdAccount(Integer customerId) {
        return accountOutPort.findAccountsByCustomerId(customerId);
    }

    @Override
    public Account updateAccountAmountAccount(Integer accountId, BigDecimal newAmount) {
        return accountOutPort.updateAccountAmount(accountId, newAmount);
    }

}
