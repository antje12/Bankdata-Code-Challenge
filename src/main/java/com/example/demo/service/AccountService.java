package com.example.demo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.Account;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public boolean CreateAccount(Account account) {
        try {
            if (account.getAccountNumber() == null) {
                account.setAccountNumber(UUID.randomUUID());
            }
            var existing = accountRepository.findById(account.getAccountNumber());
            if (existing.isEmpty()) {
                accountRepository.save(account);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean DepositToAccount(UUID accountNumber, BigDecimal balance) {
        try {
            var existing = accountRepository.findById(accountNumber);
            if (existing.isPresent()) {
                var account = existing.get();
                var newBalance = account.getBalance().add(balance);
                account.setBalance(newBalance);
                accountRepository.save(account);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean TransferBetweenAccounts(UUID fromAccountNumber, UUID toAccountNumber, BigDecimal balance) {
        try {
            var existingFrom = accountRepository.findById(fromAccountNumber);
            var existingTo = accountRepository.findById(toAccountNumber);
            if (existingFrom.isPresent() && existingTo.isPresent()) {
                var accountFrom = existingFrom.get();
                var accountTo = existingTo.get();

                var newBalanceFrom = accountFrom.getBalance().subtract(balance);
                accountFrom.setBalance(newBalanceFrom);

                var newBalanceTo = accountTo.getBalance().add(balance);
                accountTo.setBalance(newBalanceTo);

                accountRepository.save(accountFrom);
                accountRepository.save(accountTo);

                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<BigDecimal> GetAccountBalance(UUID accountNumber) {
        try {
            var existing = accountRepository.findById(accountNumber);
            if (existing.isPresent()) {
                var account = existing.get();
                var balance = account.getBalance();
                return Optional.of(balance);
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
