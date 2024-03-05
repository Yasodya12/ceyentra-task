package com.example.project_01.service;

import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.entity.LoanSettle;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleLoanService {
    LoanSettleDTO payLoan(LoanSettleDTO loanSettleDTO);
}
