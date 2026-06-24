// ============================================================
// C++ 流程控制
// 编译运行：g++ 01_流程控制.cpp -o 01_流程控制 && ./01_流程控制
// ============================================================

#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {
    cout << "=== 1. if-else 语句 ===" << endl;
    int score = 85;
    if (score >= 90) {
        cout << "优秀" << endl;
    } else if (score >= 80) {
        cout << "良好" << endl;
    } else if (score >= 60) {
        cout << "及格" << endl;
    } else {
        cout << "不及格" << endl;
    }

    cout << "\n=== 2. switch 语句 ===" << endl;
    int day = 3;
    switch (day) {
        case 1: cout << "星期一" << endl; break;
        case 2: cout << "星期二" << endl; break;
        case 3: cout << "星期三" << endl; break;
        case 4: cout << "星期四" << endl; break;
        case 5: cout << "星期五" << endl; break;
        case 6:
        case 7: cout << "周末" << endl; break;
        default: cout << "无效" << endl;
    }

    cout << "\n=== 3. 三元运算符 ===" << endl;
    int a = 10, b = 20;
    int max = a > b ? a : b;
    cout << a << " 和 " << b << " 较大的是: " << max << endl;

    cout << "\n=== 4. for 循环 ===" << endl;
    for (int i = 0; i < 5; i++) {
        cout << "第 " << i + 1 << " 次循环" << endl;
    }

    cout << "\n=== 5. 范围 for 循环 (C++11) ===" << endl;
    vector<int> nums = {1, 2, 3, 4, 5};
    cout << "遍历 vector: ";
    for (int n : nums) {
        cout << n << " ";
    }
    cout << endl;

    string str = "Hello";
    cout << "遍历字符串: ";
    for (char c : str) {
        cout << c << " ";
    }
    cout << endl;

    cout << "\n=== 6. while 循环 ===" << endl;
    int count = 1;
    while (count <= 5) {
        cout << "count = " << count << endl;
        count++;
    }

    cout << "\n=== 7. do-while 循环 ===" << endl;
    int num = 1;
    do {
        cout << "num = " << num << endl;
        num++;
    } while (num <= 5);

    cout << "\n=== 8. break 和 continue ===" << endl;
    cout << "break 示例（到5停止）: ";
    for (int i = 0; i < 10; i++) {
        if (i == 5) break;
        cout << i << " ";
    }
    cout << endl;

    cout << "continue 示例（跳过偶数）: ";
    for (int i = 0; i < 10; i++) {
        if (i % 2 == 0) continue;
        cout << i << " ";
    }
    cout << endl;

    cout << "\n=== 9. 嵌套循环（九九乘法表） ===" << endl;
    for (int i = 1; i < 10; i++) {
        for (int j = 1; j <= i; j++) {
            cout << j << "x" << i << "=" << i * j << "\t";
        }
        cout << endl;
    }

    cout << "\n=== 10. 使用示例：斐波那契数列 ===" << endl;
    cout << "前10项: ";
    int fib_prev = 0, fib_curr = 1;
    for (int i = 0; i < 10; i++) {
        cout << fib_prev << " ";
        int next = fib_prev + fib_curr;
        fib_prev = fib_curr;
        fib_curr = next;
    }
    cout << endl;

    return 0;
}
