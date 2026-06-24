// ============================================================
// C# 流程控制 - 循环语句
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;
using System.Collections.Generic;

namespace CSharpBasics
{
    class LoopStatements
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. for 循环 ===");
            for (int i = 0; i < 5; i++)
            {
                Console.WriteLine($"第 {i + 1} 次循环");
            }

            Console.WriteLine("\n=== 2. for 循环 - 步长 ===");
            Console.Write("1到9的奇数: ");
            for (int i = 1; i < 10; i += 2)
            {
                Console.Write($"{i} ");
            }
            Console.WriteLine();

            Console.WriteLine("\n=== 3. foreach 循环（遍历集合） ===");
            string[] fruits = { "苹果", "香蕉", "橙子", "葡萄" };
            foreach (string fruit in fruits)
            {
                Console.WriteLine($"水果: {fruit}");
            }

            Console.WriteLine("\n=== 4. while 循环 ===");
            int count = 1;
            while (count <= 5)
            {
                Console.WriteLine($"第 {count} 次循环");
                count++;
            }

            Console.WriteLine("\n=== 5. do-while 循环 ===");
            int num = 1;
            do
            {
                Console.WriteLine($"num = {num}");
                num++;
            } while (num <= 5);

            Console.WriteLine("\n=== 6. break 语句（跳出循环） ===");
            for (int i = 0; i < 10; i++)
            {
                if (i == 5)
                {
                    break;
                }
                Console.Write($"{i} ");
            }
            Console.WriteLine("\n遇到 break，循环结束");

            Console.WriteLine("\n=== 7. continue 语句（跳过当前迭代） ===");
            for (int i = 0; i < 10; i++)
            {
                if (i % 2 == 0)
                {
                    continue;
                }
                Console.Write($"{i} ");
            }
            Console.WriteLine("\n只打印了奇数");

            Console.WriteLine("\n=== 8. goto 语句（不推荐） ===");
            int x = 0;
        start:
            Console.WriteLine($"x = {x}");
            x++;
            if (x < 3)
            {
                goto start;
            }

            Console.WriteLine("\n=== 9. 嵌套循环 ===");
            Console.WriteLine("九九乘法表:");
            for (int i = 1; i < 10; i++)
            {
                for (int j = 1; j <= i; j++)
                {
                    Console.Write($"{j}x{i}={i * j}\t");
                }
                Console.WriteLine();
            }

            Console.WriteLine("\n=== 10. 使用示例：累加求和 ===");
            int total = 0;
            for (int i = 1; i <= 100; i++)
            {
                total += i;
            }
            Console.WriteLine($"1到100的和: {total}");

            Console.WriteLine("\n=== 11. 使用示例：查找质数 ===");
            static bool IsPrime(int n)
            {
                if (n < 2) return false;
                for (int i = 2; i * i <= n; i++)
                {
                    if (n % i == 0) return false;
                }
                return true;
            }

            Console.WriteLine("1到50之间的质数:");
            for (int num2 = 1; num2 <= 50; num2++)
            {
                if (IsPrime(num2))
                {
                    Console.Write($"{num2} ");
                }
            }
            Console.WriteLine();

            Console.WriteLine("\n=== 12. 带索引的遍历 ===");
            var fruitsList = new List<string> { "苹果", "香蕉", "橙子" };
            foreach (var (fruit, index) in fruitsList.Select((f, i) => (f, i)))
            {
                Console.WriteLine($"索引 {index}: {fruit}");
            }
        }
    }
}
