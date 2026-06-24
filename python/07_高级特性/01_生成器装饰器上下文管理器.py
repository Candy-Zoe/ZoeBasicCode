# ============================================================
# Python 高级特性 - 生成器、装饰器、上下文管理器
# ============================================================

print("=== 1. 生成器 ===")


def countdown(n):
    """生成器函数 - 倒计时"""
    while n > 0:
        yield n
        n -= 1


print("倒计时 5:")
for num in countdown(5):
    print(num, end=" ")
print()

# 逐个获取值
gen = countdown(3)
print(f"next(gen) = {next(gen)}")
print(f"next(gen) = {next(gen)}")
print(f"next(gen) = {next(gen)}")

try:
    next(gen)
except StopIteration:
    print("生成器已耗尽")


def fibonacci(n):
    """生成斐波那契数列"""
    a, b = 0, 1
    for _ in range(n):
        yield a
        a, b = b, a + b


print("\n斐波那契数列前10个:")
for num in fibonacci(10):
    print(num, end=" ")
print()

# 生成器表达式
print("\n生成器表达式:")
squares_gen = (x * x for x in range(10))
print(f"平方数生成器: {list(squares_gen)}")


print("\n=== 2. 装饰器 ===")


def my_decorator(func):
    """简单装饰器"""
    def wrapper(*args, **kwargs):
        print(f"[装饰器] 调用 {func.__name__} 之前")
        result = func(*args, **kwargs)
        print(f"[装饰器] 调用 {func.__name__} 之后")
        return result
    return wrapper


@my_decorator
def say_hello(name):
    print(f"你好，{name}！")


say_hello("张三")


def timer(func):
    """计时器装饰器"""
    import time
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        elapsed = time.time() - start
        print(f"[计时器] {func.__name__} 执行时间: {elapsed:.4f}秒")
        return result
    return wrapper


@timer
def slow_function():
    import time
    time.sleep(0.1)
    return "完成"


result = slow_function()
print(f"结果: {result}")


def repeat(n):
    """带参数的装饰器 - 重复执行n次"""
    def decorator(func):
        def wrapper(*args, **kwargs):
            for i in range(n):
                result = func(*args, **kwargs)
            return result
        return wrapper
    return decorator


@repeat(3)
def print_message(msg):
    print(f"  {msg}")


print("\n带参数的装饰器 (重复3次):")
print_message("Hello!")


from functools import wraps


def logged(func):
    """使用 functools.wraps 保留原函数信息"""
    @wraps(func)
    def wrapper(*args, **kwargs):
        print(f"[日志] 调用 {func.__name__}")
        return func(*args, **kwargs)
    return wrapper


@logged
def add(a, b):
    """加法函数"""
    return a + b


print(f"\n函数名: {add.__name__}")
print(f"文档: {add.__doc__}")
print(f"10 + 20 = {add(10, 20)}")


print("\n=== 3. 上下文管理器 ===")


class FileManager:
    """类实现的上下文管理器"""
    def __init__(self, filename, mode):
        self.filename = filename
        self.mode = mode
        self.file = None

    def __enter__(self):
        print(f"[上下文] 打开文件 {self.filename}")
        self.file = open(self.filename, self.mode, encoding='utf-8')
        return self.file

    def __exit__(self, exc_type, exc_val, exc_tb):
        print(f"[上下文] 关闭文件 {self.filename}")
        if self.file:
            self.file.close()
        return False


with FileManager("test_context.txt", "w") as f:
    f.write("Hello, World!")
    f.write("\n你好，世界！")

# 验证文件已关闭
# print(f"文件已关闭: {f.closed}")  # f 在 with 外不可访问


from contextlib import contextmanager


@contextmanager
def timer_context(name):
    """使用 contextmanager 实现的计时器上下文"""
    import time
    start = time.time()
    print(f"[计时器] 开始 {name}")
    try:
        yield
    finally:
        elapsed = time.time() - start
        print(f"[计时器] {name} 耗时: {elapsed:.4f}秒")


with timer_context("耗时操作"):
    import time
    time.sleep(0.05)


@contextmanager
def temporary_list():
    """临时列表上下文"""
    temp_list = []
    print("[列表] 创建临时列表")
    try:
        yield temp_list
    finally:
        print(f"[列表] 清理临时列表，内容: {temp_list}")
        temp_list.clear()


with temporary_list() as lst:
    lst.append(1)
    lst.append(2)
    lst.append(3)
    print(f"临时列表内容: {lst}")


print("\n=== 4. 魔术方法深入 ===")


class MyNumber:
    """自定义数字类，演示算术运算符重载"""
    def __init__(self, value):
        self.value = value

    def __str__(self):
        return f"MyNumber({self.value})"

    def __repr__(self):
        return f"MyNumber({self.value})"

    def __add__(self, other):
        if isinstance(other, MyNumber):
            return MyNumber(self.value + other.value)
        return MyNumber(self.value + other)

    def __sub__(self, other):
        if isinstance(other, MyNumber):
            return MyNumber(self.value - other.value)
        return MyNumber(self.value - other)

    def __mul__(self, other):
        if isinstance(other, MyNumber):
            return MyNumber(self.value * other.value)
        return MyNumber(self.value * other)

    def __eq__(self, other):
        if isinstance(other, MyNumber):
            return self.value == other.value
        return self.value == other

    def __lt__(self, other):
        if isinstance(other, MyNumber):
            return self.value < other.value
        return self.value < other


a = MyNumber(10)
b = MyNumber(5)
print(f"a = {a}")
print(f"b = {b}")
print(f"a + b = {a + b}")
print(f"a - b = {a - b}")
print(f"a * b = {a * b}")
print(f"a == b: {a == b}")
print(f"a > b: {a > b}")


class FibSequence:
    """可迭代的斐波那契数列"""
    def __init__(self, n):
        self.n = n

    def __iter__(self):
        self.a, self.b = 0, 1
        self.count = 0
        return self

    def __next__(self):
        if self.count >= self.n:
            raise StopIteration
        result = self.a
        self.a, self.b = self.b, self.a + self.b
        self.count += 1
        return result


print("\n可迭代斐波那契数列:")
fib = FibSequence(10)
for num in fib:
    print(num, end=" ")
print()


print("\n=== 5. 枚举类 ===")

from enum import Enum, IntEnum, auto


class Color(Enum):
    RED = 1
    GREEN = 2
    BLUE = 3


print(f"Color.RED = {Color.RED}")
print(f"Color.RED.name = {Color.RED.name}")
print(f"Color.RED.value = {Color.RED.value}")

print("\n遍历 Color:")
for c in Color:
    print(f"  {c.name} = {c.value}")


class Direction(IntEnum):
    NORTH = 1
    EAST = 2
    SOUTH = 3
    WEST = 4


print(f"\nDirection.NORTH < Direction.SOUTH: {Direction.NORTH < Direction.SOUTH}")
print(f"Direction.EAST == 2: {Direction.EAST == 2}")


class Status(Enum):
    PENDING = auto()
    RUNNING = auto()
    SUCCESS = auto()
    FAILED = auto()


print("\n自动赋值的 Status:")
for s in Status:
    print(f"  {s.name} = {s.value}")


print("\n=== 6. 数据类 ===")

from dataclasses import dataclass, field


@dataclass
class PersonData:
    name: str
    age: int
    city: str = "北京"
    hobbies: list = field(default_factory=list)

    def greet(self):
        return f"你好，我是{self.name}，来自{self.city}"


p1 = PersonData("张三", 25)
p2 = PersonData("李四", 30, "上海")
p3 = PersonData("张三", 25)

print(f"p1 = {p1}")
print(f"p2 = {p2}")
print(f"p1 == p3: {p1 == p3}")
print(f"p1.greet(): {p1.greet()}")

p1.hobbies.append("读书")
p1.hobbies.append("编程")
print(f"p1.hobbies = {p1.hobbies}")


@dataclass(frozen=True)
class PointData:
    x: int
    y: int


p = PointData(10, 20)
print(f"\n不可变 Point: {p}")
# p.x = 30  # 错误！frozen 不可修改


print("\n=== 7. namedtuple ===")

from collections import namedtuple

PointNT = namedtuple('PointNT', ['x', 'y'])
p_nt = PointNT(10, 20)
print(f"p_nt = {p_nt}")
print(f"p_nt.x = {p_nt.x}")
print(f"p_nt.y = {p_nt.y}")

x, y = p_nt
print(f"解包: x={x}, y={y}")


print("\n=== 8. property 高级用法 ===")


class Temperature:
    def __init__(self, celsius=0):
        self._celsius = celsius

    @property
    def celsius(self):
        return self._celsius

    @celsius.setter
    def celsius(self, value):
        if value < -273.15:
            raise ValueError("温度不能低于绝对零度")
        self._celsius = value

    @property
    def fahrenheit(self):
        return self._celsius * 9 / 5 + 32

    @fahrenheit.setter
    def fahrenheit(self, value):
        self.celsius = (value - 32) * 5 / 9

    @property
    def kelvin(self):
        return self._celsius + 273.15


temp = Temperature(25)
print(f"摄氏: {temp.celsius}°C")
print(f"华氏: {temp.fahrenheit:.1f}°F")
print(f"开尔文: {temp.kelvin:.1f}K")

temp.fahrenheit = 212
print(f"\n设置华氏212°F后:")
print(f"摄氏: {temp.celsius:.1f}°C")


print("\n=== 运行完成 ===")