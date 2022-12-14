package com.business.creditmarketloanapi.repository;

import com.business.creditmarketloanapi.entity.Loan;
import com.business.creditmarketloanapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Contains API for basic CRUD operations and queries on the Payment entity
 *
 * @author Julian Alvarado
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    /**
     * Find all payments made on a loan
     *
     * @param loan Loan to query
     * @return The list of all payments recorded for the loan
     */
    List<Payment> findByLoan(Loan loan);

    /**
     * Find the debt of a loan until the given date
     * @param loan Loan to query
     * @param toDate Debt cut-off date
     * @return The list of all payments recorded for the loan up to date 'toDate'
     */
    List<Payment> findByLoanAndCreatedAtLessThanEqual(Loan loan, Date toDate);
}
