# ============================================================
# Python 高级特性 - 元类、描述符、异步编程
# ============================================================

print("=== 1. 元类 ===")

# type 创建类
print("--- type 动态创建类 ---")

def greet(self):
    return f"你好，我是{self.name}"


# type(类名, 父类元组, 属性字典)
DynamicPerson = type('DynamicPerson', (), {
    'species': '人类',
    '__init__': lambda self, name: setattr(self, 'name', name),
    'greet': greet
})

p = DynamicPerson("张三")
print(f"p.name = {p.name}")
print(f"p.greet() = {p.greet()}")
print(f"p.species = {p.species}")


# 自定义元类
print("\n--- 自定义元类 ---")


class UpperMeta(type):
    """将属性名转为大写的元类"""
    def __new__(mcs, name, bases, attrs):
        # 转换属性名为大写
        upper_attrs = {}
        for attr_name, attr_value in attrs.items():
            if not attr_name.startswith('__'):
                upper_attrs[attr_name.upper()] = attr_value
            else:
                upper_attrs[attr_name] = attr_value

        return super().__new__(mcs, name, bases, upper_attrs)


class MyClass(metaclass=UpperMeta):
    name = "test"
    value = 42


# 属性名都变成大写了
print(f"MyClass.NAME = {MyClass.NAME}")
print(f"MyClass.VALUE = {MyClass.VALUE}")


# 注册类的元类
print("\n--- 类注册元类 ---")


class RegistryMeta(type):
    registry = {}

    def __new__(mcs, name, bases, attrs):
        cls = super().__new__(mcs, name, bases, attrs)
        if name != 'BasePlugin':
            mcs.registry[name] = cls
        return cls


class BasePlugin(metaclass=RegistryMeta):
    pass


class PluginA(BasePlugin):
    pass


class PluginB(BasePlugin):
    pass


print("已注册的插件:")
for name, cls in RegistryMeta.registry.items():
    print(f"  {name}: {cls}")


print("\n=== 2. 描述符 ===")


class PositiveNumber:
    """确保数值为正的描述符"""
    def __init__(self, name):
        self.name = name

    def __get__(self, instance, owner):
        if instance is None:
            return self
        return instance.__dict__.get(self.name, 0)

    def __set__(self, instance, value):
        if value < 0:
            raise ValueError(f"{self.name} 不能为负数")
        instance.__dict__[self.name] = value

    def __delete__(self, instance):
        raise AttributeError(f"不能删除 {self.name}")


class Product:
    price = PositiveNumber('price')
    quantity = PositiveNumber('quantity')

    def __init__(self, name, price, quantity):
        self.name = name
        self.price = price
        self.quantity = quantity

    @property
    def total(self):
        return self.price * self.quantity


product = Product("苹果", 5.5, 10)
print(f"商品: {product.name}")
print(f"价格: {product.price}")
print(f"数量: {product.quantity}")
print(f"总价: {product.total}")

try:
    product.price = -10
except ValueError as e:
    print(f"设置负价格时: {e}")


# property 本质上也是描述符
print("\n--- 自定义 property 描述符 ---")


class MyProperty:
    def __init__(self, fget=None, fset=None):
        self.fget = fget
        self.fset = fset

    def __get__(self, instance, owner):
        if instance is None:
            return self
        return self.fget(instance)

    def __set__(self, instance, value):
        if self.fset is None:
            raise AttributeError("只读属性")
        self.fset(instance, value)

    def setter(self, fset):
        self.fset = fset
        return self


class Circle:
    def __init__(self, radius):
        self._radius = radius

    @MyProperty
    def radius(self):
        return self._radius

    @radius.setter
    def radius(self, value):
        if value < 0:
            raise ValueError("半径不能为负")
        self._radius = value

    @MyProperty
    def area(self):
        return 3.14159 * self._radius ** 2


c = Circle(5)
print(f"半径: {c.radius}")
print(f"面积: {c.area}")
c.radius = 10
print(f"修改后半径: {c.radius}")
print(f"修改后面积: {c.area}")


print("\n=== 3. 装饰器类 ===")


class CountCalls:
    """统计函数调用次数的装饰器类"""
    def __init__(self, func):
        self.func = func
        self.count = 0

    def __call__(self, *args, **kwargs):
        self.count += 1
        print(f"[计数] {self.func.__name__} 已调用 {self.count} 次")
        return self.func(*args, **kwargs)


@CountCalls
def add(a, b):
    return a + b


print(f"add(1, 2) = {add(1, 2)}")
print(f"add(3, 4) = {add(3, 4)}")
print(f"add(5, 6) = {add(5, 6)}")
print(f"总共调用次数: {add.count}")


class Retry:
    """失败重试装饰器"""
    def __init__(self, max_times=3):
        self.max_times = max_times

    def __call__(self, func):
        def wrapper(*args, **kwargs):
            last_error = None
            for i in range(self.max_times):
                try:
                    return func(*args, **kwargs)
                except Exception as e:
                    last_error = e
                    print(f"  第{i+1}次失败，重试...")
            raise last_error
        return wrapper


import random


@Retry(max_times=3)
def unstable_function():
    if random.random() < 0.5:
        raise ValueError("随机失败")
    return "成功！"


print("\n测试重试装饰器:")
for i in range(3):
    try:
        result = unstable_function()
        print(f"第{i+1}次尝试: {result}")
    except ValueError as e:
        print(f"第{i+1}次尝试最终失败: {e}")


print("\n=== 4. 上下文管理器深入 ===")

from contextlib import contextmanager, ExitStack


@contextmanager
def nested_context(name, depth):
    """嵌套上下文"""
    print(f"{'  '*depth}进入 {name}")
    try:
        yield name
    finally:
        print(f"{'  '*depth}退出 {name}")


print("--- 嵌套上下文管理器 ---")
with nested_context("外层", 0) as outer:
    with nested_context("中层", 1) as middle:
        with nested_context("内层", 2) as inner:
            print(f"    三层嵌套: {outer} -> {middle} -> {inner}")


print("\n--- ExitStack 动态管理上下文 ---")
with ExitStack() as stack:
    file_handles = []
    for i in range(3):
        ctx = stack.enter_context(nested_context(f"动态{i}", 0))
        file_handles.append(ctx)
    print(f"所有上下文已进入: {file_handles}")


print("\n=== 5. 异步编程基础 ===")

import asyncio


async def say_hello(name, delay):
    """异步函数"""
    await asyncio.sleep(delay)
    print(f"你好，{name}！（延迟 {delay} 秒）")
    return f"{name} 的问候"


async def main_async():
    print("--- 顺序执行 ---")
    result1 = await say_hello("张三", 0.1)
    result2 = await say_hello("李四", 0.1)
    print(f"结果: {result1}, {result2}")

    print("\n--- 并发执行 (gather) ---")
    results = await asyncio.gather(
        say_hello("A", 0.2),
        say_hello("B", 0.1),
        say_hello("C", 0.15)
    )
    print(f"所有结果: {results}")

    print("\n--- 任务 (Task) ---")
    task1 = asyncio.create_task(say_hello("任务1", 0.1))
    task2 = asyncio.create_task(say_hello("任务2", 0.15))
    await task1
    await task2
    print("所有任务完成")


asyncio.run(main_async())


print("\n=== 6. 迭代器协议和自定义迭代器 ===")


class RangeCustom:
    """自定义 range 类"""
    def __init__(self, start, end, step=1):
        self.start = start
        self.end = end
        self.step = step

    def __iter__(self):
        self.current = self.start
        return self

    def __next__(self):
        if self.current >= self.end:
            raise StopIteration
        result = self.current
        self.current += self.step
        return result

    def __len__(self):
        return max(0, (self.end - self.start + self.step - 1) // self.step)

    def __getitem__(self, index):
        value = self.start + index * self.step
        if value >= self.end:
            raise IndexError("索引超出范围")
        return value


r = RangeCustom(1, 10, 2)
print(f"自定义 range(1, 10, 2):")
print(f"  长度: {len(r)}")
print(f"  元素: {list(r)}")
print(f"  r[0] = {r[0]}")
print(f"  r[2] = {r[2]}")


print("\n=== 7. slots 和内存优化 ===")


class RegularClass:
    def __init__(self, x, y):
        self.x = x
        self.y = y


class SlotClass:
    __slots__ = ['x', 'y']

    def __init__(self, x, y):
        self.x = x
        self.y = y


import sys

regular = RegularClass(1, 2)
slotted = SlotClass(1, 2)

print(f"普通类大小: {sys.getsizeof(regular) + sys.getsizeof(regular.__dict__)} 字节")
print(f"__slots__ 类大小: {sys.getsizeof(slotted)} 字节")
print("__slots__ 节省内存，且属性访问更快")


print("\n=== 运行完成 ===")