package com.example.todo.controller;

import com.example.todo.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private Map<Long, Task> taskRepo = new HashMap<>();
    private Long idCounter = 1L;

    @GetMapping
    public Collection<Task> getAllTasks() {
        return taskRepo.values();  // ✅ returns a list-like structure
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(idCounter++);
        taskRepo.put(task.getId(), task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task existing = taskRepo.get(id);
        if (existing != null) {
            existing.setTitle(updatedTask.getTitle());
            existing.setCompleted(updatedTask.isCompleted());
        }
        return existing;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepo.remove(id);
        return "Deleted";
    }
}