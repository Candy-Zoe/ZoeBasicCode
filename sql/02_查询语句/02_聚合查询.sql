-- ============================================================
-- SQL 查询语句 - 聚合查询
-- 包含：聚合函数, GROUP BY, HAVING
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
-- 1. 聚合函数
-- ============================================================

-- COUNT 统计数量
SELECT COUNT(*) AS 总人数 FROM students;

-- AVG 求平均值
SELECT AVG(age) AS 平均年龄 FROM students;

-- MAX/MIN 最大最小值
SELECT MAX(score) AS 最高分, MIN(score) AS 最低分 FROM scores;

-- SUM 求和
SELECT SUM(score) AS 总分 FROM scores WHERE student_id = 1;

-- ============================================================
-- 2. GROUP BY 分组
-- ============================================================

-- 按年级分组统计人数
SELECT grade, COUNT(*) AS 人数 FROM students GROUP BY grade;

-- 按学生分组统计平均分
SELECT student_id, AVG(score) AS 平均分 FROM scores GROUP BY student_id;

-- 多字段分组
SELECT grade, age, COUNT(*) AS 人数
FROM students
GROUP BY grade, age;

-- ============================================================
-- 3. HAVING 分组后过滤
-- ============================================================

-- 查询平均分大于90分的学生
SELECT student_id, AVG(score) AS 平均分
FROM scores
GROUP BY student_id
HAVING AVG(score) > 90;

-- 查询人数大于1的年级
SELECT grade, COUNT(*) AS 人数
FROM students
GROUP BY grade
HAVING COUNT(*) > 1;
