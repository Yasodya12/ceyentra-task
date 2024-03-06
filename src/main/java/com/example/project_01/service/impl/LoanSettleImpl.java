package com.example.project_01.service.impl;

import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.entity.Loan;
import com.example.project_01.entity.LoanSettle;
import com.example.project_01.repo.LoanRepo;
import com.example.project_01.repo.SettleLoanRepo;
import com.example.project_01.service.SettleLoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        loanSettleDTO.setId(incrementId(settleLoanRepo.findFirstByOrderByDateDesc().getId()));
        LoanSettle save = settleLoanRepo.save(toEntity(loanSettleDTO));
        Loan loan = loanRepo.findById(loanSettleDTO.getLoan()).get();
        loan.setRemaingAmount(loan.getRemaingAmount()-loanSettleDTO.getAmount());
        loanRepo.save(loan);
        return toDTO(save);
    }

    @Override
    public LoanSettleDTO searchByPayId(String id) {
        LoanSettle loanSettle = settleLoanRepo.findById(id).get();
       return toDTO(loanSettle);
    }

    @Override
    public List<LoanSettleDTO> payLoanByActId(int id) {
        List<LoanSettle> byLoanAccountAccntId = settleLoanRepo.findByLoan_Account_AccntId(id);
        return toDtoList(byLoanAccountAccntId);
    }

    @Override
    public List<LoanSettleDTO> payLoanByUserId(int id) {
        List<LoanSettle> byLoanAccountUserId = settleLoanRepo.findByLoan_Account_UserId(id);
        return toDtoList(byLoanAccountUserId);
    }

    private LoanSettle toEntity(LoanSettleDTO loanSettleDTO){

        return new LoanSettle(loanSettleDTO.getId(),loanSettleDTO.getAmount(),loanSettleDTO.getDate(),loanRepo.findById(loanSettleDTO.getLoan()).get());
    }

    private LoanSettleDTO toDTO(LoanSettle loanSettle){
        return new LoanSettleDTO(loanSettle.getId(),loanSettle.getAmount(),loanSettle.getDate(),loanSettle.getLoan().getLoanID());
    }

    private List<LoanSettleDTO> toDtoList(List<LoanSettle> loanSettles){
        List<LoanSettleDTO> dtoList=new ArrayList();
        for (LoanSettle loans:loanSettles) {

            dtoList.add(new LoanSettleDTO(loans.getId(),loans.getAmount(),loans.getDate(),loans.getLoan().getLoanID()));
        }
        return dtoList;
    }

    private String incrementId(String originalId) {

        String numericPart = originalId.substring(3);


        int incrementedValue = Integer.parseInt(numericPart) + 1;


        String incrementedId = String.format("PAY%03d", incrementedValue);

        return incrementedId;
    }
}
