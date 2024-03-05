package com.example.project_01.service;

import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.entity.DepositeMoney;

import java.util.List;

public interface DepositeMoneyService {

    DepositeMoneyDTO depositeMoney(DepositeMoneyDTO depositeMoneyDTO);

    List<DepositeMoneyDTO> getListByActId(int id);

    List<DepositeMoneyDTO> getListByUserId(int id);

}
