package com.uns.ftn.sciencejournal.mapper.payment;

import com.uns.ftn.sciencejournal.dto.payment.IssuePurchaseDTO;
import com.uns.ftn.sciencejournal.model.enums.PurchaseType;
import com.uns.ftn.sciencejournal.model.payment.IssuePurchase;
import com.uns.ftn.sciencejournal.repository.common.PaperIssueRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssuePurchaseMapper {

    @Autowired
    PaperIssueRepository paperIssueRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public IssuePurchase mapFromDTO(IssuePurchaseDTO dto) {
        IssuePurchase purchase = new IssuePurchase();

        if (dto.getIssuePK() != null) purchase.setPaperIssue(paperIssueRepository.getOne(dto.getIssuePK()));

        if (dto.getPaymentOption() != null) purchase.setOption(paymentOptionRepository.getOne(dto.getPaymentOption()));

        purchase.setSuccessful(dto.getSuccessful());
        purchase.setTimeOfPurchase(dto.getTimeOfPurchase());
        purchase.setTransactionId(dto.getId());
        purchase.setType(PurchaseType.ISSUE);
        purchase.setAmount(dto.getAmount());

        //if (dto.getUser() == null) purchase.setUser(credentialsRepository.getOne(dto.getUser()));

        return purchase;
    }

    public IssuePurchaseDTO mapToDTO(IssuePurchase issuePurchase) {
        IssuePurchaseDTO dto = new IssuePurchaseDTO();

        dto.setIssuePK(issuePurchase.getPaperIssue().getId());
        if (issuePurchase.getOption() != null) dto.setPaymentOption(issuePurchase.getOption().getPaymentOptionCode());
        dto.setSuccessful(issuePurchase.getSuccessful());
        dto.setTimeOfPurchase(issuePurchase.getTimeOfPurchase());
        dto.setId(issuePurchase.getTransactionId());
        //if(issuePurchase.getUser() != null) dto.setUser(issuePurchase.getUser().getUsername());
        dto.setAmount(issuePurchase.getAmount());

        return dto;
    }

    public List<IssuePurchase> mapManyFromDTO(List<IssuePurchaseDTO> issuePurchaseDTOs) {
        List<IssuePurchase> issuePurchases = new ArrayList<>();
        for (IssuePurchaseDTO issuePurchaseDTO : issuePurchaseDTOs) {
            issuePurchases.add(mapFromDTO(issuePurchaseDTO));
        }

        return issuePurchases;
    }

    public List<IssuePurchaseDTO> mapManyToDTO(List<IssuePurchase> issuePurchases) {
        List<IssuePurchaseDTO> issuePurchaseDTOs = new ArrayList<>();
        for (IssuePurchase issuePurchase : issuePurchases) {
            issuePurchaseDTOs.add(mapToDTO(issuePurchase));
        }

        return issuePurchaseDTOs;
    }
}
