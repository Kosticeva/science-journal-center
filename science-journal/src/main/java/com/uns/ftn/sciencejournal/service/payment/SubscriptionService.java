package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.repository.common.MagazineRepository;
import com.uns.ftn.sciencejournal.repository.payment.SubscriptionRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    MagazineRepository magazineRepository;

    public Subscription getById(Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription createSubscription(Subscription subscription) {

        if (subscription.getId() != null) {
            return null;
        }

        if (!checkSubscriptionValidity(subscription)) {
            return null;
        }

        subscription.setPaid(false);
        subscription.setCancelled(null);

        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Subscription newSubscription, Long id) {

        if (id == null) {
            return null;
        }

        Subscription subscription = getById(id);
        if (subscription == null) {
            return null;
        }

        if (!checkSubscriptionValidity(newSubscription)) {
            return null;
        }

        subscription.setCancelled(newSubscription.getCancelled());
        subscription.setPaid(newSubscription.getPaid());
        subscription.setDate(newSubscription.getDate());
        subscription.setMagazine(newSubscription.getMagazine());
        subscription.setType(newSubscription.getType());
        subscription.setUser(newSubscription.getUser());

        return subscriptionRepository.save(subscription);
    }

    private boolean checkSubscriptionValidity(Subscription subscription) {
        if (subscription.getType() == null) {
            return false;
        }

        if (subscription.getDate() == null) {
            return false;
        }

        if (subscription.getMagazine() == null || subscription.getMagazine().getIssn() == null) {
            return false;
        }

        if (magazineRepository.getOne(subscription.getMagazine().getIssn()) == null) {
            return false;
        }

        if (subscription.getUser() == null || subscription.getUser().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(subscription.getUser().getUsername()) == null) {
            return false;
        }

        return true;
    }

    public void deleteSubscription(Long id) {
        if (id != null) {
            subscriptionRepository.deleteById(id);
        }
    }


}
