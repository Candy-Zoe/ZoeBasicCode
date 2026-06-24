# ============================================================
# Python 流程控制 - 条件语句
# ============================================================

print("=== 1. 基本 if 语句 ===")
age = 18
if age >= 18:
    print("你已经成年了")

print("\n=== 2. if-else 语句 ===")
score = 85
if score >= 60:
    print("及格了")
else:
    print("不及格")

print("\n=== 3. if-elif-else 语句 ===")
score = 85
if score >= 90:
    print("优秀")
elif score >= 80:
    print("良好")
elif score >= 60:
    print("及格")
else:
    print("不及格")

print("\n=== 4. 嵌套 if 语句 ===")
age = 20
has_license = True
if age >= 18:
    if has_license:
        print("你可以开车")
    else:
        print("你需要先考驾照")
else:
    print("你还未成年，不能开车")

print("\n=== 5. 多条件组合 ===")
age = 25
salary = 8000
if age >= 22 and salary >= 5000:
    print("符合贷款条件")
else:
    print("不符合贷款条件")

print("\n=== 6. 三元表达式 ===")
a, b = 10, 20
max_val = a if a > b else b
print(f"{a} 和 {b} 中较大的是: {max_val}")

print("\n=== 7. 使用示例：成绩等级判定 ===")
def get_grade(score):
    if score < 0 or score > 100:
        return "无效成绩"
    elif score >= 90:
        return "A"
    elif score >= 80:
        return "B"
    elif score >= 70:
        return "C"
    elif score >= 60:
        return "D"
    else:
        return "F"

for s in [95, 82, 73, 61, 45, 100, -5, 105]:
    print(f"成绩 {s}: 等级 {get_grade(s)}")
