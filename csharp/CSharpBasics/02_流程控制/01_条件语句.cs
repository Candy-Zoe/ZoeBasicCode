// ============================================================
// C# 流程控制 - 条件语句
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;

namespace CSharpBasics
{
    class ConditionStatements
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 基本 if 语句 ===");
            int age = 18;
            if (age >= 18)
            {
                Console.WriteLine("你已经成年了");
            }

            Console.WriteLine("\n=== 2. if-else 语句 ===");
            int score = 85;
            if (score >= 60)
            {
                Console.WriteLine("及格了");
            }
            else
            {
                Console.WriteLine("不及格");
            }

            Console.WriteLine("\n=== 3. if-else if-else 语句 ===");
            score = 85;
            if (score >= 90)
            {
                Console.WriteLine("优秀");
            }
            else if (score >= 80)
            {
                Console.WriteLine("良好");
            }
            else if (score >= 60)
            {
                Console.WriteLine("及格");
            }
            else
            {
                Console.WriteLine("不及格");
            }

            Console.WriteLine("\n=== 4. 嵌套 if 语句 ===");
            age = 20;
            bool hasLicense = true;
            if (age >= 18)
            {
                if (hasLicense)
                {
                    Console.WriteLine("你可以开车");
                }
                else
                {
                    Console.WriteLine("你需要先考驾照");
                }
            }
            else
            {
                Console.WriteLine("你还未成年，不能开车");
            }

            Console.WriteLine("\n=== 5. 多条件组合 ===");
            age = 25;
            int salary = 8000;
            if (age >= 22 && salary >= 5000)
            {
                Console.WriteLine("符合贷款条件");
            }
            else
            {
                Console.WriteLine("不符合贷款条件");
            }

            Console.WriteLine("\n=== 6. switch 语句 ===");
            int day = 3;
            switch (day)
            {
                case 1:
                    Console.WriteLine("星期一");
                    break;
                case 2:
                    Console.WriteLine("星期二");
                    break;
                case 3:
                    Console.WriteLine("星期三");
                    break;
                case 4:
                    Console.WriteLine("星期四");
                    break;
                case 5:
                    Console.WriteLine("星期五");
                    break;
                case 6:
                case 7:
                    Console.WriteLine("周末");
                    break;
                default:
                    Console.WriteLine("无效的日期");
                    break;
            }

            Console.WriteLine("\n=== 7. switch 表达式 (C# 8.0+) ===");
            string dayName = day switch
            {
                1 => "星期一",
                2 => "星期二",
                3 => "星期三",
                4 => "星期四",
                5 => "星期五",
                6 or 7 => "周末",
                _ => "无效的日期"
            };
            Console.WriteLine($"第{day}天是: {dayName}");

            Console.WriteLine("\n=== 8. switch 模式匹配 ===");
            object obj = 42;
            string typeDescription = obj switch
            {
                int i when i > 0 => "正整数",
                int i when i < 0 => "负整数",
                int _ => "零",
                string s => $"字符串: {s}",
                null => "空值",
                _ => "其他类型"
            };
            Console.WriteLine($"obj 的类型描述: {typeDescription}");

            Console.WriteLine("\n=== 9. 三元表达式 ===");
            int a = 10, b = 20;
            int maxVal = a > b ? a : b;
            Console.WriteLine($"{a} 和 {b} 中较大的是: {maxVal}");

            Console.WriteLine("\n=== 10. 使用示例：成绩等级判定 ===");
            static string GetGrade(int score)
            {
                return score switch
                {
                    < 0 or > 100 => "无效成绩",
                    >= 90 => "A",
                    >= 80 => "B",
                    >= 70 => "C",
                    >= 60 => "D",
                    _ => "F"
                };
            }

            int[] scores = { 95, 82, 73, 61, 45, 100, -5, 105 };
            foreach (int s in scores)
            {
                Console.WriteLine($"成绩 {s}: 等级 {GetGrade(s)}");
            }
        }
    }
}
