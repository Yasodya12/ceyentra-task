package com.example.project_01.api;


import com.example.project_01.dto.DepositeMoneyDTO;
import com.example.project_01.service.DepositeMoneyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposite")
@CrossOrigin
public class DepositeMoneyApi {



    DepositeMoneyService depositeMoneyService;


    public DepositeMoneyApi(DepositeMoneyService depositeMoneyService) {
        this.depositeMoneyService = depositeMoneyService;
    }

    @PostMapping
    public ResponseEntity addAccount(@RequestBody DepositeMoneyDTO dto ){

            System.out.println("awa "+dto);

            DepositeMoneyDTO depositeMoneyDTO = depositeMoneyService.depositeMoney(dto);
            return ResponseEntity.ok(depositeMoneyDTO);

    }

    @GetMapping(path = "/getByAct")
    public ResponseEntity depositeByActId( @RequestParam("id") int id)  {

        return ResponseEntity.ok( depositeMoneyService.getListByActId(id));
    }

    @GetMapping(path = "/getByUser")
    public ResponseEntity depositeByUserId( @RequestParam("id") int id)  {

        return ResponseEntity.ok( depositeMoneyService.getListByUserId(id));
    }
}
