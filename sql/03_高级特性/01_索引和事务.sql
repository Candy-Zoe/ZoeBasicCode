-- ============================================================
-- SQL 高级特性 - 索引和事务
-- 包含：索引, 事务, 视图
-- 运行环境：MySQL
-- ============================================================

-- 创建数据库和表（确保表存在）
CREATE DATABASE IF NOT EXISTS tutorial DEFAULT CHARACTER SET utf8mb4;
USE tutorial;

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

-- 插入测试数据（忽略重复）
INSERT IGNORE INTO students (name, age, email, grade) VALUES
    ('张三', 18, 'zhangsan@example.com', '高三'),
    ('李四', 17, 'lisi@example.com', '高二'),
    ('王五', 16, 'wangwu@example.com', '高一'),
    ('赵六', 18, 'zhaoliu@example.com', '高三'),
    ('钱七', 17, 'qianqi@example.com', '高二');

INSERT IGNORE INTO courses (id, course_name, teacher, credits) VALUES
    (1, '数学', '张老师', 4),
    (2, '英语', '李老师', 3),
    (3, '物理', '王老师', 3),
    (4, '化学', '赵老师', 2);

INSERT IGNORE INTO scores (student_id, course_id, score) VALUES
    (1, 1, 95.5), (1, 2, 88.0), (1, 3, 92.5),
    (2, 1, 85.0), (2, 2, 90.5), (2, 4, 78.0),
    (3, 1, 98.0), (3, 3, 88.5), (3, 4, 92.0),
    (4, 1, 76.5), (4, 2, 82.0), (4, 3, 80.0),
    (5, 1, 91.0), (5, 2, 95.5), (5, 4, 88.5);

-- ============================================================
-- 1. 索引 INDEX
-- ============================================================

-- 创建普通索引
CREATE INDEX idx_student_name ON students(name);

-- 创建唯一索引（已用 UNIQUE 约束时会自动创建）
-- CREATE UNIQUE INDEX idx_student_email ON students(email);

-- 创建复合索引
CREATE INDEX idx_score_student_course ON scores(student_id, course_id);

-- 查看索引
SHOW INDEX FROM students;

-- 使用索引查询（EXPLAIN 查看执行计划）
EXPLAIN SELECT * FROM students WHERE name = '张三';

-- 删除索引
-- DROP INDEX idx_student_name ON students;

-- ============================================================
-- 2. 事务 TRANSACTION
-- ============================================================

-- 开启事务
START TRANSACTION;

-- 执行操作1：给学生1的所有成绩加10分
UPDATE scores SET score = score + 10 WHERE student_id = 1;

-- 设置保存点
SAVEPOINT sp1;

-- 执行操作2：给学生2的所有成绩加5分
UPDATE scores SET score = score + 5 WHERE student_id = 2;

-- 回滚到保存点（取消操作2）
-- ROLLBACK TO sp1;

-- 提交事务（所有操作生效）
COMMIT;

-- 回滚事务（所有操作取消）
-- ROLLBACK;

-- 查看事务结果
SELECT s.name, AVG(sc.score) AS 平均分
FROM students s
JOIN scores sc ON s.id = sc.student_id
WHERE s.id IN (1, 2)
GROUP BY s.id;

-- ============================================================
-- 3. 视图 VIEW
-- ============================================================

-- 创建视图
CREATE VIEW student_score_view AS
SELECT s.name, c.course_name, sc.score
FROM scores sc
JOIN students s ON sc.student_id = s.id
JOIN courses c ON sc.course_id = c.id;

-- 查询视图
SELECT * FROM student_score_view;

-- 带条件查询视图
SELECT * FROM student_score_view WHERE score > 90;

-- 查看所有视图
SHOW TABLES WHERE Tables_in_tutorial LIKE '%view%';

-- 删除视图
-- DROP VIEW IF EXISTS student_score_view;
