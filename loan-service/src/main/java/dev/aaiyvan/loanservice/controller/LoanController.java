package dev.aaiyvan.loanservice.controller;

import dev.aaiyvan.loanservice.model.dto.LoanRequest;
import dev.aaiyvan.loanservice.model.dto.LoanResponse;
import dev.aaiyvan.loanservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<LoanResponse>> getAllCustomerLoans(
            @PathVariable final UUID customerId
    ) {
        return ResponseEntity.ok(loanService.getAllCustomerLoans(customerId));
    }

    @GetMapping("/info/{loanId}")
    public ResponseEntity<LoanResponse> getInfoLoan(
            @PathVariable final UUID loanId
    ) {
        return ResponseEntity.ok(loanService.getInfoLoan(loanId));
    }

    @PostMapping("/create")
    public ResponseEntity<LoanResponse> takeLoan(
            @RequestBody final LoanRequest loanRequest
    ) {
        return ResponseEntity.ok(loanService.createLoan(loanRequest));
    }

}
