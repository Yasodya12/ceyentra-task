package com.example.project_01.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
}
