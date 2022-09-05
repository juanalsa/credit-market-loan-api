package com.business.creditmarketloanapi.service;

import com.business.creditmarketloanapi.dto.LoanRequestDTO;
import com.business.creditmarketloanapi.entity.Loan;
import com.business.creditmarketloanapi.global.exceptions.AttributeException;
import com.business.creditmarketloanapi.global.exceptions.ResourceNotFoundException;

public interface LoanService {
    public Loan saveLoan(LoanRequestDTO loanRequestDTO) throws ResourceNotFoundException, AttributeException;
    public Double calculateInstallment(Double rate, Integer term, Double amount);
}
