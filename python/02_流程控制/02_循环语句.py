# ============================================================
# Python 流程控制 - 循环语句
# ============================================================

print("=== 1. for 循环 - 遍历列表 ===")
fruits = ["苹果", "香蕉", "橙子", "葡萄"]
for fruit in fruits:
    print(f"水果: {fruit}")

print("\n=== 2. for 循环 - range() 函数 ===")
print("range(5):")
for i in range(5):  # 0 到 4
    print(i, end=" ")
print()

print("range(2, 7):")
for i in range(2, 7):  # 2 到 6
    print(i, end=" ")
print()

print("range(1, 10, 2):")
for i in range(1, 10, 2):  # 1 到 9，步长为2
    print(i, end=" ")
print()

print("\n=== 3. for 循环 - 带索引遍历 ===")
for index, fruit in enumerate(fruits):
    print(f"索引 {index}: {fruit}")

print("\n=== 4. while 循环 ===")
count = 1
while count <= 5:
    print(f"第 {count} 次循环")
    count += 1

print("\n=== 5. break 语句（跳出循环） ===")
for i in range(10):
    if i == 5:
        break
    print(i, end=" ")
print("\n遇到 break，循环结束")

print("\n=== 6. continue 语句（跳过当前迭代） ===")
for i in range(10):
    if i % 2 == 0:
        continue
    print(i, end=" ")
print("\n只打印了奇数")

print("\n=== 7. 循环中的 else 子句 ===")
for i in range(5):
    print(i, end=" ")
else:
    print("\n循环正常结束（没有被 break 中断）")

print("\n=== 8. 嵌套循环 ===")
print("九九乘法表:")
for i in range(1, 10):
    for j in range(1, i + 1):
        print(f"{j}x{i}={i*j}\t", end="")
    print()

print("\n=== 9. 使用示例：累加求和 ===")
total = 0
for i in range(1, 101):
    total += i
print(f"1到100的和: {total}")

print("\n=== 10. 使用示例：查找质数 ===")
def is_prime(n):
    if n < 2:
        return False
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True

print("1到50之间的质数:")
for num in range(1, 51):
    if is_prime(num):
        print(num, end=" ")
print()
