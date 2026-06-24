/* ============================================================
 * C 语言基础语法 - 运算符
 * 编译运行：gcc 02_运算符.c -o 02_运算符 && ./02_运算符
 * ============================================================ */

#include <stdio.h>

int main() {
    printf("=== 1. 算术运算符 ===\n");
    int a = 10, b = 3;
    printf("a = %d, b = %d\n", a, b);
    printf("加法: a + b = %d\n", a + b);
    printf("减法: a - b = %d\n", a - b);
    printf("乘法: a * b = %d\n", a * b);
    printf("除法: a / b = %d (整数除法)\n", a / b);
    printf("取余: a %% b = %d\n", a % b);

    double x = 10.0, y = 3.0;
    printf("浮点除法: x / y = %.2lf\n", x / y);

    printf("\n=== 2. 自增自减运算符 ===\n");
    int i = 0;
    printf("i = %d\n", i);
    i++; // i = i + 1
    printf("i++ 后: i = %d\n", i);
    i--;
    printf("i-- 后: i = %d\n", i);

    // 前缀和后缀的区别
    int n = 5;
    printf("n = %d\n", n);
    printf("n++ = %d (先返回值再加)\n", n++);
    printf("此时 n = %d\n", n);
    printf("++n = %d (先加再返回值)\n", ++n);

    printf("\n=== 3. 赋值运算符 ===\n");
    int num = 10;
    printf("初始值 num = %d\n", num);
    num += 5;
    printf("num += 5 后: %d\n", num);
    num -= 3;
    printf("num -= 3 后: %d\n", num);
    num *= 2;
    printf("num *= 2 后: %d\n", num);
    num /= 4;
    printf("num /= 4 后: %d\n", num);
    num %= 3;
    printf("num %%= 3 后: %d\n", num);

    printf("\n=== 4. 关系运算符 ===\n");
    int m = 10, n2 = 20;
    printf("m = %d, n = %d\n", m, n2);
    printf("m == n: %d (0为假, 非0为真)\n", m == n2);
    printf("m != n: %d\n", m != n2);
    printf("m > n: %d\n", m > n2);
    printf("m < n: %d\n", m < n2);
    printf("m >= n: %d\n", m >= n2);
    printf("m <= n: %d\n", m <= n2);

    printf("\n=== 5. 逻辑运算符 ===\n");
    int p = 1, q = 0; // 非0为真，0为假
    printf("p = %d (真), q = %d (假)\n", p, q);
    printf("p && q: %d (逻辑与)\n", p && q);
    printf("p || q: %d (逻辑或)\n", p || q);
    printf("!p: %d (逻辑非)\n", !p);
    printf("!q: %d\n", !q);

    printf("\n=== 6. 位运算符 ===\n");
    int bitA = 6;  // 二进制: 0110
    int bitB = 3;  // 二进制: 0011
    printf("bitA = %d (二进制: 0110)\n", bitA);
    printf("bitB = %d (二进制: 0011)\n", bitB);
    printf("bitA & bitB = %d (按位与)\n", bitA & bitB);
    printf("bitA | bitB = %d (按位或)\n", bitA | bitB);
    printf("bitA ^ bitB = %d (按位异或)\n", bitA ^ bitB);
    printf("~bitA = %d (按位取反)\n", ~bitA);
    printf("bitA << 1 = %d (左移)\n", bitA << 1);
    printf("bitA >> 1 = %d (右移)\n", bitA >> 1);

    printf("\n=== 7. 三元运算符 ===\n");
    int num1 = 10, num2 = 20;
    int max = (num1 > num2) ? num1 : num2;
    printf("%d 和 %d 中较大的是: %d\n", num1, num2, max);

    printf("\n=== 8. sizeof 运算符 ===\n");
    printf("sizeof(int) = %zu 字节\n", sizeof(int));
    printf("sizeof(double) = %zu 字节\n", sizeof(double));
    printf("sizeof(char) = %zu 字节\n", sizeof(char));

    printf("\n=== 9. 运算符优先级示例 ===\n");
    int result = 2 + 3 * 4;    // 先乘后加
    printf("2 + 3 * 4 = %d\n", result);
    
    result = (2 + 3) * 4;      // 括号优先
    printf("(2 + 3) * 4 = %d\n", result);

    return 0;
}
