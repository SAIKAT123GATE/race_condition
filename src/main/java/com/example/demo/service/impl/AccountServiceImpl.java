package com.example.demo.service.impl;

import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.DemoAccount;
import com.example.demo.entity.DemoUser;
import com.example.demo.model.GetAccountBalance;
import com.example.demo.model.GetAccountResponse;
import com.example.demo.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public List<GetAccountResponse> getAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();
        accountRepository.findAll().forEach(account -> {
            responseList.add(new GetAccountResponse(account.getUser().getName(), account.getBalance()));
        });
        return responseList;
    }

    @Override
    @Transactional()
    public List<GetAccountBalance> transferBalance() {
        List<GetAccountBalance> result = new ArrayList<>();
        DemoUser eko = userRepository.findById("123").orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        DemoAccount ekoAccount = accountRepository.findByUser(eko).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found")
        );

        DemoUser alex = userRepository.findById("125").orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        DemoAccount alexAccount = accountRepository.findByUser(alex).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found")
        );

        GetAccountBalance ekoBalance = new GetAccountBalance();
        GetAccountBalance alexBalance = new GetAccountBalance();

        ekoBalance.setName(eko.getName());
        ekoBalance.setLastBalance(ekoAccount.getBalance());

        alexBalance.setName(alex.getName());
        alexBalance.setLastBalance(alexAccount.getBalance());

        //* Transfer balance from eko to alex for $1
        BigDecimal amount = BigDecimal.valueOf(1);
        if (ekoAccount.getBalance().compareTo(BigDecimal.valueOf(1)) >= 1) {
            ekoAccount.setBalance(ekoAccount.getBalance().subtract(amount));
            DemoAccount ekoSuccessTransfer = accountRepository.saveAndFlush(ekoAccount);
            ekoBalance.setCurrentBalance(ekoSuccessTransfer.getBalance());
            alexAccount.setBalance(alexAccount.getBalance().add(amount));
            DemoAccount alexSuccessReceive = accountRepository.saveAndFlush(alexAccount);
            alexBalance.setCurrentBalance(alexSuccessReceive.getBalance());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }
        result.add(ekoBalance);
        result.add(alexBalance);
        System.out.println("Transfer Balance");
        return result;
    }
}
