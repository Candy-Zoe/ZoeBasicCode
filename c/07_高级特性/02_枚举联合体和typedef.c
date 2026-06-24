/* ============================================================
 * C 语言高级特性 - 枚举、联合体、typedef
 * 编译运行：gcc 02_枚举联合体和typedef.c -o 02_枚举联合体和typedef && ./02_枚举联合体和typedef
 * ============================================================ */

#include <stdio.h>
#include <string.h>

// ============================================================
// 1. 枚举类型
// ============================================================

// 基本枚举
enum Weekday {
    MONDAY,    // 0
    TUESDAY,   // 1
    WEDNESDAY, // 2
    THURSDAY,  // 3
    FRIDAY,    // 4
    SATURDAY,  // 5
    SUNDAY     // 6
};

// 指定值的枚举
enum Color {
    RED = 1,
    GREEN = 2,
    BLUE = 4,
    YELLOW = 8,
    PURPLE = 16
};

// 状态枚举
enum Status {
    SUCCESS = 0,
    ERROR_INVALID_INPUT = -1,
    ERROR_OUT_OF_MEMORY = -2,
    ERROR_FILE_NOT_FOUND = -3
};

const char* get_weekday_name(enum Weekday day) {
    switch (day) {
        case MONDAY:    return "星期一";
        case TUESDAY:   return "星期二";
        case WEDNESDAY: return "星期三";
        case THURSDAY:  return "星期四";
        case FRIDAY:    return "星期五";
        case SATURDAY:  return "星期六";
        case SUNDAY:    return "星期日";
        default:        return "未知";
    }
}

const char* get_status_message(enum Status status) {
    switch (status) {
        case SUCCESS:              return "操作成功";
        case ERROR_INVALID_INPUT:  return "输入无效";
        case ERROR_OUT_OF_MEMORY:  return "内存不足";
        case ERROR_FILE_NOT_FOUND: return "文件未找到";
        default:                   return "未知错误";
    }
}

void demo_enum() {
    printf("=== 1. 枚举类型 ===\n");

    // 基本枚举使用
    enum Weekday today = WEDNESDAY;
    printf("今天是: %s (数值: %d)\n", get_weekday_name(today), today);

    // 遍历枚举
    printf("一周七天:\n");
    for (int i = MONDAY; i <= SUNDAY; i++) {
        printf("  %d: %s\n", i, get_weekday_name((enum Weekday)i));
    }

    // 状态枚举
    enum Status result = ERROR_FILE_NOT_FOUND;
    printf("状态: %s (代码: %d)\n", get_status_message(result), result);

    // 枚举作为标志位（位运算）
    enum Color mix = RED | BLUE;  // 紫色 = 1 | 4 = 5
    printf("RED | BLUE = %d\n", mix);
    if (mix & RED) {
        printf("包含红色\n");
    }
}

// ============================================================
// 2. 联合体 union
// ============================================================

// 基本联合体
union Data {
    int i;
    float f;
    char c;
    char str[20];
};

// 使用联合体查看内存表示
union IntBytes {
    int value;
    unsigned char bytes[sizeof(int)];
};

// 变体类型（类似弱类型）
typedef enum {
    TYPE_INT,
    TYPE_FLOAT,
    TYPE_STRING
} VariantType;

typedef struct {
    VariantType type;
    union {
        int int_val;
        float float_val;
        char string_val[50];
    } data;
} Variant;

void print_variant(Variant v) {
    switch (v.type) {
        case TYPE_INT:
            printf("整数: %d\n", v.data.int_val);
            break;
        case TYPE_FLOAT:
            printf("浮点数: %.2f\n", v.data.float_val);
            break;
        case TYPE_STRING:
            printf("字符串: %s\n", v.data.string_val);
            break;
    }
}

void demo_union() {
    printf("\n=== 2. 联合体 union ===\n");

    // 基本用法
    union Data data;
    data.i = 65;
    printf("data.i = %d\n", data.i);
    printf("data.c = %c (共享内存)\n", data.c);

    data.f = 3.14f;
    printf("data.f = %.2f (覆盖了i)\n", data.f);

    strcpy(data.str, "Hello");
    printf("data.str = %s\n", data.str);

    printf("union 大小: %zu 字节\n", sizeof(union Data));

    // 查看整数的字节表示
    printf("\n整数的字节表示:\n");
    union IntBytes ib;
    ib.value = 0x12345678;
    printf("值: 0x%08X\n", ib.value);
    printf("字节: ");
    for (int i = 0; i < (int)sizeof(int); i++) {
        printf("0x%02X ", ib.bytes[i]);
    }
    printf("\n");
    if (ib.bytes[0] == 0x78) {
        printf("当前系统是小端序(Little Endian)\n");
    } else {
        printf("当前系统是大端序(Big Endian)\n");
    }

    // 变体类型
    printf("\n变体类型示例:\n");
    Variant v1 = {TYPE_INT, .data.int_val = 42};
    Variant v2 = {TYPE_FLOAT, .data.float_val = 3.14f};
    Variant v3;
    v3.type = TYPE_STRING;
    strcpy(v3.data.string_val, "Hello World");

    print_variant(v1);
    print_variant(v2);
    print_variant(v3);
}

// ============================================================
// 3. typedef 类型定义
// ============================================================

// 基本类型别名
typedef unsigned char byte;
typedef unsigned int uint;
typedef char* string;

// 结构体类型别名
typedef struct {
    double real;
    double imag;
} Complex;

// 指针类型别名
typedef int* IntPtr;

// 函数指针类型别名
typedef int (*CompareFunc)(int, int);

// 数组类型别名
typedef int IntArray5[5];  // IntArray5 是 "5个int的数组" 类型

int compare_asc(int a, int b) { return a - b; }
int compare_desc(int a, int b) { return b - a; }

void sort_array(int *arr, int size, CompareFunc cmp) {
    for (int i = 0; i < size - 1; i++) {
        for (int j = i + 1; j < size; j++) {
            if (cmp(arr[i], arr[j]) > 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}

Complex complex_add(Complex a, Complex b) {
    Complex result = {a.real + b.real, a.imag + b.imag};
    return result;
}

void demo_typedef() {
    printf("\n=== 3. typedef 类型定义 ===\n");

    // 基本类型别名
    byte b = 0xFF;
    uint u = 4294967295U;
    printf("byte: %u\n", b);
    printf("uint: %u\n", u);

    // 结构体别名
    Complex c1 = {3.0, 4.0};
    Complex c2 = {1.0, 2.0};
    Complex sum = complex_add(c1, c2);
    printf("复数相加: %.1f + %.1fi\n", sum.real, sum.imag);

    // 指针别名
    int x = 10;
    IntPtr p = &x;
    printf("指针: %d\n", *p);

    // 函数指针别名
    int arr[] = {5, 2, 8, 1, 9, 3};
    int size = 6;

    printf("升序排序: ");
    sort_array(arr, size, compare_asc);
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    printf("降序排序: ");
    sort_array(arr, size, compare_desc);
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    // 数组类型别名
    IntArray5 nums = {10, 20, 30, 40, 50};
    printf("数组: ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", nums[i]);
    }
    printf("\n");
}

// ============================================================
// 4. 枚举 + 结构体 + typedef 综合示例
// ============================================================

typedef enum {
    SHAPE_CIRCLE,
    SHAPE_RECTANGLE,
    SHAPE_TRIANGLE
} ShapeType;

typedef struct {
    ShapeType type;
    union {
        struct { double radius; } circle;
        struct { double width, height; } rectangle;
        struct { double a, b, c; } triangle;
    } data;
} Shape;

double shape_area(Shape s) {
    switch (s.type) {
        case SHAPE_CIRCLE:
            return 3.14159 * s.data.circle.radius * s.data.circle.radius;
        case SHAPE_RECTANGLE:
            return s.data.rectangle.width * s.data.rectangle.height;
        case SHAPE_TRIANGLE: {
            double a = s.data.triangle.a;
            double b = s.data.triangle.b;
            double c = s.data.triangle.c;
            double p = (a + b + c) / 2;
            return p * (p - a) * (p - b) * (p - c);  // 简化海伦公式
        }
        default:
            return 0;
    }
}

void demo_combined() {
    printf("\n=== 4. 综合示例: 图形计算器 ===\n");

    Shape shapes[] = {
        {SHAPE_CIRCLE, .data.circle.radius = 5.0},
        {SHAPE_RECTANGLE, .data.rectangle = {4.0, 6.0}},
        {SHAPE_TRIANGLE, .data.triangle = {3.0, 4.0, 5.0}}
    };

    const char* names[] = {"圆形", "矩形", "三角形"};
    int count = sizeof(shapes) / sizeof(shapes[0]);

    for (int i = 0; i < count; i++) {
        printf("%s面积: %.2f\n", names[i], shape_area(shapes[i]));
    }
}

int main() {
    demo_enum();
    demo_union();
    demo_typedef();
    demo_combined();

    printf("\n=== 运行完成 ===\n");
    return 0;
}