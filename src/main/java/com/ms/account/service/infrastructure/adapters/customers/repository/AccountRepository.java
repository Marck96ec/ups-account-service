package com.ms.account.service.infrastructure.adapters.customers.repository;

import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*
* @EnableJPA sirve para redirigir
* */


//JPArepository obtiene los datos de la BD, no trabaja solo necesita en el <la entidad, y aqui va el tipo de identificador>
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {

    List<AccountEntity> findByCustomerId(Integer customerId);

}
