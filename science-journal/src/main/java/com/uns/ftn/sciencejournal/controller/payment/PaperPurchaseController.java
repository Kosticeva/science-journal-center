package com.uns.ftn.sciencejournal.controller.payment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.uns.ftn.sciencejournal.configuration.JwtTokenProvider;
import com.uns.ftn.sciencejournal.dto.payment.PaperPurchaseDTO;
import com.uns.ftn.sciencejournal.mapper.payment.PaperPurchaseMapper;
import com.uns.ftn.sciencejournal.model.PaymentSession;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.payment.PaperPurchase;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.service.payment.PaperPurchaseService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Context;
import java.util.List;


@RestController
@RequestMapping(path = "/api/paperPurchases")
@CrossOrigin(origins = "http://localhost:4201")
public class PaperPurchaseController {

    @Autowired
    PaperPurchaseService paperPurchaseService;

    @Autowired
    PaperPurchaseMapper paperPurchaseMapper;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    JwtTokenProvider provider;

    @Autowired
    @Qualifier("simpleRestTemplate")
    RestTemplate simpleRestTemplate;

    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperPurchaseDTO>> getPaperPurchasesForLoggedUser(javax.servlet.http.HttpServletRequest request) {
        String username = provider.parseToken(request);
        return ResponseEntity.ok().body(paperPurchaseMapper.mapManyToDTO(paperPurchaseService.getAllFromUser(username)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaperPurchaseDTO>> getAllPaperPurchases() {
        return ResponseEntity.ok(paperPurchaseMapper.mapManyToDTO(paperPurchaseService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> getPaperPurchaseById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(paperPurchaseMapper.mapToDTO(paperPurchaseService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> createPaperPurchase(@RequestBody PaperPurchaseDTO newPaperPurchase) {
        if (newPaperPurchase.getId() == null) {
            PaperPurchase paperPurchase = paperPurchaseService.createPaperPurchase(
                    paperPurchaseMapper.mapFromDTO(newPaperPurchase));

            if (!paperPurchase.equals(null)) {
                return ResponseEntity.ok(paperPurchaseMapper.mapToDTO(paperPurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaperPurchaseDTO> updatePaperPurchase(@PathVariable("id") Long id,
                                                                @RequestBody PaperPurchaseDTO newPaperPurchase) {
        if (!newPaperPurchase.getId().equals(null) && !id.equals(null)) {
            PaperPurchase paperPurchase = paperPurchaseService.updatePaperPurchase(
                    paperPurchaseMapper.mapFromDTO(newPaperPurchase), id);

            if (!paperPurchase.equals(null)) {
                return ResponseEntity.ok(paperPurchaseMapper.mapToDTO(paperPurchase));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePaperPurchase(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            paperPurchaseService.deletePaperPurchase(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/buy/{doi}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> buyPaper(@PathVariable String doi, @Context HttpServletRequest request) {
        JwtTokenProvider provider = new JwtTokenProvider();

        PaymentSession session = new PaymentSession();

        Credentials credentials = credentialsRepository.findFirstByUsername(provider.parseToken(request));

        session.setUsername(credentials.getUsername());
        session.setBuyerName(credentials.getUserDetails().getfName());
        session.setBuyerSurname(credentials.getUserDetails().getlName());
        session.setBuyerEmail(credentials.getUserDetails().getEmail());

        Paper paper = paperRepository.getOne(doi);
        session.setIssn(paper.getPaperIssue().getMagazine().getIssn());
        session.setMerchandise(paper.getTitle());
        session.setPrice(paper.getPrice());
        session.setCurrency(paper.getCurrency());
        session.setQuantity(1);

        ResponseEntity<String> responseToken = simpleRestTemplate.postForEntity("https://localhost:8080/sessions", session, String.class);

        String jwtSessionToken = new Gson().fromJson(responseToken.getBody(), JsonObject.class).get("token").getAsString().substring(6);
        return ResponseEntity.ok().body("{\"link\": \"https://localhost:4200/#/choose-payment/" + jwtSessionToken + "\"}");
    }
}
