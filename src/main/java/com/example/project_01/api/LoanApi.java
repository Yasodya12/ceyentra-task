package com.example.project_01.api;

import com.example.project_01.dto.AccountDTO;
import com.example.project_01.dto.LoanDTO;
import com.example.project_01.service.LoanService;
import com.example.project_01.util.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//This endpoint allows basic crud operation for loan

//This endpoint  can access to User only

@RestController
@RequestMapping("/loan")
@CrossOrigin
public class LoanApi {
    LoanService loanService;

    public LoanApi(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public String getAccount(){
        System.out.println("get loan call");
        return "get loan";
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<LoanDTO>> getLoan(@RequestBody LoanDTO dto){


        LoanDTO loanDTO = loanService.addLoan(dto);
        ResponseMessage<LoanDTO> responseMessage = new ResponseMessage("Loan sucefully issued", loanDTO);
        return ResponseEntity.ok(responseMessage);
    }
}
