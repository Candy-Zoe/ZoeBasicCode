/* ============================================================
 * C 语言流程控制 - 条件语句
 * 编译运行：gcc 01_条件语句.c -o 01_条件语句 && ./01_条件语句
 * ============================================================ */

#include <stdio.h>

int main() {
    printf("=== 1. 基本 if 语句 ===\n");
    int age = 18;
    if (age >= 18) {
        printf("你已经成年了\n");
    }

    printf("\n=== 2. if-else 语句 ===\n");
    int score = 85;
    if (score >= 60) {
        printf("及格了\n");
    } else {
        printf("不及格\n");
    }

    printf("\n=== 3. if-else if-else 语句 ===\n");
    score = 85;
    if (score >= 90) {
        printf("优秀\n");
    } else if (score >= 80) {
        printf("良好\n");
    } else if (score >= 60) {
        printf("及格\n");
    } else {
        printf("不及格\n");
    }

    printf("\n=== 4. 嵌套 if 语句 ===\n");
    age = 20;
    int has_license = 1; // 1表示有驾照
    if (age >= 18) {
        if (has_license) {
            printf("你可以开车\n");
        } else {
            printf("你需要先考驾照\n");
        }
    } else {
        printf("你还未成年，不能开车\n");
    }

    printf("\n=== 5. 多条件组合 ===\n");
    age = 25;
    int salary = 8000;
    if (age >= 22 && salary >= 5000) {
        printf("符合贷款条件\n");
    } else {
        printf("不符合贷款条件\n");
    }

    printf("\n=== 6. switch 语句 ===\n");
    int day = 3;
    switch (day) {
        case 1:
            printf("星期一\n");
            break;
        case 2:
            printf("星期二\n");
            break;
        case 3:
            printf("星期三\n");
            break;
        case 4:
            printf("星期四\n");
            break;
        case 5:
            printf("星期五\n");
            break;
        case 6:
        case 7:
            printf("周末\n");
            break;
        default:
            printf("无效的日期\n");
            break;
    }

    printf("\n=== 7. switch 穿透效果 ===\n");
    printf("数字对应的英文: ");
    int num = 2;
    switch (num) {
        case 1: printf("One ");
        case 2: printf("Two ");
        case 3: printf("Three ");
        default: printf("Other");
    }
    printf("\n(没有 break 会继续执行后面的 case)\n");

    printf("\n=== 8. 三元表达式 ===\n");
    int a = 10, b = 20;
    int max = a > b ? a : b;
    printf("%d 和 %d 中较大的是: %d\n", a, b, max);

    printf("\n=== 9. 逻辑运算符短路特性 ===\n");
    int x = 0;
    int y = 10;
    // 逻辑与短路：左边为假，右边不执行
    if (x > 0 && ++y > 0) {
        printf("条件成立\n");
    }
    printf("短路测试 y = %d (y没有自增，因为短路了)\n", y);

    // 逻辑或短路：左边为真，右边不执行
    y = 10;
    if (1 || ++y) {
        printf("条件成立\n");
    }
    printf("短路测试 y = %d (y没有自增)\n", y);

    printf("\n=== 10. 使用示例：成绩等级判定 ===\n");
    int scores[] = {95, 82, 73, 61, 45, 100, -5, 105};
    int count = sizeof(scores) / sizeof(scores[0]);
    for (int i = 0; i < count; i++) {
        int s = scores[i];
        char* grade;
        if (s < 0 || s > 100) {
            grade = "无效成绩";
        } else if (s >= 90) {
            grade = "A";
        } else if (s >= 80) {
            grade = "B";
        } else if (s >= 70) {
            grade = "C";
        } else if (s >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
        printf("成绩 %d: 等级 %s\n", s, grade);
    }

    return 0;
}
