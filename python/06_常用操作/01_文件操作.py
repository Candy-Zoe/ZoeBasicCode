# ============================================================
# Python 常用操作 - 文件操作
# ============================================================
import os

print("=== 1. 写入文件 ===")
with open("test.txt", "w", encoding="utf-8") as f:
    f.write("第一行\n")
    f.write("第二行\n")
    f.write("第三行\n")
print("已写入 test.txt")

print("\n=== 2. 读取文件（全部读取） ===")
with open("test.txt", "r", encoding="utf-8") as f:
    content = f.read()
print("文件内容:")
print(content)

print("=== 3. 读取文件（按行读取） ===")
with open("test.txt", "r", encoding="utf-8") as f:
    lines = f.readlines()
print(f"共 {len(lines)} 行:")
for i, line in enumerate(lines):
    print(f"  第{i+1}行: {line.strip()}")

print("\n=== 4. 逐行读取（大文件推荐） ===")
with open("test.txt", "r", encoding="utf-8") as f:
    for line in f:
        print(f"  {line.strip()}")

print("\n=== 5. 追加写入 ===")
with open("test.txt", "a", encoding="utf-8") as f:
    f.write("第四行（追加）\n")
print("已追加内容")

with open("test.txt", "r", encoding="utf-8") as f:
    print(f.read())

print("=== 6. 文件和目录操作 ===")
print(f"当前工作目录: {os.getcwd()}")
print(f"文件是否存在: {os.path.exists('test.txt')}")
print(f"是否是文件: {os.path.isfile('test.txt')}")
print(f"是否是目录: {os.path.isdir('.')}")
print(f"文件大小: {os.path.getsize('test.txt')} 字节")

print("\n=== 7. 创建和删除目录 ===")
if not os.path.exists("test_dir"):
    os.mkdir("test_dir")
    print("创建目录 test_dir")
print(f"目录存在: {os.path.exists('test_dir')}")

if os.path.exists("test_dir"):
    os.rmdir("test_dir")
    print("删除目录 test_dir")

print("\n=== 8. 重命名和删除文件 ===")
with open("old_name.txt", "w") as f:
    f.write("测试重命名")

os.rename("old_name.txt", "new_name.txt")
print("文件已重命名为 new_name.txt")

if os.path.exists("new_name.txt"):
    os.remove("new_name.txt")
    print("文件 new_name.txt 已删除")

print("\n=== 9. 遍历目录 ===")
print("当前目录下的文件和文件夹:")
for item in os.listdir("."):
    if os.path.isfile(item):
        print(f"  [文件] {item}")
    elif os.path.isdir(item):
        print(f"  [目录] {item}")

print("\n=== 10. 路径操作 ===")
path = "/home/user/documents/file.txt"
print(f"路径: {path}")
print(f"目录名: {os.path.dirname(path)}")
print(f"文件名: {os.path.basename(path)}")
print(f"扩展名: {os.path.splitext(path)[1]}")
print(f"路径拼接: {os.path.join('/home/user', 'test.txt')}")

print("\n=== 11. JSON 文件操作 ===")
import json

data = {
    "name": "张三",
    "age": 25,
    "hobbies": ["读书", "编程", "音乐"]
}

with open("data.json", "w", encoding="utf-8") as f:
    json.dump(data, f, ensure_ascii=False, indent=2)
print("已写入 data.json")

with open("data.json", "r", encoding="utf-8") as f:
    loaded_data = json.load(f)
print(f"读取JSON: {loaded_data}")
print(f"姓名: {loaded_data['name']}")

print("\n=== 12. CSV 文件操作 ===")
import csv

csv_data = [
    ["姓名", "年龄", "城市"],
    ["张三", "25", "北京"],
    ["李四", "30", "上海"]
]

with open("data.csv", "w", newline="", encoding="utf-8") as f:
    writer = csv.writer(f)
    writer.writerows(csv_data)
print("已写入 data.csv")

with open("data.csv", "r", encoding="utf-8") as f:
    reader = csv.reader(f)
    print("读取CSV:")
    for row in reader:
        print(f"  {row}")

print("\n=== 清理测试文件 ===")
for f in ["test.txt", "data.json", "data.csv"]:
    if os.path.exists(f):
        os.remove(f)
        print(f"已删除 {f}")
