package com.uns.ftn.sciencejournal.mapper;

import com.uns.ftn.sciencejournal.model.PaperSearchModel;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.service.utils.PDFUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ElasticSearchPaperMapper {

    public PaperSearchModel mapPaperToElasticSearchModel(Paper paper) {
        PaperSearchModel paperSearchModel = new PaperSearchModel();

        paperSearchModel.setDoi(paper.getDoi());
        paperSearchModel.setTitle(paper.getTitle());
        if (paper.getField() != null) paperSearchModel.setField(paper.getField().getName());
        paperSearchModel.setKeywords(parseKeywords(paper.getKeyTerms()));
        paperSearchModel.setContent(PDFUtils.readFromPDF(paper.getFile()));

        if (paper.getPaperIssue() != null && paper.getPaperIssue().getMagazine() != null) {
            paperSearchModel.setMagazine(paperSearchModel.new PaperMagazineSearchModel(
                    paper.getPaperIssue().getMagazine().getIssn(), paper.getPaperIssue().getMagazine().getName()));
        }

        List<PaperSearchModel.PaperAuthorSearchModel> authors = new ArrayList<>();

        UserDetails author = paper.getAuthor().getUserDetails();
        authors.add(paperSearchModel.new PaperAuthorSearchModel(
                author.getfName(), author.getlName(),
                author.getUserId(), paperSearchModel.new Location(author.getLatitude().toString(), author.getLongitude().toString())));

        for (UserDetails coauthor : paper.getCoauthors()) {
            authors.add(paperSearchModel.new PaperAuthorSearchModel(
                    coauthor.getfName(), coauthor.getlName(),
                    coauthor.getUserId(), paperSearchModel.new Location(coauthor.getLatitude().toString(), coauthor.getLongitude().toString())));
        }

        paperSearchModel.setAuthors(authors);

        List<PaperSearchModel.PaperReviewerSearchModel> reviewers = new ArrayList<>();

        // TODO: 8/24/2019 fixme!
        /*List<Task> reviewTasks = taskRepository.findByPaperAndType(paper.getLastRevision(), PaperApplicationState.REVIEW);
        
        for(Task task: reviewTasks){
            UserDetails reviewer = task.getUser().getUserDetails();
            reviewers.add(paperSearchModel.new PaperReviewerSearchModel(
                    task.getUser().getUsername(), paperSearchModel.new Location(reviewer.getLatitude().toString(), reviewer.getLongitude().toString())));
        }*/

        return paperSearchModel;
    }

    private List<String> parseKeywords(String keywords) {
        String[] wordsArray = keywords.split(",");
        return Arrays.asList(wordsArray);
    }
}
