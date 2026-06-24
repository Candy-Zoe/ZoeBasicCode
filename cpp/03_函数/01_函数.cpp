// ============================================================
// C++ 函数
// 编译运行：g++ 01_函数.cpp -o 01_函数 && ./01_函数
// ============================================================

#include <iostream>
#include <vector>
#include <functional>

using namespace std;

// 1. 无参数无返回值
void greet() {
    cout << "Hello, World!" << endl;
}

// 2. 带参数的函数
void greetUser(string name) {
    cout << "你好，" << name << "！" << endl;
}

// 3. 带返回值的函数
int add(int a, int b) {
    return a + b;
}

// 4. 默认参数
int power(int base, int exponent = 2) {
    int result = 1;
    for (int i = 0; i < exponent; i++) {
        result *= base;
    }
    return result;
}

// 5. 引用参数
void swap(int &a, int &b) {
    int temp = a;
    a = b;
    b = temp;
}

// 6. 函数重载
int add(int a, int b) { return a + b; }
double add(double a, double b) { return a + b; }
string add(string a, string b) { return a + b; }

// 7. 递归函数
int factorial(int n) {
    if (n == 0 || n == 1) return 1;
    return n * factorial(n - 1);
}

// 8. 内联函数
inline int square(int x) {
    return x * x;
}

// 9. 函数模板
template <typename T>
T maxValue(T a, T b) {
    return a > b ? a : b;
}

int main() {
    cout << "=== 1. 基本函数 ===" << endl;
    greet();
    greetUser("张三");
    cout << "3 + 5 = " << add(3, 5) << endl;

    cout << "\n=== 2. 默认参数 ===" << endl;
    cout << "3的平方: " << power(3) << endl;
    cout << "3的立方: " << power(3, 3) << endl;

    cout << "\n=== 3. 引用参数（交换变量） ===" << endl;
    int x = 10, y = 20;
    cout << "交换前: x=" << x << ", y=" << y << endl;
    swap(x, y);
    cout << "交换后: x=" << x << ", y=" << y << endl;

    cout << "\n=== 4. 函数重载 ===" << endl;
    cout << "add(3, 5) = " << add(3, 5) << endl;
    cout << "add(3.5, 2.5) = " << add(3.5, 2.5) << endl;
    cout << "add(\"Hello\", \" World\") = " << add("Hello", string(" World")) << endl;

    cout << "\n=== 5. 递归函数 ===" << endl;
    cout << "阶乘:" << endl;
    for (int i = 0; i < 6; i++) {
        cout << i << "! = " << factorial(i) << endl;
    }

    cout << "\n=== 6. 内联函数 ===" << endl;
    cout << "5的平方: " << square(5) << endl;

    cout << "\n=== 7. 函数模板 ===" << endl;
    cout << "maxValue(3, 5) = " << maxValue(3, 5) << endl;
    cout << "maxValue(3.5, 2.5) = " << maxValue(3.5, 2.5) << endl;

    cout << "\n=== 8. Lambda 表达式 (C++11) ===" << endl;
    auto square_lambda = [](int x) { return x * x; };
    cout << "5的平方 (lambda): " << square_lambda(5) << endl;

    auto add_lambda = [](int a, int b) -> int { return a + b; };
    cout << "3+4 (lambda): " << add_lambda(3, 4) << endl;

    cout << "\n=== 9. Lambda 捕获变量 ===" << endl;
    int factor = 10;
    auto multiply = [factor](int x) { return x * factor; };
    cout << "factor = " << factor << endl;
    cout << "multiply(5) = " << multiply(5) << endl;

    cout << "\n=== 10. 函数指针 ===" << endl;
    int (*add_ptr)(int, int) = add;
    cout << "函数指针调用 add: " << add_ptr(3, 5) << endl;

    cout << "\n=== 11. std::function ===" << endl;
    function<int(int, int)> func = add;
    cout << "std::function 调用: " << func(10, 20) << endl;

    cout << "\n=== 12. 可变参数 (initializer_list) ===" << endl;
    int sumAll(initializer_list<int> list) {
        int total = 0;
        for (int n : list) total += n;
        return total;
    }
    cout << "sumAll({1,2,3,4,5}) = " << sumAll({1, 2, 3, 4, 5}) << endl;

    return 0;
}
