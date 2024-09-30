package com.ms.account.service.infrastructure.adapters.customers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "T_ACCOUNTS")
@Data
public class AccountEntity {

    @Id //este atributo permite identificar el primary key por lo general estos se deben autogenerar
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera los valores para los primary key de manera auto

    @Column(name = "ACC_ID") //esto permite identificar como se llama las columnas en la BD
    private Integer id;

    @Column(name = "ACC_NUMBER_ACCOUNT")
    private String numberAccount;

    @Column(name = "ACC_CURRENCY_TYPE")
    private Integer currencyType;

    @Column(name = "ACC_AMOUNT")
    private BigDecimal amount;

    @Column(name = "ACC_CUSTOMER_ID")
    private Integer customerId;
}
