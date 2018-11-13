package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.repository.payment.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

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

        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Subscription newSubscription, Long id) {

        if (id == null) {
            return null;
        }

        Subscription subscription = getById(id);
        if (subscription != null) {

            return subscriptionRepository.save(subscription);
        }

        return null;
    }

    public void deleteSubscription(Long id) {
        if (id != null) {
            subscriptionRepository.deleteById(id);
        }
    }


}
