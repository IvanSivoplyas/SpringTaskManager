package com.silvan.taskmanager.models;

import com.silvan.taskmanager.enumerations.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",unique = true, nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Task> createdTasks;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Task> assignedTasks;
}
