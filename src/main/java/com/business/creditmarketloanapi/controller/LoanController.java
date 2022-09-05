package com.business.creditmarketloanapi.controller;

import com.business.creditmarketloanapi.dto.LoanRequestDTO;
import com.business.creditmarketloanapi.dto.LoanResponseDTO;
import com.business.creditmarketloanapi.dto.ResponseDTO;
import com.business.creditmarketloanapi.entity.Loan;
import com.business.creditmarketloanapi.global.exceptions.AttributeException;
import com.business.creditmarketloanapi.global.exceptions.ResourceNotFoundException;
import com.business.creditmarketloanapi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/loan")
    public ResponseEntity<ResponseDTO> createLoan(@Valid @RequestBody LoanRequestDTO loanRequestDTO) throws ResourceNotFoundException, AttributeException {
        Loan loan = loanService.saveLoan(loanRequestDTO);

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK,
                new LoanResponseDTO(loan.getId(), loan.getInstallment())));
    }
}
