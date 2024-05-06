package dev.aaiyvan.loanservice.mapper;

import dev.aaiyvan.loanservice.model.dto.LoanRequest;
import dev.aaiyvan.loanservice.model.dto.LoanResponse;
import dev.aaiyvan.loanservice.model.entity.Loan;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    Loan toLoan(LoanRequest loanRequest);

    LoanResponse toResponse(Loan loan);

    List<LoanResponse> toResponse(List<Loan> loans);

}
