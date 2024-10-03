package com.ms.account.service.application.ports.out;

import com.ms.account.service.domain.models.Account;
import java.math.BigDecimal;
import java.util.List;

public interface AccountOutPort {

    Account save(Account account);
    Account findById(Integer accountId);
    List<Account> findAccountsByCustomerId(Integer customerId);
    Account updateAccountAmount(Integer accountId, BigDecimal newAmount);

}
