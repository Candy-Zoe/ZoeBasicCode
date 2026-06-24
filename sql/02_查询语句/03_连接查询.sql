-- ============================================================
-- SQL 查询语句 - 连接查询 JOIN
-- 包含：INNER JOIN, LEFT JOIN, RIGHT JOIN, 自连接
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
-- 1. INNER JOIN 内连接
-- ============================================================

-- 三表连接查询学生成绩详情
SELECT s.name, c.course_name, sc.score
FROM scores sc
INNER JOIN students s ON sc.student_id = s.id
INNER JOIN courses c ON sc.course_id = c.id;

-- 带条件的内连接
SELECT s.name, c.course_name, sc.score
FROM scores sc
INNER JOIN students s ON sc.student_id = s.id
INNER JOIN courses c ON sc.course_id = c.id
WHERE sc.score > 90;

-- ============================================================
-- 2. LEFT JOIN 左连接
-- ============================================================

-- 查询所有学生及其平均分（没有成绩的学生也显示）
SELECT s.name, AVG(sc.score) AS 平均分
FROM students s
LEFT JOIN scores sc ON s.id = sc.student_id
GROUP BY s.id;

-- 查询没有成绩的学生
SELECT s.name
FROM students s
LEFT JOIN scores sc ON s.id = sc.student_id
WHERE sc.id IS NULL;

-- ============================================================
-- 3. RIGHT JOIN 右连接
-- ============================================================

-- 查询所有课程及其选课人数
SELECT c.course_name, COUNT(sc.id) AS 选课人数
FROM scores sc
RIGHT JOIN courses c ON sc.course_id = c.id
GROUP BY c.id;

-- 查询没有人选的课程
SELECT c.course_name
FROM scores sc
RIGHT JOIN courses c ON sc.course_id = c.id
WHERE sc.id IS NULL;

-- ============================================================
-- 4. 自连接
-- ============================================================

-- 查询同一年级的学生对
SELECT a.name AS 学生A, b.name AS 学生B, a.grade
FROM students a
INNER JOIN students b ON a.grade = b.grade AND a.id < b.id
ORDER BY a.grade, a.id;
