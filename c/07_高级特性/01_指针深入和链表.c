/* ============================================================
 * C 语言高级特性 - 指针深入、函数指针、链表
 * 编译运行：gcc 01_指针深入和链表.c -o 01_指针深入和链表 && ./01_指针深入和链表
 * ============================================================ */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// ============================================================
// 1. 指针的指针
// ============================================================
void change_pointer(int **pp) {
    // 修改指针本身的值（指向新的地址）
    static int x = 100;
    *pp = &x;
}

void demo_pointer_to_pointer() {
    printf("=== 1. 指针的指针 ===\n");

    int a = 10;
    int *p = &a;
    int **pp = &p;

    printf("a = %d\n", a);
    printf("*p = %d\n", *p);
    printf("**pp = %d\n", **pp);

    // 通过二级指针修改
    **pp = 20;
    printf("修改后 a = %d\n", a);

    // 函数中修改指针指向
    int *ptr = NULL;
    change_pointer(&ptr);
    printf("函数修改后 *ptr = %d\n", *ptr);
}

// ============================================================
// 2. 函数指针
// ============================================================
int add(int a, int b) { return a + b; }
int subtract(int a, int b) { return a - b; }
int multiply(int a, int b) { return a * b; }

void print_element(int x) {
    printf("%d ", x);
}

// 函数指针作为参数（回调函数）
void foreach_array(int *arr, int size, void (*callback)(int)) {
    for (int i = 0; i < size; i++) {
        callback(arr[i]);
    }
}

void demo_function_pointer() {
    printf("\n=== 2. 函数指针 ===\n");

    // 定义函数指针
    int (*operation)(int, int);

    operation = add;
    printf("10 + 5 = %d\n", operation(10, 5));

    operation = subtract;
    printf("10 - 5 = %d\n", operation(10, 5));

    operation = multiply;
    printf("10 * 5 = %d\n", operation(10, 5));

    // 函数指针数组
    int (*ops[])(int, int) = {add, subtract, multiply};
    printf("函数指针数组: ");
    for (int i = 0; i < 3; i++) {
        printf("%d ", ops[i](10, 3));
    }
    printf("\n");

    // 回调函数示例
    int arr[] = {1, 2, 3, 4, 5};
    printf("遍历数组: ");
    foreach_array(arr, 5, print_element);
    printf("\n");
}

// ============================================================
// 3. 链表 - 单向链表
// ============================================================
typedef struct Node {
    int data;
    struct Node *next;
} Node;

// 创建新节点
Node* create_node(int data) {
    Node *new_node = (Node *)malloc(sizeof(Node));
    if (new_node == NULL) {
        printf("内存分配失败\n");
        exit(1);
    }
    new_node->data = data;
    new_node->next = NULL;
    return new_node;
}

// 头部插入
Node* list_insert_head(Node *head, int data) {
    Node *new_node = create_node(data);
    new_node->next = head;
    return new_node;
}

// 尾部插入
Node* list_insert_tail(Node *head, int data) {
    Node *new_node = create_node(data);
    if (head == NULL) {
        return new_node;
    }

    Node *current = head;
    while (current->next != NULL) {
        current = current->next;
    }
    current->next = new_node;
    return head;
}

// 按位置插入
Node* list_insert_at(Node *head, int index, int data) {
    if (index == 0 || head == NULL) {
        return list_insert_head(head, data);
    }

    Node *current = head;
    for (int i = 0; i < index - 1 && current != NULL; i++) {
        current = current->next;
    }

    if (current == NULL) {
        return head;  // 位置超出范围
    }

    Node *new_node = create_node(data);
    new_node->next = current->next;
    current->next = new_node;
    return head;
}

// 删除节点（按值）
Node* list_delete(Node *head, int value) {
    if (head == NULL) return NULL;

    // 删除头节点
    if (head->data == value) {
        Node *temp = head;
        head = head->next;
        free(temp);
        return head;
    }

    Node *current = head;
    while (current->next != NULL && current->next->data != value) {
        current = current->next;
    }

    if (current->next != NULL) {
        Node *temp = current->next;
        current->next = temp->next;
        free(temp);
    }
    return head;
}

// 查找节点
int list_search(Node *head, int value) {
    Node *current = head;
    int index = 0;
    while (current != NULL) {
        if (current->data == value) {
            return index;
        }
        current = current->next;
        index++;
    }
    return -1;
}

// 打印链表
void list_print(Node *head) {
    Node *current = head;
    while (current != NULL) {
        printf("%d -> ", current->data);
        current = current->next;
    }
    printf("NULL\n");
}

// 释放链表
void list_free(Node *head) {
    Node *current = head;
    while (current != NULL) {
        Node *temp = current;
        current = current->next;
        free(temp);
    }
}

void demo_linked_list() {
    printf("\n=== 3. 单向链表 ===\n");

    Node *head = NULL;

    // 尾部插入
    printf("尾部插入 10, 20, 30: ");
    head = list_insert_tail(head, 10);
    head = list_insert_tail(head, 20);
    head = list_insert_tail(head, 30);
    list_print(head);

    // 头部插入
    printf("头部插入 5: ");
    head = list_insert_head(head, 5);
    list_print(head);

    // 中间插入
    printf("位置2插入 15: ");
    head = list_insert_at(head, 2, 15);
    list_print(head);

    // 查找
    int pos = list_search(head, 20);
    printf("查找 20 的位置: %d\n", pos);

    // 删除
    printf("删除 15: ");
    head = list_delete(head, 15);
    list_print(head);

    printf("删除头节点 5: ");
    head = list_delete(head, 5);
    list_print(head);

    list_free(head);
}

// ============================================================
// 4. 动态内存分配
// ============================================================
void demo_dynamic_memory() {
    printf("\n=== 4. 动态内存分配 ===\n");

    // malloc
    int *arr1 = (int *)malloc(5 * sizeof(int));
    if (arr1 == NULL) {
        printf("malloc 失败\n");
        return;
    }
    for (int i = 0; i < 5; i++) {
        arr1[i] = (i + 1) * 10;
    }
    printf("malloc 数组: ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", arr1[i]);
    }
    printf("\n");
    free(arr1);

    // calloc - 初始化为0
    int *arr2 = (int *)calloc(5, sizeof(int));
    printf("calloc 数组(初始为0): ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", arr2[i]);
    }
    printf("\n");

    // realloc - 重新分配
    arr2 = (int *)realloc(arr2, 10 * sizeof(int));
    for (int i = 5; i < 10; i++) {
        arr2[i] = (i + 1) * 10;
    }
    printf("realloc 后: ");
    for (int i = 0; i < 10; i++) {
        printf("%d ", arr2[i]);
    }
    printf("\n");
    free(arr2);

    // 二维动态数组
    int rows = 3, cols = 4;
    int **matrix = (int **)malloc(rows * sizeof(int *));
    for (int i = 0; i < rows; i++) {
        matrix[i] = (int *)malloc(cols * sizeof(int));
    }

    int count = 1;
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            matrix[i][j] = count++;
        }
    }

    printf("二维动态数组:\n");
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            printf("%3d ", matrix[i][j]);
        }
        printf("\n");
    }

    // 释放二维数组
    for (int i = 0; i < rows; i++) {
        free(matrix[i]);
    }
    free(matrix);
}

// ============================================================
// 5. 位域
// ============================================================
void demo_bit_field() {
    printf("\n=== 5. 位域 ===\n");

    struct Status {
        unsigned int is_online : 1;   // 1位
        unsigned int is_muted   : 1;  // 1位
        unsigned int priority   : 3;  // 3位 (0-7)
        unsigned int level      : 4;  // 4位 (0-15)
    };

    struct Status s = {0};
    s.is_online = 1;
    s.is_muted = 0;
    s.priority = 5;
    s.level = 10;

    printf("is_online: %u\n", s.is_online);
    printf("is_muted: %u\n", s.is_muted);
    printf("priority: %u\n", s.priority);
    printf("level: %u\n", s.level);
    printf("结构体大小: %zu 字节\n", sizeof(s));
}

// ============================================================
// 6. const 指针
// ============================================================
void demo_const_pointer() {
    printf("\n=== 6. const 指针 ===\n");

    int x = 10, y = 20;

    // 指向常量的指针：不能通过指针修改值，但可以改变指向
    const int *p1 = &x;
    // *p1 = 30;  // 错误！不能修改值
    p1 = &y;      // 可以修改指向
    printf("p1 指向 y: %d\n", *p1);

    // 常量指针：指针本身是常量，不能改变指向，但可以修改值
    int * const p2 = &x;
    *p2 = 30;     // 可以修改值
    // p2 = &y;   // 错误！不能改变指向
    printf("p2 指向的 x: %d\n", x);

    // 指向常量的常量指针：都不能改
    const int * const p3 = &x;
    printf("p3: %d\n", *p3);
}

int main() {
    demo_pointer_to_pointer();
    demo_function_pointer();
    demo_linked_list();
    demo_dynamic_memory();
    demo_bit_field();
    demo_const_pointer();

    printf("\n=== 运行完成 ===\n");
    return 0;
}