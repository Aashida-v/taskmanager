package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService service;

    // ✅ ADD TASK
    @PostMapping
    public Task addTask(@RequestBody Task task) {

        if (task.getStatus() == null || task.getStatus().isEmpty()) {
            task.setStatus("todo");
        }

        return service.addTask(task);
    }

    // ✅ UPDATE TASK (FIXED - no getTaskById needed)
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    // ✅ DELETE TASK
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        service.deleteTask(id);
        return "Task Deleted";
    }
}