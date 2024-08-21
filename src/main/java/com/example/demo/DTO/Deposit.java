package com.example.demo.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public class Deposit {

    private UUID accountNumber;
    private BigDecimal balance;

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(UUID accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
