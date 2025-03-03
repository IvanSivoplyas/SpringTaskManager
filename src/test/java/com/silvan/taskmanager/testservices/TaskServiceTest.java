package com.silvan.taskmanager.testservices;

import com.silvan.taskmanager.models.Task;
import com.silvan.taskmanager.models.User;
import com.silvan.taskmanager.repositories.TaskRepository;
import com.silvan.taskmanager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        // Подготовка данных
        Task task = new Task();
        task.setTitle("Test Task");

        when(taskRepository.save(task)).thenReturn(task);

        // Вызов метода
        Task createdTask = taskService.createTask(task);

        // Проверки
        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetTasksByAuthor() {
        User author = new User();
        author.setId(1);

        Task task = new Task();
        task.setTitle("Test Task");
        task.setAuthor(author);

        when(taskRepository.findByAuthor(author, PageRequest.of(0, 10)))
                .thenReturn(Collections.singletonList(task));

        List<Task> tasks = taskService.getTasksByAuthor(author, 0, 10, null);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
        verify(taskRepository, times(1)).findByAuthor(author, PageRequest.of(0, 10));
    }
}