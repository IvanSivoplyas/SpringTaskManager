package com.silvan.taskmanager.testcontrollers;

import com.silvan.taskmanager.controllers.TaskController;
import com.silvan.taskmanager.models.Task;
import com.silvan.taskmanager.models.User;
import com.silvan.taskmanager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    public void testCreateTask() {
        // Подготовка данных
        Task task = new Task();
        task.setTitle("Test Task");

        when(taskService.createTask(task)).thenReturn(task);

        // Вызов метода
        ResponseEntity<Task> response = taskController.createTask(task);

        // Проверки
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Используем HttpStatus
        assertEquals("Test Task", response.getBody().getTitle());
        verify(taskService, times(1)).createTask(task);
    }

    @Test
    public void testGetTasksByAuthor() {
        // Подготовка данных
        Task task = new Task();
        task.setTitle("Test Task");

        when(taskService.getTasksByAuthor(any(User.class), eq(0), eq(10), eq(null)))
                .thenReturn(Collections.singletonList(task));

        // Вызов метода
        ResponseEntity<List<Task>> response = taskController.getTasksByAuthor(1, 0, 10, null);

        // Проверки
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Используем HttpStatus
        assertEquals(1, response.getBody().size());
        assertEquals("Test Task", response.getBody().get(0).getTitle());
        verify(taskService, times(1)).getTasksByAuthor(any(User.class), eq(0), eq(10), eq(null));
    }
}