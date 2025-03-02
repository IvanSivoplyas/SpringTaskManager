package com.silvan.taskmanager.repositories;

import com.silvan.taskmanager.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByTaskId(int taskId);
}
