package com.silvan.taskmanager.models;

import com.silvan.taskmanager.enumerations.TaskPriority;
import com.silvan.taskmanager.enumerations.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "priority",nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Comment> comments;
}
