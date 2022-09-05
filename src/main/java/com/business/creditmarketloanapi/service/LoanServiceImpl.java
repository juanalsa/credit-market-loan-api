package com.business.creditmarketloanapi.service;

import com.business.creditmarketloanapi.dto.LoanRequestDTO;
import com.business.creditmarketloanapi.entity.Loan;
import com.business.creditmarketloanapi.entity.Parameter;
import com.business.creditmarketloanapi.entity.User;
import com.business.creditmarketloanapi.global.exceptions.AttributeException;
import com.business.creditmarketloanapi.global.exceptions.ResourceNotFoundException;
import com.business.creditmarketloanapi.global.utils.Constants;
import com.business.creditmarketloanapi.repository.LoanRepository;
import com.business.creditmarketloanapi.repository.ParameterRepository;
import com.business.creditmarketloanapi.repository.UserRepository;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    public Loan saveLoan(LoanRequestDTO loanRequestDTO) throws ResourceNotFoundException, AttributeException {
        Long userId = loanRequestDTO.getUser_id();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ID not found: " + userId));

        Long maxAmountByTargetId = user.getTarget().equals(Constants.NEW_TARGET) ? Constants.NEW_TARGET_MAX_AMOUNT_LOANS :
                user.getTarget().equals(Constants.FREQUENT_TARGET) ? Constants.FREQUENT_TARGET_MAX_AMOUNT_LOANS :
                        Constants.PREMIUM_TARGET_MAX_AMOUNT_LOANS;

        Parameter maxAmountByTargetParameter = parameterRepository.findById(maxAmountByTargetId)
                .orElseThrow(() -> new ResourceNotFoundException("Parameter not found: " + maxAmountByTargetId));
        Double maxAmountByTargetValue = Double.parseDouble(maxAmountByTargetParameter.getStrValue());

        if (loanRequestDTO.getAmount() > maxAmountByTargetValue) {
            throw new AttributeException("Amount cannot be greater than maximum amount: " + maxAmountByTargetValue);
        }

        Long targetRateId = user.getTarget().equals(Constants.NEW_TARGET) ? Constants.NEW_TARGET_RATE :
                user.getTarget().equals(Constants.FREQUENT_TARGET) ? Constants.FREQUENT_TARGET_RATE :
                        Constants.PREMIUM_TARGET_RATE;

        Parameter targetRateParameter = parameterRepository.findById(targetRateId)
                .orElseThrow(() -> new ResourceNotFoundException("Parameter not found: " + targetRateId));
        Double targetRateValue = Double.parseDouble(targetRateParameter.getStrValue());

        Double installment = Precision.round(calculateInstallment(targetRateValue,
                        loanRequestDTO.getTerm(),
                        loanRequestDTO.getAmount()),
                Constants.DECIMAL_NUMBER_SCALE);

        Loan loan = new Loan();
        loan.setAmount(loanRequestDTO.getAmount());
        loan.setTerm(loanRequestDTO.getTerm());
        loan.setRate(targetRateValue);
        loan.setInstallment(installment);
        loan.setUser(user);

        return loanRepository.save(loan);
    }

    @Override
    public Double calculateInstallment(Double rate, Integer term, Double amount) {
        Double r = rate / 12;
        return (r + (r / (Math.pow(1 + r, term) - 1))) * amount;
    }
}
