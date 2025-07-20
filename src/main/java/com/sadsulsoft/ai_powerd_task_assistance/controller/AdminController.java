package com.sadsulsoft.ai_powerd_task_assistance.controller;

import com.sadsulsoft.ai_powerd_task_assistance.entity.Task;
import com.sadsulsoft.ai_powerd_task_assistance.repository.TaskRepository;
import com.sadsulsoft.ai_powerd_task_assistance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/tasks")
    public List<Task> getAllTasksFromAllUsers() {
        return taskRepository.findAll();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}