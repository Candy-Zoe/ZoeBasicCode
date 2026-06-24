// ============================================================
// C# 函数 - 基础
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;

namespace CSharpBasics
{
    class Functions
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 函数的定义和调用 ===");
            Greet();

            Console.WriteLine("\n=== 2. 带参数的函数 ===");
            GreetUser("张三");
            GreetUser("李四");

            Console.WriteLine("\n=== 3. 带返回值的函数 ===");
            int result = Add(3, 5);
            Console.WriteLine($"3 + 5 = {result}");

            Console.WriteLine("\n=== 4. 带默认参数的函数 ===");
            Console.WriteLine($"3的平方: {Power(3)}");
            Console.WriteLine($"3的立方: {Power(3, 3)}");

            Console.WriteLine("\n=== 5. 命名参数 ===");
            PrintInfo(name: "张三", age: 25, city: "北京");
            PrintInfo(age: 30, city: "上海", name: "李四"); // 顺序可以任意

            Console.WriteLine("\n=== 6. 可变参数 params ===");
            Console.WriteLine($"SumAll(1, 2, 3) = {SumAll(1, 2, 3)}");
            Console.WriteLine($"SumAll(1, 2, 3, 4, 5) = {SumAll(1, 2, 3, 4, 5)}");

            Console.WriteLine("\n=== 7. out 参数（多个返回值） ===");
            int sum, product;
            Calculate(10, 3, out sum, out product);
            Console.WriteLine($"10 和 3: 和 = {sum}, 积 = {product}");

            Console.WriteLine("\n=== 8. ref 参数（按引用传递） ===");
            int num = 10;
            Console.WriteLine($"调用前 num = {num}");
            Increment(ref num);
            Console.WriteLine($"调用后 num = {num}");

            Console.WriteLine("\n=== 9. 元组返回多个值 (C# 7.0+) ===");
            (int addResult, int subResult, int mulResult, double divResult) = CalculateTuple(10, 3);
            Console.WriteLine($"10 和 3 的计算结果:");
            Console.WriteLine($"  和: {addResult}");
            Console.WriteLine($"  差: {subResult}");
            Console.WriteLine($"  积: {mulResult}");
            Console.WriteLine($"  商: {divResult}");

            Console.WriteLine("\n=== 10. 局部函数 ===");
            int Factorial(int n)
            {
                if (n == 0 || n == 1) return 1;
                return n * Factorial(n - 1);
            }
            Console.WriteLine($"5! = {Factorial(5)}");

            Console.WriteLine("\n=== 11. 表达式体函数 ===");
            static int Square(int x) => x * x;
            Console.WriteLine($"5的平方: {Square(5)}");

            Console.WriteLine("\n=== 12. Lambda 表达式 ===");
            Func<int, int> square = x => x * x;
            Console.WriteLine($"5的平方（lambda）: {square(5)}");

            Func<int, int, int> addLambda = (a, b) => a + b;
            Console.WriteLine($"3+4（lambda）: {addLambda(3, 4)}");

            Console.WriteLine("\n=== 13. Action 委托（无返回值） ===");
            Action<string> printMessage = msg => Console.WriteLine($"消息: {msg}");
            printMessage("Hello C#");

            Console.WriteLine("\n=== 14. 函数重载 ===");
            Console.WriteLine($"Add(3, 5) = {Add(3, 5)}");
            Console.WriteLine($"Add(3.5, 2.5) = {Add(3.5, 2.5)}");
            Console.WriteLine($"Add(\"Hello\", \" World\") = {Add("Hello", " World")}");
        }

        // 无参数无返回值的函数
        static void Greet()
        {
            Console.WriteLine("Hello, World!");
        }

        // 带参数的函数
        static void GreetUser(string name)
        {
            Console.WriteLine($"你好，{name}！");
        }

        // 带返回值的函数
        static int Add(int a, int b)
        {
            return a + b;
        }

        // 带默认参数的函数
        static int Power(int baseNum, int exponent = 2)
        {
            int result = 1;
            for (int i = 0; i < exponent; i++)
            {
                result *= baseNum;
            }
            return result;
        }

        // 打印个人信息
        static void PrintInfo(string name, int age, string city)
        {
            Console.WriteLine($"姓名: {name}, 年龄: {age}, 城市: {city}");
        }

        // 可变参数
        static int SumAll(params int[] numbers)
        {
            int total = 0;
            foreach (int num in numbers)
            {
                total += num;
            }
            return total;
        }

        // out 参数
        static void Calculate(int a, int b, out int sum, out int product)
        {
            sum = a + b;
            product = a * b;
        }

        // ref 参数
        static void Increment(ref int x)
        {
            x++;
        }

        // 元组返回多个值
        static (int, int, int, double) CalculateTuple(int a, int b)
        {
            return (a + b, a - b, a * b, (double)a / b);
        }

        // 函数重载：参数类型不同
        static double Add(double a, double b)
        {
            return a + b;
        }

        // 函数重载：参数类型不同
        static string Add(string a, string b)
        {
            return a + b;
        }
    }
}
