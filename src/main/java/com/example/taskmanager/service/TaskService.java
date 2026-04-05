
package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {

    private DatabaseReference db;

    // initialize AFTER FirebaseConfig
    @PostConstruct
    public void init() {
        db = FirebaseDatabase.getInstance().getReference("tasks");
    }

    // ✅ ADD TASK
    public Task addTask(Task task) {

        DatabaseReference newRef = db.push();
        newRef.setValueAsync(task);

        return task;
    }

    // ✅ UPDATE TASK
    public Task updateTask(String id, Task task) {

        Map<String, Object> updates = new HashMap<>();

        if (task.getText() != null)
            updates.put("text", task.getText());

        if (task.getAssignee() != null)
            updates.put("assignee", task.getAssignee());

        if (task.getPriority() != null)
            updates.put("priority", task.getPriority());

        if (task.getStatus() != null)
            updates.put("status", task.getStatus());

        db.child(id).updateChildrenAsync(updates);

        return task;
    }

    // ✅ DELETE TASK
    public void deleteTask(String id) {
        db.child(id).removeValueAsync();
    }
}