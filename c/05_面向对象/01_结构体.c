/* ============================================================
 * C 语言结构体
 * 编译运行：gcc 01_结构体.c -o 01_结构体 && ./01_结构体
 * ============================================================ */

#include <stdio.h>
#include <string.h>

// 定义结构体
struct Point {
    int x;
    int y;
};

struct Student {
    char name[50];
    int age;
    float score;
};

// 嵌套结构体
struct Date {
    int year;
    int month;
    int day;
};

struct Person {
    char name[50];
    int age;
    struct Date birthday;
};

// 结构体指针函数参数
void print_student(struct Student s);
void update_score(struct Student *s, float new_score);

// typedef 定义类型别名
typedef struct {
    double real;
    double imag;
} Complex;

int main() {
    printf("=== 1. 结构体的定义和初始化 ===\n");
    struct Point p1;
    p1.x = 10;
    p1.y = 20;
    printf("p1: x = %d, y = %d\n", p1.x, p1.y);

    // 初始化方式
    struct Point p2 = {30, 40};
    printf("p2: x = %d, y = %d\n", p2.x, p2.y);

    // 指定成员初始化（C99）
    struct Point p3 = {.y = 50, .x = 60};
    printf("p3: x = %d, y = %d\n", p3.x, p3.y);

    printf("\n=== 2. 结构体变量 ===\n");
    struct Student stu1;
    strcpy(stu1.name, "张三");
    stu1.age = 20;
    stu1.score = 95.5f;
    
    printf("学生信息:\n");
    printf("  姓名: %s\n", stu1.name);
    printf("  年龄: %d\n", stu1.age);
    printf("  成绩: %.1f\n", stu1.score);

    printf("\n=== 3. 结构体赋值 ===\n");
    struct Student stu2 = stu1;  // 结构体可以直接赋值
    strcpy(stu2.name, "李四");
    printf("stu1.name = %s\n", stu1.name);
    printf("stu2.name = %s\n", stu2.name);

    printf("\n=== 4. 结构体作为函数参数 ===\n");
    print_student(stu1);

    printf("\n=== 5. 结构体指针 ===\n");
    struct Student stu = {"王五", 22, 88.5f};
    struct Student *p_stu = &stu;
    
    // 使用 -> 访问成员
    printf("通过指针访问:\n");
    printf("  name: %s\n", p_stu->name);
    printf("  age: %d\n", p_stu->age);
    printf("  score: %.1f\n", p_stu->score);

    printf("\n=== 6. 结构体指针作为函数参数 ===\n");
    printf("修改前成绩: %.1f\n", stu.score);
    update_score(&stu, 99.0f);
    printf("修改后成绩: %.1f\n", stu.score);

    printf("\n=== 7. 嵌套结构体 ===\n");
    struct Person person;
    strcpy(person.name, "赵六");
    person.age = 30;
    person.birthday.year = 1995;
    person.birthday.month = 6;
    person.birthday.day = 15;
    
    printf("人员信息:\n");
    printf("  姓名: %s\n", person.name);
    printf("  年龄: %d\n", person.age);
    printf("  生日: %d-%d-%d\n", person.birthday.year, person.birthday.month, person.birthday.day);

    printf("\n=== 8. 结构体数组 ===\n");
    struct Student students[3] = {
        {"小明", 18, 90.0f},
        {"小红", 19, 85.5f},
        {"小刚", 20, 92.0f}
    };
    
    printf("学生列表:\n");
    for (int i = 0; i < 3; i++) {
        printf("  %d. %s, %d岁, 成绩: %.1f\n", 
               i + 1, students[i].name, students[i].age, students[i].score);
    }

    printf("\n=== 9. typedef 类型别名 ===\n");
    Complex c1 = {3.0, 4.0};
    printf("复数: %.1f + %.1fi\n", c1.real, c1.imag);

    printf("\n=== 10. 结构体大小和内存对齐 ===\n");
    struct Test1 {
        char a;
        int b;
        char c;
    };
    
    struct Test2 {
        int a;
        char b;
        char c;
    };
    
    printf("Test1 大小: %zu 字节\n", sizeof(struct Test1));
    printf("Test2 大小: %zu 字节\n", sizeof(struct Test2));
    printf("(内存对齐可能导致大小不同)\n");

    printf("\n=== 11. 枚举 ===\n");
    enum Weekday {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    };
    
    enum Weekday day = WEDNESDAY;
    printf("星期几: %d (0=周一, 2=周三)\n", day);

    printf("\n=== 12. 联合 union ===\n");
    union Data {
        int i;
        float f;
        char str[20];
    };
    
    union Data data;
    data.i = 10;
    printf("data.i = %d\n", data.i);
    data.f = 3.14f;
    printf("data.f = %.2f (覆盖了i)\n", data.f);
    printf("union 所有成员共享同一块内存\n");

    return 0;
}

void print_student(struct Student s) {
    printf("学生信息: %s, %d岁, 成绩: %.1f\n", s.name, s.age, s.score);
}

void update_score(struct Student *s, float new_score) {
    s->score = new_score;
}
