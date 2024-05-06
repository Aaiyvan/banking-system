package dev.aaiyvan.loanservice.service;

public interface InterestRateService {

   Double calculateInterestAccrued(
           Double monthlyPayment,
           Double amount,
           Double downPay,
           Integer term
   );

}
