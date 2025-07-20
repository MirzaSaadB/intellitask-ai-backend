package com.sadsulsoft.ai_powerd_task_assistance.controller;

import com.sadsulsoft.ai_powerd_task_assistance.dto.TaskDto;
import com.sadsulsoft.ai_powerd_task_assistance.dto.TaskRequest;
import com.sadsulsoft.ai_powerd_task_assistance.entity.Task;
import com.sadsulsoft.ai_powerd_task_assistance.entity.User;
import com.sadsulsoft.ai_powerd_task_assistance.repository.UserRepository;
import com.sadsulsoft.ai_powerd_task_assistance.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<TaskDto> getAllTasks(@AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getAllTasks(userDetails)
                .stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskRequest taskRequest, @AuthenticationPrincipal UserDetails userDetails) {
        User author = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        User assignee = userRepository.findById(taskRequest.getAssigneeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setAuthor(author);
        task.setAssignee(assignee);
        
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}