# ============================================================
# Python 数据结构 - 字典和集合
# ============================================================

print("=== 1. 字典的创建 ===")
person = {"name": "张三", "age": 25, "city": "北京"}
empty_dict = {}
dict_constructor = dict(name="李四", age=30, city="上海")

print(f"person = {person}")
print(f"空字典 = {empty_dict}")
print(f"dict构造 = {dict_constructor}")

print("\n=== 2. 字典的访问 ===")
print(f"person['name'] = {person['name']}")
print(f"person['age'] = {person['age']}")
print(f"person.get('city') = {person.get('city')}")
print(f"person.get('job', '未知') = {person.get('job', '未知')}")  # 默认值

print("\n=== 3. 字典的修改和添加 ===")
person_copy = person.copy()
print(f"原字典: {person_copy}")
person_copy["age"] = 26  # 修改
print(f"修改 age: {person_copy}")
person_copy["job"] = "工程师"  # 添加
print(f"添加 job: {person_copy}")

print("\n=== 4. 字典的删除 ===")
person_copy = person.copy()
person_copy["job"] = "工程师"
print(f"原字典: {person_copy}")
del person_copy["city"]  # 删除键
print(f"del city: {person_copy}")
age = person_copy.pop("age")  # 弹出并返回值
print(f"pop age: 值为 {age}，剩余: {person_copy}")

print("\n=== 5. 字典的遍历 ===")
print(f"遍历键:")
for key in person.keys():
    print(f"  {key}")

print(f"遍历值:")
for value in person.values():
    print(f"  {value}")

print(f"遍历键值对:")
for key, value in person.items():
    print(f"  {key}: {value}")

print("\n=== 6. 字典的其他方法 ===")
print(f"person.keys() = {list(person.keys())}")
print(f"person.values() = {list(person.values())}")
print(f"person.items() = {list(person.items())}")
print(f"'name' in person = {'name' in person}")
print(f"'job' in person = {'job' in person}")
print(f"len(person) = {len(person)}")

print("\n=== 7. 字典推导式 ===")
squares_dict = {x: x ** 2 for x in range(5)}
print(f"平方数字典: {squares_dict}")

print("\n=== 8. 集合的创建 ===")
set1 = {1, 2, 3, 4, 5}
set2 = set([1, 2, 2, 3, 3, 3])  # 自动去重
empty_set = set()  # 空集合只能用 set()

print(f"set1 = {set1}")
print(f"set2 = {set2}")
print(f"空集合 = {empty_set}")

print("\n=== 9. 集合的添加和删除 ===")
s = {1, 2, 3}
print(f"原集合: {s}")
s.add(4)
print(f"add(4): {s}")
s.update([5, 6])
print(f"update([5,6]): {s}")
s.remove(1)
print(f"remove(1): {s}")
s.discard(10)  # 元素不存在不会报错
print(f"discard(10): {s}")

print("\n=== 10. 集合的运算 ===")
a = {1, 2, 3, 4, 5}
b = {4, 5, 6, 7, 8}
print(f"a = {a}")
print(f"b = {b}")
print(f"交集 a & b = {a & b}")
print(f"并集 a | b = {a | b}")
print(f"差集 a - b = {a - b}")
print(f"对称差集 a ^ b = {a ^ b}")

print("\n=== 11. 集合的应用：去重 ===")
nums = [1, 2, 2, 3, 3, 3, 4, 5, 5]
unique_nums = list(set(nums))
print(f"原列表: {nums}")
print(f"去重后: {unique_nums}")
