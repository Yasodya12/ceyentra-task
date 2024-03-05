package com.example.project_01.api;

import com.example.project_01.dto.AccountDTO;
import com.example.project_01.dto.LoanDTO;
import org.springframework.web.bind.annotation.*;


//This endpoint allows basic crud operation for loan

//This endpoint  can access to User only

@RestController
@RequestMapping("/loan")
@CrossOrigin
public class LoanApi {
    @GetMapping
    public String getAccount(){
        System.out.println("get loan call");
        return "get loan";
    }

    @PostMapping
    public void getLoan(@RequestBody LoanDTO dto){

    }
}
