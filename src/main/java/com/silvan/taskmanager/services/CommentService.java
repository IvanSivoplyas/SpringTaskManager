package com.silvan.taskmanager.services;

import com.silvan.taskmanager.models.Comment;
import com.silvan.taskmanager.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByTask(int taskId) {
        return commentRepository.findByTaskId(taskId);
    }
}
