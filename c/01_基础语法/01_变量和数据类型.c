/* ============================================================
 * C 语言基础语法 - 变量和数据类型
 * 编译运行：gcc 01_变量和数据类型.c -o 01_变量和数据类型 && ./01_变量和数据类型
 * ============================================================ */

#include <stdio.h>
#include <limits.h>
#include <float.h>

int main() {
    // 1. 基本变量类型
    printf("=== 1. 基本变量类型 ===\n");
    
    int age = 25;                    // 整型
    float height = 1.75f;            // 单精度浮点型
    double weight = 65.5;            // 双精度浮点型
    char grade = 'A';                // 字符型
    
    printf("年龄: %d\n", age);
    printf("身高: %.2f\n", height);
    printf("体重: %.1lf\n", weight);
    printf("等级: %c\n", grade);

    // 2. 整数类型
    printf("\n=== 2. 整数类型 ===\n");
    
    char c = 'A';                    // 字符型 1字节
    short s = 32767;                 // 短整型 2字节
    int i = 2147483647;              // 整型 4字节
    long l = 9223372036854775807;    // 长整型 8字节
    
    printf("char 大小: %zu 字节\n", sizeof(char));
    printf("short 大小: %zu 字节\n", sizeof(short));
    printf("int 大小: %zu 字节\n", sizeof(int));
    printf("long 大小: %zu 字节\n", sizeof(long));
    printf("long long 大小: %zu 字节\n", sizeof(long long));

    // 3. 有符号和无符号
    printf("\n=== 3. 有符号和无符号 ===\n");
    
    signed int si = -100;
    unsigned int ui = 100;
    
    printf("有符号: %d\n", si);
    printf("无符号: %u\n", ui);
    printf("int 范围: %d ~ %d\n", INT_MIN, INT_MAX);
    printf("unsigned int 最大值: %u\n", UINT_MAX);

    // 4. 浮点类型
    printf("\n=== 4. 浮点类型 ===\n");
    
    float f = 3.14f;
    double d = 3.1415926535;
    long double ld = 3.14159265358979323846L;
    
    printf("float 大小: %zu 字节, 值: %.6f\n", sizeof(float), f);
    printf("double 大小: %zu 字节, 值: %.10lf\n", sizeof(double), d);
    printf("float 精度: 6-7位有效数字\n");
    printf("double 精度: 15-16位有效数字\n");

    // 5. 常量
    printf("\n=== 5. 常量 ===\n");
    
    const double PI = 3.1415926;     // const 常量
    #define MAX_SIZE 1024            // 宏定义常量
    
    printf("PI = %f\n", PI);
    printf("MAX_SIZE = %d\n", MAX_SIZE);

    // 6. 类型转换
    printf("\n=== 6. 类型转换 ===\n");
    
    int num_int = 10;
    double num_double = num_int;     // 隐式转换（自动）
    printf("int -> double: %d -> %.1lf\n", num_int, num_double);
    
    double price = 9.99;
    int price_int = (int)price;      // 强制类型转换
    printf("double -> int: %.2lf -> %d\n", price, price_int);

    // 7. 格式化输出
    printf("\n=== 7. 格式化输出 ===\n");
    
    printf("十进制: %d\n", 42);
    printf("八进制: %o\n", 42);
    printf("十六进制: %x\n", 42);
    printf("十六进制(大写): %X\n", 42);
    printf("浮点数: %.2f\n", 3.14159);
    printf("科学计数法: %e\n", 123456.789);
    printf("字符串: %s\n", "Hello C");
    printf("字符: %c\n", 'A');
    printf("指针地址: %p\n", &age);

    // 8. 转义字符
    printf("\n=== 8. 转义字符 ===\n");
    
    printf("换行符: 第一行\\n第二行\n");
    printf("制表符: 列1\\t列2\\t列3\n");
    printf("反斜杠: \\\\\n");
    printf("单引号: \\'\n");
    printf("双引号: \\\"\n");
    printf("响铃: \\a (可能发出声音)\n");

    return 0;
}
