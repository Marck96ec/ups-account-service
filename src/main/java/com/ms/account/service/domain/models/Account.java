package com.ms.account.service.domain.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {

    private Integer id;
    private String numberAccount;
    private com.ms.customer.service.server.models.Account.CurrencyTypeEnum currencyType;
    private BigDecimal amount;
    private Integer customerId;

}
