package com.uns.ftn.sciencejournal.mapper;

import com.uns.ftn.sciencejournal.model.PaperSearchModel;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.common.Task;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.repository.common.TaskRepository;
import com.uns.ftn.sciencejournal.service.utils.GoogleCoordinatesService;
import com.uns.ftn.sciencejournal.service.utils.PDFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ElasticSearchPaperMapper {

    @Autowired
    TaskRepository taskRepository;
    
    public PaperSearchModel mapPaperToElasticSearchModel(Paper paper){
        PaperSearchModel paperSearchModel = new PaperSearchModel();

        paperSearchModel.setDoi(paper.getDoi());
        paperSearchModel.setTitle(paper.getTitle());
        if(paper.getField() != null) paperSearchModel.setField(paper.getField().getName());
        paperSearchModel.setKeywords(parseKeywords(paper.getKeyTerms()));
        paperSearchModel.setContent(PDFUtils.readFromPDF(paper.getFile()));

        if(paper.getIssue() != null && paper.getIssue().getMagazine() != null) {
            paperSearchModel.setMagazine(paperSearchModel.new PaperMagazineSearchModel(
                    paper.getIssue().getMagazine().getIssn(), paper.getIssue().getMagazine().getName()));
        }

        List<PaperSearchModel.PaperAuthorSearchModel> authors = new ArrayList<>();

        User author = paper.getAuthor().getUserDetails();
        authors.add(paperSearchModel.new PaperAuthorSearchModel(
                author.getfName(), author.getlName(),
                author.getUserId(), paperSearchModel.new Location(author.getLatitude().toString(), author.getLongitude().toString())));

        for(User coauthor: paper.getCoauthors()){
            authors.add(paperSearchModel.new PaperAuthorSearchModel(
                    coauthor.getfName(), coauthor.getlName(),
                    coauthor.getUserId(), paperSearchModel.new Location(coauthor.getLatitude().toString(), coauthor.getLongitude().toString())));
        }
        
        paperSearchModel.setAuthors(authors);

        List<PaperSearchModel.PaperReviewerSearchModel> reviewers = new ArrayList<>();
        List<Task> reviewTasks = taskRepository.findByPaperAndType(paper.getLastRevision(), PaperApplicationState.REVIEW);
        
        for(Task task: reviewTasks){
            User reviewer = task.getUser().getUserDetails();
            reviewers.add(paperSearchModel.new PaperReviewerSearchModel(
                    task.getUser().getUsername(), paperSearchModel.new Location(reviewer.getLatitude().toString(), reviewer.getLongitude().toString())));
        }
        
        return paperSearchModel;
    }

    private List<String> parseKeywords(String keywords){
        String[] wordsArray = keywords.split(",");
        List<String> wordsList = new ArrayList<>();
        wordsList.addAll(Arrays.asList(wordsArray));
        return wordsList;
    }
}
