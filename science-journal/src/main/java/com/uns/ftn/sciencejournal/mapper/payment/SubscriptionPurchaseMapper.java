package com.uns.ftn.sciencejournal.mapper.payment;

import com.uns.ftn.sciencejournal.dto.payment.SubscriptionPurchaseDTO;
import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.repository.payment.PaymentOptionRepository;
import com.uns.ftn.sciencejournal.repository.payment.SubscriptionRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionPurchaseMapper {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaymentOptionRepository paymentOptionRepository;

    public SubscriptionPurchase mapFromDTO(SubscriptionPurchaseDTO dto) {
        SubscriptionPurchase purchase = new SubscriptionPurchase();

        purchase.setSubscription(subscriptionRepository.getOne(dto.getSubscription()));
        purchase.setOption(paymentOptionRepository.getOne(dto.getPaymentOption()));
        purchase.setSuccessful(dto.getSuccessful());
        purchase.setTimeOfPurchase(dto.getTimeOfPurchase());
        purchase.setTransactionId(dto.getId());
        purchase.setType(dto.getType());
        purchase.setUser(credentialsRepository.getOne(dto.getUser()));

        return purchase;
    }

    public SubscriptionPurchaseDTO mapToDTO(SubscriptionPurchase subscriptionPurchase) {
        SubscriptionPurchaseDTO dto = new SubscriptionPurchaseDTO();

        dto.setSubscription(subscriptionPurchase.getSubscription().getId());
        dto.setPaymentOption(subscriptionPurchase.getOption().getPaymentOptionCode());
        dto.setSuccessful(subscriptionPurchase.getSuccessful());
        dto.setTimeOfPurchase(subscriptionPurchase.getTimeOfPurchase());
        dto.setId(subscriptionPurchase.getTransactionId());
        dto.setType(subscriptionPurchase.getType());
        dto.setUser(subscriptionPurchase.getUser().getUsername());

        return dto;
    }

    public List<SubscriptionPurchase> mapManyFromDTO(List<SubscriptionPurchaseDTO> subscriptionPurchaseDTOs) {
        List<SubscriptionPurchase> subscriptionPurchases = new ArrayList<>();
        for (SubscriptionPurchaseDTO subscriptionPurchaseDTO : subscriptionPurchaseDTOs) {
            subscriptionPurchases.add(mapFromDTO(subscriptionPurchaseDTO));
        }

        return subscriptionPurchases;
    }

    public List<SubscriptionPurchaseDTO> mapManyToDTO(List<SubscriptionPurchase> subscriptionPurchases) {
        List<SubscriptionPurchaseDTO> subscriptionPurchaseDTOs = new ArrayList<>();
        for (SubscriptionPurchase subscriptionPurchase : subscriptionPurchases) {
            subscriptionPurchaseDTOs.add(mapToDTO(subscriptionPurchase));
        }

        return subscriptionPurchaseDTOs;
    }
}
