# ============================================================
# Python 函数 - 递归和作用域
# ============================================================

print("=== 1. 递归函数 ===")
def factorial(n):
    """计算阶乘：n! = n * (n-1) * ... * 1"""
    if n == 0 or n == 1:
        return 1
    return n * factorial(n - 1)

for i in range(6):
    print(f"{i}! = {factorial(i)}")

print("\n=== 2. 斐波那契数列 ===")
def fibonacci(n):
    """斐波那契数列：第n个数"""
    if n <= 1:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)

print("斐波那契数列前10项:")
for i in range(10):
    print(fibonacci(i), end=" ")
print()

print("\n=== 3. 全局变量和局部变量 ===")
x = 100  # 全局变量

def test_scope():
    x = 200  # 局部变量
    print(f"函数内部 x = {x}")

test_scope()
print(f"函数外部 x = {x}")

print("\n=== 4. global 关键字 ===")
y = 100

def modify_global():
    global y
    y = 200
    print(f"函数内部修改后 y = {y}")

modify_global()
print(f"函数外部 y = {y}")

print("\n=== 5. 闭包（内部函数引用外部函数变量） ===")
def outer(x):
    def inner(y):
        return x + y
    return inner

add_5 = outer(5)
print(f"add_5(10) = {add_5(10)}")
print(f"add_5(20) = {add_5(20)}")

add_10 = outer(10)
print(f"add_10(10) = {add_10(10)}")

print("\n=== 6. 装饰器 ===")
def log_decorator(func):
    def wrapper(*args, **kwargs):
        print(f"调用函数: {func.__name__}")
        result = func(*args, **kwargs)
        print(f"函数执行完成，结果: {result}")
        return result
    return wrapper

@log_decorator
def add(a, b):
    return a + b

add(3, 5)

print("\n=== 7. 生成器函数 ===")
def countdown(n):
    while n > 0:
        yield n
        n -= 1

print("倒计时:")
for num in countdown(5):
    print(num, end=" ")
print()

print("\n=== 8. 装饰器使用示例：计算函数运行时间 ===")
import time

def timer(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        end = time.time()
        print(f"{func.__name__} 执行时间: {end - start:.6f}秒")
        return result
    return wrapper

@timer
def slow_function():
    time.sleep(0.1)
    return "完成"

slow_function()
