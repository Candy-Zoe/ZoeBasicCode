# ============================================================
# Python 面向对象 - 类的高级特性
# ============================================================

print("=== 1. @property 装饰器 ===")
class Circle:
    def __init__(self, radius):
        self._radius = radius

    @property
    def radius(self):
        return self._radius

    @radius.setter
    def radius(self, value):
        if value < 0:
            raise ValueError("半径不能为负数")
        self._radius = value

    @property
    def area(self):
        return 3.14159 * self._radius ** 2

circle = Circle(5)
print(f"半径: {circle.radius}")
print(f"面积: {circle.area}")
circle.radius = 10
print(f"修改后半径: {circle.radius}")
print(f"修改后面积: {circle.area}")

print("\n=== 2. 类方法 (@classmethod) ===")
class MyClass:
    count = 0

    def __init__(self):
        MyClass.count += 1

    @classmethod
    def get_count(cls):
        return f"已创建 {cls.count} 个实例"

a = MyClass()
b = MyClass()
c = MyClass()
print(MyClass.get_count())

print("\n=== 3. 静态方法 (@staticmethod) ===")
class MathUtils:
    @staticmethod
    def add(x, y):
        return x + y

    @staticmethod
    def multiply(x, y):
        return x * y

    @staticmethod
    def is_even(n):
        return n % 2 == 0

print(f"MathUtils.add(3, 5) = {MathUtils.add(3, 5)}")
print(f"MathUtils.multiply(3, 5) = {MathUtils.multiply(3, 5)}")
print(f"MathUtils.is_even(4) = {MathUtils.is_even(4)}")

print("\n=== 4. 抽象类 ===")
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self):
        pass

    @abstractmethod
    def perimeter(self):
        pass

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

    def perimeter(self):
        return 2 * (self.width + self.height)

class CircleShape(Shape):
    def __init__(self, radius):
        self.radius = radius

    def area(self):
        return 3.14159 * self.radius ** 2

    def perimeter(self):
        return 2 * 3.14159 * self.radius

rect = Rectangle(4, 5)
circ = CircleShape(3)

print(f"矩形面积: {rect.area()}, 周长: {rect.perimeter()}")
print(f"圆形面积: {circ.area():.2f}, 周长: {circ.perimeter():.2f}")

print("\n=== 5. 枚举类 ===")
from enum import Enum

class Color(Enum):
    RED = 1
    GREEN = 2
    BLUE = 3

print(f"Color.RED = {Color.RED}")
print(f"Color.RED.value = {Color.RED.value}")
print(f"Color.RED.name = {Color.RED.name}")

for color in Color:
    print(f"  {color.name}: {color.value}")

print("\n=== 6. 数据类 (@dataclass) ===")
from dataclasses import dataclass

@dataclass
class PersonData:
    name: str
    age: int
    city: str = "北京"

p1 = PersonData("张三", 25)
p2 = PersonData("李四", 30, "上海")
p3 = PersonData("张三", 25)

print(f"p1 = {p1}")
print(f"p2 = {p2}")
print(f"p1 == p3: {p1 == p3}")

print("\n=== 7. 迭代器 ===")
class CountDown:
    def __init__(self, start):
        self.start = start

    def __iter__(self):
        self.current = self.start
        return self

    def __next__(self):
        if self.current <= 0:
            raise StopIteration
        self.current -= 1
        return self.current + 1

print("倒计时:")
for num in CountDown(5):
    print(num, end=" ")
print()

print("\n=== 8. 上下文管理器 ===")
class FileManager:
    def __init__(self, filename, mode):
        self.filename = filename
        self.mode = mode
        self.file = None

    def __enter__(self):
        self.file = open(self.filename, self.mode, encoding='utf-8')
        return self.file

    def __exit__(self, exc_type, exc_val, exc_tb):
        if self.file:
            self.file.close()
        return False

print("上下文管理器示例：with 语句会自动管理资源")
