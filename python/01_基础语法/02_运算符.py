# ============================================================
# Python 基础语法 - 运算符
# ============================================================

print("=== 1. 算术运算符 ===")
a, b = 10, 3
print(f"a = {a}, b = {b}")
print(f"加法: a + b = {a + b}")
print(f"减法: a - b = {a - b}")
print(f"乘法: a * b = {a * b}")
print(f"除法: a / b = {a / b}")  # 结果是浮点数
print(f"整除: a // b = {a // b}")  # 取整数部分
print(f"取余: a % b = {a % b}")  # 取余数
print(f"幂运算: a ** b = {a ** b}")  # a 的 b 次方

print("\n=== 2. 赋值运算符 ===")
x = 10
print(f"初始值 x = {x}")
x += 5  # 等价于 x = x + 5
print(f"x += 5 后: x = {x}")
x -= 3
print(f"x -= 3 后: x = {x}")
x *= 2
print(f"x *= 2 后: x = {x}")
x /= 4
print(f"x /= 4 后: x = {x}")
x //= 2
print(f"x //= 2 后: x = {x}")
x %= 3
print(f"x %= 3 后: x = {x}")
x **= 2
print(f"x **= 2 后: x = {x}")

print("\n=== 3. 比较运算符 ===")
m, n = 10, 20
print(f"m = {m}, n = {n}")
print(f"m == n: {m == n}")  # 等于
print(f"m != n: {m != n}")  # 不等于
print(f"m > n: {m > n}")  # 大于
print(f"m < n: {m < n}")  # 小于
print(f"m >= n: {m >= n}")  # 大于等于
print(f"m <= n: {m <= n}")  # 小于等于

print("\n=== 4. 逻辑运算符 ===")
p, q = True, False
print(f"p = {p}, q = {q}")
print(f"p and q: {p and q}")  # 与：都为True才为True
print(f"p or q: {p or q}")  # 或：有一个为True就为True
print(f"not p: {not p}")  # 非：取反
print(f"not q: {not q}")

print("\n=== 5. 成员运算符 ===")
fruits = ["苹果", "香蕉", "橙子"]
print(f"列表: {fruits}")
print(f"'苹果' in fruits: {'苹果' in fruits}")
print(f"'葡萄' in fruits: {'葡萄' in fruits}")
print(f"'葡萄' not in fruits: {'葡萄' not in fruits}")

print("\n=== 6. 身份运算符 ===")
list1 = [1, 2, 3]
list2 = [1, 2, 3]
list3 = list1
print(f"list1 = {list1}, list2 = {list2}, list3 = list1")
print(f"list1 is list2: {list1 is list2}")  # 比较内存地址
print(f"list1 is list3: {list1 is list3}")
print(f"list1 == list2: {list1 == list2}")  # 比较值
