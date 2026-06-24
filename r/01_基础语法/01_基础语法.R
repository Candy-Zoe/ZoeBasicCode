# ============================================================
# R 语言基础语法
# 运行：Rscript 01_基础语法.R
# ============================================================

cat("=== 1. 变量和数据类型 ===\n")

# 变量赋值
x <- 10        # 推荐用 <-
y = 20         # 也可以用 =
z <<- 30       # 全局赋值
cat("x =", x, ", y =", y, "\n")

# 数据类型
print(class(123))         # numeric
print(class(123L))        # integer
print(class("Hello"))     # character
print(class(TRUE))        # logical
print(class(1+2i))        # complex
print(class(factor(c("a","b"))))  # factor

# 类型转换
as.numeric("123")
as.character(123)
as.logical(1)
as.integer(3.14)

# 查看变量
ls()
exists("x")
# rm(x)  # 删除变量

# ============================================================
# 2. 向量
# ============================================================

cat("\n=== 2. 向量 Vector ===\n")

# 创建向量
v <- c(1, 2, 3, 4, 5)
print(v)

# 序列
v2 <- 1:10
print(v2)

v3 <- seq(1, 10, by = 2)
print(v3)

v4 <- rep("A", 5)
print(v4)

# 向量运算
a <- c(1, 2, 3)
b <- c(4, 5, 6)
cat("a + b =", a + b, "\n")
cat("a * b =", a * b, "\n")
cat("a ^ 2 =", a ^ 2, "\n")

# 常用函数
nums <- c(5, 2, 8, 1, 9, 3)
cat("长度:", length(nums), "\n")
cat("总和:", sum(nums), "\n")
cat("均值:", mean(nums), "\n")
cat("中位数:", median(nums), "\n")
cat("方差:", var(nums), "\n")
cat("标准差:", sd(nums), "\n")
cat("最大值:", max(nums), "\n")
cat("最小值:", min(nums), "\n")
cat("排序:", sort(nums), "\n")

# 索引（从 1 开始！）
v <- c("a", "b", "c", "d", "e")
cat("v[1] =", v[1], "\n")
cat("v[2:4] =", v[2:4], "\n")
cat("v[c(1,3,5)] =", v[c(1,3,5)], "\n")
cat("v[-1] =", v[-1], "\n")  # 删除第一个

# 逻辑索引
nums <- 1:10
cat("大于5的:", nums[nums > 5], "\n")

# 命名向量
scores <- c(语文=95, 数学=88, 英语=92)
cat("数学成绩:", scores["数学"], "\n")

# ============================================================
# 3. 矩阵
# ============================================================

cat("\n=== 3. 矩阵 Matrix ===\n")

# 创建矩阵
m <- matrix(1:9, nrow=3, ncol=3)
print(m)

m2 <- matrix(1:6, nrow=2, byrow=TRUE)
print(m2)

# 矩阵运算
m1 <- matrix(1:4, nrow=2)
m2 <- matrix(5:8, nrow=2)
cat("矩阵相加:\n")
print(m1 + m2)

cat("矩阵相乘:\n")
print(m1 %*% m2)

# 矩阵索引
cat("m[1,1] =", m[1,1], "\n")
cat("m[2,] =", m[2,], "\n")
cat("m[,3] =", m[,3], "\n")

# 行列名
rownames(m) <- c("行1", "行2", "行3")
colnames(m) <- c("列1", "列2", "列3")
print(m)

# ============================================================
# 4. 列表
# ============================================================

cat("\n=== 4. 列表 List ===\n")

# 创建列表
person <- list(
    name = "张三",
    age = 25,
    scores = c(95, 88, 92),
    info = list(city = "北京", phone = "123456")
)

print(person)

# 访问列表
cat("姓名:", person$name, "\n")
cat("年龄:", person[["age"]], "\n")
cat("成绩:", person$scores, "\n")
cat("城市:", person$info$city, "\n")

# 修改和添加
person$email <- "zhangsan@example.com"
cat("邮箱:", person$email, "\n")

# ============================================================
# 5. 数据框
# ============================================================

cat("\n=== 5. 数据框 Data Frame ===\n")

# 创建数据框
df <- data.frame(
    姓名 = c("张三", "李四", "王五", "赵六"),
    年龄 = c(25, 30, 28, 35),
    城市 = c("北京", "上海", "广州", "深圳"),
    成绩 = c(95, 88, 92, 85),
    stringsAsFactors = FALSE
)

print(df)

# 查看信息
cat("行列数:", dim(df), "\n")
cat("行数:", nrow(df), "\n")
cat("列数:", ncol(df), "\n")
cat("列名:", colnames(df), "\n")

# 访问数据
cat("姓名列:", df$姓名, "\n")
cat("第二行:", as.character(df[2, ]), "\n")

# 条件筛选
cat("成绩大于90的:\n")
print(df[df$成绩 > 90, ])

# 添加列
df$等级 <- ifelse(df$成绩 >= 90, "优秀", "良好")
print(df)

# ============================================================
# 6. 因子
# ============================================================

cat("\n=== 6. 因子 Factor ===\n")

# 创建因子
grades <- c("小学", "中学", "大学", "中学", "小学", "大学")
f <- factor(grades)
print(f)
print(table(f))  # 频数统计

# 有序因子
size <- c("小", "中", "大", "中", "小")
size_factor <- factor(size, ordered = TRUE, levels = c("小", "中", "大"))
print(size_factor)

# ============================================================
# 7. 函数
# ============================================================

cat("\n=== 7. 函数 ===\n")

# 基本函数
add <- function(a, b) {
    return(a + b)
}
cat("add(3, 5) =", add(3, 5), "\n")

# 默认参数
greet <- function(name = "世界") {
    cat("你好,", name, "!\n")
}
greet()
greet("张三")

# 可变参数
my_sum <- function(...) {
    args <- list(...)
    return(sum(unlist(args)))
}
cat("my_sum(1,2,3,4,5) =", my_sum(1,2,3,4,5), "\n")

# 函数作为参数
apply_func <- function(arr, func) {
    return(sapply(arr, func))
}
result <- apply_func(1:5, function(x) x^2)
cat("平方:", result, "\n")

# 递归
factorial <- function(n) {
    if (n <= 1) {
        return(1)
    }
    return(n * factorial(n - 1))
}
cat("5! =", factorial(5), "\n")

# ============================================================
# 8. 流程控制
# ============================================================

cat("\n=== 8. 流程控制 ===\n")

# if-else
score <- 85
if (score >= 90) {
    cat("优秀\n")
} else if (score >= 80) {
    cat("良好\n")
} else if (score >= 60) {
    cat("及格\n")
} else {
    cat("不及格\n")
}

# ifelse 向量运算
scores <- c(95, 82, 75, 60, 55)
grades <- ifelse(scores >= 90, "优秀", ifelse(scores >= 60, "及格", "不及格"))
cat("成绩分级:", grades, "\n")

# for 循环
cat("for 循环:\n")
for (i in 1:3) {
    cat("  ", i, "\n")
}

# for 遍历向量
fruits <- c("苹果", "香蕉", "橙子")
cat("遍历水果:\n")
for (f in fruits) {
    cat("  -", f, "\n")
}

# while 循环
cat("while 循环:\n")
i <- 1
while (i <= 3) {
    cat("  ", i, "\n")
    i <- i + 1
}

# repeat 循环
cat("repeat 循环:\n")
i <- 1
repeat {
    cat("  ", i, "\n")
    i <- i + 1
    if (i > 3) break
}

# next 和 break
cat("next/break:\n")
for (i in 1:5) {
    if (i == 3) next
    if (i == 5) break
    cat("  ", i, "\n")
}

# switch
day <- "周一"
result <- switch(day,
    "周一" = "工作日",
    "周二" = "工作日",
    "周三" = "工作日",
    "周四" = "工作日",
    "周五" = "工作日",
    "周六" = "周末",
    "周日" = "周末"
)
cat("周一:", result, "\n")

# ============================================================
# 9. apply 族函数
# ============================================================

cat("\n=== 9. apply 族函数 ===\n")

# apply - 矩阵/数组
m <- matrix(1:9, nrow=3)
cat("行求和:\n")
print(apply(m, 1, sum))
cat("列求平均:\n")
print(apply(m, 2, mean))

# lapply - 列表/向量，返回列表
nums <- list(a=1:3, b=4:6, c=7:9)
cat("lapply 求和:\n")
print(lapply(nums, sum))

# sapply - 简化结果
cat("sapply 求和:\n")
print(sapply(nums, sum))

# tapply - 分组应用
df <- data.frame(
    name = c("A","B","C","D","E"),
    group = c("甲","乙","甲","乙","甲"),
    score = c(90, 85, 92, 88, 95)
)
cat("分组平均值:\n")
print(tapply(df$score, df$group, mean))

# ============================================================
# 10. 常用统计函数
# ============================================================

cat("\n=== 10. 统计函数 ===\n")

set.seed(42)  # 固定随机种子
data <- rnorm(100, mean=0, sd=1)  # 正态分布

cat("长度:", length(data), "\n")
cat("均值:", mean(data), "\n")
cat("中位数:", median(data), "\n")
cat("方差:", var(data), "\n")
cat("标准差:", sd(data), "\n")
cat("最小值:", min(data), "\n")
cat("最大值:", max(data), "\n")
cat("分位数:\n")
print(quantile(data))

# 相关性
x <- 1:10
y <- 2*x + rnorm(10)
cat("相关系数:", cor(x, y), "\n")

# 线性回归
model <- lm(y ~ x)
cat("回归模型:\n")
print(summary(model))

cat("\n=== 运行完成 ==\n")