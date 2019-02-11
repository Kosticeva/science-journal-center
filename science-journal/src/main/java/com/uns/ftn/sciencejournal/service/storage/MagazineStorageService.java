package com.uns.ftn.sciencejournal.service.storage;

import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MagazineStorageService {

    private final StorageService storageService;

    private static final String ISSUES_REPO_NAME = "issues";
    private static final String APPLICATIONS_REPO_NAME = "applications";

    @Autowired
    public MagazineStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    //init magazine repo
    //init applications repo
    public Path initMagazineRepository(Magazine magazine) {
        try {
            Path magazineRepo = storageService.createDirectory(magazine.getIssn(), null);
            Path magazineIssuesRepo = storageService.createDirectory(ISSUES_REPO_NAME, magazineRepo);
            Path magazineApplicationsRepo = storageService.createDirectory(APPLICATIONS_REPO_NAME, magazineRepo);
            return magazineRepo;
        }catch (StorageException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    public void removeMagazineRepository(Magazine magazine) {
        storageService.deleteDirectory(storageService.load(magazine.getIssn()));
    }

    public Path initIssueRepository(Issue issue){
        try {
            return storageService.createDirectory(issue.getId().toString(),
                    storageService.load(issue.getMagazine().getIssn()).resolve(ISSUES_REPO_NAME));
        }catch (StorageException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    public void removeIssueRepository(Issue issue) {
        storageService.deleteDirectory(storageService.load(issue.getMagazine().getIssn()).resolve(ISSUES_REPO_NAME).resolve(issue.getId().toString()));
    }

    public Path storeApplication(Application application, MultipartFile file) {
        try{
            return storageService.store(file, getApplicationStoragePath(application));
        }catch (StorageException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    public Resource downloadPaper(Paper paper) {
        Path filePath = Paths.get("./" + paper.getFile());

        Resource resource = new FileSystemResource(filePath.normalize().toFile());
        if(resource.exists()) {
            return resource;
        } else {
            return null;
        }
    }

    public void removeApplication(Application application) {
        storageService.deleteDirectory(getApplicationStoragePath(application));
    }

    public Path publishPaper(Paper paper) {
        try{
            return storageService.copyFile(Paths.get(paper.getLastRevision().getFile()),
                    storageService.load(paper.getIssue().getMagazine().getIssn())
                            .resolve(ISSUES_REPO_NAME).resolve(paper.getIssue().getId().toString()));
        }catch (StorageException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }

    public void removePaper(Paper paper) {
        storageService.deleteDirectory(getPathToPaper(paper));
    }

    private Path getApplicationStoragePath(Application application) {
        return Paths.get(application.getMagazine().getIssn()).resolve(APPLICATIONS_REPO_NAME)
                .resolve(getApplicationNameFormat(application));
    }

    private String getApplicationNameFormat(Application application) {
        return String.format("%d-%s", application.getId(), application.getFile());
    }


    public Path getPathToPaper(Paper paper) {
        return storageService.load(paper.getIssue().getMagazine().getIssn())
                .resolve(ISSUES_REPO_NAME).resolve(paper.getIssue().getId().toString())
                .resolve(getApplicationNameFormat(paper.getLastRevision()));
    }
}
