// ============================================================
// C# 高级特性 - 泛型、委托、事件、LINQ
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;
using System.Collections.Generic;
using System.Linq;

namespace CSharpBasics
{
    class AdvancedCSharp
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 泛型类 ===");
            DemoGenericClass();

            Console.WriteLine("\n=== 2. 泛型方法 ===");
            DemoGenericMethod();

            Console.WriteLine("\n=== 3. 泛型约束 ===");
            DemoGenericConstraint();

            Console.WriteLine("\n=== 4. 委托 ===");
            DemoDelegate();

            Console.WriteLine("\n=== 5. 事件 ===");
            DemoEvent();

            Console.WriteLine("\n=== 6. LINQ 查询 ===");
            DemoLinq();

            Console.WriteLine("\n=== 7. 特性 (Attribute) ===");
            DemoAttribute();

            Console.WriteLine("\n=== 8. 索引器 ===");
            DemoIndexer();

            Console.WriteLine("\n=== 9. 运算符重载 ===");
            DemoOperatorOverload();

            Console.WriteLine("\n=== 10. 分部类 ===");
            DemoPartialClass();
        }

        // ============================================================
        // 1. 泛型类
        // ============================================================
        static void DemoGenericClass()
        {
            var intBox = new Box<int>();
            intBox.Content = 100;
            Console.WriteLine($"intBox.Content = {intBox.Content}");

            var strBox = new Box<string>();
            strBox.Content = "Hello";
            Console.WriteLine($"strBox.Content = {strBox.Content}");

            var pair = new Pair<string, int>("张三", 25);
            Console.WriteLine($"pair: {pair.Key} -> {pair.Value}");
        }

        class Box<T>
        {
            public T Content { get; set; }
        }

        class Pair<TKey, TValue>
        {
            public TKey Key { get; set; }
            public TValue Value { get; set; }

            public Pair(TKey key, TValue value)
            {
                Key = key;
                Value = value;
            }
        }

        // ============================================================
        // 2. 泛型方法
        // ============================================================
        static void DemoGenericMethod()
        {
            int[] ints = { 1, 2, 3, 4, 5 };
            string[] strs = { "A", "B", "C" };

            Console.Write("整数数组: ");
            PrintArray(ints);
            Console.Write("字符串数组: ");
            PrintArray(strs);

            Console.WriteLine($"Max(10, 20) = {Max(10, 20)}");
            Console.WriteLine($"Max('a', 'z') = {Max('a', 'z')}");

            int index = FindIndex(ints, 3);
            Console.WriteLine($"3 在数组中的索引: {index}");
        }

        static void PrintArray<T>(T[] array)
        {
            foreach (T item in array)
            {
                Console.Write(item + " ");
            }
            Console.WriteLine();
        }

        static T Max<T>(T a, T b) where T : IComparable<T>
        {
            return a.CompareTo(b) > 0 ? a : b;
        }

        static int FindIndex<T>(T[] array, T target)
        {
            for (int i = 0; i < array.Length; i++)
            {
                if (EqualityComparer<T>.Default.Equals(array[i], target))
                    return i;
            }
            return -1;
        }

        // ============================================================
        // 3. 泛型约束
        // ============================================================
        static void DemoGenericConstraint()
        {
            var calculator = new Calculator<int>();
            Console.WriteLine($"10 + 20 = {calculator.Add(10, 20)}");

            var factory = new Factory<Person>();
            var person = factory.Create("张三");
            Console.WriteLine($"创建的人: {person.Name}");
        }

        class Calculator<T> where T : struct
        {
            public T Add(T a, T b)
            {
                dynamic x = a, y = b;
                return x + y;
            }
        }

        class Person
        {
            public string Name { get; set; }
            public Person() { }
            public Person(string name) { Name = name; }
        }

        class Factory<T> where T : new()
        {
            public T Create()
            {
                return new T();
            }

            public T Create(string name)
            {
                dynamic obj = new T();
                obj.Name = name;
                return obj;
            }
        }

        // ============================================================
        // 4. 委托
        // ============================================================
        static void DemoDelegate()
        {
            // 自定义委托
            MathOp add = Add;
            MathOp sub = Subtract;
            Console.WriteLine($"10 + 5 = {add(10, 5)}");
            Console.WriteLine($"10 - 5 = {sub(10, 5)}");

            // 多播委托
            MathOp multi = add;
            multi += sub;
            Console.WriteLine("多播委托:");
            multi(10, 5);  // 会依次调用 Add 和 Subtract

            // Action 和 Func
            Action<string> print = s => Console.WriteLine(s);
            print("Hello from Action");

            Func<int, int, int> multiply = (a, b) => a * b;
            Console.WriteLine($"3 * 4 = {multiply(3, 4)}");

            // Predicate
            Predicate<int> isEven = n => n % 2 == 0;
            Console.WriteLine($"4 是偶数吗? {isEven(4)}");
        }

        delegate int MathOp(int a, int b);

        static int Add(int a, int b)
        {
            int result = a + b;
            Console.WriteLine($"Add: {a} + {b} = {result}");
            return result;
        }

        static int Subtract(int a, int b)
        {
            int result = a - b;
            Console.WriteLine($"Subtract: {a} - {b} = {result}");
            return result;
        }

        // ============================================================
        // 5. 事件
        // ============================================================
        static void DemoEvent()
        {
            var button = new Button();
            button.Click += OnButtonClick;
            button.Click += (sender, e) => Console.WriteLine("Lambda 也收到了点击事件");

            button.ClickMe();

            button.Click -= OnButtonClick;
            button.ClickMe();  // 只会触发 Lambda
        }

        static void OnButtonClick(object sender, EventArgs e)
        {
            Console.WriteLine("按钮被点击了！");
        }

        class Button
        {
            public event EventHandler Click;

            public void ClickMe()
            {
                Console.WriteLine("按钮被按下...");
                OnClick(EventArgs.Empty);
            }

            protected virtual void OnClick(EventArgs e)
            {
                Click?.Invoke(this, e);
            }
        }

        // ============================================================
        // 6. LINQ
        // ============================================================
        static void DemoLinq()
        {
            int[] numbers = { 5, 2, 8, 1, 9, 3, 7, 4, 6 };

            // 查询语法
            var evens = from n in numbers
                        where n % 2 == 0
                        orderby n
                        select n;

            Console.Write("偶数(排序): ");
            foreach (var n in evens) Console.Write(n + " ");
            Console.WriteLine();

            // 方法语法
            var squares = numbers.Where(n => n > 5)
                                 .Select(n => n * n)
                                 .OrderByDescending(n => n);

            Console.Write("大于5的数的平方(降序): ");
            foreach (var n in squares) Console.Write(n + " ");
            Console.WriteLine();

            // 常用操作
            Console.WriteLine($"总和: {numbers.Sum()}");
            Console.WriteLine($"最大值: {numbers.Max()}");
            Console.WriteLine($"最小值: {numbers.Min()}");
            Console.WriteLine($"平均值: {numbers.Average():F2}");
            Console.WriteLine($"所有数都大于0? {numbers.All(n => n > 0)}");
            Console.WriteLine($"有大于8的数吗? {numbers.Any(n => n > 8)}");
            Console.WriteLine($"第一个大于5的数: {numbers.First(n => n > 5)}");

            // 字符串数组操作
            string[] names = { "Tom", "Jerry", "Alice", "Bob", "Charlie" };

            var nameGroups = names.GroupBy(n => n.Length);
            Console.WriteLine("\n按名字长度分组:");
            foreach (var group in nameGroups)
            {
                Console.WriteLine($"  长度 {group.Key}: {string.Join(", ", group)}");
            }
        }

        // ============================================================
        // 7. 特性 (Attribute)
        // ============================================================
        static void DemoAttribute()
        {
            var type = typeof(StudentInfo);
            var attrs = type.GetCustomAttributes(false);

            foreach (var attr in attrs)
            {
                if (attr is DescriptionAttribute descAttr)
                {
                    Console.WriteLine($"类描述: {descAttr.Description}");
                }
            }

            var prop = typeof(StudentInfo).GetProperty("Name");
            var propAttrs = prop.GetCustomAttributes(false);
            foreach (var attr in propAttrs)
            {
                if (attr is RequiredAttribute reqAttr)
                {
                    Console.WriteLine($"Name 属性 {reqAttr.ErrorMessage}");
                }
            }
        }

        [AttributeUsage(AttributeTargets.Class | AttributeTargets.Property)]
        class DescriptionAttribute : Attribute
        {
            public string Description { get; }
            public DescriptionAttribute(string description)
            {
                Description = description;
            }
        }

        [AttributeUsage(AttributeTargets.Property)]
        class RequiredAttribute : Attribute
        {
            public string ErrorMessage { get; set; } = "是必填字段";
        }

        [Description("学生信息类")]
        class StudentInfo
        {
            [Required(ErrorMessage = "姓名不能为空")]
            public string Name { get; set; }
            public int Age { get; set; }
        }

        // ============================================================
        // 8. 索引器
        // ============================================================
        static void DemoIndexer()
        {
            var list = new StringList();
            list[0] = "苹果";
            list[1] = "香蕉";
            list[2] = "橙子";

            Console.WriteLine($"list[0] = {list[0]}");
            Console.WriteLine($"list[1] = {list[1]}");
            Console.WriteLine($"list[2] = {list[2]}");
            Console.WriteLine($"长度: {list.Length}");
        }

        class StringList
        {
            private string[] _data = new string[100];
            private int _count = 0;

            public string this[int index]
            {
                get { return _data[index]; }
                set
                {
                    _data[index] = value;
                    if (index >= _count) _count = index + 1;
                }
            }

            public int Length => _count;
        }

        // ============================================================
        // 9. 运算符重载
        // ============================================================
        static void DemoOperatorOverload()
        {
            var p1 = new Point2D(1, 2);
            var p2 = new Point2D(3, 4);

            var p3 = p1 + p2;
            var p4 = p1 - p2;

            Console.WriteLine($"p1 = {p1}");
            Console.WriteLine($"p2 = {p2}");
            Console.WriteLine($"p1 + p2 = {p3}");
            Console.WriteLine($"p1 - p2 = {p4}");
            Console.WriteLine($"p1 == p2: {p1 == p2}");
            Console.WriteLine($"p1 != p2: {p1 != p2}");
        }

        class Point2D
        {
            public int X { get; set; }
            public int Y { get; set; }

            public Point2D(int x, int y) { X = x; Y = y; }

            public static Point2D operator +(Point2D a, Point2D b)
            {
                return new Point2D(a.X + b.X, a.Y + b.Y);
            }

            public static Point2D operator -(Point2D a, Point2D b)
            {
                return new Point2D(a.X - b.X, a.Y - b.Y);
            }

            public static bool operator ==(Point2D a, Point2D b)
            {
                return a.X == b.X && a.Y == b.Y;
            }

            public static bool operator !=(Point2D a, Point2D b)
            {
                return !(a == b);
            }

            public override string ToString()
            {
                return $"({X}, {Y})";
            }

            public override bool Equals(object obj)
            {
                if (obj is Point2D other)
                    return X == other.X && Y == other.Y;
                return false;
            }

            public override int GetHashCode()
            {
                return HashCode.Combine(X, Y);
            }
        }

        // ============================================================
        // 10. 分部类
        // ============================================================
        static void DemoPartialClass()
        {
            var user = new User
            {
                FirstName = "张",
                LastName = "三",
                Email = "zhangsan@example.com"
            };

            Console.WriteLine($"全名: {user.FullName}");
            user.PrintInfo();
        }

        partial class User
        {
            public string FirstName { get; set; }
            public string LastName { get; set; }
            public string FullName => $"{FirstName}{LastName}";
        }

        partial class User
        {
            public string Email { get; set; }

            public void PrintInfo()
            {
                Console.WriteLine($"用户: {FullName}, 邮箱: {Email}");
            }
        }
    }
}