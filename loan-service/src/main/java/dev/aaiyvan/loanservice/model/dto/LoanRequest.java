package dev.aaiyvan.loanservice.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanRequest {

    Double amount;

    Integer term;

    Double downpayment;

    UUID customerId;

}
