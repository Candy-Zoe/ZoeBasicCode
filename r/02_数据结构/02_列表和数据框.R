# ============================================================
# R 语言数据结构 - 列表和数据框
# 运行：Rscript 02_列表和数据框.R
# ============================================================

cat("=== 列表 List ===\n")

# 创建列表
person <- list(
    name = "张三",
    age = 25,
    scores = c(95, 88, 92),
    info = list(city = "北京", phone = "123456")
)

cat("列表内容:\n")
print(person)

# 访问列表
cat("\n访问列表元素:\n")
cat("  姓名:", person$name, "\n")
cat("  年龄:", person[["age"]], "\n")
cat("  成绩:", person$scores, "\n")
cat("  城市:", person$info$city, "\n")

# 修改和添加
person$email <- "zhangsan@example.com"
cat("  邮箱(新增):", person$email, "\n")

# ============================================================
# 数据框 Data Frame
# ============================================================

cat("\n=== 数据框 Data Frame ===\n")

# 创建数据框
df <- data.frame(
    姓名 = c("张三", "李四", "王五", "赵六"),
    年龄 = c(25, 30, 28, 35),
    城市 = c("北京", "上海", "广州", "深圳"),
    成绩 = c(95, 88, 92, 85),
    stringsAsFactors = FALSE
)

cat("数据框内容:\n")
print(df)

# 查看信息
cat("\n数据框信息:\n")
cat("  行列数:", dim(df), "\n")
cat("  行数:", nrow(df), "\n")
cat("  列数:", ncol(df), "\n")
cat("  列名:", colnames(df), "\n")

# 访问数据
cat("\n访问数据:\n")
cat("  姓名列:", df$姓名, "\n")
cat("  第二行:\n")
print(df[2, ])

# 条件筛选
cat("\n条件筛选 - 成绩大于90的:\n")
print(df[df$成绩 > 90, ])

# 添加列
df$等级 <- ifelse(df$成绩 >= 90, "优秀", "良好")
cat("\n添加等级列后:\n")
print(df)

cat("\n=== 运行完成 ===\n")
