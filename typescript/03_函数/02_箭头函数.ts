// ============================================================
// TypeScript 函数 - 箭头函数
// 运行：ts-node 02_箭头函数.ts
// ============================================================

console.log("=== 1. 箭头函数基础 ===");

// 基本箭头函数
let add = (a: number, b: number): number => {
    return a + b;
};
console.log(`add(1, 2) = ${add(1, 2)}`);

// 简写：只有一个表达式时可以省略花括号和 return
let add2 = (a: number, b: number): number => a + b;
console.log(`add2(3, 4) = ${add2(3, 4)}`);

// 只有一个参数时可以省略括号
let square = (x: number): number => x * x;
console.log(`square(5) = ${square(5)}`);

// 没有参数
let greet = (): string => "Hello, World!";
console.log(greet());

// ============================================================
// 2. 箭头函数与 this
// ============================================================

console.log("\n=== 2. 箭头函数与 this ===");

// 普通函数的 this 问题
// 在 setTimeout 中，普通函数的 this 指向全局对象
// 箭头函数的 this 继承自外层作用域

class Person {
    name: string;
    age: number;

    constructor(name: string, age: number) {
        this.name = name;
        this.age = age;
    }

    // 使用箭头函数，this 正确指向实例
    introduceDelay() {
        setTimeout(() => {
            console.log(`大家好，我是 ${this.name}，今年 ${this.age} 岁`);
        }, 0);
    }
}

let p = new Person("张三", 25);
p.introduceDelay();

// ============================================================
// 3. 箭头函数与数组方法
// ============================================================

console.log("\n=== 3. 箭头函数与数组方法 ===");

let numbers = [1, 2, 3, 4, 5];

// map - 映射
let doubled = numbers.map(n => n * 2);
console.log(`原数组: [${numbers}]`);
console.log(`翻倍后: [${doubled}]`);

// filter - 过滤
let evens = numbers.filter(n => n % 2 === 0);
console.log(`偶数: [${evens}]`);

// reduce - 归约
let sum = numbers.reduce((acc, n) => acc + n, 0);
console.log(`总和: ${sum}`);

// find - 查找
let found = numbers.find(n => n > 3);
console.log(`第一个大于 3 的数: ${found}`);

// every - 全部满足
let allPositive = numbers.every(n => n > 0);
console.log(`都是正数: ${allPositive}`);

// some - 至少一个满足
let hasEven = numbers.some(n => n % 2 === 0);
console.log(`有偶数: ${hasEven}`);

// sort - 排序
let sorted = [...numbers].sort((a, b) => b - a);
console.log(`降序排列: [${sorted}]`);

// ============================================================
// 4. 箭头函数与解构
// ============================================================

console.log("\n=== 4. 箭头函数与解构 ===");

// 参数解构
let user = { name: "李四", age: 30, city: "上海" };

let getInfo = ({ name, age }: { name: string; age: number }): string => {
    return `${name}, ${age} 岁`;
};
console.log(`用户信息: ${getInfo(user)}`);

// 数组解构
let point = [10, 20];
let distance = ([x, y]: [number, number]): number => Math.sqrt(x * x + y * y);
console.log(`点 (10, 20) 到原点距离: ${distance(point).toFixed(2)}`);

// ============================================================
// 5. 箭头函数的使用场景
// ============================================================

console.log("\n=== 5. 箭头函数的使用场景 ===");

// 1. 数组操作
let words = ["hello", "world", "typescript"];
let lengths = words.map(w => w.length);
console.log(`单词长度: [${lengths}]`);

// 2. 事件处理（模拟）
let button = {
    onClick: () => console.log("按钮被点击了")
};
button.onClick();

// 3. Promise 链式调用
let fetchData = (): Promise<string> => {
    return new Promise(resolve => {
        setTimeout(() => resolve("数据加载完成"), 0);
    });
};

fetchData().then(data => console.log(data));

// 4. 函数柯里化
let curryAdd = (a: number) => (b: number) => a + b;
let add5 = curryAdd(5);
console.log(`add5(3) = ${add5(3)}`);
console.log(`curryAdd(10)(20) = ${curryAdd(10)(20)}`);

console.log("\n=== 运行完成 ===");
