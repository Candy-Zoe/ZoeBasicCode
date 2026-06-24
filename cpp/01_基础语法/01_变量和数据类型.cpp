// ============================================================
// C++ 基础语法 - 变量和数据类型
// 编译运行：g++ 01_变量和数据类型.cpp -o 01_变量和数据类型 && ./01_变量和数据类型
// ============================================================

#include <iostream>
#include <string>
#include <typeinfo>
#include <climits>
#include <cfloat>

using namespace std;

int main() {
    cout << "=== 1. 基本变量类型 ===" << endl;
    
    int age = 25;                    // 整型
    float height = 1.75f;            // 单精度浮点型
    double weight = 65.5;            // 双精度浮点型
    char grade = 'A';                // 字符型
    bool isStudent = true;           // 布尔类型
    string name = "张三";            // 字符串类型
    
    cout << "姓名: " << name << endl;
    cout << "年龄: " << age << endl;
    cout << "身高: " << height << endl;
    cout << "体重: " << weight << endl;
    cout << "等级: " << grade << endl;
    cout << "是否学生: " << (isStudent ? "是" : "否") << endl;

    cout << "\n=== 2. 整数类型 ===" << endl;
    cout << "char: " << sizeof(char) << "字节" << endl;
    cout << "short: " << sizeof(short) << "字节" << endl;
    cout << "int: " << sizeof(int) << "字节" << endl;
    cout << "long: " << sizeof(long) << "字节" << endl;
    cout << "long long: " << sizeof(long long) << "字节" << endl;
    cout << "int 范围: " << INT_MIN << " ~ " << INT_MAX << endl;

    cout << "\n=== 3. 浮点类型 ===" << endl;
    cout << "float: " << sizeof(float) << "字节" << endl;
    cout << "double: " << sizeof(double) << "字节" << endl;
    cout << "long double: " << sizeof(long double) << "字节" << endl;

    cout << "\n=== 4. bool 类型 ===" << endl;
    bool b1 = true;
    bool b2 = false;
    cout << "true = " << b1 << " (输出为1)" << endl;
    cout << "false = " << b2 << " (输出为0)" << endl;
    cout << boolalpha;
    cout << "boolalpha 后 true = " << b1 << endl;
    cout << "boolalpha 后 false = " << b2 << endl;

    cout << "\n=== 5. string 字符串 ===" << endl;
    string str1 = "Hello";
    string str2 = "World";
    string str3 = str1 + " " + str2;  // 字符串拼接
    cout << "str1 = " << str1 << endl;
    cout << "str2 = " << str2 << endl;
    cout << "str3 = " << str3 << endl;
    cout << "str3 长度: " << str3.length() << endl;
    cout << "str3 是否为空: " << str3.empty() << endl;
    cout << "str3 第0个字符: " << str3[0] << endl;

    cout << "\n=== 6. auto 类型推断 (C++11) ===" << endl;
    auto x = 10;          // 推断为 int
    auto y = 3.14;        // 推断为 double
    auto str = "hello"s;  // C++14 string 字面量
    cout << "x 类型: " << typeid(x).name() << " 值: " << x << endl;
    cout << "y 类型: " << typeid(y).name() << " 值: " << y << endl;

    cout << "\n=== 7. const 常量 ===" << endl;
    const double PI = 3.1415926;
    const int MAX_SIZE = 1024;
    cout << "PI = " << PI << endl;
    cout << "MAX_SIZE = " << MAX_SIZE << endl;

    cout << "\n=== 8. constexpr 编译期常量 (C++11) ===" << endl;
    constexpr int square(int n) { return n * n; }
    constexpr int val = square(5);  // 编译期计算
    cout << "square(5) = " << val << " (编译期计算)" << endl;

    cout << "\n=== 9. 类型转换 ===" << endl;
    int num_int = 10;
    double num_double = num_int;     // 隐式转换
    cout << "int -> double: " << num_int << " -> " << num_double << endl;
    
    double price = 9.99;
    int price_int = static_cast<int>(price);  // 静态转换
    cout << "double -> int: " << price << " -> " << price_int << endl;

    cout << "\n=== 10. 输入输出 ===" << endl;
    cout << "C++ 使用 cin/cout 进行输入输出" << endl;
    cout << "cout << 输出内容 << endl;" << endl;
    cout << "cin >> 变量;" << endl;

    return 0;
}
