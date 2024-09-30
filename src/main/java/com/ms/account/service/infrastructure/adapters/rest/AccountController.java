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

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountsApi {

//    private final CustomerInPort customerInPort;
//    private final CustomerDomainMapper customerDomainMapper;
//
//    @Override
//    @CrossOrigin
//    public ResponseEntity<Customer> createCustomer(String xCmClientRequestId, String xCmClientUserAgent, Customer customer) {
//        //customerInPort.saveCustomer(customerDomainMapper.toCustomerDomain(customer));
//        //return CustomersApi.super.createCustomer(xCmClientRequestId, xCmClientUserAgent, customer);
//        return new ResponseEntity<>(customerDomainMapper.toCustomer(customerInPort.saveCustomer(customerDomainMapper.toCustomerDomain(customer))), HttpStatus.CREATED);
//    }
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
        return AccountsApi.super.getAccountById(xSwClientRequestId, xCmClientUserAgent, id);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Account> getAccountByIdCustomer(String xSwClientRequestId, String xCmClientUserAgent, Long id) {
        return AccountsApi.super.getAccountByIdCustomer(xSwClientRequestId, xCmClientUserAgent, id);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Account> updateAccount(String xSwClientRequestId, String xCmClientUserAgent, Long id, Account account) {
        return AccountsApi.super.updateAccount(xSwClientRequestId, xCmClientUserAgent, id, account);
    }
}
