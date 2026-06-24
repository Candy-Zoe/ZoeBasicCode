# ============================================================
# R 语言统计分析 - 相关和回归
# 运行：Rscript 02_相关和回归.R
# ============================================================

cat("=== 相关和回归 ===\n")

set.seed(42)

# 相关性分析
cat("相关性分析:\n")
x <- 1:10
y <- 2*x + rnorm(10)
cat("x:", x, "\n")
cat("y:", round(y, 2), "\n")
cat("相关系数:", cor(x, y), "\n")

# 相关系数检验
cat("\n相关系数检验:\n")
cor_test <- cor.test(x, y)
print(cor_test)

# 线性回归
cat("\n线性回归:\n")
model <- lm(y ~ x)
cat("回归模型摘要:\n")
print(summary(model))

cat("\n回归系数:\n")
print(coef(model))

cat("\n拟合值:\n")
print(fitted(model))

cat("\n残差:\n")
print(residuals(model))

# 预测
cat("\n预测:\n")
new_x <- data.frame(x = c(11, 12, 13))
predictions <- predict(model, newdata = new_x)
cat("x = 11, 12, 13 时的预测值:\n")
print(predictions)

cat("\n=== 运行完成 ===\n")
