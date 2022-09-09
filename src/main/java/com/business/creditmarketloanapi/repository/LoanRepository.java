package com.business.creditmarketloanapi.repository;

import com.business.creditmarketloanapi.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Contains API for basic CRUD operations and queries on the Loan entity
 *
 * @author Julian Alvarado
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    /**
     * Filter by loans registered between two dates (fromDate and toDate)
     *
     * @param fromDate Consultation start date
     * @param toDate   Consultation end date
     * @param pageable Object that represents pagination information
     * @return Provides information about the position of an item in the containing list
     */
    Page<Loan> findByCreatedAtBetween(Date fromDate, Date toDate, Pageable pageable);
}
