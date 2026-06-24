# ============================================================
# R 语言函数 - 函数基础
# 运行：Rscript 01_函数基础.R
# ============================================================

cat("=== 函数基础 ===\n")

# 基本函数
add <- function(a, b) {
    return(a + b)
}
cat("add(3, 5) =", add(3, 5), "\n")

# 默认参数
greet <- function(name = "世界") {
    cat("你好,", name, "!\n")
}
cat("\n默认参数:\n")
greet()
greet("张三")

# 可变参数
my_sum <- function(...) {
    args <- list(...)
    return(sum(unlist(args)))
}
cat("\n可变参数 my_sum(1,2,3,4,5) =", my_sum(1,2,3,4,5), "\n")

# 函数作为参数
cat("\n函数作为参数:\n")
apply_func <- function(arr, func) {
    return(sapply(arr, func))
}
result <- apply_func(1:5, function(x) x^2)
cat("平方:", result, "\n")

# 递归函数
factorial <- function(n) {
    if (n <= 1) {
        return(1)
    }
    return(n * factorial(n - 1))
}
cat("\n递归函数 5! =", factorial(5), "\n")

# 作用域
cat("\n作用域:\n")
global_var <- 100
test_scope <- function() {
    local_var <- 200
    cat("  函数内访问全局变量:", global_var, "\n")
    cat("  函数内局部变量:", local_var, "\n")
}
test_scope()
# cat(local_var)  # 这行会报错，因为 local_var 是局部变量

cat("\n=== 运行完成 ===\n")
