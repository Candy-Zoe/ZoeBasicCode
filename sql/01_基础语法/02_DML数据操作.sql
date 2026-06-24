-- ============================================================
-- SQL 基础语法 - DML 数据操作语言
-- 包含：INSERT, UPDATE, DELETE
-- 运行环境：MySQL
-- ============================================================

-- 创建数据库和表（确保表存在）
CREATE DATABASE IF NOT EXISTS tutorial DEFAULT CHARACTER SET utf8mb4;
USE tutorial;

-- 如果表不存在则创建
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100) UNIQUE,
    grade VARCHAR(20) DEFAULT '一年级',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    teacher VARCHAR(50),
    credits INT DEFAULT 3
);

CREATE TABLE IF NOT EXISTS scores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    score DECIMAL(5,2),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- ============================================================
-- 1. 插入数据 INSERT
-- ============================================================

-- 插入学生数据
INSERT INTO students (name, age, email, grade) VALUES
    ('张三', 18, 'zhangsan@example.com', '高三'),
    ('李四', 17, 'lisi@example.com', '高二'),
    ('王五', 16, 'wangwu@example.com', '高一'),
    ('赵六', 18, 'zhaoliu@example.com', '高三'),
    ('钱七', 17, 'qianqi@example.com', '高二');

-- 插入课程数据
INSERT INTO courses (course_name, teacher, credits) VALUES
    ('数学', '张老师', 4),
    ('英语', '李老师', 3),
    ('物理', '王老师', 3),
    ('化学', '赵老师', 2);

-- 插入成绩数据
INSERT INTO scores (student_id, course_id, score) VALUES
    (1, 1, 95.5), (1, 2, 88.0), (1, 3, 92.5),
    (2, 1, 85.0), (2, 2, 90.5), (2, 4, 78.0),
    (3, 1, 98.0), (3, 3, 88.5), (3, 4, 92.0),
    (4, 1, 76.5), (4, 2, 82.0), (4, 3, 80.0),
    (5, 1, 91.0), (5, 2, 95.5), (5, 4, 88.5);

-- 查看插入结果
SELECT * FROM students;
SELECT * FROM courses;
SELECT * FROM scores;

-- ============================================================
-- 2. 更新数据 UPDATE
-- ============================================================

-- 更新单条记录
UPDATE students SET grade = '高三' WHERE id = 5;

-- 批量更新（分数低于80分的加5分）
UPDATE scores SET score = score + 5 WHERE score < 80;

-- 查看更新结果
SELECT * FROM students WHERE id = 5;
SELECT * FROM scores WHERE score < 85;

-- ============================================================
-- 3. 删除数据 DELETE
-- ============================================================

-- 删除指定条件的数据（谨慎使用）
-- DELETE FROM scores WHERE score < 60;

-- 清空表（TRUNCATE 比 DELETE 快，不能回滚）
-- TRUNCATE TABLE table_name;

-- 查看最终数据
SELECT * FROM students;
SELECT * FROM courses;
SELECT * FROM scores;
