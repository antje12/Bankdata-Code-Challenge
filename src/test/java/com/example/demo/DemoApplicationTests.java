package com.example.demo;

import com.example.demo.DTO.Account;
import com.example.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreate_AccountDoesNotExist() {
        UUID accountNumber = UUID.randomUUID();
        var account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(new BigDecimal("1000.00"));
        account.setFirstName("John");
        account.setLastName("Doe");

        boolean result = accountService.CreateAccount(account);
        assertTrue(result);
    }

    @Test
    void testCreate_AccountExists() {
        UUID accountNumber = UUID.randomUUID();
        var account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(new BigDecimal("1000.00"));
        account.setFirstName("John");
        account.setLastName("Doe");

        boolean result = accountService.CreateAccount(account);
        assertTrue(result);

        result = accountService.CreateAccount(account);
        assertFalse(result);
    }

    @Test
    void testDeposit_AccountExists() {
        UUID accountNumber = UUID.randomUUID();
        var account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(new BigDecimal("1000.00"));
        account.setFirstName("John");
        account.setLastName("Doe");

        accountService.CreateAccount(account);
        var balance = accountService.GetAccountBalance(accountNumber);
        assertTrue(balance.isPresent());
        assertEquals(new BigDecimal("1000.00"), balance.get());

        boolean result = accountService.DepositToAccount(accountNumber, new BigDecimal("500.00"));
        assertTrue(result);

        balance = accountService.GetAccountBalance(accountNumber);
        assertTrue(balance.isPresent());
        assertEquals(new BigDecimal("1500.00"), balance.get());
    }

    @Test
    void testDeposit_AccountDoesNotExist() {
        UUID accountNumber = UUID.randomUUID();

        boolean result = accountService.DepositToAccount(accountNumber, new BigDecimal("500.00"));
        assertFalse(result);
    }

    @Test
    void testTransfer_BothAccountsExist() {
        UUID fromAccountNumber = UUID.randomUUID();
        var fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(new BigDecimal("1000.00"));
        fromAccount.setFirstName("John");
        fromAccount.setLastName("Doe");

        UUID toAccountNumber = UUID.randomUUID();
        var toAccount = new Account();
        toAccount.setAccountNumber(toAccountNumber);
        toAccount.setBalance(new BigDecimal("1000.00"));
        toAccount.setFirstName("Jane");
        toAccount.setLastName("Doe");

        accountService.CreateAccount(fromAccount);
        accountService.CreateAccount(toAccount);

        BigDecimal transferAmount = new BigDecimal("500.00");
        boolean result = accountService.TransferBetweenAccounts(fromAccountNumber, toAccountNumber, transferAmount);
        assertTrue(result);

        var fromAccountBalance = accountService.GetAccountBalance(fromAccountNumber);
        var toAccountBalance = accountService.GetAccountBalance(toAccountNumber);
        assertTrue(fromAccountBalance.isPresent());
        assertTrue(toAccountBalance.isPresent());
        assertEquals(new BigDecimal("500.00"), fromAccountBalance.get());
        assertEquals(new BigDecimal("1500.00"), toAccountBalance.get());
    }

    @Test
    void testTransfer_OneAccountDoesNotExist() {
        UUID fromAccountNumber = UUID.randomUUID();
        var fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setBalance(new BigDecimal("1000.00"));
        fromAccount.setFirstName("John");
        fromAccount.setLastName("Doe");

        UUID toAccountNumber = UUID.randomUUID();

        accountService.CreateAccount(fromAccount);

        BigDecimal transferAmount = new BigDecimal("500.00");

        boolean result = accountService.TransferBetweenAccounts(fromAccountNumber, toAccountNumber, transferAmount);
        assertFalse(result);

        var fromAccountBalance = accountService.GetAccountBalance(fromAccountNumber);
        var toAccountBalance = accountService.GetAccountBalance(toAccountNumber);
        assertTrue(fromAccountBalance.isPresent());
        assertFalse(toAccountBalance.isPresent());
        assertEquals(new BigDecimal("1000.00"), fromAccountBalance.get());
    }

    @Test
    void testBalance_AccountExists() {
        UUID accountNumber = UUID.randomUUID();
        var account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(new BigDecimal("1000.00"));
        account.setFirstName("John");
        account.setLastName("Doe");

        boolean result = accountService.CreateAccount(account);
        assertTrue(result);

        var balance = accountService.GetAccountBalance(accountNumber);
        assertTrue(balance.isPresent());
        assertEquals(new BigDecimal("1000.00"), balance.get());
    }

    @Test
    void testBalance_AccountDoesNotExist() {
        UUID accountNumber = UUID.randomUUID();

        var balance = accountService.GetAccountBalance(accountNumber);
        assertFalse(balance.isPresent());
    }
}
