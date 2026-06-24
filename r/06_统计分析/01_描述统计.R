# ============================================================
# R 语言统计分析 - 描述统计
# 运行：Rscript 01_描述统计.R
# ============================================================

cat("=== 描述统计 ===\n")

set.seed(42)  # 固定随机种子
data <- rnorm(100, mean=0, sd=1)  # 正态分布

cat("数据长度:", length(data), "\n")
cat("均值:", mean(data), "\n")
cat("中位数:", median(data), "\n")
cat("方差:", var(data), "\n")
cat("标准差:", sd(data), "\n")
cat("最小值:", min(data), "\n")
cat("最大值:", max(data), "\n")
cat("极差:", max(data) - min(data), "\n")

cat("\n分位数:\n")
print(quantile(data))

cat("\n四分位距(IQR):", IQR(data), "\n")

# summary 函数
cat("\nsummary 函数:\n")
print(summary(data))

# 离散数据的频数统计
cat("\n离散数据频数统计:\n")
grades <- sample(c("A", "B", "C", "D"), 50, replace = TRUE)
cat("table 频数:\n")
print(table(grades))
cat("prop.table 比例:\n")
print(prop.table(table(grades)))

cat("\n=== 运行完成 ===\n")
