package com.example.project_01.api;

import com.example.project_01.dto.LoanSettleDTO;
import com.example.project_01.service.SettleLoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//This is the endpoint that we settle the loan

//This endpoint  can access to User only




@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayApi {

    SettleLoanService settleLoanService;

    public PayApi(SettleLoanService settleLoanService) {
        this.settleLoanService = settleLoanService;
    }

    @GetMapping
    public String getAccount(){
        System.out.println("get pay call");
        return "get pay";
    }

    @PostMapping
    public ResponseEntity payLoan(@RequestBody LoanSettleDTO loanSettleDTO){

        LoanSettleDTO loanSettleDTO1 = settleLoanService.payLoan(loanSettleDTO);
        return ResponseEntity.ok(loanSettleDTO1);
    }

    @GetMapping(path = "/actID")
    public ResponseEntity getPayLoanByAct(@RequestParam("id") int id ){
        List<LoanSettleDTO> loanSettleDTOS = settleLoanService.payLoanByActId(id);
        return ResponseEntity.ok(loanSettleDTOS);

    }

    @GetMapping("/userID")
    public ResponseEntity getPayLoanByUserId(@RequestParam("id") int id ){
        List<LoanSettleDTO> loanSettleDTOS = settleLoanService.payLoanByUserId(id);
        return ResponseEntity.ok(loanSettleDTOS);

    }

    @GetMapping("/payID")
    public ResponseEntity searchByPayId(@RequestParam("id") String  id ){
        LoanSettleDTO loanSettleDTO = settleLoanService.searchByPayId(id);
        return ResponseEntity.ok(loanSettleDTO);

    }
}
