-- ============================================================
-- SQL 基础语法 - DDL 数据定义语言
-- 包含：CREATE DATABASE, CREATE TABLE, ALTER, DROP
-- 运行环境：MySQL
-- ============================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS tutorial DEFAULT CHARACTER SET utf8mb4;
USE tutorial;

-- ============================================================
-- 1. 创建表
-- ============================================================

-- 学生表
CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100) UNIQUE,
    grade VARCHAR(20) DEFAULT '一年级',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 课程表
CREATE TABLE IF NOT EXISTS courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    teacher VARCHAR(50),
    credits INT DEFAULT 3
);

-- 成绩表
CREATE TABLE IF NOT EXISTS scores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    score DECIMAL(5,2),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- ============================================================
-- 2. 修改表 ALTER
-- ============================================================

-- 添加列
ALTER TABLE students ADD COLUMN phone VARCHAR(20);

-- 修改列类型
ALTER TABLE students MODIFY COLUMN name VARCHAR(80);

-- 删除列
ALTER TABLE students DROP COLUMN phone;

-- ============================================================
-- 3. 查看表结构
-- ============================================================

-- 查看表结构
DESCRIBE students;

-- 查看建表语句
SHOW CREATE TABLE students;

-- 查看所有表
SHOW TABLES;

-- ============================================================
-- 4. 删除表 DROP
-- ============================================================

-- 删除表（谨慎使用）
-- DROP TABLE IF EXISTS scores;
-- DROP TABLE IF EXISTS courses;
-- DROP TABLE IF EXISTS students;

-- 删除数据库
-- DROP DATABASE IF EXISTS tutorial;
