package com.ms.account.service.infrastructure.adapters.customers.repository;

import com.ms.account.service.domain.models.Customer;
import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;


/*
* @EnableJPA sirve para redirigir
* */


public interface CustomerRepository {

    @GetMapping("/customers")
    ResponseEntity<Customer> findCustomerById(@RequestHeader(value = "x-cm-client-request-id", required = true) String xCmClientRequestId,
                                            @RequestHeader(value = "x-cm-client-user-agent", required = true) String xCmClientUserAgent,
                                            @RequestBody Customer customer);

}
