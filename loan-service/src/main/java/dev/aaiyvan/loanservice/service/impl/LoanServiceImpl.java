package dev.aaiyvan.loanservice.service.impl;

import dev.aaiyvan.loanservice.exception.*;
import dev.aaiyvan.loanservice.mapper.LoanMapper;
import dev.aaiyvan.loanservice.model.dto.LoanRequest;
import dev.aaiyvan.loanservice.model.dto.LoanResponse;
import dev.aaiyvan.loanservice.model.entity.Loan;
import dev.aaiyvan.loanservice.repository.LoanRepository;
import dev.aaiyvan.loanservice.service.InterestRateService;
import dev.aaiyvan.loanservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final InterestRateService interestRateService;
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Override
    public Loan getLoanById(
            final UUID loanId
    ) {
        return loanRepository.findById(loanId)
                .orElseThrow(LoanNotFoundException::new);
    }

    @Override
    public List<LoanResponse> getAllCustomerLoans(
            final UUID customerId
    ) {
        return loanMapper.toResponse(loanRepository.findAllByCustomerId(customerId));
    }

    @Override
    public LoanResponse getInfoLoan(
            final UUID loanId
    ) {
        return loanMapper.toResponse(getLoanById(loanId));
    }

    @Override
    public LoanResponse createLoan(
            final LoanRequest loanRequest
    ) {
        Loan loan = loanMapper.toLoan(loanRequest);

        loan.setInterestRate(16.00);

        loan.setMonthlyPayment(calculateMonthlyPayment(
                loan.getAmount(),
                loan.getTerm(),
                loan.getInterestRate(),
                loan.getDownpayment()
        ));

        loan.setTotalAccruedInterest(interestRateService.calculateInterestAccrued(
                loan.getMonthlyPayment(),
                loan.getAmount(),
                loan.getDownpayment(),
                loan.getTerm()
        ));

        loanRepository.save(loan);
        log.info("Saving loan with id: {}", loan.getId());

        return loanMapper.toResponse(loan);
    }

    public static Double calculateMonthlyPayment(
            Double amount,
            final Integer term,
            final Double rate,
            final Double downPay
    ) {
        double monthlyRate = (rate/100.0) / 12;
        int termsInMonths = term * 12;
        amount -= downPay;

        double monthlyPayment = (monthlyRate * amount)/(1-Math.pow((1+monthlyRate), -termsInMonths));

        return monthlyPayment;
    }


}
