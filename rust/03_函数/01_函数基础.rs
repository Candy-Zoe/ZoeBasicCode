// ============================================================
// Rust 函数 - 函数基础
// 编译运行：rustc 01_函数基础.rs -o 01_函数基础 && ./01_函数基础
// ============================================================

fn main() {
    println!("=== 1. 函数基本用法 ===");
    greet();
    greet_user("张三");
    println!("add(5, 3) = {}", add(5, 3));
    println!("add(10, 20) = {}", add(10, 20));

    println!("\n=== 2. 函数返回值 ===");
    let five = five();
    println!("five() = {}", five);

    let x = plus_one(5);
    println!("plus_one(5) = {}", x);

    println!("\n=== 3. 表达式 vs 语句 ===");
    // 函数作为表达式
    let y = {
        let x = 3;
        x + 1  // 注意没有分号，这是表达式
    };
    println!("y = {}", y);

    // 语句没有返回值
    // let z = (let x = 3); // 错误！

    println!("\n=== 4. 多参数函数 ===");
    println!("max(5, 10) = {}", max(5, 10));
    println!("max(100, 50) = {}", max(100, 50));

    println!("\n=== 5. 元组返回多个值 ===");
    let (sum, product) = calculate(10, 3);
    println!("10 和 3: 和 = {}, 积 = {}", sum, product);

    // 忽略返回值
    let (s, _) = calculate(5, 2);
    println!("只取和: {}", s);

    println!("\n=== 6. 递归函数 ===");
    println!("阶乘:");
    for i in 0..=5 {
        println!("{}! = {}", i, factorial(i));
    }

    println!("\n=== 7. 闭包 ===");

    // 简单闭包
    let add_one = |x: i32| x + 1;
    println!("add_one(5) = {}", add_one(5));

    let sum = |a, b| a + b;
    println!("sum(3, 4) = {}", sum(3, 4));

    // 捕获环境
    let x = 4;
    let equal_to_x = |z| z == x;
    println!("4 == x: {}", equal_to_x(4));

    println!("\n=== 8. 函数作为参数 ===");
    let numbers = vec![1, 2, 3, 4, 5];
    let doubled: Vec<_> = numbers.iter().map(|&x| x * 2).collect();
    println!("原数组: {:?}", numbers);
    println!("翻倍后: {:?}", doubled);

    println!("\n=== 9. 使用示例 ===");
    println!("斐波那契前 10 项:");
    for i in 0..10 {
        print!("{} ", fibonacci(i));
    }
    println!();

    println!("\n1 到 50 之间的质数:");
    for num in 1..=50 {
        if is_prime(num) {
            print!("{} ", num);
        }
    }
    println!();

    println!("\n=== 运行完成 ===");
}

fn greet() {
    println!("Hello, World!");
}

fn greet_user(name: &str) {
    println!("你好，{}！", name);
}

fn add(a: i32, b: i32) -> i32 {
    a + b  // 表达式，没有分号
}

fn five() -> i32 {
    5
}

fn plus_one(x: i32) -> i32 {
    x + 1
}

fn max(a: i32, b: i32) -> i32 {
    if a > b { a } else { b }
}

fn calculate(a: i32, b: i32) -> (i32, i32) {
    (a + b, a * b)
}

fn factorial(n: u32) -> u32 {
    if n == 0 || n == 1 {
        1
    } else {
        n * factorial(n - 1)
    }
}

fn fibonacci(n: u32) -> u32 {
    if n <= 1 {
        return n;
    }
    fibonacci(n - 1) + fibonacci(n - 2)
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
