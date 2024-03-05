package com.example.project_01.service;

import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.dto.WithdrawMoneyDTO;
import com.example.project_01.ex.InsufficientBalanceExeption;

import java.util.List;

public interface WithdrawMoneyService {
    WithdrawMoneyDTO withdrawMoney(WithdrawMoneyDTO withdrawMoneyDTO) throws InsufficientBalanceExeption;

    List<WithdrawMoneyDTO> getListByActId(int id);

    List<WithdrawMoneyDTO> getListByUserId(int id);
}
