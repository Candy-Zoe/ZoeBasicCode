# ============================================================
# R 语言基础语法 - 变量和数据类型
# 运行：Rscript 01_变量和数据类型.R
# ============================================================

cat("=== 变量和数据类型 ===\n")

# 变量赋值
x <- 10        # 推荐用 <-
y = 20         # 也可以用 =
z <<- 30       # 全局赋值
cat("x =", x, ", y =", y, "\n")

# 数据类型
cat("\n数据类型:\n")
cat("  numeric:", class(123), "\n")
cat("  integer:", class(123L), "\n")
cat("  character:", class("Hello"), "\n")
cat("  logical:", class(TRUE), "\n")
cat("  complex:", class(1+2i), "\n")
cat("  factor:", class(factor(c("a","b"))), "\n")

# 类型转换
cat("\n类型转换:\n")
cat("  as.numeric('123') =", as.numeric("123"), "\n")
cat("  as.character(123) =", as.character(123), "\n")
cat("  as.logical(1) =", as.logical(1), "\n")
cat("  as.integer(3.14) =", as.integer(3.14), "\n")

# 查看变量
cat("\n查看变量:\n")
cat("  当前环境变量:", ls(), "\n")
cat("  x 是否存在:", exists("x"), "\n")
# rm(x)  # 删除变量

cat("\n=== 运行完成 ===\n")
