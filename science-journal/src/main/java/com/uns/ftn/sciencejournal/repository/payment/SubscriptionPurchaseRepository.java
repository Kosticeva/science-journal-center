package com.uns.ftn.sciencejournal.repository.payment;

import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionPurchaseRepository extends JpaRepository<SubscriptionPurchase, Long> {

    List<SubscriptionPurchase> getByUser(Credentials user);
    List<SubscriptionPurchase> getBySubscription(Subscription subscription);
}
