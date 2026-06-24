// ============================================================
// Java 泛型深入 - 泛型类、泛型方法、协变与逆变
// 编译运行：javac GenericsAdvanced.java && java GenericsAdvanced
// ============================================================

import java.util.*;
import java.lang.reflect.*;

public class GenericsAdvanced {

    // ============================================================
    // 1. 泛型类与多类型参数
    // ============================================================

    static class Triple<T, U, V> {
        private T first;
        private U second;
        private V third;

        public Triple(T first, U second, V third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public T getFirst() { return first; }
        public U getSecond() { return second; }
        public V getThird() { return third; }

        public void setFirst(T first) { this.first = first; }
        public void setSecond(U second) { this.second = second; }
        public void setThird(V third) { this.third = third; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ", " + third + ")";
        }
    }

    static void demoMultiType() {
        System.out.println("=== 1. 多类型参数 ===");

        Triple<String, Integer, Boolean> t1 = new Triple<>("张三", 25, true);
        System.out.println("t1: " + t1);
        System.out.println("姓名: " + t1.getFirst() + ", 年龄: " + t1.getSecond());
    }

    // ============================================================
    // 2. 泛型方法
    // ============================================================

    static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    static <T> List<T> arrayToList(T[] array) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }

    static void demoGenericMethod() {
        System.out.println("\n=== 2. 泛型方法 ===");

        Integer[] ints = {1, 2, 3, 4, 5};
        String[] strs = {"A", "B", "C"};

        System.out.print("整数: ");
        printArray(ints);
        System.out.print("字符串: ");
        printArray(strs);

        System.out.println("max(10, 20) = " + max(10, 20));
        System.out.println("max('a', 'z') = " + max('a', 'z'));

        List<Integer> list = arrayToList(ints);
        System.out.println("列表: " + list);
    }

    // ============================================================
    // 3. 泛型边界 - extends 和 super
    // ============================================================

    // 上界：只能读不能写
    static double sumOfList(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // 下界：只能写不能读
    static void addIntegers(List<? super Integer> list, int n) {
        for (int i = 1; i <= n; i++) {
            list.add(i * 10);
        }
    }

    static void demoBoundedWildcard() {
        System.out.println("\n=== 3. 泛型边界 ===");

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        System.out.println("整数和: " + sumOfList(intList));
        System.out.println("浮点和: " + sumOfList(doubleList));

        List<Number> numList = new ArrayList<>();
        addIntegers(numList, 3);
        System.out.println("添加后: " + numList);
    }

    // ============================================================
    // 4. 类型擦除
    // ============================================================

    static class ErasedBox<T> {
        private T value;

        public void set(T value) { this.value = value; }
        public T get() { return value; }
    }

    static void demoTypeErasure() throws Exception {
        System.out.println("\n=== 4. 类型擦除 ===");

        ErasedBox<Integer> intBox = new ErasedBox<>();
        ErasedBox<String> strBox = new ErasedBox<>();

        // 反射查看，编译后类型已被擦除为 Object
        System.out.println("Integer Box 类型: " + intBox.getClass().getName());
        System.out.println("String Box 类型: " + strBox.getClass().getName());
        System.out.println("它们是同一个类? " + (intBox.getClass() == strBox.getClass()));

        // 编译期类型检查
        intBox.set(100);
        // intBox.set("error");  // 编译错误
    }

    // ============================================================
    // 5. 泛型接口
    // ============================================================

    interface Transformer<T, R> {
        R transform(T input);
    }

    static class StringToInt implements Transformer<String, Integer> {
        @Override
        public Integer transform(String input) {
            return Integer.parseInt(input);
        }
    }

    static class IntToString implements Transformer<Integer, String> {
        @Override
        public String transform(Integer input) {
            return "数字: " + input;
        }
    }

    static void demoGenericInterface() {
        System.out.println("\n=== 5. 泛型接口 ===");

        Transformer<String, Integer> s2i = new StringToInt();
        System.out.println("\"123\" -> " + s2i.transform("123"));

        Transformer<Integer, String> i2s = new IntToString();
        System.out.println("456 -> " + i2s.transform(456));
    }

    // ============================================================
    // 6. 泛型与继承
    // ============================================================

    static class Box2<T> {
        protected T content;
        public void set(T content) { this.content = content; }
        public T get() { return content; }
    }

    static class StringBox extends Box2<String> {
        public StringBox(String content) { this.content = content; }
        public void printLength() { System.out.println("长度: " + content.length()); }
    }

    static void demoGenericInheritance() {
        System.out.println("\n=== 6. 泛型与继承 ===");

        StringBox sb = new StringBox("Hello");
        sb.printLength();

        // 泛型类不能像普通类那样多态
        // Box2<String> box = new StringBox(...);  // 但这样可以
    }

    // ============================================================
    // 7. PECS 原则
    // ============================================================

    // Producer Extends - 从集合读取
    static void copyProducerExtends(List<? extends Number> source, List<? super Number> dest) {
        for (Number n : source) {
            dest.add(n);
        }
    }

    static void demoPECS() {
        System.out.println("\n=== 7. PECS 原则 ===");

        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5);
        List<Number> dest = new ArrayList<>();

        copyProducerExtends(source, dest);
        System.out.println("复制后: " + dest);
    }

    // ============================================================
    // 8. 泛型数组
    // ============================================================

    static void demoGenericArray() {
        System.out.println("\n=== 8. 泛型数组 ===");

        // 不能直接创建泛型数组
        // List<String>[] lists = new List<String>[10];  // 错误

        // 但可以创建带类型的 List 集合
        List<String>[] stringLists = new List[10];  // 警告
        stringLists[0] = new ArrayList<>();
        stringLists[0].add("Hello");
        System.out.println("数组元素: " + stringLists[0]);

        // 推荐使用 List<List<String>>
        List<List<String>> listOfLists = new ArrayList<>();
        listOfLists.add(Arrays.asList("A", "B"));
        listOfLists.add(Arrays.asList("C", "D"));
        System.out.println("List 集合: " + listOfLists);
    }

    // ============================================================
    // 9. 泛型静态方法
    // ============================================================

    static class Utils {
        public static <T> T getMiddle(T... values) {
            return values[values.length / 2];
        }

        public static <T extends Number> double average(T... values) {
            double sum = 0;
            for (T v : values) {
                sum += v.doubleValue();
            }
            return sum / values.length;
        }
    }

    static void demoStaticMethod() {
        System.out.println("\n=== 9. 泛型静态方法 ===");

        System.out.println("中间值(int): " + Utils.getMiddle(1, 2, 3, 4, 5));
        System.out.println("中间值(string): " + Utils.getMiddle("A", "B", "C"));
        System.out.println("平均值: " + Utils.average(1.0, 2.0, 3.0, 4.0, 5.0));
    }

    public static void main(String[] args) throws Exception {
        demoMultiType();
        demoGenericMethod();
        demoBoundedWildcard();
        demoTypeErasure();
        demoGenericInterface();
        demoGenericInheritance();
        demoPECS();
        demoGenericArray();
        demoStaticMethod();

        System.out.println("\n=== 运行完成 ===");
    }
}