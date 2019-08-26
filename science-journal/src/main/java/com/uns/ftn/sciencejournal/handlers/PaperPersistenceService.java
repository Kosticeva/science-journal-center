package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.common.PaperIssue;
import com.uns.ftn.sciencejournal.model.enums.MagazinePaymentType;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperIssueRepository;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.service.common.PaperService;
import com.uns.ftn.sciencejournal.service.utils.DOIUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaperPersistenceService implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Autowired
    PaperIssueRepository paperIssueRepository;

    @Autowired
    PaperService paperService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Long appId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        PaperApplication app = paperApplicationRepository.getOne(appId);

        Paper paper = new Paper();
        paper.setAuthor(app.getAuthor());
        paper.setField(app.getField());
        paper.setKeyTerms(app.getKeyTerms());
        paper.setTitle(app.getTitle());
        paper.setFile(app.getFile());

        paper.setCoauthors(app.getCoauthors());
        paper.setPaperAbstract(app.getPaperAbstract());

        paper.setLastRevision(app);

        Optional<PaperIssue> lastPaperIssue = paperIssueRepository.findByMagazine(app.getMagazine())
                .stream()
                .max(Comparator.comparing(PaperIssue::getId));

        lastPaperIssue.ifPresent(paper::setPaperIssue);

        if (app.getMagazine().getType() == MagazinePaymentType.PAID_ACCESS) {
            paper.setCurrency(app.getMagazine().getCurrency());
            paper.setPrice(app.getMagazine().getMembership());
        }

        paper.setDoi(UUID.randomUUID().toString());
        paperService.createPaper(paper);
    }
}
