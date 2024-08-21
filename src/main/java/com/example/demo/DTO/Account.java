package com.example.demo.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;
import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    private UUID accountNumber;
    private BigDecimal balance;
    private String firstName;
    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
