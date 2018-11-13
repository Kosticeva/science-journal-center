package com.uns.ftn.sciencejournal.service.common;

import com.uns.ftn.sciencejournal.model.common.Task;
import com.uns.ftn.sciencejournal.repository.common.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

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

        return taskRepository.save(task);
    }

    public Task updateTask(Task newTask, Long id) {

        if (id == null) {
            return null;
        }

        Task task = getById(id);
        if (task != null) {

            return taskRepository.save(task);
        }

        return null;
    }

    public void deleteTask(Long id) {
        if (id != null) {
            taskRepository.deleteById(id);
        }
    }


}
