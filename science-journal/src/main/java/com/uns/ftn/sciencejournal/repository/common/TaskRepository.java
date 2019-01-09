package com.uns.ftn.sciencejournal.repository.common;

import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.model.common.Task;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByPaperAndType(Application application, PaperApplicationState state);
}
