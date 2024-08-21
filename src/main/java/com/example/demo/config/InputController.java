package com.example.demo.config;

import com.example.demo.DTO.Account;
import com.example.demo.DTO.Deposit;
import com.example.demo.DTO.Transfer;
import com.example.demo.service.AccountService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class InputController {

    private final AccountService accountService;

    public InputController(AccountService accountService) {
        this.accountService = accountService;
    }

    /* http://localhost:8080/hello */
    /* http://localhost:8080/hello?name=Gudrun */
    @GetMapping("/hello")
    public ResponseEntity<?> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new ResponseEntity<>(String.format("Welcome %s!", name), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        String message;
        HttpStatusCode code;

        var result = accountService.CreateAccount(account);
        if (result) {
            message = String.format("Account created, welcome %s!", account.getFirstName());
            code = HttpStatusCode.valueOf(200);
        } else {
            message = String.format("Creation error, try again %s!", account.getFirstName());
            code = HttpStatusCode.valueOf(400);
        }

        return new ResponseEntity<>(message, code);
    }

    @PostMapping("/depositToAccount")
    public ResponseEntity<?> DepositToAccount(@RequestBody Deposit deposit) {
        String message;
        HttpStatusCode code;

        var result = accountService.DepositToAccount(deposit.getAccountNumber(), deposit.getBalance());
        if (result) {
            message = "Amount deposited!";
            code = HttpStatusCode.valueOf(200);
        } else {
            message = "Deposit error, try again!";
            code = HttpStatusCode.valueOf(400);
        }

        return new ResponseEntity<>(message, code);
    }

    @PostMapping("/transferBetweenAccounts")
    public ResponseEntity<?> TransferBetweenAccounts(@RequestBody Transfer transfer) {
        String message;
        HttpStatusCode code;

        var result = accountService.TransferBetweenAccounts(transfer.getFromAccountNumber(), transfer.getToAccountNumber(), transfer.getBalance());
        if (result) {
            message = "Amount transferred!";
            code = HttpStatusCode.valueOf(200);
        } else {
            message = "Transfer error, try again!";
            code = HttpStatusCode.valueOf(400);
        }

        return new ResponseEntity<>(message, code);
    }

    @GetMapping("/accountBalance")
    public ResponseEntity<?> AccountBalance(@RequestParam(value = "accountNumber") UUID accountNumber) {
        String message;
        HttpStatusCode code;

        var result = accountService.GetAccountBalance(accountNumber);
        if (result.isPresent()) {
            message = String.format("Account balance is %s!", result.get());
            code = HttpStatusCode.valueOf(200);
        } else {
            message = "Account not found!";
            code = HttpStatusCode.valueOf(404);
        }

        return new ResponseEntity<>(message, code);
    }
}
