// ============================================================
// Java 文件操作和异常处理
// 编译运行：javac FileAndException.java && java FileAndException
// ============================================================

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileAndException {
    public static void main(String[] args) {
        System.out.println("=== 1. 写入文件（FileWriter） ===");
        try {
            FileWriter writer = new FileWriter("test.txt");
            writer.write("第一行\n");
            writer.write("第二行\n");
            writer.write("第三行\n");
            writer.close();
            System.out.println("已写入 test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 2. 读取文件（FileReader） ===");
        try {
            FileReader reader = new FileReader("test.txt");
            int ch;
            System.out.println("文件内容:");
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 3. 按行读取（BufferedReader） ===");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                System.out.println("第" + lineNum + "行: " + line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 4. try-with-resources 自动关闭 ===");
        try (PrintWriter writer = new PrintWriter(new FileWriter("auto_close.txt"))) {
            writer.println("自动关闭的文件");
            System.out.println("已写入，try结束会自动关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 5. 基本异常处理 ===");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("算术异常: " + e.getMessage());
        }

        System.out.println("\n=== 6. 多重 catch ===");
        try {
            int[] arr = new int[5];
            arr[10] = 20;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("数组越界异常: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("其他异常: " + e.getMessage());
        }

        System.out.println("\n=== 7. finally 语句 ===");
        FileReader fr = null;
        try {
            fr = new FileReader("test.txt");
            System.out.println("文件打开成功");
        } catch (IOException e) {
            System.out.println("文件打开失败");
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("finally 总是执行");
        }

        System.out.println("\n=== 8. 自定义异常 ===");
        try {
            setAge(200);
        } catch (AgeException e) {
            System.out.println("自定义异常: " + e.getMessage());
        }

        System.out.println("\n=== 9. throw 抛出异常 ===");
        try {
            validateAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("参数异常: " + e.getMessage());
        }

        System.out.println("\n=== 10. 常见异常类型 ===");
        System.out.println("NullPointerException - 空指针");
        System.out.println("ArrayIndexOutOfBoundsException - 数组越界");
        System.out.println("ClassCastException - 类型转换");
        System.out.println("IllegalArgumentException - 非法参数");
        System.out.println("IOException - IO异常");
        System.out.println("RuntimeException - 运行时异常");

        System.out.println("\n=== 11. NIO Files 类 (Java 7+) ===");
        try {
            Path path = Paths.get("test.txt");
            System.out.println("文件存在: " + Files.exists(path));
            System.out.println("文件大小: " + Files.size(path) + " 字节");
            
            List<String> lines = Files.readAllLines(path);
            System.out.println("读取所有行:");
            for (String l : lines) {
                System.out.println("  " + l);
            }
            
            // 写入
            List<String> newLines = Arrays.asList("A", "B", "C");
            Files.write(Paths.get("nio_test.txt"), newLines);
            System.out.println("已写入 nio_test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== 12. 序列化/反序列化 ===");
        System.out.println("对象序列化需要实现 Serializable 接口");
        System.out.println("使用 ObjectOutputStream / ObjectInputStream");

        System.out.println("\n=== 清理测试文件 ===");
        new File("test.txt").delete();
        new File("auto_close.txt").delete();
        new File("nio_test.txt").delete();
        System.out.println("已清理测试文件");
    }

    static void setAge(int age) throws AgeException {
        if (age < 0 || age > 150) {
            throw new AgeException("年龄 " + age + " 无效，必须在0-150之间");
        }
        System.out.println("年龄设置为: " + age);
    }

    static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("年龄必须在0-150之间");
        }
    }
}

// 自定义异常类
class AgeException extends Exception {
    public AgeException(String message) {
        super(message);
    }
}
