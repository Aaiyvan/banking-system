package dev.aaiyvan.loanservice.model.dto;

public record LoanResponse(
        Double amount,
        Integer term,
        Double interestRate,
        Double downpayment,
        Double monthlyPayment,
        Double totalAccruedInterest
) {
}
