package com.example.project_01.service;

import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.entity.LoanSettle;

public interface SettleLoanService {
    LoanSettle payLoan(LoanSettleDTO loanSettleDTO);
}
