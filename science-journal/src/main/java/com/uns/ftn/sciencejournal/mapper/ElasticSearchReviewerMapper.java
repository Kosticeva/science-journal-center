package com.uns.ftn.sciencejournal.mapper;

import com.uns.ftn.sciencejournal.model.PaperSearchModel;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchReviewerMapper {


    public PaperSearchModel.PaperReviewerSearchModel mapReviewerToReviewerSearchModel(Reviewer reviewer) {
        return new PaperSearchModel().new PaperReviewerSearchModel(
                /*reviewer.getUser().getUsername(), new PaperSearchModel().new Location(
                reviewer.getUser().getUserDetails().getLatitude().toString(),
                reviewer.getUser().getUserDetails().getLongitude().toString())*/);
    }

}
