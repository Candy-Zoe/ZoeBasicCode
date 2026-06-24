-- ============================================================
-- SQL 高级特性 - 窗口函数
-- 包含：ROW_NUMBER, RANK, DENSE_RANK, 聚合窗口函数等
-- 运行环境：MySQL 8.0+
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
-- 1. 排名窗口函数
-- ============================================================

-- ROW_NUMBER()：连续排名，相同分数排名不同
SELECT
    s.name,
    c.course_name,
    sc.score,
    ROW_NUMBER() OVER (PARTITION BY c.course_name ORDER BY sc.score DESC) AS row_num
FROM scores sc
JOIN students s ON sc.student_id = s.id
JOIN courses c ON sc.course_id = c.id
ORDER BY c.course_name, row_num;

-- RANK()：跳跃排名，相同分数排名相同，下一名跳过
SELECT
    s.name,
    c.course_name,
    sc.score,
    RANK() OVER (PARTITION BY c.course_name ORDER BY sc.score DESC) AS rank_num
FROM scores sc
JOIN students s ON sc.student_id = s.id
JOIN courses c ON sc.course_id = c.id
ORDER BY c.course_name, rank_num;

-- DENSE_RANK()：密集排名，相同分数排名相同，下一名连续
SELECT
    s.name,
    c.course_name,
    sc.score,
    DENSE_RANK() OVER (PARTITION BY c.course_name ORDER BY sc.score DESC) AS dense_rank_num
FROM scores sc
JOIN students s ON sc.student_id = s.id
JOIN courses c ON sc.course_id = c.id
ORDER BY c.course_name, dense_rank_num;

-- ============================================================
-- 2. 聚合窗口函数
-- ============================================================

-- 计算每个学生的平均分及与班级平均分的对比
SELECT
    s.name,
    c.course_name,
    sc.score,
    AVG(sc.score) OVER (PARTITION BY s.id) AS 学生平均分,
    AVG(sc.score) OVER (PARTITION BY c.id) AS 课程平均分
FROM scores sc
JOIN students s ON sc.student_id = s.id
JOIN courses c ON sc.course_id = c.id
ORDER BY s.name, c.course_name;

-- 累计求和（按分数排序后的累计总分）
SELECT
    s.name,
    sc.score,
    SUM(sc.score) OVER (ORDER BY sc.score DESC) AS 累计总分
FROM scores sc
JOIN students s ON sc.student_id = s.id
WHERE sc.course_id = 1
ORDER BY sc.score DESC;

-- ============================================================
-- 3. 前后行窗口函数
-- ============================================================

-- LAG()：获取前一行数据
SELECT
    s.name,
    sc.score,
    LAG(sc.score, 1) OVER (ORDER BY sc.score DESC) AS 前一名分数,
    sc.score - LAG(sc.score, 1) OVER (ORDER BY sc.score DESC) AS 分差
FROM scores sc
JOIN students s ON sc.student_id = s.id
WHERE sc.course_id = 1
ORDER BY sc.score DESC;

-- LEAD()：获取后一行数据
SELECT
    s.name,
    sc.score,
    LEAD(sc.score, 1) OVER (ORDER BY sc.score DESC) AS 后一名分数
FROM scores sc
JOIN students s ON sc.student_id = s.id
WHERE sc.course_id = 1
ORDER BY sc.score DESC;

-- ============================================================
-- 4. 首尾窗口函数
-- ============================================================

-- FIRST_VALUE()：窗口内第一个值
-- LAST_VALUE()：窗口内最后一个值
SELECT
    s.name,
    c.course_name,
    sc.score,
    FIRST_VALUE(sc.score) OVER (PARTITION BY c.id ORDER BY sc.score DESC) AS 最高分,
    LAST_VALUE(sc.score) OVER (PARTITION BY c.id ORDER BY sc.score DESC
        ROWS BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING) AS 最低分
FROM scores sc
JOIN students s ON sc.student_id = s.id
JOIN courses c ON sc.course_id = c.id
ORDER BY c.course_name, sc.score DESC;

-- ============================================================
-- 5. NTILE 分桶函数
-- ============================================================

-- 将学生按成绩分成3档
SELECT
    s.name,
    sc.score,
    NTILE(3) OVER (ORDER BY sc.score DESC) AS 档位
FROM scores sc
JOIN students s ON sc.student_id = s.id
WHERE sc.course_id = 1
ORDER BY sc.score DESC;
