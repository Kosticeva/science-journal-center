package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.dto.payment.SubscriptionPurchaseDTO;
import com.uns.ftn.sciencejournal.service.payment.SubscriptionPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/subscriptionPurchases")
public class SubscriptionPurchaseController {

    @Autowired
    SubscriptionPurchaseService subscriptionPurchaseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubscriptionPurchaseDTO>> getAllSubscriptionPurchases() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionPurchaseDTO> getSubscriptionPurchaseById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionPurchaseDTO> createSubscriptionPurchase(@RequestBody SubscriptionPurchaseDTO newSubscriptionPurchase) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionPurchaseDTO> updateSubscriptionPurchase(@PathVariable("id") Long id, @RequestBody SubscriptionPurchaseDTO newSubscriptionPurchase) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSubscriptionPurchase(@PathVariable("id") Long id) {
        return null;
    }
}
