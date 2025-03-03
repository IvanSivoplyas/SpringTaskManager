package com.silvan.taskmanager.repositories;

import com.silvan.taskmanager.enumerations.TaskStatus;
import com.silvan.taskmanager.models.Task;
import com.silvan.taskmanager.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByAuthor(User author, Pageable pageable);
    List<Task> findByAssignee(User assignee, Pageable pageable);
    List<Task> findByAuthorAndStatus(User author, TaskStatus status, Pageable pageable);
    List<Task> findByAssigneeAndStatus(User assignee, TaskStatus status, Pageable pageable);

}
