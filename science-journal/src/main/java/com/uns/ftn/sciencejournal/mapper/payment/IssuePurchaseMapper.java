package com.uns.ftn.sciencejournal.mapper.payment;

import com.uns.ftn.sciencejournal.dto.payment.IssuePurchaseDTO;
import com.uns.ftn.sciencejournal.model.payment.IssuePurchase;
import com.uns.ftn.sciencejournal.repository.common.IssueRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuePurchaseMapper {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public IssuePurchase mapFromDTO(IssuePurchaseDTO dto) {
        IssuePurchase purchase = new IssuePurchase();

        purchase.setIssue(issueRepository.getOne(dto.getIssue()));
        purchase.setOption(paymentOptionRepository.getOne(dto.getPaymentOption()));
        purchase.setSuccessful(dto.getSuccessful());
        purchase.setTimeOfPurchase(dto.getTimeOfPurchase());
        purchase.setTransactionId(dto.getId());
        purchase.setType(dto.getType());
        purchase.setUser(credentialsRepository.getOne(dto.getUser()));

        return purchase;
    }

    public IssuePurchaseDTO mapToDTO(IssuePurchase issuePurchase) {
        IssuePurchaseDTO dto = new IssuePurchaseDTO();

        dto.setIssue(issuePurchase.getIssue().getId());
        dto.setPaymentOption(issuePurchase.getOption().getPaymentOptionCode());
        dto.setSuccessful(issuePurchase.getSuccessful());
        dto.setTimeOfPurchase(issuePurchase.getTimeOfPurchase());
        dto.setId(issuePurchase.getTransactionId());
        dto.setType(issuePurchase.getType());
        dto.setUser(issuePurchase.getUser().getUsername());

        return dto;
    }
}
