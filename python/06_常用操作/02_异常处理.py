# ============================================================
# Python 常用操作 - 异常处理
# ============================================================

print("=== 1. 基本 try-except ===")
try:
    result = 10 / 0
except ZeroDivisionError:
    print("错误：不能除以零")

print("\n=== 2. 捕获多个异常 ===")
try:
    num = int("abc")
except ValueError:
    print("错误：无法将字符串转换为整数")
except ZeroDivisionError:
    print("错误：不能除以零")

print("\n=== 3. 捕获所有异常（不推荐） ===")
try:
    x = 10 / 0
except Exception as e:
    print(f"发生异常: {type(e).__name__}: {e}")

print("\n=== 4. else 子句（没有异常时执行） ===")
try:
    result = 10 / 2
except ZeroDivisionError:
    print("除以零错误")
else:
    print(f"计算结果: {result}")
    print("没有发生异常")

print("\n=== 5. finally 子句（总是执行） ===")
try:
    f = open("test_file.txt", "w")
    f.write("测试内容")
    print("文件写入成功")
except IOError:
    print("文件操作失败")
finally:
    f.close()
    print("文件已关闭")

print("\n=== 6. 自定义异常 ===")
class AgeError(Exception):
    """年龄异常"""
    def __init__(self, message="年龄无效"):
        self.message = message
        super().__init__(self.message)

def set_age(age):
    if age < 0 or age > 150:
        raise AgeError(f"年龄 {age} 无效，必须在0-150之间")
    print(f"年龄设置为: {age}")

try:
    set_age(200)
except AgeError as e:
    print(f"捕获到自定义异常: {e}")

print("\n=== 7. 异常链 ===")
def process_data(data):
    try:
        return int(data)
    except ValueError as e:
        raise RuntimeError("数据处理失败") from e

try:
    process_data("abc")
except RuntimeError as e:
    print(f"外层异常: {e}")
    print(f"原始异常: {e.__cause__}")

print("\n=== 8. 常见异常类型 ===")
print("常见异常列表:")
print("  - ZeroDivisionError: 除以零")
print("  - ValueError: 值错误")
print("  - TypeError: 类型错误")
print("  - IndexError: 索引越界")
print("  - KeyError: 字典键不存在")
print("  - FileNotFoundError: 文件不存在")
print("  - PermissionError: 权限错误")
print("  - AttributeError: 属性不存在")
print("  - ImportError: 导入失败")
print("  - NameError: 变量未定义")

print("\n=== 9. 使用示例：安全的用户输入 ===")
def get_integer(prompt):
    while True:
        try:
            num = int(input(prompt))
            return num
        except ValueError:
            print("输入无效，请输入一个整数")

print("提示：此示例需要用户输入，以下是模拟说明")
print("用户输入 'abc' -> 提示无效，继续等待")
print("用户输入 '123' -> 返回整数 123")

print("\n=== 10. 断言 (assert) ===")
x = 10
assert x > 0, "x 必须大于0"
print("断言通过")

print("\n=== 11. 上下文管理器中的异常 ===")
print("使用 with 语句时，即使发生异常也会自动清理资源")
print("例如: with open('file.txt') as f: ...")
print("即使中间发生异常，文件也会被正确关闭")
