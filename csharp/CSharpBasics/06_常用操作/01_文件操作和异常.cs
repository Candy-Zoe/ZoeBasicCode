// ============================================================
// C# 常用操作 - 文件操作和异常处理
// 运行方式：将此文件内容替换 Program.cs 后运行 dotnet run
// ============================================================

using System;
using System.IO;
using System.Text.Json;

namespace CSharpBasics
{
    class FileAndException
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== 1. 写入文件 ===");
            string filePath = "test.txt";
            File.WriteAllText(filePath, "第一行\n第二行\n第三行\n");
            Console.WriteLine("已写入 test.txt");

            Console.WriteLine("\n=== 2. 读取文件 ===");
            string content = File.ReadAllText(filePath);
            Console.WriteLine("文件内容:");
            Console.WriteLine(content);

            Console.WriteLine("=== 3. 按行读取 ===");
            string[] lines = File.ReadAllLines(filePath);
            Console.WriteLine($"共 {lines.Length} 行:");
            for (int i = 0; i < lines.Length; i++)
            {
                Console.WriteLine($"  第{i + 1}行: {lines[i]}");
            }

            Console.WriteLine("\n=== 4. 追加写入 ===");
            File.AppendAllText(filePath, "第四行（追加）\n");
            Console.WriteLine("已追加内容");
            Console.WriteLine(File.ReadAllText(filePath));

            Console.WriteLine("=== 5. StreamWriter 和 StreamReader ===");
            using (StreamWriter writer = new StreamWriter("stream_test.txt"))
            {
                writer.WriteLine("使用 StreamWriter 写入");
                writer.WriteLine("第二行");
            }
            Console.WriteLine("使用 StreamWriter 写入完成");

            using (StreamReader reader = new StreamReader("stream_test.txt"))
            {
                string? line;
                while ((line = reader.ReadLine()) != null)
                {
                    Console.WriteLine($"读取: {line}");
                }
            }

            Console.WriteLine("\n=== 6. 文件和目录操作 ===");
            Console.WriteLine($"当前目录: {Directory.GetCurrentDirectory()}");
            Console.WriteLine($"文件是否存在: {File.Exists(filePath)}");
            Console.WriteLine($"文件大小: {new FileInfo(filePath).Length} 字节");

            Console.WriteLine("\n=== 7. 目录操作 ===");
            string dirPath = "test_dir";
            if (!Directory.Exists(dirPath))
            {
                Directory.CreateDirectory(dirPath);
                Console.WriteLine("创建目录 test_dir");
            }
            Console.WriteLine($"目录存在: {Directory.Exists(dirPath)}");

            if (Directory.Exists(dirPath))
            {
                Directory.Delete(dirPath);
                Console.WriteLine("删除目录 test_dir");
            }

            Console.WriteLine("\n=== 8. 基本 try-catch ===");
            try
            {
                int result = 10 / 0;
            }
            catch (DivideByZeroException)
            {
                Console.WriteLine("错误：不能除以零");
            }

            Console.WriteLine("\n=== 9. 捕获多个异常 ===");
            try
            {
                int num = int.Parse("abc");
            }
            catch (FormatException)
            {
                Console.WriteLine("错误：格式不正确");
            }
            catch (OverflowException)
            {
                Console.WriteLine("错误：数值溢出");
            }

            Console.WriteLine("\n=== 10. 捕获所有异常 ===");
            try
            {
                int x = 10 / 0;
            }
            catch (Exception e)
            {
                Console.WriteLine($"发生异常: {e.GetType().Name}: {e.Message}");
            }

            Console.WriteLine("\n=== 11. finally 子句 ===");
            FileStream? fs = null;
            try
            {
                fs = File.Open("finally_test.txt", FileMode.Create);
                byte[] data = System.Text.Encoding.UTF8.GetBytes("测试");
                fs.Write(data, 0, data.Length);
                Console.WriteLine("文件写入成功");
            }
            catch (IOException e)
            {
                Console.WriteLine($"文件操作失败: {e.Message}");
            }
            finally
            {
                if (fs != null)
                {
                    fs.Close();
                    Console.WriteLine("文件已关闭");
                }
            }

            Console.WriteLine("\n=== 12. 自定义异常 ===");
            try
            {
                SetAge(200);
            }
            catch (AgeException e)
            {
                Console.WriteLine($"捕获到自定义异常: {e.Message}");
            }

            Console.WriteLine("\n=== 13. throw 抛出异常 ===");
            static void ValidateAge(int age)
            {
                if (age < 0 || age > 150)
                {
                    throw new ArgumentOutOfRangeException(nameof(age), "年龄必须在0-150之间");
                }
            }

            try
            {
                ValidateAge(-1);
            }
            catch (ArgumentOutOfRangeException e)
            {
                Console.WriteLine($"参数异常: {e.Message}");
            }

            Console.WriteLine("\n=== 14. JSON 序列化/反序列化 ===");
            var person = new PersonData { Name = "张三", Age = 25, City = "北京" };
            string json = JsonSerializer.Serialize(person, new JsonSerializerOptions { WriteIndented = true });
            Console.WriteLine("序列化后的JSON:");
            Console.WriteLine(json);

            var deserialized = JsonSerializer.Deserialize<PersonData>(json);
            Console.WriteLine($"\n反序列化: Name={deserialized?.Name}, Age={deserialized?.Age}");

            Console.WriteLine("\n=== 清理测试文件 ===");
            foreach (string f in new[] { "test.txt", "stream_test.txt", "finally_test.txt" })
            {
                if (File.Exists(f))
                {
                    File.Delete(f);
                    Console.WriteLine($"已删除 {f}");
                }
            }
        }

        static void SetAge(int age)
        {
            if (age < 0 || age > 150)
            {
                throw new AgeException($"年龄 {age} 无效，必须在0-150之间");
            }
            Console.WriteLine($"年龄设置为: {age}");
        }
    }

    class PersonData
    {
        public string Name { get; set; } = "";
        public int Age { get; set; }
        public string City { get; set; } = "";
    }

    class AgeException : Exception
    {
        public AgeException(string message) : base(message) { }
    }
}
