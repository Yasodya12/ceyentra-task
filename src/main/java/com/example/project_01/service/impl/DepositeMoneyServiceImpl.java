package com.example.project_01.service.impl;

import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.entity.Account;
import com.example.project_01.entity.DepositeMoney;
import com.example.project_01.repo.AccountRepo;
import com.example.project_01.repo.DepositeMoneyRepo;
import com.example.project_01.service.DepositeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepositeMoneyServiceImpl implements DepositeMoneyService {

    AccountRepo accountRepo;
    DepositeMoneyRepo depositeMoneyRepo;

    @Autowired
    public DepositeMoneyServiceImpl(AccountRepo accountRepo, DepositeMoneyRepo depositeMoneyRepo) {
        this.accountRepo = accountRepo;
        this.depositeMoneyRepo = depositeMoneyRepo;
    }




    @Override
    public DepositeMoneyDTO depositeMoney(DepositeMoneyDTO depositeMoneyDTO) {
        DepositeMoney save = depositeMoneyRepo.save(toEnity(depositeMoneyDTO));
        Account account = accountRepo.findById(depositeMoneyDTO.getAccount()).get();
        account.setBalance(account.getBalance()+depositeMoneyDTO.getDepositeAmount());
        return toDTO(save);
    }

    @Override
    public List<DepositeMoneyDTO> getListByActId(int id) {

        return entiyListToDto( depositeMoneyRepo.findByAccount_AccntId(id));
    }

    @Override
    public List<DepositeMoneyDTO> getListByUserId(int id) {
      return entiyListToDto(depositeMoneyRepo.findByAccount_User_Id(id));
    }


    private DepositeMoney toEnity(DepositeMoneyDTO depositeMoneyDTO){


        return new DepositeMoney(incrementId(depositeMoneyRepo.findFirstByOrderByDateDesc().getDepositeID()), depositeMoneyDTO.getDepositeAmount(),
                accountRepo.findById(depositeMoneyDTO.getAccount()).get());
    }

    private DepositeMoneyDTO toDTO(DepositeMoney depositeMoney){
        return new DepositeMoneyDTO(depositeMoney.getDepositeID(), depositeMoney.getDepositeAmount(),depositeMoney.getDate(), depositeMoney.getAccount().getAccntId());
    }

    private String incrementId(String originalId) {

        String numericPart = originalId.substring(4);


        int incrementedValue = Integer.parseInt(numericPart) + 1;


        String incrementedId = String.format("DPST%03d", incrementedValue);

        return incrementedId;
    }

    private List<DepositeMoneyDTO> entiyListToDto(List<DepositeMoney> depositeMonies){

        ArrayList<DepositeMoneyDTO> depositeMoneyDTOS=new ArrayList<>();
        for (DepositeMoney depositeMoney:depositeMonies){

            depositeMoneyDTOS.add(new DepositeMoneyDTO(depositeMoney.getDepositeID(),
                 depositeMoney.getDepositeAmount(),depositeMoney.getDate(), depositeMoney.getAccount().getAccntId()));

        }
        return depositeMoneyDTOS;
    }
}
