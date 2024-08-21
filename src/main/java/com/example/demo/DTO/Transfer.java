package com.example.demo.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public class Transfer {

    private UUID fromAccountNumber;
    private UUID toAccountNumber;
    private BigDecimal balance;

    public UUID getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(UUID accountNumber) {
        this.fromAccountNumber = accountNumber;
    }

    public UUID getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(UUID accountNumber) {
        this.toAccountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
