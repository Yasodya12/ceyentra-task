package com.example.project_01.api;


import com.example.project_01.config.JwtUtil;
import com.example.project_01.dto.UserDTO;
import com.example.project_01.dto.ErrorRes;
import com.example.project_01.dto.LoginReq;
import com.example.project_01.dto.LoginRes;
import com.example.project_01.ex.UserNotFoundException;
import com.example.project_01.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/log")
@CrossOrigin
public class LoginAPI {
    private final JwtUtil util;
    private final UserService service;
    public LoginAPI(UserService service, JwtUtil util){
        this.util=util;
        this.service = service;
        System.out.println("LoginAPI");
    }

   @GetMapping
    public ResponseEntity login(@RequestBody LoginReq req){
        try {
            System.out.println("get awa");
            UserDTO search = service.search(req.getEmail(), req.getPassword());
            String token = util.createToken(search);
            return ResponseEntity.ok(new LoginRes(req.getEmail(),token, Arrays.stream(search.getType()).toList()));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ErrorRes(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }

//    @GetMapping("/search")
//    public String get(){
//        System.out.println("OK");
//        return "OK";
//    }



}
