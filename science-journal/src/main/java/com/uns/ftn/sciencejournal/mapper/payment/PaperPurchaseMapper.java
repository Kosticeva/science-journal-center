package com.uns.ftn.sciencejournal.mapper.payment;

import com.uns.ftn.sciencejournal.dto.payment.PaperPurchaseDTO;
import com.uns.ftn.sciencejournal.model.payment.PaperPurchase;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperPurchaseMapper {

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public PaperPurchase mapFromDTO(PaperPurchaseDTO dto) {
        PaperPurchase purchase = new PaperPurchase();

        purchase.setPaper(paperRepository.getOne(dto.getPaper()));
        purchase.setOption(paymentOptionRepository.getOne(dto.getPaymentOption()));
        purchase.setSuccessful(dto.getSuccessful());
        purchase.setTimeOfPurchase(dto.getTimeOfPurchase());
        purchase.setTransactionId(dto.getId());
        purchase.setType(dto.getType());
        purchase.setUser(credentialsRepository.getOne(dto.getUser()));

        return purchase;
    }

    public PaperPurchaseDTO mapToDTO(PaperPurchase paperPurchase) {
        PaperPurchaseDTO dto = new PaperPurchaseDTO();

        dto.setPaper(paperPurchase.getPaper().getDoi());
        dto.setPaymentOption(paperPurchase.getOption().getPaymentOptionCode());
        dto.setSuccessful(paperPurchase.getSuccessful());
        dto.setTimeOfPurchase(paperPurchase.getTimeOfPurchase());
        dto.setId(paperPurchase.getTransactionId());
        dto.setType(paperPurchase.getType());
        dto.setUser(paperPurchase.getUser().getUsername());

        return dto;
    }

    public List<PaperPurchase> mapManyFromDTO(List<PaperPurchaseDTO> paperPurchaseDTOs) {
        List<PaperPurchase> paperPurchases = new ArrayList<>();
        for (PaperPurchaseDTO paperPurchaseDTO : paperPurchaseDTOs) {
            paperPurchases.add(mapFromDTO(paperPurchaseDTO));
        }

        return paperPurchases;
    }

    public List<PaperPurchaseDTO> mapManyToDTO(List<PaperPurchase> paperPurchases) {
        List<PaperPurchaseDTO> paperPurchaseDTOs = new ArrayList<>();
        for (PaperPurchase paperPurchase : paperPurchases) {
            paperPurchaseDTOs.add(mapToDTO(paperPurchase));
        }

        return paperPurchaseDTOs;
    }
}
