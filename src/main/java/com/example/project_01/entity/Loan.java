package com.example.project_01.entity;


import com.example.project_01.entity.enums.LoanTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Loan {
    @Id
    private String loanID;
    @Enumerated(EnumType.STRING)
    private LoanTypes loanTypes;

    private double period;

    private double amount;

    private double interest;

    private double amountWithInterest;

    private double remaingAmount;

    private double monthlySettlement;

}
