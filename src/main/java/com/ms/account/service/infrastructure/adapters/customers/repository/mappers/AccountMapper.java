package com.ms.account.service.infrastructure.adapters.customers.repository.mappers;

import com.ms.account.service.domain.models.Account;
import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "createAt", expression = "java(getCurrentTimestamp())")
    AccountEntity toCustomerEntity(Account customer);

    Account toAccount(AccountEntity accountEntity);

    default Account toAccountOptional(Optional<AccountEntity> accountEntityOptional) {
        return accountEntityOptional.map(this::toAccount).orElse(null);
    }

    default List<Account> toAccountList(List<AccountEntity> accountEntityList) {
        if (accountEntityList == null) {
            return null;
        }
        return accountEntityList.stream()
                .map(this::toAccount)
                .collect(Collectors.toList());
    }

    default Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
