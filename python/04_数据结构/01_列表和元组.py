# ============================================================
# Python 数据结构 - 列表和元组
# ============================================================

print("=== 1. 列表的创建 ===")
fruits = ["苹果", "香蕉", "橙子"]
numbers = [1, 2, 3, 4, 5]
mixed = [1, "hello", 3.14, True]
empty = []

print(f"水果列表: {fruits}")
print(f"数字列表: {numbers}")
print(f"混合列表: {mixed}")
print(f"空列表: {empty}")

print("\n=== 2. 列表的访问 ===")
print(f"fruits[0] = {fruits[0]}")  # 第一个元素
print(f"fruits[1] = {fruits[1]}")  # 第二个元素
print(f"fruits[-1] = {fruits[-1]}")  # 最后一个元素
print(f"fruits[-2] = {fruits[-2]}")  # 倒数第二个

print("\n=== 3. 列表切片 ===")
nums = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(f"原列表: {nums}")
print(f"nums[2:5] = {nums[2:5]}")  # 索引2到4
print(f"nums[:5] = {nums[:5]}")  # 前5个
print(f"nums[5:] = {nums[5:]}")  # 从第5个开始
print(f"nums[::2] = {nums[::2]}")  # 步长为2
print(f"nums[::-1] = {nums[::-1]}")  # 反转

print("\n=== 4. 列表的修改 ===")
fruits_copy = fruits.copy()
print(f"原列表: {fruits_copy}")
fruits_copy[0] = "草莓"
print(f"修改第一个元素: {fruits_copy}")

print("\n=== 5. 列表的添加 ===")
fruits_copy = fruits.copy()
fruits_copy.append("葡萄")  # 末尾添加
print(f"append('葡萄'): {fruits_copy}")
fruits_copy.insert(1, "芒果")  # 指定位置插入
print(f"insert(1, '芒果'): {fruits_copy}")
fruits_copy.extend(["西瓜", "菠萝"])  # 合并列表
print(f"extend(['西瓜', '菠萝']): {fruits_copy}")

print("\n=== 6. 列表的删除 ===")
fruits_copy = ["苹果", "香蕉", "橙子", "香蕉", "葡萄"]
print(f"原列表: {fruits_copy}")
fruits_copy.remove("香蕉")  # 删除第一个匹配项
print(f"remove('香蕉'): {fruits_copy}")
popped = fruits_copy.pop()  # 删除并返回最后一个元素
print(f"pop(): 删除了 {popped}，剩余: {fruits_copy}")
del fruits_copy[0]  # 删除指定索引
print(f"del fruits_copy[0]: {fruits_copy}")

print("\n=== 7. 列表的其他常用方法 ===")
nums_copy = [3, 1, 4, 1, 5, 9, 2, 6]
print(f"原列表: {nums_copy}")
print(f"len(): {len(nums_copy)}")
print(f"count(1): {nums_copy.count(1)}")  # 统计出现次数
print(f"index(5): {nums_copy.index(5)}")  # 查找索引
nums_copy.sort()
print(f"sort(): {nums_copy}")
nums_copy.reverse()
print(f"reverse(): {nums_copy}")

print("\n=== 8. 列表推导式 ===")
squares = [x ** 2 for x in range(10)]
print(f"平方数: {squares}")

evens = [x for x in range(20) if x % 2 == 0]
print(f"偶数: {evens}")

print("\n=== 9. 元组 ===")
tuple1 = (1, 2, 3)
tuple2 = 1, 2, 3  # 括号可以省略
single_tuple = (1,)  # 单个元素的元组需要加逗号

print(f"元组1: {tuple1}")
print(f"元组2: {tuple2}")
print(f"单元素元组: {single_tuple}")
print(f"tuple1[0] = {tuple1[0]}")
print(f"len(tuple1) = {len(tuple1)}")

print("\n=== 10. 元组与列表的转换 ===")
list_from_tuple = list(tuple1)
print(f"元组转列表: {list_from_tuple}, 类型: {type(list_from_tuple)}")
tuple_from_list = tuple(fruits)
print(f"列表转元组: {tuple_from_list}, 类型: {type(tuple_from_list)}")
