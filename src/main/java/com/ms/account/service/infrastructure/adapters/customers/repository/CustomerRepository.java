package com.ms.account.service.infrastructure.adapters.customers.repository;

import com.ms.account.service.domain.models.Customer;
import com.ms.account.service.infrastructure.adapters.customers.entity.AccountEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(url = "http://localhost:8084/api/v1",name = "customerserviceclient")
public interface CustomerRepository {

    @GetMapping("/customers/{customerId}")
    ResponseEntity<Customer> getCustomerById(@RequestHeader(value = "x-sw-client-request-id", required = true) String xCmClientRequestId,
                                            @RequestHeader(value = "x-sw-client-device-type", required = true) String xCmClientUserAgent,
                                            @PathVariable Integer customerId);

}
