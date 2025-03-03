CREATE TABLE users (
                       id INTEGER PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL NOT NULL CHECK (role IN ('ROLE_ADMIN', 'ROLE_USER'))
);

CREATE TABLE tasks (
                       id INTEGER PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       status VARCHAR(50) NOT NULL CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')),
                       priority VARCHAR(50) NOT NULL CHECK (priority IN ('HIGH', 'MEDIUM', 'LOW')),
                       author_id INTEGER NOT NULL REFERENCES users(id),
                       assignee_id INTEGER REFERENCES users(id)
);

CREATE TABLE comments (
                          id INTEGER PRIMARY KEY,
                          text TEXT NOT NULL,
                          task_id INTEGER NOT NULL REFERENCES tasks(id),
                          author_id INTEGER NOT NULL REFERENCES users(id)
);

CREATE TABLE user_tasks (
                            user_id INTEGER NOT NULL REFERENCES users(id),
                            task_id INTEGER NOT NULL REFERENCES tasks(id),
                            role VARCHAR(50) NOT NULL,
                            PRIMARY KEY (user_id, task_id)
);

CREATE TABLE task_comments (
                               task_id INTEGER NOT NULL REFERENCES tasks(id),
                               comment_id INTEGER NOT NULL REFERENCES comments(id),
                               PRIMARY KEY (task_id, comment_id)
);