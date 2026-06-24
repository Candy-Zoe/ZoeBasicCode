# ============================================================
# R 语言流程控制 - 循环语句 (for, while, repeat)
# 运行：Rscript 02_循环语句.R
# ============================================================

cat("=== 循环语句 ===\n")

# for 循环
cat("for 循环:\n")
for (i in 1:3) {
    cat("  ", i, "\n")
}

# for 遍历向量
cat("\nfor 遍历向量:\n")
fruits <- c("苹果", "香蕉", "橙子")
for (f in fruits) {
    cat("  -", f, "\n")
}

# while 循环
cat("\nwhile 循环:\n")
i <- 1
while (i <= 3) {
    cat("  ", i, "\n")
    i <- i + 1
}

# repeat 循环
cat("\nrepeat 循环:\n")
i <- 1
repeat {
    cat("  ", i, "\n")
    i <- i + 1
    if (i > 3) break
}

# next 和 break
cat("\nnext 和 break:\n")
for (i in 1:5) {
    if (i == 3) next
    if (i == 5) break
    cat("  ", i, "\n")
}

cat("\n=== 运行完成 ===\n")
