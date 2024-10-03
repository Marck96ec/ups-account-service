package com.ms.account.service.infrastructure.adapters.rest;

import com.ms.account.service.application.ports.in.AccountInPort;
import com.ms.account.service.infrastructure.adapters.rest.mapper.AccountDomainMapper;
import com.ms.customer.service.server.AccountsApi;
import com.ms.customer.service.server.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountsApi {

    private final AccountInPort accountInPort;
    private final AccountDomainMapper accountDomainMapper;

    @Override
    @CrossOrigin
    public ResponseEntity<Account> createAccount(String xSwClientRequestId, String xCmClientUserAgent, Account account) {
        return new ResponseEntity<>(accountDomainMapper.toAccount(accountInPort.saveAccount(accountDomainMapper.toAccountDomain(account))), HttpStatus.CREATED);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Account> getAccountById(String xSwClientRequestId, String xCmClientUserAgent, Long id) {
        return new ResponseEntity<>(accountDomainMapper.toAccount(accountInPort.findByIdAccount(id.intValue())), HttpStatus.OK);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<List<Account>> getAccountByIdCustomer(String xSwClientRequestId, String xCmClientUserAgent, Long id) {
        return new ResponseEntity<>(accountDomainMapper.toAccountList(accountInPort.findAccountsByCustomerIdAccount(id.intValue())), HttpStatus.OK);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Account> updateAccount(String xSwClientRequestId, String xCmClientUserAgent, Long id, Account account) {
        return new ResponseEntity<>(accountDomainMapper.toAccount(accountInPort.updateAccountAmountAccount(id.intValue(), account.getAmount())), HttpStatus.OK);
    }
}
