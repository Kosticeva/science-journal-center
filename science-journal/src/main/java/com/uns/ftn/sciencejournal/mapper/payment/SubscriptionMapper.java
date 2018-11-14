package com.uns.ftn.sciencejournal.mapper.payment;

import com.uns.ftn.sciencejournal.dto.payment.SubscriptionDTO;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        subscription.setMagazine(magazineRepository.getOne(dto.getMagazine()));
        subscription.setPaid(dto.getPaid());
        subscription.setType(dto.getType());
        subscription.setUser(credentialsRepository.getOne(dto.getUser()));

        return subscription;
    }

    public SubscriptionDTO mapToDTO(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();

        dto.setCancelled(subscription.getCancelled());
        dto.setDate(subscription.getDate());
        dto.setId(subscription.getId());
        dto.setMagazine(subscription.getMagazine().getIssn());
        dto.setPaid(subscription.getPaid());
        dto.setType(subscription.getType());
        dto.setUser(subscription.getUser().getUsername());

        return dto;

    }
}
