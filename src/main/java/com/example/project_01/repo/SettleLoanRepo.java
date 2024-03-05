package com.example.project_01.repo;

import com.example.project_01.entity.LoanSettle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleLoanRepo extends JpaRepository<LoanSettle, String> {

}
