/* ============================================================
 * C 语言函数
 * 编译运行：gcc 01_函数基础.c -o 01_函数基础 && ./01_函数基础
 * ============================================================ */

#include <stdio.h>

// 函数声明（函数原型）
void greet();
void greet_user(char name[]);
int add(int a, int b);
int power(int base, int exponent);
int sum_all(int numbers[], int count);
void swap(int *a, int *b);
int factorial(int n);

int main() {
    printf("=== 1. 函数的定义和调用 ===\n");
    greet();

    printf("\n=== 2. 带参数的函数 ===\n");
    greet_user("张三");
    greet_user("李四");

    printf("\n=== 3. 带返回值的函数 ===\n");
    int result = add(3, 5);
    printf("3 + 5 = %d\n", result);

    printf("\n=== 4. 函数声明与定义分离 ===\n");
    printf("函数原型告诉编译器函数的签名\n");
    printf("实际的函数定义可以在文件的其他位置\n");

    printf("\n=== 5. 幂函数 ===\n");
    printf("3的平方: %d\n", power(3, 2));
    printf("3的立方: %d\n", power(3, 3));

    printf("\n=== 6. 数组作为参数 ===\n");
    int nums[] = {1, 2, 3, 4, 5};
    int total = sum_all(nums, 5);
    printf("数组元素和: %d\n", total);

    printf("\n=== 7. 指针参数（交换变量） ===\n");
    int x = 10, y = 20;
    printf("交换前: x = %d, y = %d\n", x, y);
    swap(&x, &y);
    printf("交换后: x = %d, y = %d\n", x, y);

    printf("\n=== 8. 递归函数 ===\n");
    printf("阶乘:\n");
    for (int i = 0; i < 6; i++) {
        printf("%d! = %d\n", i, factorial(i));
    }

    printf("\n=== 9. 函数的作用域 ===\n");
    // 局部变量只能在函数内访问
    int local_var = 100;
    printf("局部变量 local_var = %d\n", local_var);

    printf("\n=== 10. 静态变量 ===\n");
    void counter() {
        static int count = 0; // 静态变量只初始化一次
        count++;
        printf("count = %d\n", count);
    }
    counter();
    counter();
    counter();

    printf("\n=== 11. 全局变量 ===\n");
    // 全局变量在所有函数中都可以访问
    printf("全局变量可以在所有函数中使用，但要谨慎使用\n");

    printf("\n=== 12. 函数指针 ===\n");
    int (*add_ptr)(int, int) = add; // 函数指针
    printf("通过函数指针调用 add: %d\n", add_ptr(3, 5));

    printf("\n=== 13. 头文件和多文件编译说明 ===\n");
    printf("大型项目中，通常:\n");
    printf("  - .h 文件: 存放函数声明\n");
    printf("  - .c 文件: 存放函数定义\n");
    printf("  - 编译: gcc main.c utils.c -o program\n");

    return 0;
}

// 函数定义

void greet() {
    printf("Hello, World!\n");
}

void greet_user(char name[]) {
    printf("你好，%s！\n", name);
}

int add(int a, int b) {
    return a + b;
}

int power(int base, int exponent) {
    int result = 1;
    for (int i = 0; i < exponent; i++) {
        result *= base;
    }
    return result;
}

int sum_all(int numbers[], int count) {
    int total = 0;
    for (int i = 0; i < count; i++) {
        total += numbers[i];
    }
    return total;
}

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int factorial(int n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    return n * factorial(n - 1);
}
