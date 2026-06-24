// ============================================================
// C# 泛型深入
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;
using System.Collections.Generic;
using System.Linq;

namespace CSharpBasics
{
    class GenericsAdvanced
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 泛型类 ===");
            DemoGenericClass();

            Console.WriteLine("\n=== 2. 泛型方法 ===");
            DemoGenericMethod();

            Console.WriteLine("\n=== 3. 泛型约束 ===");
            DemoConstraint();

            Console.WriteLine("\n=== 4. 协变与逆变 ===");
            DemoVariance();

            Console.WriteLine("\n=== 5. 泛型集合 ===");
            DemoGenericCollection();

            Console.WriteLine("\n=== 6. 自定义泛型栈 ===");
            DemoCustomStack();

            Console.WriteLine("\n=== 7. 泛型单例 ===");
            DemoSingleton();

            Console.WriteLine("\n运行完成");
        }

        // ============================================================
        // 1. 泛型类
        // ============================================================
        static void DemoGenericClass()
        {
            var intBox = new Box<int>(100);
            Console.WriteLine($"intBox: {intBox.Content}");

            var strBox = new Box<string>("Hello");
            Console.WriteLine($"strBox: {strBox.Content}");

            var pair = new Pair<string, double>("圆周率", 3.14159);
            Console.WriteLine($"pair: {pair.Key} = {pair.Value}");
        }

        class Box<T>
        {
            public T Content { get; set; }

            public Box(T content)
            {
                Content = content;
            }

            public void Swap<U>(Box<U> other) where U : class
            {
                // 演示泛型方法
            }
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
            int[] ints = { 5, 2, 8, 1, 9, 3 };
            string[] strs = { "Banana", "Apple", "Cherry" };

            Console.Write("整数: ");
            PrintArray(ints);
            Console.Write("字符串: ");
            PrintArray(strs);

            Console.WriteLine($"Max(10, 20) = {Max(10, 20)}");
            Console.WriteLine($"Max(3.14, 2.71) = {Max(3.14, 2.71)}");

            // 多个泛型参数
            var result = Combine("数字: ", 42);
            Console.WriteLine($"Combine 结果: {result}");
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

        static TResult Combine<T1, T2, TResult>(T1 a, T2 b)
        {
            return (TResult)(object)(a.ToString() + b.ToString());
        }

        // ============================================================
        // 3. 泛型约束
        // ============================================================
        static void DemoConstraint()
        {
            var intCalc = new Calculator<int>();
            Console.WriteLine($"int Calc: 5 + 3 = {intCalc.Add(5, 3)}");

            var dblCalc = new Calculator<double>();
            Console.WriteLine($"double Calc: 1.5 + 2.5 = {dblCalc.Add(1.5, 2.5)}");

            var factory = new Factory<Person2>();
            var person = factory.Create("张三");
            Console.WriteLine($"创建的人: {person.Name}");

            // where T : class
            var nullable = new NullableChecker<int>();
            Console.WriteLine($"int 默认值: {nullable.GetValueOrDefault()}");
        }

        // where T : struct - 必须是值类型
        class Calculator<T> where T : struct
        {
            public T Add(T a, T b)
            {
                dynamic x = a, y = b;
                return x + y;
            }
        }

        // where T : new() - 必须有无参构造
        class Person2
        {
            public string Name { get; set; }
            public Person2() { Name = "未命名"; }
        }

        class Factory<T> where T : new()
        {
            public T Create()
            {
                return new T();
            }

            public T Create(string name)
            {
                T obj = new T();
                dynamic d = obj;
                d.Name = name;
                return d;
            }
        }

        // where T : class - 必须是引用类型
        class NullableChecker<T> where T : class
        {
            private T value;
            public T GetValueOrDefault(T defaultValue = null) => value ?? defaultValue;
        }

        // ============================================================
        // 4. 协变与逆变
        // ============================================================
        static void DemoVariance()
        {
            // 协变: out 关键字 (IEnumerable<string> -> IEnumerable<object>)
            IEnumerable<string> strings = new[] { "A", "B", "C" };
            IEnumerable<object> objects = strings;  // 隐式转换
            Console.WriteLine("协变 IEnumerable<string> -> IEnumerable<object>:");
            foreach (var obj in objects) Console.WriteLine($"  {obj}");

            // Action 委托 - 逆变 (in)
            Action<object> actionObj = obj => Console.WriteLine($"处理: {obj}");
            Action<string> actionStr = actionObj;  // 隐式转换
            actionStr("Hello");

            // Func 委托 - 协变 (out)
            Func<string> funcStr = () => "Hello";
            Func<object> funcObj = funcStr;
            Console.WriteLine($"Func<string> -> Func<object>: {funcObj()}");
        }

        // 自定义协变接口
        interface IProducer<out T>
        {
            T Produce();
        }

        interface IConsumer<in T>
        {
            void Consume(T item);
        }

        class Animal
        {
            public virtual string Name => "动物";
        }

        class Dog : Animal
        {
            public override string Name => "狗";
        }

        class DogProducer : IProducer<Dog>
        {
            public Dog Produce() => new Dog();
        }

        // 协变允许 IProducer<Dog> 当作 IProducer<Animal> 使用
        static void UseProducer(IProducer<Animal> producer)
        {
            Animal animal = producer.Produce();
            Console.WriteLine($"生产: {animal.Name}");
        }

        // ============================================================
        // 5. 泛型集合
        // ============================================================
        static void DemoGenericCollection()
        {
            // List<T>
            List<string> names = new List<string> { "张三", "李四", "王五" };
            names.Add("赵六");
            Console.WriteLine($"List 数量: {names.Count}");

            // Dictionary<TKey, TValue>
            Dictionary<string, int> scores = new Dictionary<string, int>
            {
                {"张三", 95},
                {"李四", 88},
                {"王五", 92}
            };

            foreach (var pair in scores)
            {
                Console.WriteLine($"  {pair.Key}: {pair.Value}");
            }

            // HashSet<T>
            HashSet<int> set = new HashSet<int> { 1, 2, 3, 3, 4, 4, 5 };
            Console.WriteLine($"HashSet 元素(去重): [{string.Join(", ", set)}]");

            // Queue<T>
            Queue<string> queue = new Queue<string>();
            queue.Enqueue("A");
            queue.Enqueue("B");
            queue.Enqueue("C");
            Console.WriteLine($"Queue 出队: {queue.Dequeue()}");

            // Stack<T>
            Stack<string> stack = new Stack<string>();
            stack.Push("A");
            stack.Push("B");
            stack.Push("C");
            Console.WriteLine($"Stack 弹栈: {stack.Pop()}");
        }

        // ============================================================
        // 6. 自定义泛型栈
        // ============================================================
        static void DemoCustomStack()
        {
            var intStack = new MyStack<int>(3);
            intStack.Push(10);
            intStack.Push(20);
            intStack.Push(30);

            Console.Write("自定义整数栈: ");
            while (!intStack.IsEmpty) Console.Write($"{intStack.Pop()} ");
            Console.WriteLine();

            var strStack = new MyStack<string>(3);
            strStack.Push("A");
            strStack.Push("B");
            strStack.Push("C");

            Console.Write("自定义字符串栈: ");
            while (!strStack.IsEmpty) Console.Write($"{strStack.Pop()} ");
            Console.WriteLine();
        }

        class MyStack<T>
        {
            private T[] array;
            private int top;
            private int capacity;

            public MyStack(int size)
            {
                capacity = size;
                array = new T[size];
                top = -1;
            }

            public bool IsEmpty => top == -1;
            public bool IsFull => top == capacity - 1;

            public void Push(T item)
            {
                if (IsFull)
                {
                    Console.WriteLine("栈满");
                    return;
                }
                array[++top] = item;
            }

            public T Pop()
            {
                if (IsEmpty)
                {
                    throw new InvalidOperationException("栈空");
                }
                return array[top--];
            }

            public T Peek()
            {
                if (IsEmpty) throw new InvalidOperationException("栈空");
                return array[top];
            }
        }

        // ============================================================
        // 7. 泛型单例
        // ============================================================
        static void DemoSingleton()
        {
            var s1 = Singleton<int>.Instance;
            s1.Value = 42;

            var s2 = Singleton<int>.Instance;
            Console.WriteLine($"s1.Value: {s1.Value}");
            Console.WriteLine($"s2.Value: {s2.Value}");
            Console.WriteLine($"s1 == s2: {ReferenceEquals(s1, s2)}");

            Singleton<string>.Instance.Value = "Hello";
            Console.WriteLine($"字符串单例: {Singleton<string>.Instance.Value}");
        }

        class Singleton<T> where T : new()
        {
            private static T instance;
            public static T Instance
            {
                get
                {
                    if (instance == null)
                    {
                        instance = new T();
                    }
                    return instance;
                }
            }

            // 用于演示
            public T Value { get; set; }
        }
    }
}