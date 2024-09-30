package com.ms.account.service.infrastructure.adapters.customers.repository.mappers;

import com.ms.account.service.domain.models.Account;
import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity toCustomerEntity(Account customer);

    Account toAccount(AccountEntity accountEntity);
}
