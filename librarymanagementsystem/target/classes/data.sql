-- 初始化分类
INSERT INTO categories (name) VALUES
                                  ('小说'), ('科技'), ('历史'), ('文学');

-- 初始化管理员账号 (密码: admin123)
INSERT INTO users (username, password, role) VALUES
    ('admin', '$2a$10$NkM5WZbH6Lr6o7jzV1qY0OcXe9wY1lL7hJ6Kd3vGjRt7Mm4JYzWm', 'ADMIN');