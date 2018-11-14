package com.uns.ftn.sciencejournal.mapper.common;

import com.uns.ftn.sciencejournal.dto.common.TaskDTO;
import com.uns.ftn.sciencejournal.model.common.Task;
import com.uns.ftn.sciencejournal.repository.common.ApplicationRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public Task mapFromDTO(TaskDTO dto) {
        Task task = new Task();

        task.setDeadline(dto.getDeadline());
        task.setFinished(dto.getFinished());
        task.setId(dto.getId());
        task.setSummary(dto.getSummary());
        task.setType(dto.getType());
        task.setPaper(applicationRepository.getOne(dto.getPaper()));
        task.setUser(credentialsRepository.getOne(dto.getUser()));

        return task;
    }

    public TaskDTO mapToDTO(Task task) {
        TaskDTO dto = new TaskDTO();

        dto.setDeadline(task.getDeadline());
        dto.setFinished(task.getFinished());
        dto.setId(task.getId());
        dto.setSummary(task.getSummary());
        dto.setType(task.getType());
        dto.setPaper(task.getPaper().getPaperId());
        dto.setUser(task.getUser().getUsername());

        return dto;
    }
}
