package com.sadsulsoft.ai_powerd_task_assistance.dto;

public class TaskRequest {
    private String title;
    private String description;
    private Long assigneeId; // ID of the user to assign the task to

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
}