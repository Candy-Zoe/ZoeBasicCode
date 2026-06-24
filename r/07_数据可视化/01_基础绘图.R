# ============================================================
# R 语言数据可视化 - 基础绘图
# 运行：Rscript 01_基础绘图.R
# ============================================================

cat("=== 基础绘图 ===\n")

# 设置随机种子
set.seed(42)

# 创建数据
x <- 1:10
y <- 2*x + rnorm(10)

# 将绘图保存到文件（因为 Rscript 非交互式环境下不显示图形窗口）
png("01_基础绘图_散点图.png", width = 800, height = 600)

# 1. 散点图
cat("1. 散点图 (保存为 01_基础绘图_散点图.png)\n")
plot(x, y,
     main = "散点图示例",
     xlab = "X 轴",
     ylab = "Y 轴",
     col = "blue",
     pch = 16,
     cex = 1.5)

# 添加回归线
abline(lm(y ~ x), col = "red", lwd = 2)

dev.off()

# 2. 折线图
cat("2. 折线图 (保存为 01_基础绘图_折线图.png)\n")
png("01_基础绘图_折线图.png", width = 800, height = 600)

x2 <- 1:12
y2 <- sin(x2) * 10 + 20
plot(x2, y2,
     type = "l",
     main = "折线图示例",
     xlab = "月份",
     ylab = "数值",
     col = "darkgreen",
     lwd = 2)

# 添加点
points(x2, y2, col = "red", pch = 19)

dev.off()

# 3. 柱状图
cat("3. 柱状图 (保存为 01_基础绘图_柱状图.png)\n")
png("01_基础绘图_柱状图.png", width = 800, height = 600)

sales <- c(120, 150, 180, 140, 200, 160)
names(sales) <- c("1月", "2月", "3月", "4月", "5月", "6月")
barplot(sales,
        main = "月度销售额",
        xlab = "月份",
        ylab = "销售额(万元)",
        col = rainbow(6),
        beside = TRUE)

dev.off()

# 4. 饼图
cat("4. 饼图 (保存为 01_基础绘图_饼图.png)\n")
png("01_基础绘图_饼图.png", width = 600, height = 600)

expenses <- c(30, 20, 15, 25, 10)
labels <- c("食品", "交通", "娱乐", "住房", "其他")
pie(expenses,
    labels = labels,
    main = "支出分布",
    col = c("red", "blue", "green", "yellow", "purple"))

dev.off()

# 5. 直方图
cat("5. 直方图 (保存为 01_基础绘图_直方图.png)\n")
png("01_基础绘图_直方图.png", width = 800, height = 600)

data <- rnorm(1000, mean=50, sd=10)
hist(data,
     main = "正态分布直方图",
     xlab = "数值",
     ylab = "频数",
     col = "lightblue",
     border = "darkblue",
     breaks = 20)

dev.off()

# 6. 箱线图
cat("6. 箱线图 (保存为 01_基础绘图_箱线图.png)\n")
png("01_基础绘图_箱线图.png", width = 800, height = 600)

group1 <- rnorm(50, mean=10, sd=2)
group2 <- rnorm(50, mean=12, sd=3)
group3 <- rnorm(50, mean=8, sd=1.5)
boxplot(group1, group2, group3,
        main = "三组数据箱线图",
        names = c("组1", "组2", "组3"),
        col = c("pink", "lightgreen", "lightblue"))

dev.off()

cat("\n所有图表已保存到当前目录!\n")
cat("\n=== 运行完成 ===\n")
