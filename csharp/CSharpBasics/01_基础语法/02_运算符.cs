// ============================================================
// C# 基础语法 - 运算符
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;

namespace CSharpBasics
{
    class Operators
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 算术运算符 ===");
            int a = 10, b = 3;
            Console.WriteLine($"a = {a}, b = {b}");
            Console.WriteLine($"加法: a + b = {a + b}");
            Console.WriteLine($"减法: a - b = {a - b}");
            Console.WriteLine($"乘法: a * b = {a * b}");
            Console.WriteLine($"除法: a / b = {a / b}");       // 整数除法
            Console.WriteLine($"取余: a % b = {a % b}");       // 取余数

            double x = 10.0, y = 3.0;
            Console.WriteLine($"浮点除法: x / y = {x / y}");

            Console.WriteLine("\n=== 2. 自增自减运算符 ===");
            int i = 0;
            Console.WriteLine($"i = {i}");
            i++; // 等价于 i = i + 1
            Console.WriteLine($"i++ 后: i = {i}");
            i--;
            Console.WriteLine($"i-- 后: i = {i}");

            // 前缀和后缀的区别
            int n = 5;
            Console.WriteLine($"n = {n}");
            Console.WriteLine($"n++ = {n++} (先返回值再加)");
            Console.WriteLine($"此时 n = {n}");
            Console.WriteLine($"++n = {++n} (先加再返回值)");

            Console.WriteLine("\n=== 3. 赋值运算符 ===");
            int num = 10;
            Console.WriteLine($"初始值 num = {num}");
            num += 5;
            Console.WriteLine($"num += 5 后: {num}");
            num -= 3;
            Console.WriteLine($"num -= 3 后: {num}");
            num *= 2;
            Console.WriteLine($"num *= 2 后: {num}");
            num /= 4;
            Console.WriteLine($"num /= 4 后: {num}");
            num %= 3;
            Console.WriteLine($"num %= 3 后: {num}");

            Console.WriteLine("\n=== 4. 比较运算符 ===");
            int m = 10, n2 = 20;
            Console.WriteLine($"m = {m}, n = {n2}");
            Console.WriteLine($"m == n: {m == n2}");
            Console.WriteLine($"m != n: {m != n2}");
            Console.WriteLine($"m > n: {m > n2}");
            Console.WriteLine($"m < n: {m < n2}");
            Console.WriteLine($"m >= n: {m >= n2}");
            Console.WriteLine($"m <= n: {m <= n2}");

            Console.WriteLine("\n=== 5. 逻辑运算符 ===");
            bool p = true, q = false;
            Console.WriteLine($"p = {p}, q = {q}");
            Console.WriteLine($"p && q: {p && q}");   // 逻辑与（短路）
            Console.WriteLine($"p || q: {p || q}");   // 逻辑或（短路）
            Console.WriteLine($"!p: {!p}");           // 逻辑非
            Console.WriteLine($"!q: {!q}");

            // 非短路版本
            Console.WriteLine($"\n非短路版本:");
            Console.WriteLine($"p & q: {p & q}");     // 逻辑与（不短路）
            Console.WriteLine($"p | q: {p | q}");     // 逻辑或（不短路）

            Console.WriteLine("\n=== 6. 条件运算符（三元） ===");
            int num1 = 10, num2 = 20;
            int max = num1 > num2 ? num1 : num2;
            Console.WriteLine($"{num1} 和 {num2} 中较大的是: {max}");

            Console.WriteLine("\n=== 7. 位运算符 ===");
            int bitA = 6;  // 二进制: 0110
            int bitB = 3;  // 二进制: 0011
            Console.WriteLine($"bitA = {bitA} (二进制: {Convert.ToString(bitA, 2)})");
            Console.WriteLine($"bitB = {bitB} (二进制: {Convert.ToString(bitB, 2)})");
            Console.WriteLine($"bitA & bitB = {bitA & bitB} (按位与)");
            Console.WriteLine($"bitA | bitB = {bitA | bitB} (按位或)");
            Console.WriteLine($"bitA ^ bitB = {bitA ^ bitB} (按位异或)");
            Console.WriteLine($"~bitA = {~bitA} (按位取反)");
            Console.WriteLine($"bitA << 1 = {bitA << 1} (左移)");
            Console.WriteLine($"bitA >> 1 = {bitA >> 1} (右移)");

            Console.WriteLine("\n=== 8. is 运算符（类型检查） ===");
            object obj = "hello";
            Console.WriteLine($"obj 是 string 类型: {obj is string}");
            Console.WriteLine($"obj 是 int 类型: {obj is int}");

            Console.WriteLine("\n=== 9. as 运算符（安全转换） ===");
            object objStr = "world";
            string? str = objStr as string;
            Console.WriteLine($"转换结果: {str}");

            object objInt = 123;
            string? str2 = objInt as string;  // 转换失败返回 null
            Console.WriteLine($"转换失败结果: {str2 == null}");

            Console.WriteLine("\n=== 10. sizeof 运算符 ===");
            Console.WriteLine($"sizeof(int) = {sizeof(int)}");
            Console.WriteLine($"sizeof(double) = {sizeof(double)}");
            Console.WriteLine($"sizeof(char) = {sizeof(char)}");
        }
    }
}
