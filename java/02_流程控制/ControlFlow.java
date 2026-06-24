// ============================================================
// Java 流程控制
// 编译运行：javac ControlFlow.java && java ControlFlow
// ============================================================

import java.util.Arrays;
import java.util.List;

public class ControlFlow {
    public static void main(String[] args) {
        System.out.println("=== 1. if-else 语句 ===");
        int score = 85;
        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 80) {
            System.out.println("良好");
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }

        System.out.println("\n=== 2. switch 语句 ===");
        int day = 3;
        switch (day) {
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
            case 7:
                System.out.println("周末");
                break;
            default:
                System.out.println("无效");
        }

        System.out.println("\n=== 3. switch 表达式 (Java 14+) ===");
        String dayName = switch (day) {
            case 1 -> "星期一";
            case 2 -> "星期二";
            case 3 -> "星期三";
            case 4 -> "星期四";
            case 5 -> "星期五";
            case 6, 7 -> "周末";
            default -> "无效";
        };
        System.out.println("第" + day + "天是: " + dayName);

        System.out.println("\n=== 4. 三元运算符 ===");
        int a = 10, b = 20;
        int max = a > b ? a : b;
        System.out.println(a + " 和 " + b + " 较大的是: " + max);

        System.out.println("\n=== 5. for 循环 ===");
        for (int i = 0; i < 5; i++) {
            System.out.println("第 " + (i + 1) + " 次循环");
        }

        System.out.println("\n=== 6. 增强 for 循环 ===");
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.print("遍历数组: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        List<String> fruits = Arrays.asList("苹果", "香蕉", "橙子");
        System.out.print("遍历列表: ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        System.out.println("\n=== 7. while 循环 ===");
        int count = 1;
        while (count <= 5) {
            System.out.println("count = " + count);
            count++;
        }

        System.out.println("\n=== 8. do-while 循环 ===");
        int num = 1;
        do {
            System.out.println("num = " + num);
            num++;
        } while (num <= 5);

        System.out.println("\n=== 9. break 和 continue ===");
        System.out.print("break 示例（到5停止）: ");
        for (int i = 0; i < 10; i++) {
            if (i == 5) break;
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("continue 示例（跳过偶数）: ");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("\n=== 10. 嵌套循环（九九乘法表） ===");
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "x" + i + "=" + (i * j) + "\t");
            }
            System.out.println();
        }

        System.out.println("\n=== 11. 使用示例：斐波那契数列 ===");
        System.out.print("前10项: ");
        int prev = 0, curr = 1;
        for (int i = 0; i < 10; i++) {
            System.out.print(prev + " ");
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        System.out.println();
    }
}
