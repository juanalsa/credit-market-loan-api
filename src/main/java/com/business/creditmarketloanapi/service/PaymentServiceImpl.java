package com.business.creditmarketloanapi.service;

import com.business.creditmarketloanapi.dto.DebtResponseDTO;
import com.business.creditmarketloanapi.dto.PaymentRequestDTO;
import com.business.creditmarketloanapi.dto.PaymentResponseDTO;
import com.business.creditmarketloanapi.entity.Loan;
import com.business.creditmarketloanapi.entity.Payment;
import com.business.creditmarketloanapi.global.exceptions.AttributeException;
import com.business.creditmarketloanapi.global.exceptions.ResourceNotFoundException;
import com.business.creditmarketloanapi.global.utils.Constants;
import com.business.creditmarketloanapi.repository.LoanRepository;
import com.business.creditmarketloanapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service that implements payment request and consultation operations
 *
 * @author Julian Alvarado
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Implements the process of validation, calculation and registration of a loan payment
     *
     * @param paymentRequestDTO DTO object with the payment data
     * @return DTO Response Object with payment ID, loan ID, and debt balance
     * @throws ResourceNotFoundException Exception generated by the validation of the loan to which the payment is made
     * @throws AttributeException        Exception generated by the validation of the debt amount
     */
    @Override
    public PaymentResponseDTO savePayment(PaymentRequestDTO paymentRequestDTO) throws ResourceNotFoundException, AttributeException {
        Long loanId = paymentRequestDTO.getLoan_id();
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan ID not found: " + loanId));
        Double totalDebt = calculateDebtByLoan(loan);

        if (paymentRequestDTO.getAmount() > totalDebt) {
            throw new AttributeException("The payment amount cannot be greater than the loan debt");
        }

        Payment payment = new Payment();
        payment.setLoan(loan);
        payment.setAmount(paymentRequestDTO.getAmount());
        Payment newPayment = paymentRepository.save(payment);

        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(
                newPayment.getId(),
                newPayment.getLoan().getId(),
                calculateDebtByLoan(loan)
        );
        return paymentResponseDTO;
    }

    /**
     * Implements the loan debt calculation process according to the loan identifier, user target and loan request dat
     *
     * @param loanId Loan identifier
     * @param target The target of the user from whom you want to obtain the debt
     * @param toDate The debt consultation date
     * @return DTO Response Object with debt balance
     * @throws ResourceNotFoundException Exception generated in the validation of the entered loan
     * @throws AttributeException        Exception generated in the validation of the user's target
     */
    @Override
    public DebtResponseDTO getLoanDebt(Long loanId, String target, Date toDate) throws ResourceNotFoundException, AttributeException {
        DebtResponseDTO debtResponseDTO = new DebtResponseDTO();

        if (target.length() > 0 && !Constants.TARGET.contains(target)) {
            throw new AttributeException("Target is not valid: " + target);
        }

        if (loanId != null) {
            Loan loan = loanRepository.findById(loanId)
                    .orElseThrow(() -> new ResourceNotFoundException("Loan ID not found: " + loanId));
            debtResponseDTO.setBalance(calculateDebtByLoanAndDate(loan, toDate));
        } else {
            debtResponseDTO.setBalance(calculateDebtByTargetAndDate(target, toDate));
        }

        return debtResponseDTO;
    }

    /**
     * Implements the calculation of the value of the loan debt
     *
     * @param loan The loan to query
     * @return The amount of the debt
     */
    public Double calculateDebtByLoan(Loan loan) {
        List<Payment> payments = loan.getPayments();
        Double totalAmountPaid = payments.stream()
                .map(payment -> payment.getAmount())
                .collect(Collectors.summingDouble(Double::doubleValue));

        return (loan.getAmount() - totalAmountPaid);
    }

    /**
     * Implements the calculation of the value of the loan debt by date
     *
     * @param loan   The loan to query
     * @param toDate The debt consultation date
     * @return The amount of the debt
     */
    public Double calculateDebtByLoanAndDate(Loan loan, Date toDate) {
        List<Payment> payments = paymentRepository.findByLoanAndCreatedAtLessThanEqual(loan, toDate);
        Double totalAmountPaid = payments.stream()
                .map(payment -> payment.getAmount())
                .collect(Collectors.summingDouble(Double::doubleValue));
        System.out.println("loan amount: " + loan.getAmount() + " totalAmountPaid: " + totalAmountPaid);

        return loan.getCreatedAt().before(toDate) || loan.getCreatedAt().equals(toDate) ?
                (loan.getAmount() - totalAmountPaid) : 0.0;
    }

    /**
     * Implements the calculation of the value of the loan debt by user's target and total debt
     *
     * @param target The user's target
     * @param toDate The debt consultation date
     * @return The amount of the debt
     */
    public Double calculateDebtByTargetAndDate(String target, Date toDate) {
        List<Loan> loansByTarget = new ArrayList<>();

        if (target.length() > 0) {
            loansByTarget = loanRepository.findAll().stream()
                    .filter(loan -> (loan.getUser().getTarget().equals(target)) &&
                            (loan.getCreatedAt().before(toDate) || loan.getCreatedAt().equals(toDate)))
                    .collect(Collectors.toList());
        } else {
            loansByTarget = loanRepository.findAll().stream()
                    .filter(loan -> loan.getCreatedAt().before(toDate) || loan.getCreatedAt().equals(toDate))
                    .collect(Collectors.toList());
        }

        Double totalLoanAmount = loansByTarget.stream()
                .map(loan -> loan.getAmount())
                .collect(Collectors.summingDouble(Double::doubleValue));

        Double totalAmountPaid = loansByTarget.stream()
                .map(loan -> {
                    List<Payment> loanPayments = loan.getPayments().stream()
                            .filter(payment -> payment.getCreatedAt().before(toDate) || payment.getCreatedAt().equals(toDate))
                            .collect(Collectors.toList());

                    return loanPayments.stream()
                            .map(payment -> payment.getAmount())
                            .collect(Collectors.summingDouble(Double::doubleValue));
                })
                .collect(Collectors.summingDouble(Double::doubleValue));
        System.out.println("totalLoanAmount: " + totalLoanAmount + " totalAmountPaid:" + totalAmountPaid);

        return (totalLoanAmount - totalAmountPaid);
    }
}
