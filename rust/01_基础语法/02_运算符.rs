// ============================================================
// Rust 基础语法 - 运算符
// 编译运行：rustc 02_运算符.rs -o 02_运算符 && ./02_运算符
// ============================================================

fn main() {
    println!("=== 1. 算术运算符 ===");
    let a = 10;
    let b = 3;

    println!("a = {}, b = {}", a, b);
    println!("a + b = {}", a + b);
    println!("a - b = {}", a - b);
    println!("a * b = {}", a * b);
    println!("a / b = {}", a / b);
    println!("a % b = {}", a % b);

    // 自增、自减（Rust 没有 ++ 和 --，使用 += 1 或 -= 1）
    let mut x = 5;
    x += 1;
    println!("x += 1 后 x = {}", x);
    x -= 1;
    println!("x -= 1 后 x = {}", x);

    println!("\n=== 2. 关系运算符 ===");
    let x = 5;
    let y = 10;

    println!("x = {}, y = {}", x, y);
    println!("x == y : {}", x == y);
    println!("x != y : {}", x != y);
    println!("x > y  : {}", x > y);
    println!("x < y  : {}", x < y);
    println!("x >= y : {}", x >= y);
    println!("x <= y : {}", x <= y);

    println!("\n=== 3. 逻辑运算符 ===");
    let t = true;
    let f = false;

    println!("t = {}, f = {}", t, f);
    println!("t && f : {}", t && f);
    println!("t || f : {}", t || f);
    println!("!t     : {}", !t);
    println!("!f     : {}", !f);

    // 短路求值
    println!("\n短路求值示例：");
    let mut n = 0;
    if false && { n += 1; true } {
        println!("不会执行到这里");
    }
    println!("短路后 n = {}（右边没有被求值）", n);

    println!("\n=== 4. 位运算符 ===");
    let m: u8 = 0b0011_1100; // 60
    let n: u8 = 0b0000_1101; // 13

    println!("m = {:08b} ({})", m, m);
    println!("n = {:08b} ({})", n, n);
    println!("m & n  = {:08b} ({})  按位与", m & n, m & n);
    println!("m | n  = {:08b} ({})  按位或", m | n, m | n);
    println!("m ^ n  = {:08b} ({})  按位异或", m ^ n, m ^ n);
    println!("!m     = {:08b} ({})  按位取反", !m, !m);
    println!("m << 2 = {:08b} ({})  左移", m << 2, m << 2);
    println!("m >> 2 = {:08b} ({})  右移", m >> 2, m >> 2);

    println!("\n=== 5. 赋值运算符 ===");
    let mut c = 10;
    println!("初始 c = {}", c);

    c += 5;
    println!("c += 5 后 c = {}", c);

    c -= 3;
    println!("c -= 3 后 c = {}", c);

    c *= 2;
    println!("c *= 2 后 c = {}", c);

    c /= 4;
    println!("c /= 4 后 c = {}", c);

    c %= 3;
    println!("c %= 3 后 c = {}", c);

    println!("\n=== 6. 其他运算符 ===");

    // 引用和解引用
    let d = 42;
    let p = &d;
    println!("d 的引用: {}", p);
    println!("解引用 p: {}", *p);

    // 范围运算符
    println!("\n--- 范围运算符 ---");
    println!("1..5 :");
    for i in 1..5 {
        print!("{} ", i);
    }
    println!();

    println!("1..=5 :");
    for i in 1..=5 {
        print!("{} ", i);
    }
    println!();

    println!("\n=== 7. 运算符优先级示例 ===");
    let result = 5 + 3 * 2;
    println!("5 + 3 * 2 = {}（先乘后加）", result);

    let result = (5 + 3) * 2;
    println!("(5 + 3) * 2 = {}（括号优先）", result);
}
