-- ============================================================
-- SQL 基础语法 - DDL、DML、查询
-- 运行：复制到 MySQL / PostgreSQL / SQLite 中执行
-- ============================================================

-- ============================================================
-- 1. DDL 数据定义语言
-- ============================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS tutorial DEFAULT CHARACTER SET utf8mb4;
USE tutorial;

-- 创建表
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

-- 修改表
ALTER TABLE students ADD COLUMN phone VARCHAR(20);
ALTER TABLE students MODIFY COLUMN name VARCHAR(80);
ALTER TABLE students DROP COLUMN phone;

-- 查看表结构
DESCRIBE students;
SHOW CREATE TABLE students;

-- ============================================================
-- 2. DML 数据操作语言
-- ============================================================

-- 插入数据
INSERT INTO students (name, age, email, grade) VALUES
    ('张三', 18, 'zhangsan@example.com', '高三'),
    ('李四', 17, 'lisi@example.com', '高二'),
    ('王五', 16, 'wangwu@example.com', '高一'),
    ('赵六', 18, 'zhaoliu@example.com', '高三'),
    ('钱七', 17, 'qianqi@example.com', '高二');

INSERT INTO courses (course_name, teacher, credits) VALUES
    ('数学', '张老师', 4),
    ('英语', '李老师', 3),
    ('物理', '王老师', 3),
    ('化学', '赵老师', 2);

INSERT INTO scores (student_id, course_id, score) VALUES
    (1, 1, 95.5), (1, 2, 88.0), (1, 3, 92.5),
    (2, 1, 85.0), (2, 2, 90.5), (2, 4, 78.0),
    (3, 1, 98.0), (3, 3, 88.5), (3, 4, 92.0),
    (4, 1, 76.5), (4, 2, 82.0), (4, 3, 80.0),
    (5, 1, 91.0), (5, 2, 95.5), (5, 4, 88.5);

-- 更新数据
UPDATE students SET grade = '高三' WHERE id = 5;
UPDATE scores SET score = score + 5 WHERE score < 80;

-- 删除数据
-- DELETE FROM scores WHERE score < 60;
-- TRUNCATE TABLE table_name;  -- 清空表

-- ============================================================
-- 3. 基础查询
-- ============================================================

-- 基本 SELECT
SELECT * FROM students;
SELECT name, age, email FROM students;

-- 别名
SELECT name AS 姓名, age AS 年龄 FROM students;

-- 去重
SELECT DISTINCT grade FROM students;

-- WHERE 条件
SELECT * FROM students WHERE age > 17;
SELECT * FROM students WHERE grade = '高三' AND age >= 18;
SELECT * FROM students WHERE grade IN ('高三', '高二');
SELECT * FROM students WHERE age BETWEEN 16 AND 17;
SELECT * FROM students WHERE name LIKE '张%';
SELECT * FROM students WHERE email IS NOT NULL;

-- 排序 ORDER BY
SELECT * FROM students ORDER BY age DESC;
SELECT * FROM students ORDER BY grade ASC, age DESC;

-- 限制 LIMIT
SELECT * FROM students ORDER BY age DESC LIMIT 3;
SELECT * FROM students ORDER BY id LIMIT 2, 3;  -- 跳过2条，取3条

-- ============================================================
-- 4. 聚合查询
-- ============================================================

-- 聚合函数
SELECT COUNT(*) AS 总人数 FROM students;
SELECT AVG(age) AS 平均年龄 FROM students;
SELECT MAX(score) AS 最高分, MIN(score) AS 最低分 FROM scores;
SELECT SUM(score) AS 总分 FROM scores WHERE student_id = 1;

-- GROUP BY 分组
SELECT grade, COUNT(*) AS 人数 FROM students GROUP BY grade;
SELECT student_id, AVG(score) AS 平均分 FROM scores GROUP BY student_id;

-- HAVING 分组后过滤
SELECT student_id, AVG(score) AS 平均分
FROM scores
GROUP BY student_id
HAVING AVG(score) > 90;

-- ============================================================
-- 5. 连接查询 JOIN
-- ============================================================

-- INNER JOIN
SELECT s.name, c.course_name, sc.score
FROM scores sc
INNER JOIN students s ON sc.student_id = s.id
INNER JOIN courses c ON sc.course_id = c.id;

-- LEFT JOIN
SELECT s.name, AVG(sc.score) AS 平均分
FROM students s
LEFT JOIN scores sc ON s.id = sc.student_id
GROUP BY s.id;

-- RIGHT JOIN
SELECT c.course_name, COUNT(sc.id) AS 选课人数
FROM scores sc
RIGHT JOIN courses c ON sc.course_id = c.id
GROUP BY c.id;

-- 自连接
-- SELECT a.name, b.name FROM students a, students b WHERE a.grade = b.grade AND a.id != b.id;

-- ============================================================
-- 6. 子查询
-- ============================================================

-- 标量子查询
SELECT name, (SELECT AVG(score) FROM scores WHERE student_id = students.id) AS 平均分
FROM students;

-- IN 子查询
SELECT * FROM students
WHERE id IN (SELECT DISTINCT student_id FROM scores WHERE score > 95);

-- EXISTS 子查询
SELECT * FROM students s
WHERE EXISTS (SELECT 1 FROM scores sc WHERE sc.student_id = s.id AND sc.score > 95);

-- FROM 子查询
SELECT name, 平均分
FROM (
    SELECT s.name, AVG(sc.score) AS 平均分
    FROM students s
    INNER JOIN scores sc ON s.id = sc.student_id
    GROUP BY s.id
) AS student_avg
WHERE 平均分 > 90;

-- ============================================================
-- 7. 高级查询
-- ============================================================

-- UNION 并集
SELECT name FROM students WHERE grade = '高三'
UNION
SELECT course_name FROM courses;

-- CASE WHEN
SELECT
    name,
    CASE
        WHEN age < 16 THEN '少年'
        WHEN age < 18 THEN '青年'
        ELSE '成年'
    END AS 年龄段
FROM students;

-- 窗口函数 (MySQL 8.0+)
-- SELECT
--     name,
--     grade,
--     ROW_NUMBER() OVER (PARTITION BY grade ORDER BY age DESC) AS rn
-- FROM students;

-- 常用函数
SELECT
    UPPER(name) AS 大写,
    LOWER(name) AS 小写,
    LENGTH(name) AS 长度,
    SUBSTRING(name, 1, 1) AS 首字,
    CONCAT(name, ' - ', grade) AS 组合
FROM students;

SELECT
    ROUND(95.678, 2) AS 四舍五入,
    CEIL(95.1) AS 向上取整,
    FLOOR(95.9) AS 向下取整,
    ABS(-10) AS 绝对值;

SELECT
    NOW() AS 当前时间,
    CURDATE() AS 当前日期,
    YEAR(NOW()) AS 年,
    MONTH(NOW()) AS 月,
    DAY(NOW()) AS 日;

-- ============================================================
-- 8. 视图、索引、事务
-- ============================================================

-- 视图
CREATE VIEW student_score_view AS
SELECT s.name, c.course_name, sc.score
FROM scores sc
JOIN students s ON sc.student_id = s.id
JOIN courses c ON sc.course_id = c.id;

SELECT * FROM student_score_view WHERE score > 90;

-- 索引
CREATE INDEX idx_student_name ON students(name);
CREATE INDEX idx_score_student ON scores(student_id);
-- DROP INDEX idx_student_name ON students;

-- 事务
START TRANSACTION;
    UPDATE scores SET score = score + 10 WHERE student_id = 1;
    SAVEPOINT sp1;
    UPDATE scores SET score = score + 5 WHERE student_id = 2;
    -- ROLLBACK TO sp1;  -- 回滚到保存点
COMMIT;  -- 提交
-- ROLLBACK;  -- 回滚

-- ============================================================
-- 清理（可选）
-- ============================================================
-- DROP TABLE IF EXISTS scores;
-- DROP TABLE IF EXISTS courses;
-- DROP TABLE IF EXISTS students;
-- DROP DATABASE IF EXISTS tutorial;