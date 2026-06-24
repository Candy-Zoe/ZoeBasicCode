# ============================================================
# R 语言流程控制 - 条件语句 (if, ifelse, switch)
# 运行：Rscript 01_条件语句.R
# ============================================================

cat("=== 条件语句 ===\n")

# if-else 语句
cat("if-else 语句:\n")
score <- 85
if (score >= 90) {
    cat("  优秀\n")
} else if (score >= 80) {
    cat("  良好\n")
} else if (score >= 60) {
    cat("  及格\n")
} else {
    cat("  不及格\n")
}

# ifelse 向量运算
cat("\nifelse 向量运算:\n")
scores <- c(95, 82, 75, 60, 55)
grades <- ifelse(scores >= 90, "优秀", ifelse(scores >= 60, "及格", "不及格"))
cat("  成绩:", scores, "\n")
cat("  分级:", grades, "\n")

# switch 语句
cat("\nswitch 语句:\n")
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
cat("  周一:", result, "\n")

day2 <- "周六"
result2 <- switch(day2,
    "周一" = "工作日",
    "周二" = "工作日",
    "周三" = "工作日",
    "周四" = "工作日",
    "周五" = "工作日",
    "周六" = "周末",
    "周日" = "周末"
)
cat("  周六:", result2, "\n")

cat("\n=== 运行完成 ===\n")
