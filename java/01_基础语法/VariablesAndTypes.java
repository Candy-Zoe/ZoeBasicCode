// ============================================================
// Java 基础语法 - 变量和数据类型
// 编译运行：javac VariablesAndTypes.java && java VariablesAndTypes
// ============================================================

public class VariablesAndTypes {
    public static void main(String[] args) {
        System.out.println("=== 1. 基本数据类型 ===");
        
        // 整数类型
        byte b = 127;                    // 字节型 1字节
        short s = 32767;                 // 短整型 2字节
        int i = 2147483647;              // 整型 4字节（最常用）
        long l = 9223372036854775807L;   // 长整型 8字节
        
        // 浮点类型
        float f = 3.14f;                 // 单精度 4字节
        double d = 3.1415926535;         // 双精度 8字节（最常用）
        
        // 字符类型
        char c = 'A';                    // 字符型 2字节
        
        // 布尔类型
        boolean bool = true;             // 布尔型
        
        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char: " + c);
        System.out.println("boolean: " + bool);

        System.out.println("\n=== 2. 类型大小和范围 ===");
        System.out.println("byte 大小: " + Byte.BYTES + "字节, 范围: " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE);
        System.out.println("int 大小: " + Integer.BYTES + "字节, 范围: " + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE);
        System.out.println("long 大小: " + Long.BYTES + "字节");

        System.out.println("\n=== 3. 字符串类型（引用类型） ===");
        String name = "张三";
        String greeting = "Hello, " + name;  // 字符串拼接
        System.out.println("name = " + name);
        System.out.println("greeting = " + greeting);
        System.out.println("字符串长度: " + name.length());
        System.out.println("字符串为空: " + name.isEmpty());

        System.out.println("\n=== 4. 常量 ===");
        final double PI = 3.1415926;
        final int MAX_SIZE = 1024;
        System.out.println("PI = " + PI);
        System.out.println("MAX_SIZE = " + MAX_SIZE);

        System.out.println("\n=== 5. 类型转换 ===");
        // 自动类型转换（小到大）
        int numInt = 10;
        double numDouble = numInt;
        System.out.println("int -> double: " + numInt + " -> " + numDouble);
        
        // 强制类型转换（大到小）
        double price = 9.99;
        int priceInt = (int) price;
        System.out.println("double -> int: " + price + " -> " + priceInt);
        
        // 字符串转数字
        String numStr = "123";
        int parsedInt = Integer.parseInt(numStr);
        System.out.println("String -> int: \"" + numStr + "\" -> " + parsedInt);
        
        double parsedDouble = Double.parseDouble("3.14");
        System.out.println("String -> double: 3.14 -> " + parsedDouble);
        
        // 数字转字符串
        String intStr = Integer.toString(456);
        System.out.println("int -> String: 456 -> \"" + intStr + "\"");

        System.out.println("\n=== 6. var 局部变量类型推断 (Java 10+) ===");
        var x = 10;           // 推断为 int
        var y = "hello";      // 推断为 String
        var z = 3.14;         // 推断为 double
        System.out.println("x = " + x + " (int)");
        System.out.println("y = " + y + " (String)");
        System.out.println("z = " + z + " (double)");

        System.out.println("\n=== 7. 包装类 ===");
        Integer intObj = 100;        // 自动装箱
        int intVal = intObj;         // 自动拆箱
        System.out.println("Integer 对象: " + intObj);
        System.out.println("int 值: " + intVal);
        System.out.println("Integer 最大值: " + Integer.MAX_VALUE);

        System.out.println("\n=== 8. 转义字符 ===");
        System.out.println("换行符: 第一行\\n第二行");
        System.out.println("制表符: 列1\\t列2\\t列3");
        System.out.println("双引号: \"Hello\"");
        System.out.println("反斜杠: \\\\");
    }
}
