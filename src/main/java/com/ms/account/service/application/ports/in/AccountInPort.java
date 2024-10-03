package com.ms.account.service.application.ports.in;

import com.ms.account.service.domain.models.Account;
import java.math.BigDecimal;
import java.util.List;

public interface AccountInPort {

    Account saveAccount(Account account);
    Account findByIdAccount(Integer accountId);
    List<Account> findAccountsByCustomerIdAccount(Integer customerId);
    Account updateAccountAmountAccount(Integer accountId, BigDecimal newAmount);

}
