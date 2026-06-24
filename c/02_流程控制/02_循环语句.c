/* ============================================================
 * C 语言流程控制 - 循环语句
 * 编译运行：gcc 02_循环语句.c -o 02_循环语句 && ./02_循环语句
 * ============================================================ */

#include <stdio.h>

int main() {
    printf("=== 1. for 循环 ===\n");
    for (int i = 0; i < 5; i++) {
        printf("第 %d 次循环\n", i + 1);
    }

    printf("\n=== 2. for 循环 - 步长 ==\n");
    printf("1到9的奇数: ");
    for (int i = 1; i < 10; i += 2) {
        printf("%d ", i);
    }
    printf("\n");

    printf("\n=== 3. while 循环 ===\n");
    int count = 1;
    while (count <= 5) {
        printf("第 %d 次循环\n", count);
        count++;
    }

    printf("\n=== 4. do-while 循环 ===\n");
    int num = 1;
    do {
        printf("num = %d\n", num);
        num++;
    } while (num <= 5);

    printf("\n=== 5. break 语句 ===\n");
    for (int i = 0; i < 10; i++) {
        if (i == 5) {
            break;
        }
        printf("%d ", i);
    }
    printf("\n遇到 break，循环结束\n");

    printf("\n=== 6. continue 语句 ===\n");
    for (int i = 0; i < 10; i++) {
        if (i % 2 == 0) {
            continue;
        }
        printf("%d ", i);
    }
    printf("\n只打印了奇数\n");

    printf("\n=== 7. goto 语句 ===\n");
    int x = 0;
start:
    printf("x = %d\n", x);
    x++;
    if (x < 3) {
        goto start;
    }

    printf("\n=== 8. 嵌套循环 ===\n");
    printf("九九乘法表:\n");
    for (int i = 1; i < 10; i++) {
        for (int j = 1; j <= i; j++) {
            printf("%dx%d=%d\t", j, i, i * j);
        }
        printf("\n");
    }

    printf("\n=== 9. 使用示例：累加求和 ===\n");
    int total = 0;
    for (int i = 1; i <= 100; i++) {
        total += i;
    }
    printf("1到100的和: %d\n", total);

    printf("\n=== 10. 使用示例：查找质数 ===\n");
    int is_prime(int n) {
        if (n < 2) return 0;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return 0;
        }
        return 1;
    }

    printf("1到50之间的质数:\n");
    for (int num2 = 1; num2 <= 50; num2++) {
        if (is_prime(num2)) {
            printf("%d ", num2);
        }
    }
    printf("\n");

    printf("\n=== 11. 使用示例：斐波那契数列 ===\n");
    printf("斐波那契数列前10项:\n");
    int a = 0, b = 1;
    for (int i = 0; i < 10; i++) {
        printf("%d ", a);
        int next = a + b;
        a = b;
        b = next;
    }
    printf("\n");

    printf("\n=== 12. for 循环的灵活用法 ===\n");
    // 初始化部分可以有多个变量
    int i, j;
    for (i = 0, j = 10; i < j; i++, j--) {
        printf("i=%d, j=%d\n", i, j);
    }

    // 无限循环
    printf("\n无限循环（用 break 退出）:\n");
    int k = 0;
    for (;;) {
        if (k >= 3) break;
        printf("k = %d\n", k);
        k++;
    }

    return 0;
}
