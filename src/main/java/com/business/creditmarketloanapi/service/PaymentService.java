package com.business.creditmarketloanapi.service;

import com.business.creditmarketloanapi.dto.DebtResponseDTO;
import com.business.creditmarketloanapi.dto.PaymentRequestDTO;
import com.business.creditmarketloanapi.dto.PaymentResponseDTO;
import com.business.creditmarketloanapi.entity.Loan;
import com.business.creditmarketloanapi.global.exceptions.AttributeException;
import com.business.creditmarketloanapi.global.exceptions.ResourceNotFoundException;

import java.util.Date;

/**
 * Contains the API for payment request and consultation operations
 *
 * @author Julian Alvarado
 */
public interface PaymentService {
    /**
     * Makes a loan payment registration request
     *
     * @param paymentRequestDTO DTO object with the payment data
     * @return Returns the payment identifier, the loan identifier and the debt balance (DTO response object)
     * @throws ResourceNotFoundException Exception generated by the validation of the loan to which the payment is made
     * @throws AttributeException        Exception generated by the validation of the debt amount
     */
    public PaymentResponseDTO savePayment(PaymentRequestDTO paymentRequestDTO) throws ResourceNotFoundException, AttributeException;

    /**
     * @param loanId Loan identifier
     * @param target The target of the user from whom you want to obtain the debt
     * @param toDate The debt consultation date
     * @return The debt balance according to the query parameters
     * @throws ResourceNotFoundException Exception generated in the validation of the entered loan
     * @throws AttributeException        Exception generated in the validation of the user's target
     */
    public DebtResponseDTO getLoanDebt(Long loanId, String target, Date toDate) throws ResourceNotFoundException, AttributeException;

    /**
     * Calculates the balance debt of a loan
     *
     * @param loan The loan to query
     * @return The amount of the debt
     */
    public Double calculateDebtByLoan(Loan loan);

    /**
     * Calculates the balance debt of a loan by date
     *
     * @param loan   The user's target
     * @param toDate The debt consultation date
     * @return The amount of the debt
     */
    public Double calculateDebtByLoanAndDate(Loan loan, Date toDate);

    /**
     * Calculates the balance debt of a loan by target, date, or total debt
     *
     * @param target The user's target
     * @param toDate The debt consultation date
     * @return The amount of the debt
     */
    public Double calculateDebtByTargetAndDate(String target, Date toDate);
}
