// ============================================================
// Java 集合框架
// 编译运行：javac CollectionsDemo.java && java CollectionsDemo
// ============================================================

import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. ArrayList 动态数组 ===");
        ArrayList<String> list = new ArrayList<>();
        list.add("苹果");
        list.add("香蕉");
        list.add("橙子");
        System.out.println("初始: " + list);
        System.out.println("大小: " + list.size());
        System.out.println("第一个元素: " + list.get(0));
        
        list.add(1, "芒果");
        System.out.println("插入后: " + list);
        
        list.remove("香蕉");
        System.out.println("删除后: " + list);
        
        System.out.print("遍历: ");
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.println("\n=== 2. LinkedList 链表 ===");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        System.out.println("LinkedList: " + linkedList);
        linkedList.addFirst("First");
        linkedList.addLast("Last");
        System.out.println("首尾添加后: " + linkedList);
        System.out.println("第一个: " + linkedList.getFirst());
        System.out.println("最后一个: " + linkedList.getLast());

        System.out.println("\n=== 3. HashMap 哈希表 ===");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("张三", 95);
        map.put("李四", 85);
        map.put("王五", 90);
        System.out.println("HashMap: " + map);
        System.out.println("张三的成绩: " + map.get("张三"));
        System.out.println("包含李四: " + map.containsKey("李四"));
        
        System.out.println("遍历键值对:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n=== 4. HashSet 集合 ===");
        HashSet<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(4);
        set.add(1);  // 重复元素不会添加
        set.add(5);
        System.out.println("HashSet: " + set + " (无序，去重)");
        System.out.println("包含4: " + set.contains(4));
        System.out.println("大小: " + set.size());

        System.out.println("\n=== 5. TreeSet 有序集合 ===");
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(2);
        System.out.println("TreeSet: " + treeSet + " (自动排序)");
        System.out.println("第一个: " + treeSet.first());
        System.out.println("最后一个: " + treeSet.last());

        System.out.println("\n=== 6. Stack 栈（后进先出） ===");
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("栈: " + stack);
        System.out.println("出栈: " + stack.pop());
        System.out.println("栈顶: " + stack.peek());

        System.out.println("\n=== 7. Queue 队列（先进先出） ===");
        Queue<String> queue = new LinkedList<>();
        queue.offer("第一个");
        queue.offer("第二个");
        queue.offer("第三个");
        System.out.println("队列: " + queue);
        System.out.println("出队: " + queue.poll());
        System.out.println("队首: " + queue.peek());

        System.out.println("\n=== 8. 数组 ===");
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("原数组: " + Arrays.toString(arr));
        
        // 排序
        Arrays.sort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
        
        // 二分查找
        int index = Arrays.binarySearch(arr, 5);
        System.out.println("5的索引: " + index);
        
        // 填充
        int[] filled = new int[5];
        Arrays.fill(filled, 10);
        System.out.println("填充: " + Arrays.toString(filled));

        System.out.println("\n=== 9. Collections 工具类 ===");
        List<Integer> numList = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));
        System.out.println("原列表: " + numList);
        
        Collections.sort(numList);
        System.out.println("排序: " + numList);
        
        Collections.reverse(numList);
        System.out.println("反转: " + numList);
        
        System.out.println("最大值: " + Collections.max(numList));
        System.out.println("最小值: " + Collections.min(numList));
        System.out.println("5出现次数: " + Collections.frequency(numList, 1));

        System.out.println("\n=== 10. 迭代器 ===");
        List<String> strList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Iterator<String> it = strList.iterator();
        System.out.print("迭代器遍历: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}
