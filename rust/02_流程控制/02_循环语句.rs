// ============================================================
// Rust 流程控制 - 循环语句
// 编译运行：rustc 02_循环语句.rs -o 02_循环语句 && ./02_循环语句
// ============================================================

fn main() {
    println!("=== 1. loop 循环 ===");

    // 基本 loop
    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;  // break 可以返回值
        }
    };
    println!("循环结果: {}", result);

    // 无限循环（用 break 退出）
    println!("\n--- 无限循环示例 ---");
    let mut n = 0;
    loop {
        if n >= 3 {
            break;
        }
        println!("n = {}", n);
        n += 1;
    }

    println!("\n=== 2. while 循环 ===");

    let mut number = 3;
    while number != 0 {
        println!("{}!", number);
        number -= 1;
    }
    println!("发射！");

    // while let
    println!("\n--- while let ---");
    let mut stack = vec![1, 2, 3];
    while let Some(top) = stack.pop() {
        println!("弹出: {}", top);
    }

    println!("\n=== 3. for 循环 ===");

    // 遍历数组
    let a = [10, 20, 30, 40, 50];
    for element in a.iter() {
        println!("值为: {}", element);
    }

    // Range
    println!("\n--- Range ---");
    for number in 1..4 {  // 1..4 是 1, 2, 3
        println!("{}", number);
    }

    // rev() 反转
    println!("\n--- rev() 反转 ---");
    for number in (1..4).rev() {
        println!("{}!", number);
    }
    println!("出发！");

    // 带索引遍历
    println!("\n--- enumerate() 带索引 ---");
    let fruits = ["苹果", "香蕉", "橙子"];
    for (i, fruit) in fruits.iter().enumerate() {
        println!("索引 {}: {}", i, fruit);
    }

    println!("\n=== 4. break 和 continue ===");

    print!("break 示例（到5停止）: ");
    for i in 0..10 {
        if i == 5 {
            break;
        }
        print!("{} ", i);
    }
    println!();

    print!("continue 示例（跳过偶数）: ");
    for i in 0..10 {
        if i % 2 == 0 {
            continue;
        }
        print!("{} ", i);
    }
    println!();

    println!("\n=== 5. 循环标签 ===");

    // 使用循环标签跳出多层循环
    let mut count = 0;
    'outer: loop {
        println!("外层循环: {}", count);
        let mut inner = 0;
        loop {
            println!("  内层循环: {}", inner);
            if inner == 3 {
                break 'outer;  // 跳出外层循环
            }
            inner += 1;
        }
    }
    println!("跳出所有循环");

    println!("\n=== 6. 使用示例：查找质数 ===");
    println!("1 到 50 之间的质数:");
    for num in 1..=50 {
        if is_prime(num) {
            print!("{} ", num);
        }
    }
    println!();

    println!("\n=== 7. 使用示例：斐波那契数列 ===");
    println!("前 10 项:");
    for i in 0..10 {
        print!("{} ", fibonacci(i));
    }
    println!();

    println!("\n=== 8. 使用示例：乘法表 ===");
    for i in 1..=5 {
        for j in 1..=i {
            print!("{}*{}={:<2} ", j, i, i * j);
        }
        println!();
    }

    println!("\n=== 运行完成 ===");
}

fn is_prime(n: i32) -> bool {
    if n < 2 {
        return false;
    }
    for i in 2.. {
        if i * i > n {
            break;
        }
        if n % i == 0 {
            return false;
        }
    }
    true
}

fn fibonacci(n: u32) -> u32 {
    if n <= 1 {
        return n;
    }
    let mut a = 0;
    let mut b = 1;
    for _ in 2..=n {
        let temp = a + b;
        a = b;
        b = temp;
    }
    b
}
