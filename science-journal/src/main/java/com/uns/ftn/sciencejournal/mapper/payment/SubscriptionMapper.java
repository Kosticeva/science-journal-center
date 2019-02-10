package com.uns.ftn.sciencejournal.mapper.payment;

import com.uns.ftn.sciencejournal.dto.payment.SubscriptionDTO;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionMapper {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public Subscription mapFromDTO(SubscriptionDTO dto) {
        Subscription subscription = new Subscription();

        subscription.setCancelled(dto.getCancelled());
        subscription.setDate(dto.getDate());
        subscription.setId(dto.getId());
        if(dto.getMagazine() != null) subscription.setMagazine(magazineRepository.getOne(dto.getMagazine()));
        subscription.setPaid(dto.getPaid());
        subscription.setType(dto.getType());
        if(dto.getUser() != null) subscription.setUser(credentialsRepository.getOne(dto.getUser()));

        return subscription;
    }

    public SubscriptionDTO mapToDTO(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();

        dto.setCancelled(subscription.getCancelled());
        dto.setDate(subscription.getDate());
        dto.setId(subscription.getId());
        if(subscription.getMagazine() != null) dto.setMagazine(subscription.getMagazine().getIssn());
        dto.setPaid(subscription.getPaid());
        dto.setType(subscription.getType());
        if(subscription.getUser() != null) dto.setUser(subscription.getUser().getUsername());

        return dto;
    }

    public List<Subscription> mapManyFromDTO(List<SubscriptionDTO> subscriptionDTOs) {
        List<Subscription> subscriptions = new ArrayList<>();
        for (SubscriptionDTO subscriptionDTO : subscriptionDTOs) {
            subscriptions.add(mapFromDTO(subscriptionDTO));
        }

        return subscriptions;
    }

    public List<SubscriptionDTO> mapManyToDTO(List<Subscription> subscriptions) {
        List<SubscriptionDTO> subscriptionDTOs = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            subscriptionDTOs.add(mapToDTO(subscription));
        }

        return subscriptionDTOs;
    }
}
