package com.ms.account.service.infrastructure.adapters.rest;

import com.ms.account.service.application.ports.in.AccountInPort;
import com.ms.account.service.globalexceptions.InvalidAccountException;
import com.ms.account.service.infrastructure.adapters.rest.mapper.AccountDomainMapper;
import com.ms.customer.service.server.AccountsApi;
import com.ms.customer.service.server.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountsApi {

    private final AccountInPort accountInPort;
    private final AccountDomainMapper accountDomainMapper;

    @Override
    @CrossOrigin
    public ResponseEntity<Account> createAccount(String xSwClientRequestId, String xCmClientUserAgent, Account account) {
        com.ms.account.service.domain.models.Account saveAccount = accountInPort.saveAccount(accountDomainMapper.toAccountDomain(account));
        if (saveAccount == null){
            throw new InvalidAccountException("Invalid customer data. Customer ID cannot be null.");

        }else{
            return new ResponseEntity<>(accountDomainMapper.toAccount(saveAccount), HttpStatus.CREATED);

        }
   }

    @Override
    @CrossOrigin
    public ResponseEntity<Account> getAccountById(String xSwClientRequestId, String xCmClientUserAgent, Long id) {
        com.ms.account.service.domain.models.Account account =accountInPort.findByIdAccount(id.intValue());
        if (Objects.isNull(account)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(accountDomainMapper.toAccount(account), HttpStatus.OK);

        }
    }

    @Override
    @CrossOrigin
    public ResponseEntity<List<Account>> getAccountByIdCustomer(String xSwClientRequestId, String xCmClientUserAgent, Long id) {
        List<com.ms.account.service.domain.models.Account> accounts = accountInPort.findAccountsByCustomerIdAccount(id.intValue());
        if (Objects.isNull(accounts)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(accountDomainMapper.toAccountList(accounts), HttpStatus.OK);

        }
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Account> updateAccount(String xSwClientRequestId, String xCmClientUserAgent, Long id, Account account) {
        return new ResponseEntity<>(accountDomainMapper.toAccount(accountInPort.updateAccountAmountAccount(id.intValue(), account.getAmount())), HttpStatus.OK);
    }
}
