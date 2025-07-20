// In TaskRepository.java
package com.sadsulsoft.ai_powerd_task_assistance.repository;

import com.sadsulsoft.ai_powerd_task_assistance.entity.Task;
import com.sadsulsoft.ai_powerd_task_assistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // New method to find tasks by author or assignee
    List<Task> findByAuthorOrAssignee(User author, User assignee);
}