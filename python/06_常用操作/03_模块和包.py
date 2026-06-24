# ============================================================
# Python 常用操作 - 模块和包
# ============================================================

print("=== 1. 导入标准库模块 ===")
import math
print(f"math.pi = {math.pi}")
print(f"math.sqrt(16) = {math.sqrt(16)}")
print(f"math.sin(0) = {math.sin(0)}")

print("\n=== 2. 导入特定函数/变量 ===")
from math import pi, sqrt
print(f"pi = {pi}")
print(f"sqrt(25) = {sqrt(25)}")

print("\n=== 3. 导入并起别名 ===")
import math as m
print(f"m.pi = {m.pi}")
print(f"m.e = {m.e}")

from math import factorial as fact
print(f"fact(5) = {fact(5)}")

print("\n=== 4. 常用标准库 - random ===")
import random
print(f"随机整数 (1-10): {random.randint(1, 10)}")
print(f"随机浮点数 (0-1): {random.random():.4f}")
fruits = ["苹果", "香蕉", "橙子", "葡萄"]
print(f"随机选择: {random.choice(fruits)}")

print("\n=== 5. 常用标准库 - datetime ===")
from datetime import datetime, date, timedelta
now = datetime.now()
print(f"当前时间: {now}")
print(f"今天日期: {date.today()}")
print(f"年: {now.year}, 月: {now.month}, 日: {now.day}")
print(f"时: {now.hour}, 分: {now.minute}, 秒: {now.second}")

tomorrow = date.today() + timedelta(days=1)
print(f"明天: {tomorrow}")

print("\n=== 6. 常用标准库 - os ===")
import os
print(f"当前目录: {os.getcwd()}")
print(f"环境变量 PATH 前50个字符: {os.environ.get('PATH', '')[:50]}...")

print("\n=== 7. 常用标准库 - sys ===")
import sys
print(f"Python 版本: {sys.version[:20]}...")
print(f"平台: {sys.platform}")

print("\n=== 8. 常用标准库 - json ===")
import json
data = {"name": "张三", "age": 25, "hobbies": ["读书", "编程"]}
json_str = json.dumps(data, ensure_ascii=False, indent=2)
print("JSON 字符串:")
print(json_str)

print("\n=== 9. 常用标准库 - re (正则表达式) ===")
import re
text = "我的邮箱是 test@example.com，请联系我"
pattern = r'[\w.-]+@[\w.-]+\.\w+'
match = re.search(pattern, text)
if match:
    print(f"找到邮箱: {match.group()}")

print("\n=== 10. 常用标准库 - collections ===")
from collections import Counter, defaultdict, deque

words = ["apple", "banana", "apple", "orange", "banana", "apple"]
word_count = Counter(words)
print(f"单词计数: {word_count}")
print(f"最常见的2个: {word_count.most_common(2)}")

dd = defaultdict(int)
dd["a"] += 1
dd["b"] += 2
print(f"defaultdict: {dict(dd)}")

d = deque([1, 2, 3])
d.append(4)
d.appendleft(0)
print(f"deque: {d}")

print("\n=== 11. 创建自定义模块说明 ===")
print("要创建自己的模块，只需创建一个 .py 文件")
print("例如创建 my_module.py:")
print("  def greet(name):")
print("      print(f'Hello, {name}!')")
print("")
print("然后在其他文件中导入:")
print("  import my_module")
print("  from my_module import greet")

print("\n=== 12. 包的结构说明 ===")
print("包是包含 __init__.py 的目录")
print("示例结构:")
print("my_package/")
print("    __init__.py")
print("    module1.py")
print("    module2.py")
print("")
print("导入方式:")
print("  from my_package import module1")
print("  from my_package.module1 import function1")

print("\n=== 13. __name__ == '__main__' 的用法 ===")
print("当脚本直接运行时，__name__ == '__main__'")
print("当脚本被导入时，__name__ == 模块名")
print("")
print("常用写法:")
print("  if __name__ == '__main__':")
print("      # 测试代码")
print("      main()")
