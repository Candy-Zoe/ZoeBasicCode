-- ============================================================
-- SQL 查询语句 - SELECT 基础查询
-- 包含：基本 SELECT, WHERE, ORDER BY, LIMIT
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

-- 插入测试数据（忽略重复）
INSERT IGNORE INTO students (name, age, email, grade) VALUES
    ('张三', 18, 'zhangsan@example.com', '高三'),
    ('李四', 17, 'lisi@example.com', '高二'),
    ('王五', 16, 'wangwu@example.com', '高一'),
    ('赵六', 18, 'zhaoliu@example.com', '高三'),
    ('钱七', 17, 'qianqi@example.com', '高二');

-- ============================================================
-- 1. 基本 SELECT
-- ============================================================

-- 查询所有列
SELECT * FROM students;

-- 查询指定列
SELECT name, age, email FROM students;

-- 使用别名
SELECT name AS 姓名, age AS 年龄 FROM students;

-- 去重查询
SELECT DISTINCT grade FROM students;

-- ============================================================
-- 2. WHERE 条件查询
-- ============================================================

-- 比较运算符
SELECT * FROM students WHERE age > 17;

-- 逻辑运算符 AND
SELECT * FROM students WHERE grade = '高三' AND age >= 18;

-- IN 子句
SELECT * FROM students WHERE grade IN ('高三', '高二');

-- BETWEEN 范围查询
SELECT * FROM students WHERE age BETWEEN 16 AND 17;

-- LIKE 模糊查询
SELECT * FROM students WHERE name LIKE '张%';

-- IS NOT NULL 非空判断
SELECT * FROM students WHERE email IS NOT NULL;

-- ============================================================
-- 3. 排序 ORDER BY
-- ============================================================

-- 降序排序
SELECT * FROM students ORDER BY age DESC;

-- 多字段排序（先按年级升序，再按年龄降序）
SELECT * FROM students ORDER BY grade ASC, age DESC;

-- ============================================================
-- 4. 限制 LIMIT
-- ============================================================

-- 取前3条
SELECT * FROM students ORDER BY age DESC LIMIT 3;

-- 跳过2条，取3条（分页）
SELECT * FROM students ORDER BY id LIMIT 2, 3;
