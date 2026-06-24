/* ============================================================
 * C 语言常用操作 - 文件操作和错误处理
 * 编译运行：gcc 01_文件操作.c -o 01_文件操作 && ./01_文件操作
 * ============================================================ */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

int main() {
    printf("=== 1. 写入文件 (fprintf) ===\n");
    FILE *fp = fopen("test.txt", "w");
    if (fp == NULL) {
        perror("打开文件失败");
        return 1;
    }
    fprintf(fp, "第一行\n");
    fprintf(fp, "第二行\n");
    fprintf(fp, "第三行\n");
    fclose(fp);
    printf("已写入 test.txt\n");

    printf("\n=== 2. 读取文件 (fscanf) ===\n");
    fp = fopen("test.txt", "r");
    if (fp == NULL) {
        perror("打开文件失败");
        return 1;
    }
    char line[100];
    printf("文件内容:\n");
    while (fscanf(fp, "%[^\n]\n", line) != EOF) {
        printf("  %s\n", line);
    }
    fclose(fp);

    printf("\n=== 3. 按行读取 (fgets) ===\n");
    fp = fopen("test.txt", "r");
    char buffer[256];
    int line_num = 0;
    while (fgets(buffer, sizeof(buffer), fp) != NULL) {
        line_num++;
        // 去掉换行符
        buffer[strcspn(buffer, "\n")] = '\0';
        printf("第%d行: %s\n", line_num, buffer);
    }
    fclose(fp);

    printf("\n=== 4. 字符读写 (fgetc/fputc) ===\n");
    fp = fopen("char_test.txt", "w");
    char str[] = "Hello C File!";
    for (int i = 0; str[i] != '\0'; i++) {
        fputc(str[i], fp);
    }
    fclose(fp);
    printf("已写入 char_test.txt\n");

    fp = fopen("char_test.txt", "r");
    printf("读取内容: ");
    int ch;
    while ((ch = fgetc(fp)) != EOF) {
        putchar(ch);
    }
    printf("\n");
    fclose(fp);

    printf("\n=== 5. 追加写入 ===\n");
    fp = fopen("test.txt", "a");
    fprintf(fp, "第四行（追加）\n");
    fclose(fp);
    printf("已追加内容\n");
    
    fp = fopen("test.txt", "r");
    printf("追加后内容:\n");
    while (fgets(buffer, sizeof(buffer), fp) != NULL) {
        printf("  %s", buffer);
    }
    fclose(fp);

    printf("\n=== 6. 文件定位 (fseek/ftell) ===\n");
    fp = fopen("test.txt", "r");
    fseek(fp, 0, SEEK_END);  // 定位到文件末尾
    long file_size = ftell(fp);
    printf("文件大小: %ld 字节\n", file_size);
    
    rewind(fp);  // 回到文件开头
    printf("回到文件开头，位置: %ld\n", ftell(fp));
    fclose(fp);

    printf("\n=== 7. 二进制文件读写 ===\n");
    int data[] = {1, 2, 3, 4, 5};
    fp = fopen("binary.dat", "wb");
    fwrite(data, sizeof(int), 5, fp);
    fclose(fp);
    printf("已写入二进制文件\n");
    
    int read_data[5];
    fp = fopen("binary.dat", "rb");
    size_t count = fread(read_data, sizeof(int), 5, fp);
    fclose(fp);
    printf("读取了 %zu 个整数: ", count);
    for (int i = 0; i < count; i++) {
        printf("%d ", read_data[i]);
    }
    printf("\n");

    printf("\n=== 8. 结构体写入二进制文件 ===\n");
    struct Student {
        char name[50];
        int age;
        float score;
    };
    
    struct Student stu = {"张三", 20, 95.5f};
    fp = fopen("student.dat", "wb");
    fwrite(&stu, sizeof(struct Student), 1, fp);
    fclose(fp);
    
    struct Student read_stu;
    fp = fopen("student.dat", "rb");
    fread(&read_stu, sizeof(struct Student), 1, fp);
    fclose(fp);
    printf("读取学生: %s, %d岁, 成绩: %.1f\n", read_stu.name, read_stu.age, read_stu.score);

    printf("\n=== 9. 错误处理 ===\n");
    fp = fopen("nonexistent.txt", "r");
    if (fp == NULL) {
        printf("打开文件失败\n");
        printf("errno = %d\n", errno);
        perror("错误信息");
    }

    printf("\n=== 10. 移除和重命名文件 ===\n");
    rename("char_test.txt", "char_renamed.txt");
    printf("文件已重命名\n");
    
    if (remove("char_renamed.txt") == 0) {
        printf("文件已删除\n");
    }

    printf("\n=== 11. 标准输入输出 ===\n");
    printf("printf 格式化输出: %d, %f, %s\n", 42, 3.14, "hello");
    
    printf("标准错误输出(stderr)示例:\n");
    fprintf(stderr, "这是一条错误信息\n");

    printf("\n=== 12. sprintf 字符串格式化 ===\n");
    char output[100];
    sprintf(output, "姓名: %s, 年龄: %d", "李四", 25);
    printf("%s\n", output);

    printf("\n=== 清理测试文件 ===\n");
    remove("test.txt");
    remove("binary.dat");
    remove("student.dat");
    printf("已清理测试文件\n");

    return 0;
}
