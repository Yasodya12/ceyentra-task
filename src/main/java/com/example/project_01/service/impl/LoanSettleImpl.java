package com.example.project_01.service.impl;

import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.entity.Loan;
import com.example.project_01.entity.LoanSettle;
import com.example.project_01.repo.LoanRepo;
import com.example.project_01.repo.SettleLoanRepo;
import com.example.project_01.service.SettleLoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanSettleImpl implements SettleLoanService {

    SettleLoanRepo settleLoanRepo;
    LoanRepo loanRepo;

    public LoanSettleImpl(SettleLoanRepo settleLoanRepo, LoanRepo loanRepo) {
        this.settleLoanRepo = settleLoanRepo;
        this.loanRepo = loanRepo;
    }

    @Override
    public LoanSettleDTO payLoan(LoanSettleDTO loanSettleDTO) {
        LoanSettle save = settleLoanRepo.save(toEntity(loanSettleDTO));
        Loan loan = loanRepo.findById(loanSettleDTO.getLoan()).get();
        loan.setRemaingAmount(loan.getRemaingAmount()-loanSettleDTO.getAmount());
        loanRepo.save(loan);
        return toDTO(save);
    }

    private LoanSettle toEntity(LoanSettleDTO loanSettleDTO){

        return new LoanSettle(loanSettleDTO.getId(),loanSettleDTO.getAmount(),loanSettleDTO.getDate(),loanRepo.findById(loanSettleDTO.getLoan()).get());
    }

    private LoanSettleDTO toDTO(LoanSettle loanSettle){
        return new LoanSettleDTO(loanSettle.getId(),loanSettle.getAmount(),loanSettle.getDate(),loanSettle.getLoan().getLoanID());
    }
}
