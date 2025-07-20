package com.sadsulsoft.ai_powerd_task_assistance.dto;

import com.sadsulsoft.ai_powerd_task_assistance.entity.Task;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String priority;
    private String category;
    private boolean completed;
    private String assigneeUsername; // New field

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.priority = task.getPriority();
        this.category = task.getCategory();
        this.completed = task.isCompleted();
        if (task.getAssignee() != null) {
            this.assigneeUsername = task.getAssignee().getUsername();
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getAssigneeUsername() {
		return assigneeUsername;
	}

	public void setAssigneeUsername(String assigneeUsername) {
		this.assigneeUsername = assigneeUsername;
	}

    // Add all getters and setters for the fields
    // ...
    
}