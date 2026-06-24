// ============================================================
// Rust 高级特性 - Trait 和泛型
// 编译运行：rustc 01_Trait和泛型.rs -o 01_Trait和泛型 && ./01_Trait和泛型
// ============================================================

fn main() {
    println!("=== 1. 泛型函数 ===");

    let number_list = vec![34, 50, 25, 100, 65];
    let result = largest_i32(&number_list);
    println!("最大数字: {}", result);

    let char_list = vec!['y', 'm', 'a', 'q'];
    let result = largest_char(&char_list);
    println!("最大字符: {}", result);

    // 使用泛型的 largest 函数（需要 PartialOrd trait）
    let number_list = vec![34, 50, 25, 100, 65];
    println!("泛型 largest: {}", largest(&number_list));

    let char_list = vec!['y', 'm', 'a', 'q'];
    println!("泛型 largest char: {}", largest(&char_list));

    println!("\n=== 2. 泛型结构体 ===");

    let p1 = Point { x: 5, y: 10 };
    let p2 = Point { x: 1.0, y: 4.0 };
    println!("整数点: ({}, {})", p1.x, p1.y);
    println!("浮点点: ({}, {})", p2.x, p2.y);

    let p = Point { x: 5, y: 10.4 };
    println!("混合点: ({}, {})", p.x, p.y);

    println!("\n=== 3. 泛型方法 ===");

    let p = Point { x: 5, y: 10 };
    println!("p.x() = {}", p.x());

    println!("\n=== 4. Trait 定义 ===");

    let tweet = Tweet {
        username: String::from("horse_ebooks"),
        content: String::from("of course, as you probably already know, people"),
        reply: false,
        retweet: false,
    };

    println!("1 条新推文: {}", tweet.summarize());

    let article = NewsArticle {
        headline: String::from("Penguins win the Stanley Cup Championship!"),
        location: String::from("Pittsburgh, PA, USA"),
        author: String::from("Iceburgh"),
        content: String::from("The Pittsburgh Penguins once again are the best hockey team in the NHL."),
    };

    println!("新文章发布: {}", article.summarize());
    println!("文章作者摘要: {}", article.summarize_author());

    println!("\n=== 5. Trait 作为参数 ===");

    notify(&tweet);
    notify(&article);

    println!("\n=== 6. 返回实现了 Trait 的类型 ===");

    let s = returns_summarizable();
    println!("返回的 Summary: {}", s.summarize());

    println!("\n=== 7. Trait 约束（Trait Bound） ===");

    let tweet1 = Tweet {
        username: String::from("user1"),
        content: String::from("tweet content 1"),
        reply: false,
        retweet: false,
    };
    let tweet2 = Tweet {
        username: String::from("user2"),
        content: String::from("tweet content 2"),
        reply: false,
        retweet: false,
    };

    let result = largest_summary(&tweet1, &tweet2);
    println!("较长的摘要: {}", result);

    println!("\n=== 8. 闭包和迭代器 ===");

    // 闭包
    let add_one = |x: i32| x + 1;
    println!("add_one(5) = {}", add_one(5));

    let sum = |a, b| a + b;
    println!("sum(3, 4) = {}", sum(3, 4));

    // 捕获环境
    let x = 4;
    let equal_to_x = |z| z == x;
    println!("4 == x: {}", equal_to_x(4));

    // 迭代器
    let v1 = vec![1, 2, 3];
    let v1_iter = v1.iter();

    println!("迭代器:");
    for val in v1_iter {
        println!("  {}", val);
    }

    // 迭代器适配器
    let v1: Vec<i32> = vec![1, 2, 3];
    let v2: Vec<_> = v1.iter().map(|x| x + 1).collect();
    println!("map 后: {:?}", v2);

    // filter
    let numbers: Vec<i32> = (1..=10).collect();
    let evens: Vec<_> = numbers.iter().filter(|x| *x % 2 == 0).collect();
    println!("偶数: {:?}", evens);

    // fold
    let sum: i32 = (1..=10).fold(0, |acc, x| acc + x);
    println!("1 到 10 的和: {}", sum);

    println!("\n=== 运行完成 ===");
}

// 泛型函数（非泛型版本）
fn largest_i32(list: &[i32]) -> i32 {
    let mut largest = list[0];
    for &item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

fn largest_char(list: &[char]) -> char {
    let mut largest = list[0];
    for &item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

// 泛型函数（使用 Trait Bound）
fn largest<T: PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];
    for item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

// 泛型结构体
struct Point<T, U> {
    x: T,
    y: U,
}

impl<T, U> Point<T, U> {
    fn x(&self) -> &T {
        &self.x
    }
}

// Trait 定义
pub trait Summary {
    fn summarize(&self) -> String;

    fn summarize_author(&self) -> String {
        String::from("(Read more...)")  // 默认实现
    }
}

struct NewsArticle {
    headline: String,
    location: String,
    author: String,
    content: String,
}

impl Summary for NewsArticle {
    fn summarize(&self) -> String {
        format!("{}, by {} ({})", self.headline, self.author, self.location)
    }
}

struct Tweet {
    username: String,
    content: String,
    reply: bool,
    retweet: bool,
}

impl Summary for Tweet {
    fn summarize(&self) -> String {
        format!("{}: {}", self.username, self.content)
    }
}

// Trait 作为参数
fn notify(item: &impl Summary) {
    println!("突发新闻! {}", item.summarize());
}

// 返回 Trait
fn returns_summarizable() -> impl Summary {
    Tweet {
        username: String::from("horse_ebooks"),
        content: String::from("of course, as you probably already know, people"),
        reply: false,
        retweet: false,
    }
}

// Trait Bound 语法
fn largest_summary<T: Summary>(a: &T, b: &T) -> String {
    let sa = a.summarize();
    let sb = b.summarize();
    if sa.len() > sb.len() { sa } else { sb }
}
