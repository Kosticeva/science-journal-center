package com.uns.ftn.sciencejournal.controller.users;

import com.uns.ftn.sciencejournal.dto.FormFieldsDto;
import com.uns.ftn.sciencejournal.dto.users.UserDetailsDTO;
import com.uns.ftn.sciencejournal.mapper.users.UserDetailsMapper;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.service.users.UserDetailsService;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin(origins = "http://localhost:4201")
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserDetailsMapper userDetailsMapper;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    RepositoryService repositoryService;

    @Autowired
    FormService formService;

    @GetMapping(path = "/registration/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public FormFieldsDto startRegistrationProcess() {

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Registration");

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        TaskFormData taskFormData = formService.getTaskFormData(task.getFormKey());
        List<FormField> properties = taskFormData.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getId() + fp.getType());
        }

        return new FormFieldsDto(task.getId(), processInstance.getId(), properties);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        return ResponseEntity.ok(userDetailsMapper.mapManyToDTO(userDetailsService.getAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            return ResponseEntity.ok(userDetailsMapper.mapToDTO(userDetailsService.getById(id)));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDTO> createUser(@RequestBody UserDetailsDTO newUser) {
        if (newUser.getId() == null) {
            UserDetails userDetails = userDetailsService.createUser(userDetailsMapper.mapFromDTO(newUser));

            if (!userDetails.equals(null)) {
                return ResponseEntity.ok(userDetailsMapper.mapToDTO(userDetails));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDetailsDTO newUser) {
        if (!newUser.getId().equals(null) && !id.equals(null)) {
            UserDetails userDetails = userDetailsService.updateUser(userDetailsMapper.mapFromDTO(newUser), id);

            if (!userDetails.equals(null)) {
                return ResponseEntity.ok(userDetailsMapper.mapToDTO(userDetails));
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        if (!id.equals(null)) {
            userDetailsService.deleteUser(id);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.badRequest().build();
    }
}
