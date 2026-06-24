// ============================================================
// C# 基础语法 - 变量和数据类型
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;

namespace CSharpBasics
{
    class VariablesAndTypes
    {
        static void Main(string[] args)
        {
            // 1. 变量的定义
            // C# 是静态类型语言，需要声明变量类型
            string name = "张三";      // 字符串类型
            int age = 25;              // 整数类型
            double height = 1.75;      // 双精度浮点数
            bool isStudent = true;     // 布尔类型

            Console.WriteLine("=== 基本变量 ===");
            Console.WriteLine($"姓名: {name}");
            Console.WriteLine($"年龄: {age}");
            Console.WriteLine($"身高: {height}");
            Console.WriteLine($"是否学生: {isStudent}");

            // 2. 查看变量类型
            Console.WriteLine("\n=== 变量类型 ===");
            Console.WriteLine($"name 的类型: {name.GetType()}");
            Console.WriteLine($"age 的类型: {age.GetType()}");
            Console.WriteLine($"height 的类型: {height.GetType()}");
            Console.WriteLine($"isStudent 的类型: {isStudent.GetType()}");

            // 3. 整数类型
            Console.WriteLine("\n=== 整数类型 ===");
            byte b = 255;                 // 字节：0~255
            sbyte sb = -128;              // 有符号字节：-128~127
            short s = 32767;              // 短整型
            ushort us = 65535;            // 无符号短整型
            int i = 2147483647;           // 整型（最常用）
            uint ui = 4294967295;         // 无符号整型
            long l = 9223372036854775807; // 长整型
            ulong ul = 18446744073709551615; // 无符号长整型

            Console.WriteLine($"byte: {byte.MinValue} ~ {byte.MaxValue}");
            Console.WriteLine($"int: {int.MinValue} ~ {int.MaxValue}");
            Console.WriteLine($"long: {long.MinValue} ~ {long.MaxValue}");

            // 4. 浮点类型
            Console.WriteLine("\n=== 浮点类型 ===");
            float f = 3.14f;              // 单精度浮点数（后缀f）
            double d = 3.1415926;         // 双精度浮点数（最常用）
            decimal dec = 12345.6789m;    // 高精度十进制（后缀m，用于金融）

            Console.WriteLine($"float: {f}");
            Console.WriteLine($"double: {d}");
            Console.WriteLine($"decimal: {dec}");

            // 5. 字符类型
            Console.WriteLine("\n=== 字符类型 ===");
            char c = 'A';
            Console.WriteLine($"char: {c}");
            Console.WriteLine($"字符的ASCII值: {(int)c}");

            // 6. 类型推断 var
            Console.WriteLine("\n=== var 类型推断 ===");
            var x = 10;        // 推断为 int
            var y = "hello";   // 推断为 string
            var z = 3.14;      // 推断为 double
            Console.WriteLine($"x = {x}, 类型: {x.GetType()}");
            Console.WriteLine($"y = {y}, 类型: {y.GetType()}");

            // 7. 常量
            Console.WriteLine("\n=== 常量 ===");
            const double PI = 3.1415926;
            const int MAX_SIZE = 1024;
            Console.WriteLine($"PI = {PI}");
            Console.WriteLine($"MAX_SIZE = {MAX_SIZE}");

            // 8. 数据类型转换
            Console.WriteLine("\n=== 类型转换 ===");
            string numStr = "123";
            int numInt = int.Parse(numStr);
            Console.WriteLine($"字符串 \"{numStr}\" 转整数: {numInt}");

            double numDouble = Convert.ToDouble(numStr);
            Console.WriteLine($"字符串 \"{numStr}\" 转双精度: {numDouble}");

            string ageStr = age.ToString();
            Console.WriteLine($"整数 {age} 转字符串: \"{ageStr}\"");

            // 9. 可空类型
            Console.WriteLine("\n=== 可空类型 ===");
            int? nullableInt = null;
            Console.WriteLine($"nullableInt = {nullableInt}");
            nullableInt = 10;
            Console.WriteLine($"nullableInt = {nullableInt}");
            Console.WriteLine($"nullableInt.HasValue = {nullableInt.HasValue}");
            Console.WriteLine($"nullableInt.Value = {nullableInt.Value}");
        }
    }
}
