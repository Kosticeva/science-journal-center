package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.configuration.JwtTokenProvider;
import com.uns.ftn.sciencejournal.dto.payment.SubscriptionDTO;
import com.uns.ftn.sciencejournal.dto.payment.SubscriptionPurchaseDTO;
import com.uns.ftn.sciencejournal.mapper.payment.SubscriptionMapper;
import com.uns.ftn.sciencejournal.mapper.payment.SubscriptionPurchaseMapper;
import com.uns.ftn.sciencejournal.model.payment.Subscription;
import com.uns.ftn.sciencejournal.model.payment.SubscriptionPurchase;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(path = "/api/subscriptions")
@CrossOrigin(origins = "http://localhost:4201")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    SubscriptionMapper subscriptionMapper;

    @Autowired
    SubscriptionPurchaseMapper subscriptionPurchaseMapper;

    @Autowired
    JwtTokenProvider provider;

    @GetMapping(value = "/magazine/{issn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionPurchaseDTO> getActiveUserSubscriptionForMagazine(HttpServletRequest request, @PathVariable String issn) {
        String username = provider.parseToken(request);
        SubscriptionPurchase purchase = subscriptionService.findActiveUserSubscriptionForMagazine(issn, username);

        if(purchase == null) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.ok().body(subscriptionPurchaseMapper.mapToDTO(purchase));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionMapper.mapManyToDTO(subscriptionService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(subscriptionMapper.mapToDTO(subscriptionService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO newSubscription) {
        if (newSubscription.getId() == null) {
            Subscription subscription = subscriptionService.createSubscription(
                    subscriptionMapper.mapFromDTO(newSubscription));

            if (!subscription.equals(null)) {
                return ResponseEntity.ok(subscriptionMapper.mapToDTO(subscription));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> updateSubscription(@PathVariable("id") String id,
                                                              @RequestBody SubscriptionDTO newSubscription) {
        if (!newSubscription.getId().equals(null) && !id.equals(null)) {
            Subscription subscription = subscriptionService.updateSubscription(
                    subscriptionMapper.mapFromDTO(newSubscription), id);

            if (!subscription.equals(null)) {
                return ResponseEntity.ok(subscriptionMapper.mapToDTO(subscription));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSubscription(@PathVariable("id") String id) {
        if (!id.equals(null)) {
            subscriptionService.deleteSubscription(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
