package com.uns.ftn.sciencejournal.controller.payment;

import com.uns.ftn.sciencejournal.configuration.JwtTokenProvider;
import com.uns.ftn.sciencejournal.dto.payment.IssuePurchaseDTO;
import com.uns.ftn.sciencejournal.mapper.payment.IssuePurchaseMapper;
import com.uns.ftn.sciencejournal.model.payment.IssuePurchase;
import com.uns.ftn.sciencejournal.service.payment.IssuePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(path = "/api/issuePurchases")
@CrossOrigin(origins = "http://localhost:4201")
public class IssuePurchaseController {

    @Autowired
    IssuePurchaseService issuePurchaseService;

    @Autowired
    IssuePurchaseMapper issuePurchaseMapper;

    @Autowired
    JwtTokenProvider provider;

    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssuePurchaseDTO>> getIssuePurchasesForLoggedUser(HttpServletRequest request) {
        String username = provider.parseToken(request);
        return ResponseEntity.ok().body(issuePurchaseMapper.mapManyToDTO(issuePurchaseService.getAllFromUser(username)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssuePurchaseDTO>> getAllIssuePurchases() {
        return ResponseEntity.ok(issuePurchaseMapper.mapManyToDTO(issuePurchaseService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssuePurchaseDTO> getIssuePurchaseById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(issuePurchaseMapper.mapToDTO(issuePurchaseService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssuePurchaseDTO> createIssuePurchase(@RequestBody IssuePurchaseDTO newIssuePurchase) {
        if (newIssuePurchase.getId() == null) {
            IssuePurchase issuePurchase = issuePurchaseService.createIssuePurchase(
                    issuePurchaseMapper.mapFromDTO(newIssuePurchase));

            if (!issuePurchase.equals(null)) {
                return ResponseEntity.ok(issuePurchaseMapper.mapToDTO(issuePurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssuePurchaseDTO> updateIssuePurchase(@PathVariable("id") Long id,
                                                                @RequestBody IssuePurchaseDTO newIssuePurchase) {
        if (!newIssuePurchase.getId().equals(null) && !id.equals(null)) {
            IssuePurchase issuePurchase = issuePurchaseService.updateIssuePurchase(
                    issuePurchaseMapper.mapFromDTO(newIssuePurchase), id);

            if (!issuePurchase.equals(null)) {
                return ResponseEntity.ok(issuePurchaseMapper.mapToDTO(issuePurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteIssuePurchase(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            issuePurchaseService.deleteIssuePurchase(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
