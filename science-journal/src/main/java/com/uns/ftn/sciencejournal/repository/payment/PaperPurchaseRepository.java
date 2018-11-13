package com.uns.ftn.sciencejournal.repository.payment;

import com.uns.ftn.sciencejournal.model.payment.PaperPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperPurchaseRepository extends JpaRepository<PaperPurchase, Long> {
}
