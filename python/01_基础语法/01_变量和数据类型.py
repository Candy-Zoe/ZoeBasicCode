# ============================================================
# Python 基础语法 - 变量和数据类型
# ============================================================

# 1. 变量的定义
# Python 是动态类型语言，不需要声明变量类型
name = "张三"  # 字符串类型
age = 25  # 整数类型
height = 1.75  # 浮点数类型
is_student = True  # 布尔类型

print("=== 基本变量 ===")
print("姓名:", name)
print("年龄:", age)
print("身高:", height)
print("是否学生:", is_student)

# 2. 查看变量类型
print("\n=== 变量类型 ===")
print("name 的类型:", type(name))
print("age 的类型:", type(age))
print("height 的类型:", type(height))
print("is_student 的类型:", type(is_student))

# 3. 变量命名规则
# - 只能包含字母、数字和下划线
# - 不能以数字开头
# - 区分大小写
# - 不能使用关键字（如 if, for, while 等）
user_name = "李四"  # 蛇形命名法（推荐）
userAge = 30  # 驼峰命名法

# 4. 多变量赋值
print("\n=== 多变量赋值 ===")
a, b, c = 1, 2, 3
print("a =", a, "b =", b, "c =", c)

x = y = z = 100
print("x =", x, "y =", y, "z =", z)

# 5. 数据类型转换
print("\n=== 类型转换 ===")
num_str = "123"
num_int = int(num_str)  # 字符串转整数
print(f"字符串 '{num_str}' 转整数: {num_int}, 类型: {type(num_int)}")

num_float = float(num_str)  # 字符串转浮点数
print(f"字符串 '{num_str}' 转浮点数: {num_float}, 类型: {type(num_float)}")

age_str = str(age)  # 整数转字符串
print(f"整数 {age} 转字符串: '{age_str}', 类型: {type(age_str)}")

# 6. 常量（Python 没有真正的常量，约定用全大写表示）
PI = 3.1415926
MAX_SIZE = 1024

print("\n=== 常量（约定） ===")
print("PI =", PI)
print("MAX_SIZE =", MAX_SIZE)
