package com.silvan.taskmanager.controllers;

import com.silvan.taskmanager.enumerations.TaskStatus;
import com.silvan.taskmanager.models.Task;
import com.silvan.taskmanager.models.User;
import com.silvan.taskmanager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/tasks")
    public ResponseEntity<Task> createTaskAsAdmin(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/user/tasks")
    public ResponseEntity<Task> createTaskAsUser(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable int taskId, @RequestBody Task updatedTask) {
        return ResponseEntity.ok(taskService.updateTask(taskId, updatedTask));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> changeStatus(@PathVariable int taskId, @RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.changeStatus(taskId, status));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Task>> getTasksByAuthor(
            @PathVariable Long authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) TaskStatus status) {
        User author = new User();
        author.setId(authorId);
        return ResponseEntity.ok(taskService.getTasksByAuthor(author, page, size, status));
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<Task>> getTasksByAssignee(
            @PathVariable Long assigneeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) TaskStatus status) {
        User assignee = new User();
        assignee.setId(assigneeId);
        return ResponseEntity.ok(taskService.getTasksByAssignee(assignee, page, size, status));
    }
}
