package com.example.project_01.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//This is the endpoint that we settle the loan

//This endpoint  can access to User only




@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayApi {
    @GetMapping
    public String getAccount(){
        System.out.println("get pay call");
        return "get pay";
    }
}
