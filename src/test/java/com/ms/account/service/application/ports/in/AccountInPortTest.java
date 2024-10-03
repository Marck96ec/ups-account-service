package com.ms.account.service.application.ports.in;

import com.ms.account.service.application.ports.out.AccountOutPort;
import com.ms.account.service.domain.models.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountInPortTest {

    @Autowired
    private AccountInPort accountInPort;

    @MockBean
    private AccountOutPort accountOutPort;

    @Test
    void saveAccount() {
        when(accountOutPort.save(new Account())).thenReturn(new Account());
        Account account = new Account();
        Account savedAccount = accountInPort.saveAccount(account);
        assertNotNull(savedAccount);
    }

    @Test
    void findByIdAccount() {
        when(accountOutPort.findById(1)).thenReturn(new Account());
        Integer accountId = 1;
        Account account = accountInPort.findByIdAccount(accountId);
        assertNotNull(account);
    }

    @Test
    void findAccountsByCustomerIdAccount() {
        when(accountOutPort.findAccountsByCustomerId(1)).thenReturn(List.of(new Account()));
        Integer customerId = 1;
        List<Account> accounts = accountInPort.findAccountsByCustomerIdAccount(customerId);
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void updateAccountAmountAccount_updatesAmountSuccessfully() {
        Integer accountId = 1;
        BigDecimal newAmount = new BigDecimal("1000.00");
        Account mockAccount = new Account();
        mockAccount.setAmount(newAmount);
        when(accountOutPort.updateAccountAmount(accountId, newAmount)).thenReturn(mockAccount);

        Account updatedAccount = accountInPort.updateAccountAmountAccount(accountId, newAmount);

        assertNotNull(updatedAccount);
        assertEquals(newAmount, updatedAccount.getAmount());
    }

    @Test
    void updateAccountAmountAccount_accountNotFound() {
        Integer accountId = 1;
        BigDecimal newAmount = new BigDecimal("1000.00");
        when(accountOutPort.updateAccountAmount(accountId, newAmount)).thenReturn(null);

        Account updatedAccount = accountInPort.updateAccountAmountAccount(accountId, newAmount);

        assertNull(updatedAccount);
    }

    @Test
    void updateAccountAmountAccount_invalidAmount() {
        Integer accountId = 1;
        BigDecimal newAmount = new BigDecimal("-1000.00");
        when(accountOutPort.updateAccountAmount(accountId, newAmount)).thenReturn(null);

        Account updatedAccount = accountInPort.updateAccountAmountAccount(accountId, newAmount);

        assertNull(updatedAccount);
    }
}