# ============================================================
# R 语言数据结构 - 因子
# 运行：Rscript 03_因子.R
# ============================================================

cat("=== 因子 Factor ===\n")

# 创建因子
grades <- c("小学", "中学", "大学", "中学", "小学", "大学")
f <- factor(grades)
cat("因子内容:\n")
print(f)

cat("\n因子水平(levels):", levels(f), "\n")
cat("因子频数统计:\n")
print(table(f))

# 有序因子
cat("\n有序因子:\n")
size <- c("小", "中", "大", "中", "小")
size_factor <- factor(size, ordered = TRUE, levels = c("小", "中", "大"))
print(size_factor)

cat("有序因子比较: 小 < 大 =", size_factor[1] < size_factor[3], "\n")

# 修改因子水平
cat("\n修改因子水平:\n")
f2 <- factor(c("A", "B", "A", "C", "B"))
print(f2)
levels(f2) <- c("等级A", "等级B", "等级C")
print(f2)

cat("\n=== 运行完成 ===\n")
