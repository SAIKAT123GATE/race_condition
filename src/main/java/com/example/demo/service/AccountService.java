package com.example.demo.service;

import com.example.demo.model.GetAccountBalance;
import com.example.demo.model.GetAccountResponse;

import java.util.List;

public interface AccountService {
    List<GetAccountResponse> getAccounts();
    List<GetAccountBalance> transferBalance();
}
