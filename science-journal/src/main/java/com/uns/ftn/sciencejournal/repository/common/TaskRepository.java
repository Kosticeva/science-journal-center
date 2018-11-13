package com.uns.ftn.sciencejournal.repository.common;

import com.uns.ftn.sciencejournal.model.common.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
