package com.business.creditmarketloanapi.service;

import com.business.creditmarketloanapi.dto.LoanListResponseDTO;
import com.business.creditmarketloanapi.dto.LoanRequestDTO;
import com.business.creditmarketloanapi.dto.LoanResponseDTO;
import com.business.creditmarketloanapi.global.exceptions.AttributeException;
import com.business.creditmarketloanapi.global.exceptions.ResourceNotFoundException;

import java.util.Date;

/**
 * Contains the API for loan request and consultation operations
 *
 * @author Julian Alvarado
 */
public interface LoanService {
    /**
     * Makes the request for a loan taking into account the amount, term and user requesting it
     *
     * @param loanRequestDTO DTO object with the loan application data
     * @return Return the identifier and monthly installment of the loan (DTO response object)
     * @throws ResourceNotFoundException Exception generated by the validation of the user requesting the loan and the validation of the parameters required for the calculation of the installment
     * @throws AttributeException        Exception generated by the validation of the loan amount according to the maximum amount allowed by TARGET
     */
    public LoanResponseDTO saveLoan(LoanRequestDTO loanRequestDTO) throws ResourceNotFoundException, AttributeException;

    /**
     * Calculate the value of the monthly installment of a loan
     *
     * @param rate   Loan interest rate
     * @param term   Loan term (En months)
     * @param amount Loan amount
     * @return The value of the monthly installment of a loan
     */
    public Double calculateInstallment(Double rate, Integer term, Double amount);

    /**
     * Get the list of loans requested by date. This list is paginated according to page number and size
     *
     * @param fromDate   Search start date (in yyyy-MM-dd format)
     * @param toDate     Search end date (in yyyy-MM-dd format)
     * @param pageNumber Number of results pages
     * @param pageSize   Result page size
     * @return The list of loans requested between fromDate and toDate (DTO response object)
     */
    public LoanListResponseDTO getLoanList(Date fromDate, Date toDate, int pageNumber, int pageSize);
}
