// ============================================================
// C# 面向对象 - 类和对象
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;

namespace CSharpBasics
{
    class OOPClasses
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 类的定义和实例化 ===");
            Person person1 = new Person("张三", 25);
            Person person2 = new Person("李四", 30);

            person1.Greet();
            person2.Greet();

            Console.WriteLine("\n=== 2. 属性的访问和修改 ===");
            Console.WriteLine($"person1.Name = {person1.Name}");
            Console.WriteLine($"person1.Age = {person1.Age}");
            person1.Age = 26;
            Console.WriteLine($"修改后 person1.Age = {person1.Age}");

            Console.WriteLine("\n=== 3. 类的静态成员 ===");
            Student s1 = new Student("小明", "2023001");
            Student s2 = new Student("小红", "2023002");

            Console.WriteLine($"s1.School = {s1.School}");
            Console.WriteLine($"s2.School = {s2.School}");
            Console.WriteLine($"Student.School = {Student.School}");
            Console.WriteLine($"学生总数: {Student.StudentCount}");

            Student.School = "清华大学";
            Console.WriteLine($"修改学校后:");
            Console.WriteLine($"s1.School = {s1.School}");

            Console.WriteLine("\n=== 4. 封装：私有字段和属性 ===");
            BankAccount account = new BankAccount(1000);
            Console.WriteLine($"初始余额: {account.Balance}");
            account.Deposit(500);
            account.Withdraw(300);
            Console.WriteLine($"当前余额: {account.Balance}");

            Console.WriteLine("\n=== 5. 构造函数重载 ===");
            Rectangle r1 = new Rectangle();
            Rectangle r2 = new Rectangle(4, 5);
            Console.WriteLine($"r1: 宽={r1.Width}, 高={r1.Height}, 面积={r1.GetArea()}");
            Console.WriteLine($"r2: 宽={r2.Width}, 高={r2.Height}, 面积={r2.GetArea()}");

            Console.WriteLine("\n=== 6. 继承 ===");
            Dog dog = new Dog("旺财");
            Cat cat = new Cat("咪咪");

            dog.Speak();
            dog.Fetch();
            cat.Speak();

            Console.WriteLine("\n=== 7. 多态（虚方法重写） ===");
            Animal[] animals = { new Dog("旺财"), new Cat("咪咪"), new Dog("大黄") };
            foreach (Animal animal in animals)
            {
                animal.Speak();
            }

            Console.WriteLine("\n=== 8. base 调用父类方法 ===");
            Manager manager = new Manager("王经理", 20000, "技术部");
            manager.Work();

            Console.WriteLine("\n=== 9. 抽象类和抽象方法 ===");
            Shape[] shapes = {
                new RectangleShape(4, 5),
                new Circle(3)
            };
            foreach (Shape shape in shapes)
            {
                Console.WriteLine($"{shape.GetType().Name}: 面积={shape.Area():F2}, 周长={shape.Perimeter():F2}");
            }

            Console.WriteLine("\n=== 10. 接口 ===");
            IMovable car = new Car();
            IMovable plane = new Plane();
            car.Move();
            plane.Move();

            Console.WriteLine("\n=== 11. 结构体 ===");
            Point p1 = new Point(1, 2);
            Point p2 = new Point(3, 4);
            Console.WriteLine($"p1 = {p1}");
            Console.WriteLine($"p2 = {p2}");
            Console.WriteLine($"p1 + p2 = {p1.Add(p2)}");

            Console.WriteLine("\n=== 12. 枚举 ===");
            Color color = Color.Red;
            Console.WriteLine($"color = {color}");
            Console.WriteLine($"color 的值 = {(int)color}");

            Console.WriteLine("\n所有颜色:");
            foreach (Color c in Enum.GetValues<Color>())
            {
                Console.WriteLine($"  {c} = {(int)c}");
            }
        }
    }

    // 1. 基本类
    class Person
    {
        public string Name { get; set; }
        public int Age { get; set; }

        public Person(string name, int age)
        {
            Name = name;
            Age = age;
        }

        public void Greet()
        {
            Console.WriteLine($"你好，我是{Name}，今年{Age}岁");
        }
    }

    // 3. 静态成员
    class Student
    {
        public static string School = "北京大学";
        public static int StudentCount { get; private set; }

        public string Name { get; set; }
        public string StudentId { get; set; }

        public Student(string name, string studentId)
        {
            Name = name;
            StudentId = studentId;
            StudentCount++;
        }
    }

    // 4. 封装
    class BankAccount
    {
        private decimal _balance;

        public decimal Balance
        {
            get { return _balance; }
        }

        public BankAccount(decimal balance = 0)
        {
            _balance = balance;
        }

        public void Deposit(decimal amount)
        {
            if (amount > 0)
            {
                _balance += amount;
                Console.WriteLine($"存入 {amount} 元");
            }
        }

        public void Withdraw(decimal amount)
        {
            if (amount > 0 && amount <= _balance)
            {
                _balance -= amount;
                Console.WriteLine($"取出 {amount} 元");
            }
            else
            {
                Console.WriteLine("取款金额无效");
            }
        }
    }

    // 5. 构造函数重载
    class Rectangle
    {
        public double Width { get; set; }
        public double Height { get; set; }

        public Rectangle() : this(1, 1) { }

        public Rectangle(double width, double height)
        {
            Width = width;
            Height = height;
        }

        public double GetArea()
        {
            return Width * Height;
        }
    }

    // 6. 继承和多态
    class Animal
    {
        public string Name { get; set; }

        public Animal(string name)
        {
            Name = name;
        }

        public virtual void Speak()
        {
            Console.WriteLine($"{Name} 发出声音");
        }
    }

    class Dog : Animal
    {
        public Dog(string name) : base(name) { }

        public override void Speak()
        {
            Console.WriteLine($"{Name} 汪汪叫");
        }

        public void Fetch()
        {
            Console.WriteLine($"{Name} 去捡球了");
        }
    }

    class Cat : Animal
    {
        public Cat(string name) : base(name) { }

        public override void Speak()
        {
            Console.WriteLine($"{Name} 喵喵叫");
        }
    }

    // 7. base 调用
    class Employee
    {
        public string Name { get; set; }
        public decimal Salary { get; set; }

        public Employee(string name, decimal salary)
        {
            Name = name;
            Salary = salary;
        }

        public virtual void Work()
        {
            Console.WriteLine($"{Name} 在工作");
        }
    }

    class Manager : Employee
    {
        public string Department { get; set; }

        public Manager(string name, decimal salary, string department)
            : base(name, salary)
        {
            Department = department;
        }

        public override void Work()
        {
            base.Work();
            Console.WriteLine($"{Name} 管理 {Department} 部门");
        }
    }

    // 9. 抽象类
    abstract class Shape
    {
        public abstract double Area();
        public abstract double Perimeter();
    }

    class RectangleShape : Shape
    {
        public double Width { get; set; }
        public double Height { get; set; }

        public RectangleShape(double width, double height)
        {
            Width = width;
            Height = height;
        }

        public override double Area() => Width * Height;
        public override double Perimeter() => 2 * (Width + Height);
    }

    class Circle : Shape
    {
        public double Radius { get; set; }

        public Circle(double radius)
        {
            Radius = radius;
        }

        public override double Area() => Math.PI * Radius * Radius;
        public override double Perimeter() => 2 * Math.PI * Radius;
    }

    // 10. 接口
    interface IMovable
    {
        void Move();
    }

    class Car : IMovable
    {
        public void Move()
        {
            Console.WriteLine("汽车在公路上行驶");
        }
    }

    class Plane : IMovable
    {
        public void Move()
        {
            Console.WriteLine("飞机在天空中飞行");
        }
    }

    // 11. 结构体
    struct Point
    {
        public int X { get; set; }
        public int Y { get; set; }

        public Point(int x, int y)
        {
            X = x;
            Y = y;
        }

        public Point Add(Point other)
        {
            return new Point(X + other.X, Y + other.Y);
        }

        public override string ToString()
        {
            return $"({X}, {Y})";
        }
    }

    // 12. 枚举
    enum Color
    {
        Red = 1,
        Green = 2,
        Blue = 3
    }
}
