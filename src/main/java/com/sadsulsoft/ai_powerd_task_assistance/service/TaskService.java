package com.sadsulsoft.ai_powerd_task_assistance.service;

import com.sadsulsoft.ai_powerd_task_assistance.entity.Task;
import com.sadsulsoft.ai_powerd_task_assistance.entity.User;
import com.sadsulsoft.ai_powerd_task_assistance.exception.ResourceNotFoundException;
import com.sadsulsoft.ai_powerd_task_assistance.repository.TaskRepository;
import com.sadsulsoft.ai_powerd_task_assistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks(UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return taskRepository.findByAuthorOrAssignee(user, user);
    }

    public Task createTask(Task task) {
        analyzeAndEnhanceTask(task);
        return taskRepository.save(task);
    }

    private void analyzeAndEnhanceTask(Task task) {
        String combinedText = (task.getTitle() + " " + task.getDescription()).toLowerCase();

        if (combinedText.contains("urgent") || combinedText.contains("asap") || combinedText.contains("critical")) {
            task.setPriority("High");
        } else if (combinedText.contains("important")) {
            task.setPriority("Medium");
        } else {
            task.setPriority("Low");
        }

        if (combinedText.contains("meeting") || combinedText.contains("report") || combinedText.contains("deploy")) {
            task.setCategory("Work");
        } else if (combinedText.contains("buy") || combinedText.contains("groceries") || combinedText.contains("shopping")) {
            task.setCategory("Personal");
        } else if (combinedText.contains("appointment") || combinedText.contains("doctor")) {
            task.setCategory("Health");
        } else {
            task.setCategory("General");
        }
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task existingTask = getTaskById(id);
        
        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setCompleted(taskDetails.isCompleted());
        
        analyzeAndEnhanceTask(existingTask); 

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}