# ============================================================
# Python 函数 - 基础
# ============================================================

print("=== 1. 函数的定义和调用 ===")
def greet():
    """简单的问候函数"""
    print("Hello, World!")

greet()

print("\n=== 2. 带参数的函数 ===")
def greet_user(name):
    """向指定用户问候"""
    print(f"你好，{name}！")

greet_user("张三")
greet_user("李四")

print("\n=== 3. 带返回值的函数 ===")
def add(a, b):
    """计算两个数的和"""
    return a + b

result = add(3, 5)
print(f"3 + 5 = {result}")

print("\n=== 4. 带默认参数的函数 ===")
def power(base, exponent=2):
    """计算幂，默认是平方"""
    return base ** exponent

print(f"3的平方: {power(3)}")
print(f"3的立方: {power(3, 3)}")

print("\n=== 5. 关键字参数 ===")
def print_info(name, age, city):
    """打印个人信息"""
    print(f"姓名: {name}, 年龄: {age}, 城市: {city}")

print_info("张三", 25, "北京")
print_info(age=30, city="上海", name="李四")  # 顺序可以任意

print("\n=== 6. 可变参数 - *args ===")
def sum_all(*args):
    """计算所有参数的和"""
    total = 0
    for num in args:
        total += num
    return total

print(f"sum_all(1, 2, 3) = {sum_all(1, 2, 3)}")
print(f"sum_all(1, 2, 3, 4, 5) = {sum_all(1, 2, 3, 4, 5)}")

print("\n=== 7. 可变关键字参数 - **kwargs ===")
def print_dict(**kwargs):
    """打印所有关键字参数"""
    for key, value in kwargs.items():
        print(f"  {key}: {value}")

print("个人信息:")
print_dict(name="王五", age=28, job="工程师", city="深圳")

print("\n=== 8. 函数返回多个值 ===")
def calculate(a, b):
    """返回两个数的和、差、积、商"""
    return a + b, a - b, a * b, a / b

add_r, sub_r, mul_r, div_r = calculate(10, 3)
print(f"10 和 3 的计算结果:")
print(f"  和: {add_r}")
print(f"  差: {sub_r}")
print(f"  积: {mul_r}")
print(f"  商: {div_r}")

print("\n=== 9. lambda 表达式（匿名函数） ===")
square = lambda x: x ** 2
print(f"5的平方（lambda）: {square(5)}")

add_lambda = lambda a, b: a + b
print(f"3+4（lambda）: {add_lambda(3, 4)}")

print("\n=== 10. 函数作为参数传递 ===")
nums = [1, 2, 3, 4, 5]
squared = list(map(lambda x: x ** 2, nums))
print(f"原列表: {nums}")
print(f"平方后: {squared}")
