package com.ms.account.service.domain.models;

import lombok.Data;

@Data
public class Customer {

    private Integer id;
    private String identification;
    private String firstName;
    private String lastName;
    private String genre;

}
