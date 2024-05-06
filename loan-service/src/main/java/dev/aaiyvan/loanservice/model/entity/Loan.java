package dev.aaiyvan.loanservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "t_loans")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "c_amount")
    Double amount;

    @Column(name = "c_term")
    Integer term;

    @Column(name = "c_interest_rate")
    Double interestRate;

    @Column(name = "c_downpayment")
    Double downpayment;

    @Column(name = "c_monthly_payment")
    Double monthlyPayment;

    @Column(name = "c_total_accrued_interest")
    Double totalAccruedInterest;

    @Column(name = "c_customer_id")
    UUID customerId;

}
