package dev.aaiyvan.loanservice.service;

import dev.aaiyvan.loanservice.model.dto.LoanRequest;
import dev.aaiyvan.loanservice.model.dto.LoanResponse;
import dev.aaiyvan.loanservice.model.entity.Loan;

import java.util.List;
import java.util.UUID;

public interface LoanService {

    Loan getLoanById(UUID loanId);

    List<LoanResponse> getAllCustomerLoans(UUID customerId);

    LoanResponse getInfoLoan(UUID loanId);

    LoanResponse createLoan(LoanRequest loanRequest);

}
