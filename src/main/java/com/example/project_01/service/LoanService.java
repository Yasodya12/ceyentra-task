package com.example.project_01.service;

import com.example.project_01.dto.LoanDTO;

import java.util.List;

public interface LoanService {
    LoanDTO addLoan(LoanDTO loanDTO);

    List<LoanDTO> loanByAct(int id);

    List<LoanDTO> loanByUser(int id);

}
