# ============================================================
# R 语言数据处理 - apply 族函数
# 运行：Rscript 01_apply族函数.R
# ============================================================

cat("=== apply 族函数 ===\n")

# apply - 矩阵/数组
cat("apply - 矩阵/数组:\n")
m <- matrix(1:9, nrow=3)
cat("矩阵:\n")
print(m)

cat("行求和:\n")
print(apply(m, 1, sum))

cat("列求平均:\n")
print(apply(m, 2, mean))

# lapply - 列表/向量，返回列表
cat("\nlapply - 返回列表:\n")
nums <- list(a=1:3, b=4:6, c=7:9)
cat("输入列表:\n")
print(nums)
cat("lapply 求和:\n")
print(lapply(nums, sum))

# sapply - 简化结果
cat("\nsapply - 简化结果:\n")
cat("sapply 求和:\n")
print(sapply(nums, sum))

# tapply - 分组应用
cat("\ntapply - 分组应用:\n")
df <- data.frame(
    name = c("A","B","C","D","E"),
    group = c("甲","乙","甲","乙","甲"),
    score = c(90, 85, 92, 88, 95)
)
cat("数据框:\n")
print(df)
cat("分组平均值:\n")
print(tapply(df$score, df$group, mean))

# mapply - 多参数函数
cat("\nmapply - 多参数函数:\n")
result <- mapply(function(x, y) x + y, 1:3, 4:6)
cat("1:3 + 4:6 =", result, "\n")

cat("\n=== 运行完成 ===\n")
