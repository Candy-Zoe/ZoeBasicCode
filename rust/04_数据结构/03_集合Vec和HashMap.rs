// ============================================================
// Rust 数据结构 - 集合 Vec 和 HashMap
// 编译运行：rustc 03_集合Vec和HashMap.rs -o 03_集合Vec和HashMap && ./03_集合Vec和HashMap
// ============================================================

use std::collections::HashMap;

fn main() {
    println!("=== 1. Vec<T> 动态数组 ===");

    // 创建 Vec
    let v: Vec<i32> = Vec::new();
    println!("空 Vec: {:?}", v);

    // 使用 vec! 宏创建
    let v = vec![1, 2, 3, 4, 5];
    println!("初始 Vec: {:?}", v);

    // 更新 Vec
    let mut v = Vec::new();
    v.push(5);
    v.push(6);
    v.push(7);
    v.push(8);
    println!("push 后: {:?}", v);

    // 移除元素
    let last = v.pop();
    println!("pop 取出: {:?}", last);
    println!("pop 后: {:?}", v);

    println!("\n=== 2. 读取 Vec 元素 ===");

    let v = vec![1, 2, 3, 4, 5];

    // 使用索引访问（可能 panic）
    let third: &i32 = &v[2];
    println!("第三个元素（索引2）: {}", third);

    // 使用 get 方法（返回 Option）
    match v.get(2) {
        Some(third) => println!("第三个元素: {}", third),
        None => println!("没有第三个元素"),
    }

    match v.get(100) {
        Some(val) => println!("第100个元素: {}", val),
        None => println!("没有第100个元素"),
    }

    println!("\n=== 3. 遍历 Vec ===");

    let v = vec![100, 32, 57];
    println!("遍历所有元素:");
    for i in &v {
        println!("  {}", i);
    }

    // 可变遍历
    let mut v = vec![100, 32, 57];
    for i in &mut v {
        *i += 50;
    }
    println!("修改后: {:?}", v);

    println!("\n=== 4. Vec 常用方法 ===");

    let v = vec![1, 2, 3, 4, 5];
    println!("长度: {}", v.len());
    println!("是否为空: {}", v.is_empty());
    println!("包含 3: {}", v.contains(&3));

    // 排序
    let mut v = vec![5, 2, 8, 1, 9];
    v.sort();
    println!("排序后: {:?}", v);

    // 反转
    v.reverse();
    println!("反转后: {:?}", v);

    println!("\n=== 5. HashMap<K, V> 哈希表 ===");

    // 创建 HashMap
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Red"), 50);
    println!("分数表: {:?}", scores);

    // 访问值
    let team_name = String::from("Blue");
    if let Some(score) = scores.get(&team_name) {
        println!("{} 队的分数: {}", team_name, score);
    }

    // 不存在的键
    let team_name = String::from("Green");
    match scores.get(&team_name) {
        Some(score) => println!("分数: {}", score),
        None => println!("队伍不存在"),
    }

    println!("\n=== 6. HashMap 常用操作 ===");

    let mut scores = HashMap::new();

    // 插入
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Red"), 50);
    println!("插入后: {:?}", scores);

    // 更新值
    scores.insert(String::from("Blue"), 25);
    println!("更新 Blue 后: {:?}", scores);

    // 只有键没有值时才插入
    scores.entry(String::from("Yellow")).or_insert(50);
    scores.entry(String::from("Blue")).or_insert(50);
    println!("entry 后: {:?}", scores);

    // 删除
    scores.remove("Red");
    println!("删除 Red 后: {:?}", scores);

    println!("\n=== 7. 遍历 HashMap ===");

    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Red"), 50);
    scores.insert(String::from("Green"), 30);

    println!("遍历所有键值对:");
    for (key, value) in &scores {
        println!("  {}: {}", key, value);
    }

    println!("\n只遍历键:");
    for key in scores.keys() {
        println!("  {}", key);
    }

    println!("\n只遍历值:");
    for value in scores.values() {
        println!("  {}", value);
    }

    println!("\n=== 8. 使用示例：统计单词出现次数 ===");

    let text = "hello world wonderful world";
    let mut map = HashMap::new();

    for word in text.split_whitespace() {
        let count = map.entry(word).or_insert(0);
        *count += 1;
    }

    println!("单词统计: {:?}", map);

    println!("\n=== 9. 使用示例：集合（Set） ===");

    // 用 HashMap 实现 Set
    let mut set = HashMap::new();
    let items = vec!["apple", "banana", "apple", "orange", "banana"];

    for item in items {
        set.insert(item, true);
    }

    println!("去重后的元素:");
    for key in set.keys() {
        println!("  {}", key);
    }

    println!("\n=== 运行完成 ===");
}
