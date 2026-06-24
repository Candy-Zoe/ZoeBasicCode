# ============================================================
# Python 面向对象 - 类和对象
# ============================================================

print("=== 1. 类的定义 ===")
class Person:
    """人类"""

    def __init__(self, name, age):
        """构造方法，初始化属性"""
        self.name = name
        self.age = age

    def greet(self):
        """问候方法"""
        print(f"你好，我是{self.name}，今年{self.age}岁")

person1 = Person("张三", 25)
person2 = Person("李四", 30)

person1.greet()
person2.greet()

print("\n=== 2. 属性的访问和修改 ===")
print(f"person1.name = {person1.name}")
print(f"person1.age = {person1.age}")
person1.age = 26
print(f"修改后 person1.age = {person1.age}")

print("\n=== 3. 类变量和实例变量 ===")
class Student:
    school = "北京大学"  # 类变量，所有实例共享

    def __init__(self, name, student_id):
        self.name = name  # 实例变量
        self.student_id = student_id  # 实例变量

s1 = Student("小明", "2023001")
s2 = Student("小红", "2023002")

print(f"s1.school = {s1.school}")
print(f"s2.school = {s2.school}")
print(f"Student.school = {Student.school}")

Student.school = "清华大学"  # 修改类变量
print(f"修改类变量后:")
print(f"s1.school = {s1.school}")
print(f"s2.school = {s2.school}")

print("\n=== 4. 私有属性和方法 ===")
class BankAccount:
    def __init__(self, balance=0):
        self.__balance = balance  # 私有属性（双下划线开头）

    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            print(f"存入 {amount} 元")
        else:
            print("存款金额必须大于0")

    def withdraw(self, amount):
        if 0 < amount <= self.__balance:
            self.__balance -= amount
            print(f"取出 {amount} 元")
        else:
            print("取款金额无效")

    def get_balance(self):
        return self.__balance

account = BankAccount(1000)
print(f"初始余额: {account.get_balance()}")
account.deposit(500)
account.withdraw(300)
print(f"当前余额: {account.get_balance()}")

print("\n=== 5. 继承 ===")
class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        print(f"{self.name} 发出声音")

class Dog(Animal):
    def speak(self):
        print(f"{self.name} 汪汪叫")

    def fetch(self):
        print(f"{self.name} 去捡球了")

class Cat(Animal):
    def speak(self):
        print(f"{self.name} 喵喵叫")

dog = Dog("旺财")
cat = Cat("咪咪")

dog.speak()
dog.fetch()
cat.speak()

print("\n=== 6. super() 调用父类方法 ===")
class Employee:
    def __init__(self, name, salary):
        self.name = name
        self.salary = salary

    def work(self):
        print(f"{self.name} 在工作")

class Manager(Employee):
    def __init__(self, name, salary, department):
        super().__init__(name, salary)  # 调用父类构造方法
        self.department = department

    def work(self):
        super().work()  # 调用父类方法
        print(f"{self.name} 管理 {self.department} 部门")

manager = Manager("王经理", 20000, "技术部")
manager.work()

print("\n=== 7. 多重继承 ===")
class Flyable:
    def fly(self):
        print(f"{self.name} 会飞")

class Swimmable:
    def swim(self):
        print(f"{self.name} 会游泳")

class Duck(Animal, Flyable, Swimmable):
    pass

duck = Duck("唐老鸭")
duck.speak()
duck.fly()
duck.swim()

print("\n=== 8. 魔术方法 ===")
class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return f"Point({self.x}, {self.y})"

    def __add__(self, other):
        return Point(self.x + other.x, self.y + other.y)

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

p1 = Point(1, 2)
p2 = Point(3, 4)
p3 = Point(1, 2)

print(f"p1 = {p1}")
print(f"p1 + p2 = {p1 + p2}")
print(f"p1 == p3: {p1 == p3}")
print(f"p1 == p2: {p1 == p2}")
