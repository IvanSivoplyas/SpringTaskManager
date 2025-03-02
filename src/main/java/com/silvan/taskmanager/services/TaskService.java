package com.silvan.taskmanager.services;

import com.silvan.taskmanager.enumerations.TaskStatus;
import com.silvan.taskmanager.models.Task;
import com.silvan.taskmanager.models.User;
import com.silvan.taskmanager.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(int taskId, Task updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setPriority(updatedTask.getPriority());
        task.setAssignee(updatedTask.getAssignee());
        return taskRepository.save(task);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task changeStatus(int taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByAuthor(User author, int page, int size, TaskStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        if (status != null) {
            return taskRepository.findByAuthorAndStatus(author, status, pageable);
        }
        return taskRepository.findByAuthor(author, pageable);
    }

    public List<Task> getTasksByAssignee(User assignee, int page, int size, TaskStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        if (status != null) {
            return taskRepository.findByAssigneeAndStatus(assignee, status, pageable);
        }
        return taskRepository.findByAssignee(assignee, pageable);
    }
}

