package com.example.project_01.service;

import com.example.project_01.dto.LoanDTO;

public interface LoanService {
    LoanDTO addLoan(LoanDTO loanDTO);

    List<LoanDTO> loanByAct(int id);
}
