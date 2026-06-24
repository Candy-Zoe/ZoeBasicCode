// ============================================================
// Java 高级特性 - 泛型、集合、Stream、枚举
// 编译运行：javac GenericsAndCollections.java && java GenericsAndCollections
// ============================================================

import java.util.*;
import java.util.stream.*;

// ============================================================
// 1. 泛型类
// ============================================================

class Box<T> {
    private T content;

    public void set(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }

    public <U> void printWithOther(U other) {
        System.out.println("content: " + content + ", other: " + other);
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
}

// 泛型方法类
class Utils {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static <T extends Number> double sum(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }
}

public class GenericsAndCollections {

    static void demoGenericClass() {
        System.out.println("=== 1. 泛型类 ===");

        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("intBox: " + intBox.get());

        Box<String> strBox = new Box<>();
        strBox.set("Hello");
        System.out.println("strBox: " + strBox.get());

        Pair<String, Integer> pair = new Pair<>("张三", 25);
        System.out.println("pair: " + pair.getKey() + " -> " + pair.getValue());
    }

    // ============================================================
    // 2. 泛型方法
    // ============================================================

    static void demoGenericMethod() {
        System.out.println("\n=== 2. 泛型方法 ===");

        Integer[] ints = {1, 2, 3, 4, 5};
        String[] strs = {"A", "B", "C"};

        System.out.print("整数数组: ");
        Utils.printArray(ints);
        System.out.print("字符串数组: ");
        Utils.printArray(strs);

        System.out.println("max(10, 20) = " + Utils.max(10, 20));
        System.out.println("max('a', 'z') = " + Utils.max('a', 'z'));
        System.out.println("sum(3, 4.5) = " + Utils.sum(3, 4.5));
    }

    // ============================================================
    // 3. 通配符
    // ============================================================

    static void printListUpper(List<? extends Number> list) {
        for (Number n : list) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    static void addNumber(List<? super Integer> list, int count) {
        for (int i = 1; i <= count; i++) {
            list.add(i * 10);
        }
    }

    static void demoWildcard() {
        System.out.println("\n=== 3. 泛型通配符 ===");

        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        System.out.print("整数列表: ");
        printListUpper(intList);
        System.out.print("浮点列表: ");
        printListUpper(doubleList);

        List<Number> numList = new ArrayList<>();
        addNumber(numList, 5);
        System.out.print("添加后: ");
        printListUpper(numList);
    }

    // ============================================================
    // 4. 集合框架进阶
    // ============================================================

    static void demoCollections() {
        System.out.println("\n=== 4. 集合框架进阶 ===");

        // LinkedList
        System.out.println("--- LinkedList ---");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("苹果");
        linkedList.addFirst("香蕉");
        linkedList.addLast("橙子");
        System.out.println("第一个: " + linkedList.getFirst());
        System.out.println("最后一个: " + linkedList.getLast());

        // TreeSet
        System.out.println("\n--- TreeSet ---");
        TreeSet<Integer> treeSet = new TreeSet<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));
        System.out.println("升序: " + treeSet);
        System.out.println("第一个: " + treeSet.first());
        System.out.println("最后一个: " + treeSet.last());
        System.out.println("小于5的: " + treeSet.headSet(5));
        System.out.println("大于等于5的: " + treeSet.tailSet(5));

        // TreeMap
        System.out.println("\n--- TreeMap ---");
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("banana", 2);
        treeMap.put("apple", 5);
        treeMap.put("cherry", 3);
        System.out.println("按key排序: " + treeMap);

        // Collections 工具类
        System.out.println("\n--- Collections 工具类 ---");
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3));
        System.out.println("原始: " + list);

        Collections.sort(list);
        System.out.println("排序: " + list);

        Collections.reverse(list);
        System.out.println("反转: " + list);

        Collections.shuffle(list);
        System.out.println("打乱: " + list);

        System.out.println("最大: " + Collections.max(list));
        System.out.println("最小: " + Collections.min(list));
        System.out.println("5出现次数: " + Collections.frequency(list, 5));
    }

    // ============================================================
    // 5. Stream API
    // ============================================================

    static void demoStream() {
        System.out.println("\n=== 5. Stream API ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter + map
        System.out.print("偶数平方: ");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // collect
        List<Integer> squares = numbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("平方列表: " + squares);

        // 求和
        int sum = numbers.stream()
            .filter(n -> n > 5)
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("大于5的和: " + sum);

        // 排序
        List<String> names = Arrays.asList("Tom", "Jerry", "Alice", "Bob");
        List<String> sortedNames = names.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("排序后的名字: " + sortedNames);

        // 分组
        Map<Integer, List<String>> grouped = names.stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("按长度分组: " + grouped);

        // 归约
        Optional<Integer> product = numbers.stream()
            .reduce((a, b) -> a * b);
        product.ifPresent(p -> System.out.println("所有数乘积: " + p));
    }

    // ============================================================
    // 6. 枚举类
    // ============================================================

    enum Day {
        MONDAY("星期一"),
        TUESDAY("星期二"),
        WEDNESDAY("星期三"),
        THURSDAY("星期四"),
        FRIDAY("星期五"),
        SATURDAY("星期六"),
        SUNDAY("星期日");

        private final String chineseName;

        Day(String chineseName) {
            this.chineseName = chineseName;
        }

        public String getChineseName() {
            return chineseName;
        }

        public boolean isWeekend() {
            return this == SATURDAY || this == SUNDAY;
        }
    }

    enum Operation {
        PLUS {
            public double apply(double a, double b) { return a + b; }
        },
        MINUS {
            public double apply(double a, double b) { return a - b; }
        },
        MULTIPLY {
            public double apply(double a, double b) { return a * b; }
        },
        DIVIDE {
            public double apply(double a, double b) { return a / b; }
        };

        public abstract double apply(double a, double b);
    }

    static void demoEnum() {
        System.out.println("\n=== 6. 枚举类 ===");

        // 基本枚举
        Day today = Day.WEDNESDAY;
        System.out.println("今天是: " + today.getChineseName());
        System.out.println("是否周末: " + today.isWeekend());

        // 遍历枚举
        System.out.println("\n一周七天:");
        for (Day day : Day.values()) {
            System.out.println("  " + day.name() + ": " + day.getChineseName());
        }

        // valueOf
        Day friday = Day.valueOf("FRIDAY");
        System.out.println("\nFRIDAY: " + friday.getChineseName());

        // 带抽象方法的枚举
        System.out.println("\n运算:");
        System.out.println("10 + 5 = " + Operation.PLUS.apply(10, 5));
        System.out.println("10 - 5 = " + Operation.MINUS.apply(10, 5));
        System.out.println("10 * 5 = " + Operation.MULTIPLY.apply(10, 5));
        System.out.println("10 / 5 = " + Operation.DIVIDE.apply(10, 5));
    }

    // ============================================================
    // 7. 注解
    // ============================================================

    @Deprecated
    static void oldMethod() {
        System.out.println("这是旧方法");
    }

    @SuppressWarnings("unchecked")
    static void demoAnnotation() {
        System.out.println("\n=== 7. 注解 ===");

        // @Override 在前面的示例中已经见过
        // @Deprecated 标记废弃方法
        System.out.println("常见注解:");
        System.out.println("  @Override - 方法重写");
        System.out.println("  @Deprecated - 已废弃");
        System.out.println("  @SuppressWarnings - 抑制警告");
        System.out.println("  @FunctionalInterface - 函数式接口");
    }

    public static void main(String[] args) {
        demoGenericClass();
        demoGenericMethod();
        demoWildcard();
        demoCollections();
        demoStream();
        demoEnum();
        demoAnnotation();

        System.out.println("\n=== 运行完成 ===");
    }
}