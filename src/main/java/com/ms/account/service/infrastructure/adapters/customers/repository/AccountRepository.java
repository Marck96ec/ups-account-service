package com.ms.account.service.infrastructure.adapters.customers.repository;

import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*
* @EnableJPA sirve para redirigir
* */


public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {

    List<AccountEntity> findByCustomerId(Integer customerId);

}
