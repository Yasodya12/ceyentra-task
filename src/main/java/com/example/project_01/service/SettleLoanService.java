package com.example.project_01.service;

import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.entity.LoanSettle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettleLoanService {
    LoanSettleDTO payLoan(LoanSettleDTO loanSettleDTO);

     LoanSettleDTO searchByPayId(String  id);
    List<LoanSettleDTO> payLoanByActId(int id);

    List<LoanSettleDTO> payLoanByUserId(int id);
}
