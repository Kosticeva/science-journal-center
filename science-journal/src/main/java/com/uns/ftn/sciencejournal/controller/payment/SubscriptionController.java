package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.dto.payment.SubscriptionDTO;
import com.uns.ftn.sciencejournal.mapper.payment.SubscriptionMapper;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/subscriptions")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    SubscriptionMapper subscriptionMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionMapper.mapManyToDTO(subscriptionService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(subscriptionMapper.mapToDTO(subscriptionService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO newSubscription) {
        if (newSubscription.getId().equals(null)) {
            Subscription subscription = subscriptionService.createSubscription(subscriptionMapper.mapFromDTO(newSubscription));

            if (!subscription.equals(null)) {
                return ResponseEntity.ok(subscriptionMapper.mapToDTO(subscription));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> updateSubscription(@PathVariable("id") Long id, @RequestBody SubscriptionDTO newSubscription) {
        if (!newSubscription.getId().equals(null) && !id.equals(null)) {
            Subscription subscription = subscriptionService.updateSubscription(subscriptionMapper.mapFromDTO(newSubscription), id);

            if (!subscription.equals(null)) {
                return ResponseEntity.ok(subscriptionMapper.mapToDTO(subscription));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSubscription(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            subscriptionService.deleteSubscription(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
