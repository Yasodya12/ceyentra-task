package com.example.project_01.dto;

import com.example.project_01.entity.Loan;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class LoanSettleDTO {

    private String id;

    private double amount;


    private Date date;


    private int loan;
}
