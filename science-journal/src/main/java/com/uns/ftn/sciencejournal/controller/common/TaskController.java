package com.uns.ftn.sciencejournal.controller.common;

import com.uns.ftn.sciencejournal.configuration.JwtTokenProvider;
import com.uns.ftn.sciencejournal.dto.common.TaskDTO;
import com.uns.ftn.sciencejournal.mapper.common.TaskMapper;
import com.uns.ftn.sciencejournal.model.common.Task;
import com.uns.ftn.sciencejournal.service.common.TaskServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@CrossOrigin(origins = "http://localhost:4201")
public class TaskController {

    @Autowired
    TaskServicer taskService;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    JwtTokenProvider provider;

    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskDTO>> getAllFromUser(HttpServletRequest request) {
        String username = provider.parseToken(request);

        return ResponseEntity.ok().body(taskMapper.mapManyToDTO(taskService.getAllForUser(username)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskMapper.mapManyToDTO(taskService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(taskMapper.mapToDTO(taskService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO newTask) {
        if (newTask.getId() == null) {
            Task task = taskService.createTask(taskMapper.mapFromDTO(newTask));

            if (!task.equals(null)) {
                return ResponseEntity.ok(taskMapper.mapToDTO(task));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") Long id, @RequestBody TaskDTO newTask) {
        if (!newTask.getId().equals(null) && !id.equals(null)) {
            Task task = taskService.updateTask(taskMapper.mapFromDTO(newTask), id);

            if (!task.equals(null)) {
                return ResponseEntity.ok(taskMapper.mapToDTO(task));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            taskService.deleteTask(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
