package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.dto.payment.SubscriptionDTO;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO newSubscription) {
        return null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubscriptionDTO> updateSubscription(@PathVariable("id") Long id, @RequestBody SubscriptionDTO newSubscription) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSubscription(@PathVariable("id") Long id) {
        return null;
    }
}
