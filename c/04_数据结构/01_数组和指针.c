/* ============================================================
 * C 语言数据结构 - 数组和指针
 * 编译运行：gcc 01_数组和指针.c -o 01_数组和指针 && ./01_数组和指针
 * ============================================================ */

#include <stdio.h>
#include <string.h>

int main() {
    printf("=== 1. 一维数组 ===\n");
    int numbers[5] = {1, 2, 3, 4, 5};
    printf("数组元素:\n");
    for (int i = 0; i < 5; i++) {
        printf("numbers[%d] = %d\n", i, numbers[i]);
    }

    printf("\n=== 2. 数组的初始化 ===\n");
    int arr1[5] = {0};          // 所有元素初始化为0
    int arr2[] = {1, 2, 3};     // 自动计算长度
    printf("arr1: {");
    for (int i = 0; i < 5; i++) {
        printf("%d%s", arr1[i], i < 4 ? ", " : "}\n");
    }
    printf("arr2 长度: %zu\n", sizeof(arr2) / sizeof(arr2[0]));

    printf("\n=== 3. 数组和指针的关系 ===\n");
    int nums[5] = {10, 20, 30, 40, 50};
    int *p = nums;  // 数组名是指向第一个元素的指针

    printf("nums = %p\n", (void*)nums);
    printf("p = %p\n", (void*)p);
    printf("nums[0] = %d\n", nums[0]);
    printf("*p = %d\n", *p);
    printf("nums[1] = %d\n", nums[1]);
    printf("*(p + 1) = %d\n", *(p + 1));

    printf("\n=== 4. 指针运算 ===\n");
    int arr[] = {100, 200, 300, 400, 500};
    int *ptr = arr;

    printf("初始: *ptr = %d\n", *ptr);
    ptr++;  // 指针向后移动一个元素
    printf("ptr++ 后: *ptr = %d\n", *ptr);
    ptr += 2;
    printf("ptr += 2 后: *ptr = %d\n", *ptr);
    ptr--;
    printf("ptr-- 后: *ptr = %d\n", *ptr);

    printf("\n=== 5. 指针和数组遍历 ===\n");
    int a[] = {1, 2, 3, 4, 5};
    int len = sizeof(a) / sizeof(a[0]);
    printf("使用指针遍历数组:\n");
    for (int *p2 = a; p2 < a + len; p2++) {
        printf("%d ", *p2);
    }
    printf("\n");

    printf("\n=== 6. 二维数组 ===\n");
    int matrix[3][3] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    printf("3x3 矩阵:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }

    printf("\n=== 7. 字符串 ===\n");
    char str1[] = "Hello";
    char str2[20] = "World";
    
    printf("str1 = %s\n", str1);
    printf("str2 = %s\n", str2);
    printf("str1 长度: %zu\n", strlen(str1));

    printf("\n=== 8. 字符串操作 ===\n");
    char dest[50];
    
    // 字符串拷贝
    strcpy(dest, str1);
    printf("strcpy(dest, str1): %s\n", dest);
    
    // 字符串拼接
    strcat(dest, " ");
    strcat(dest, str2);
    printf("strcat 后: %s\n", dest);
    
    // 字符串比较
    printf("strcmp(\"apple\", \"apple\"): %d\n", strcmp("apple", "apple"));
    printf("strcmp(\"apple\", \"banana\"): %d\n", strcmp("apple", "banana"));

    printf("\n=== 9. 字符数组的输入输出 ===\n");
    char name[50] = "张三";
    printf("姓名: %s\n", name);

    printf("\n=== 10. 动态内存分配 ===\n");
    // malloc 分配内存
    int *dynamic_arr = (int*)malloc(5 * sizeof(int));
    if (dynamic_arr != NULL) {
        for (int i = 0; i < 5; i++) {
            dynamic_arr[i] = i * 10;
        }
        printf("动态数组: ");
        for (int i = 0; i < 5; i++) {
            printf("%d ", dynamic_arr[i]);
        }
        printf("\n");
        
        // realloc 重新分配
        dynamic_arr = (int*)realloc(dynamic_arr, 10 * sizeof(int));
        printf("扩容后大小: 10个元素\n");
        
        free(dynamic_arr);  // 释放内存
        printf("内存已释放\n");
    }

    printf("\n=== 11. 指针的指针 ===\n");
    int value = 42;
    int *p_val = &value;
    int **pp_val = &p_val;
    
    printf("value = %d\n", value);
    printf("*p_val = %d\n", *p_val);
    printf("**pp_val = %d\n", **pp_val);

    printf("\n=== 12. 数组作为函数参数的本质 ===\n");
    printf("数组传递给函数时，会退化为指针\n");
    printf("所以函数中无法用 sizeof 求数组长度\n");
    printf("通常需要额外传递数组大小参数\n");

    return 0;
}
