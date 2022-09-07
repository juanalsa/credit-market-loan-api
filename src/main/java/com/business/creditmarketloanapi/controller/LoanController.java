package com.business.creditmarketloanapi.controller;

import com.business.creditmarketloanapi.dto.LoanRequestDTO;
import com.business.creditmarketloanapi.dto.ResponseDTO;
import com.business.creditmarketloanapi.global.exceptions.AttributeException;
import com.business.creditmarketloanapi.global.exceptions.ResourceNotFoundException;
import com.business.creditmarketloanapi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller for requests related to loan operations
 *
 * @author Julian Alvarado
 */
@Validated
@RestController
@RequestMapping("/api/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    /**
     * Gets loans by application date
     *
     * @param from     String date with the initial date of query
     * @param to       String date with the end date of query
     * @param pageNo   Number of pages to return
     * @param pageSize Size of pages to return
     * @return HTTP response with status and list of loans requested between dates 'from' and 'to'
     * @throws ParseException Exception generated in the conversion of dates from string to date format
     */
    @GetMapping
    public ResponseEntity<ResponseDTO> getLoansByCreatedAt(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) @Min(value = 1, message = "Parameter 'pageNo' must be greater than or equal to 1") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) @Min(value = 1, message = "Parameter 'pageSize' must be greater than or equal to 1") int pageSize) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = dateFormat.parse(from + " 00:00:00");
        Date toDate = dateFormat.parse(to + " 23:59:59");

        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK,
                loanService.getLoanList(fromDate, toDate, pageNo - 1, pageSize)));
    }

    /**
     * Creates a loan application
     *
     * @param loanRequestDTO
     * @return HTTP response with status, identifier and monthly loan installment
     * @throws ResourceNotFoundException Exception generated by the validation of the user requesting the loan and the validation of the parameters required for the calculation of the installment
     * @throws AttributeException        Exception generated by the validation of the loan amount according to the maximum amount allowed by TARGET
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> createLoan(
            @Valid @RequestBody LoanRequestDTO loanRequestDTO) throws ResourceNotFoundException, AttributeException {
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK,
                loanService.saveLoan(loanRequestDTO)));
    }
}
