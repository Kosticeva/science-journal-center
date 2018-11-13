package com.uns.ftn.sciencejournal.service.payment;

import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.repository.payment.SubscriptionPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPurchaseService {

    @Autowired
    SubscriptionPurchaseRepository subscriptionPurchaseRepository;

    public SubscriptionPurchase getById(Long id) {
        return subscriptionPurchaseRepository.findById(id).orElse(null);
    }

    public List<SubscriptionPurchase> getAll() {
        return subscriptionPurchaseRepository.findAll();
    }

    public SubscriptionPurchase createSubscriptionPurchase(SubscriptionPurchase subscriptionPurchase) {

        if (subscriptionPurchase.getTransactionId() != null) {
            return null;
        }

        return subscriptionPurchaseRepository.save(subscriptionPurchase);
    }

    public SubscriptionPurchase updateSubscriptionPurchase(SubscriptionPurchase newSubscriptionPurchase, Long id) {

        if (id == null) {
            return null;
        }

        SubscriptionPurchase subscriptionPurchase = getById(id);
        if (subscriptionPurchase != null) {

            return subscriptionPurchaseRepository.save(subscriptionPurchase);
        }

        return null;
    }

    public void deleteSubscriptionPurchase(Long id) {
        if (id != null) {
            subscriptionPurchaseRepository.deleteById(id);
        }
    }


}
