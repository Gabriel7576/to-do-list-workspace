package com.gabriel.todolist.web.portlet.task.dto;

import com.gabriel.todolist.entity.model.Task;

import java.util.List;

public class TaskDTO {

    private Task task;
    private List<Task> subtasks;

    public TaskDTO(Task task, List<Task> subtasks) {
        this.task = task;
        this.subtasks = subtasks;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }
}
