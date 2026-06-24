-- ============================================================
-- SQL 查询语句 - 子查询
-- 包含：IN子查询, EXISTS, 标量子查询, FROM子查询
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
-- 1. 标量子查询
-- ============================================================

-- 查询每个学生的平均分
SELECT name,
    (SELECT AVG(score) FROM scores WHERE student_id = students.id) AS 平均分
FROM students;

-- 查询分数高于平均分的成绩
SELECT * FROM scores
WHERE score > (SELECT AVG(score) FROM scores);

-- ============================================================
-- 2. IN 子查询
-- ============================================================

-- 查询有成绩高于95分的学生
SELECT * FROM students
WHERE id IN (SELECT DISTINCT student_id FROM scores WHERE score > 95);

-- 查询没有被选过的课程
SELECT * FROM courses
WHERE id NOT IN (SELECT DISTINCT course_id FROM scores);

-- ============================================================
-- 3. EXISTS 子查询
-- ============================================================

-- 查询有成绩的学生
SELECT * FROM students s
WHERE EXISTS (SELECT 1 FROM scores sc WHERE sc.student_id = s.id);

-- 查询有成绩高于95分的学生
SELECT * FROM students s
WHERE EXISTS (SELECT 1 FROM scores sc WHERE sc.student_id = s.id AND sc.score > 95);

-- 查询没有成绩的学生
SELECT * FROM students s
WHERE NOT EXISTS (SELECT 1 FROM scores sc WHERE sc.student_id = s.id);

-- ============================================================
-- 4. FROM 子查询（派生表）
-- ============================================================

-- 查询平均分大于90分的学生
SELECT name, 平均分
FROM (
    SELECT s.name, AVG(sc.score) AS 平均分
    FROM students s
    INNER JOIN scores sc ON s.id = sc.student_id
    GROUP BY s.id
) AS student_avg
WHERE 平均分 > 90;
