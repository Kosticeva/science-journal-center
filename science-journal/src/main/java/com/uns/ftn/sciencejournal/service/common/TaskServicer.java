package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Task;
import com.uns.ftn.sciencejournal.repository.common.ApplicationRepository;
import com.uns.ftn.sciencejournal.repository.common.TaskRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServicer {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {

        if (task.getId() != null) {
            return null;
        }

        if (!checkTaskValidity(task)) {
            return null;
        }

        return taskRepository.save(task);
    }

    public Task updateTask(Task newTask, Long id) {

        if (id == null) {
            return null;
        }

        Task task = getById(id);
        if (task == null) {
            return null;
        }

        if (!checkTaskValidity(task)) {
            return null;
        }

        task.setDeadline(newTask.getDeadline());
        task.setFinished(newTask.getFinished());
        task.setPaper(newTask.getPaper());
        task.setSummary(newTask.getSummary());
        task.setType(newTask.getType());
        task.setUser(newTask.getUser());

        return taskRepository.save(task);
    }

    private boolean checkTaskValidity(Task task) {
        if (task.getDeadline() == null) {
            return false;
        }

        if (task.getFinished() == null) {
            return false;
        }

        if (task.getType() == null) {
            return false;
        }

        if (task.getSummary() == null || task.getSummary().equals("")) {
            return false;
        }

        if (task.getUser() == null || task.getUser().getUsername() == null) {
            return false;
        }

        if (credentialsRepository.getOne(task.getUser().getUsername()) == null) {
            return false;
        }

        if (task.getPaper() == null || task.getPaper().getId() == null) {
            return false;
        }

        if (applicationRepository.getOne(task.getPaper().getId()) == null) {
            return false;
        }

        return true;
    }

    public void deleteTask(Long id) {
        if (id != null) {
            taskRepository.deleteById(id);
        }
    }


}
