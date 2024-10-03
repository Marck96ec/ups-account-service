package com.ms.account.service.infrastructure.adapters.rest;

import com.ms.account.service.application.ports.in.AccountInPort;
import com.ms.account.service.domain.models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @MockBean
    private AccountInPort accountInPort;

    @Autowired
    MockMvc mockMvc;

    HttpHeaders headers;

    private static final String URL_CREATE_ACCOUNT = "/accounts";
    private static final String URL_FIND_ACCOUNT_BY_ID = "/accounts/123";
    private static final String URL_FIND_ACCOUNTS_BY_ID_CUSTOMER = "/accounts/customer/123";
    private static final String URL_UPDATE_ACCOUNT_BY_ID = "/accounts/123";


    @BeforeEach
    public void setUp() {
        headers = new HttpHeaders();
        headers.add("x-sw-client-request-id", "123");
        headers.add("x-cm-client-user-agent", "123");
    }

    @Test
    void createAccount() throws Exception {
        when(accountInPort.saveAccount(any(Account.class))).thenReturn(new Account());
        mockMvc.perform(post(URL_CREATE_ACCOUNT)
                .content("{\n" +
                        "  \"customerId\": 1,\n" +
                        "  \"numberAccount\": \"1234567890\",\n" +
                        "  \"currencyType\": 1,\n" +
                        "  \"amount\": 1000\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andExpect(status().isCreated());
    }



    @Test
    void getAccountById() throws Exception {
        when(accountInPort.findByIdAccount(anyInt())).thenReturn(new Account());
        mockMvc.perform(get(URL_FIND_ACCOUNT_BY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    void getAccountByIdCustomer() throws Exception {
        when(accountInPort.findAccountsByCustomerIdAccount(anyInt())).thenReturn(List.of(new Account()) );
        mockMvc.perform(get(URL_FIND_ACCOUNTS_BY_ID_CUSTOMER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    void updateAccount() throws Exception {
        when(accountInPort.updateAccountAmountAccount(anyInt(), any())).thenReturn(new Account());
        mockMvc.perform(put(URL_UPDATE_ACCOUNT_BY_ID)
                        .content("{\n" +
                                "  \"customerId\": 1,\n" +
                                "  \"numberAccount\": \"1234567890\",\n" +
                                "  \"currencyType\": 1,\n" +
                                "  \"amount\": 1000\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(headers))
                .andExpect(status().isOk());
    }
}