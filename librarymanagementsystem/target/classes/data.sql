-- 创建 users 表
CREATE TABLE IF NOT EXISTS users (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     username VARCHAR(100) UNIQUE NOT NULL,
                                     password VARCHAR(100) NOT NULL,
                                     role VARCHAR(20) NOT NULL
);

-- 创建 categories 表
CREATE TABLE IF NOT EXISTS categories (
                                          id INT PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(100) NOT NULL
);

-- 创建 books 表
CREATE TABLE IF NOT EXISTS books (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     title VARCHAR(100) NOT NULL,
                                     author VARCHAR(100) NOT NULL,
                                     category_id INT NOT NULL,
                                     quantity INT NOT NULL,
                                     FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- 插入初始数据
INSERT INTO users (username, password, role) VALUES
                                                 ('admin', '$2a$10$NkM5WZbH6Lr6o7jzV1qY0OcXe9wY1lL7hJ6Kd3vGjRt7Mm4JYzWm', 'ADMIN'), -- 密码: admin123
                                                 ('user1', '$2a$10$NkM5WZbH6Lr6o7jzV1qY0OcXe9wY1lL7hJ6Kd3vGjRt7Mm4JYzWm', 'USER');  -- 密码: user123

INSERT INTO categories (name) VALUES
                                  ('小说'), ('科技'), ('历史'), ('文学');