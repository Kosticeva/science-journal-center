package com.uns.ftn.sciencejournal.handlers;

import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
import com.uns.ftn.sciencejournal.repository.common.PaperApplicationRepository;
import com.uns.ftn.sciencejournal.repository.users.ReviewerRepository;
import com.uns.ftn.sciencejournal.service.search.SearchService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperApplicationReviewProposalEndListener implements TaskListener {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    PaperApplicationRepository paperApplicationRepository;

    @Autowired
    SearchService searchService;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Override
    public void notify(DelegateTask delegateTask) {

        DelegateExecution delegateExecution = delegateTask.getExecution();

        Long paperId = (Long) runtimeService.getVariable(delegateExecution.getId(), "applicationId");
        PaperApplication app = paperApplicationRepository.getOne(paperId);

        app.setState(PaperApplicationState.REVIEW);
        paperApplicationRepository.save(app);

        String reviewer1 = runtimeService.getVariable(delegateExecution.getId(), "reviewer1").toString();
        String reviewer2 = runtimeService.getVariable(delegateExecution.getId(), "reviewer2").toString();

        String errorMessage = "";
        if(reviewer1.equals(reviewer2)) {
            errorMessage = "Recenzenti moraju biti razlicite osobe";
            runtimeService.setVariable(delegateExecution.getId(), "Choose_reviewers_error_message", errorMessage);
            throw new ValidationException(errorMessage);
        }

        boolean isInField = (boolean) runtimeService.getVariable(delegateExecution.getId(), "should_work_in_field");
        boolean isNotNearby = (boolean) runtimeService.getVariable(delegateExecution.getId(), "should_not_live_nearby_author");
        boolean isExpirienced = (boolean) runtimeService.getVariable(delegateExecution.getId(), "should_review_similar_papers");

        List<Credentials> reviewerList = new ArrayList<>();

        if (isInField && isNotNearby && isExpirienced) {  //111
            reviewerList = searchService.searchReviewers(app);
        } else if (isInField && isNotNearby) {             //110
            reviewerList = searchService.searchReviewersByRadiusAndField(app);
        } else if (isInField && isExpirienced) {         //101
            reviewerList = searchService.searchReviewersBySimilarityAndFields(app);
        } else if (isInField) {        //100
            reviewerList = reviewerRepository.findByMagazinesAndFields(app.getMagazine(), app.getField())
                    .stream()
                    .map(Reviewer::getUser)
                    .collect(Collectors.toList());          //011
        } else if (isNotNearby && isExpirienced) {
            reviewerList = searchService.searchReviewersBySimilarityAndRadius(app);
        } else if (isNotNearby) {    //010
            reviewerList = searchService.searchReviewersByRadius(app);
        } else if (isExpirienced) {    //001
            reviewerList = searchService.searchReviewersBySimilarity(app);
        } else {
            reviewerList = reviewerRepository.findByMagazines(app.getMagazine())
                    .stream()
                    .map(Reviewer::getUser)
                    .collect(Collectors.toList());
        }

        boolean firstRevMatches = reviewerList.stream().anyMatch(user -> user.getUsername().equals(reviewer1));
        boolean secondRevMatches = reviewerList.stream().anyMatch(user -> user.getUsername().equals(reviewer2));

        if (!firstRevMatches && !secondRevMatches) {
            errorMessage = "Recenzenti ne odgovaraju datim kriterijumima.";
        } else if(!firstRevMatches) {
            errorMessage = reviewer1 + " ne odgovara kriterijumu.";
        } else if(!secondRevMatches) {
            errorMessage = reviewer2 + " ne odgovara kriterijumu.";
        }

        if(!firstRevMatches || !secondRevMatches){
            errorMessage += " Moguci recenzenti su: "
                    + reviewerList.stream().map(Credentials::getUsername).collect(Collectors.joining(","));
        }

        if(!"".equals(errorMessage)) {
            runtimeService.setVariable(delegateExecution.getId(), "Choose_reviewers_error_message", errorMessage);
            throw new ValidationException(errorMessage);
        }
    }
}
