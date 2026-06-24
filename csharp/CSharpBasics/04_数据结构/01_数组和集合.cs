// ============================================================
// C# 数据结构 - 数组和集合
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;
using System.Collections.Generic;
using System.Linq;

namespace CSharpBasics
{
    class ArraysAndCollections
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 一维数组 ===");
            int[] numbers = { 1, 2, 3, 4, 5 };
            Console.WriteLine($"数组长度: {numbers.Length}");
            Console.WriteLine($"第一个元素: {numbers[0]}");
            Console.WriteLine($"最后一个元素: {numbers[^1]}");

            Console.WriteLine("\n遍历数组:");
            for (int i = 0; i < numbers.Length; i++)
            {
                Console.Write($"{numbers[i]} ");
            }
            Console.WriteLine();

            Console.WriteLine("\n=== 2. 数组的初始化方式 ===");
            int[] arr1 = new int[5];          // 指定长度，默认值为0
            int[] arr2 = new int[] { 1, 2, 3 }; // 指定元素
            int[] arr3 = { 1, 2, 3, 4, 5 };      // 简化写法

            Console.WriteLine($"arr1 长度: {arr1.Length}");
            Console.WriteLine($"arr2: {string.Join(", ", arr2)}");

            Console.WriteLine("\n=== 3. 二维数组 ===");
            int[,] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };
            Console.WriteLine($"二维数组维度: {matrix.Rank}");
            Console.WriteLine($"第0行长度: {matrix.GetLength(0)}");
            Console.WriteLine($"第1列长度: {matrix.GetLength(1)}");
            Console.WriteLine($"matrix[1,2] = {matrix[1, 2]}");

            Console.WriteLine("\n遍历二维数组:");
            for (int i = 0; i < matrix.GetLength(0); i++)
            {
                for (int j = 0; j < matrix.GetLength(1); j++)
                {
                    Console.Write($"{matrix[i, j]} ");
                }
                Console.WriteLine();
            }

            Console.WriteLine("\n=== 4. 交错数组（数组的数组） ===");
            int[][] jagged = new int[3][];
            jagged[0] = new int[] { 1, 2 };
            jagged[1] = new int[] { 3, 4, 5 };
            jagged[2] = new int[] { 6, 7, 8, 9 };

            for (int i = 0; i < jagged.Length; i++)
            {
                Console.WriteLine($"第{i}行: {string.Join(" ", jagged[i])}");
            }

            Console.WriteLine("\n=== 5. List<T> 泛型列表 ===");
            List<string> fruits = new List<string> { "苹果", "香蕉", "橙子" };
            Console.WriteLine($"初始列表: {string.Join(", ", fruits)}");
            Console.WriteLine($"列表容量: {fruits.Capacity}");
            Console.WriteLine($"列表元素数: {fruits.Count}");

            // 添加元素
            fruits.Add("葡萄");
            Console.WriteLine($"Add 后: {string.Join(", ", fruits)}");

            fruits.Insert(1, "芒果");
            Console.WriteLine($"Insert 后: {string.Join(", ", fruits)}");

            fruits.AddRange(new[] { "西瓜", "菠萝" });
            Console.WriteLine($"AddRange 后: {string.Join(", ", fruits)}");

            // 删除元素
            fruits.Remove("香蕉");
            Console.WriteLine($"Remove 后: {string.Join(", ", fruits)}");

            fruits.RemoveAt(0);
            Console.WriteLine($"RemoveAt(0) 后: {string.Join(", ", fruits)}");

            // 查找
            Console.WriteLine($"\n包含'葡萄': {fruits.Contains("葡萄")}");
            Console.WriteLine($"索引: {fruits.IndexOf("葡萄")}");

            Console.WriteLine("\n=== 6. Dictionary<TKey, TValue> 字典 ===");
            Dictionary<string, int> dict = new Dictionary<string, int>
            {
                { "苹果", 5 },
                { "香蕉", 3 },
                { "橙子", 8 }
            };

            Console.WriteLine("字典内容:");
            foreach (var kvp in dict)
            {
                Console.WriteLine($"  {kvp.Key}: {kvp.Value}");
            }

            // 添加和访问
            dict["葡萄"] = 10;
            Console.WriteLine($"\n添加后: 葡萄 = {dict["葡萄"]}");

            // 安全访问
            if (dict.TryGetValue("芒果", out int value))
            {
                Console.WriteLine($"芒果: {value}");
            }
            else
            {
                Console.WriteLine("芒果不存在");
            }

            Console.WriteLine("\n遍历键:");
            foreach (string key in dict.Keys)
            {
                Console.WriteLine($"  {key}");
            }

            Console.WriteLine("\n遍历值:");
            foreach (int val in dict.Values)
            {
                Console.WriteLine($"  {val}");
            }

            Console.WriteLine("\n=== 7. HashSet<T> 集合 ===");
            HashSet<int> set = new HashSet<int> { 1, 2, 3, 2, 1 };
            Console.WriteLine($"HashSet: {string.Join(", ", set)}");
            Console.WriteLine($"元素个数: {set.Count}");

            set.Add(4);
            set.Add(2); // 不会重复添加
            Console.WriteLine($"添加后: {string.Join(", ", set)}");

            Console.WriteLine($"\n包含3: {set.Contains(3)}");

            // 集合运算
            HashSet<int> set2 = new HashSet<int> { 3, 4, 5 };
            Console.WriteLine($"set2: {string.Join(", ", set2)}");

            set.UnionWith(set2); // 并集
            Console.WriteLine($"并集: {string.Join(", ", set)}");

            Console.WriteLine("\n=== 8. Queue<T> 队列（先进先出） ===");
            Queue<string> queue = new Queue<string>();
            queue.Enqueue("第一个");
            queue.Enqueue("第二个");
            queue.Enqueue("第三个");
            Console.WriteLine($"队列: {string.Join(", ", queue)}");
            Console.WriteLine($"出队: {queue.Dequeue()}");
            Console.WriteLine($"出队后: {string.Join(", ", queue)}");
            Console.WriteLine($"查看队首: {queue.Peek()}");

            Console.WriteLine("\n=== 9. Stack<T> 栈（后进先出） ===");
            Stack<string> stack = new Stack<string>();
            stack.Push("第一个");
            stack.Push("第二个");
            stack.Push("第三个");
            Console.WriteLine($"栈: {string.Join(", ", stack)}");
            Console.WriteLine($"出栈: {stack.Pop()}");
            Console.WriteLine($"出栈后: {string.Join(", ", stack)}");
            Console.WriteLine($"查看栈顶: {stack.Peek()}");

            Console.WriteLine("\n=== 10. LINQ 基础操作 ===");
            int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

            // Where 过滤
            var evens = nums.Where(n => n % 2 == 0);
            Console.WriteLine($"偶数: {string.Join(", ", evens)}");

            // Select 投影
            var squares = nums.Select(n => n * n);
            Console.WriteLine($"平方: {string.Join(", ", squares)}");

            // OrderBy 排序
            var desc = nums.OrderByDescending(n => n);
            Console.WriteLine($"降序: {string.Join(", ", desc)}");

            // 聚合
            Console.WriteLine($"总和: {nums.Sum()}");
            Console.WriteLine($"平均值: {nums.Average()}");
            Console.WriteLine($"最大值: {nums.Max()}");
            Console.WriteLine($"最小值: {nums.Min()}");
        }
    }
}
