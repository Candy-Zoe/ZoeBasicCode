// ============================================================
// JavaScript 流程控制和函数
// 运行方式：node 文件名.js
// ============================================================

console.log("=== 1. if-else 语句 ===");
let score = 85;
if (score >= 90) {
    console.log("优秀");
} else if (score >= 80) {
    console.log("良好");
} else if (score >= 60) {
    console.log("及格");
} else {
    console.log("不及格");
}

console.log("\n=== 2. switch 语句 ===");
let day = 3;
switch (day) {
    case 1:
        console.log("星期一");
        break;
    case 2:
        console.log("星期二");
        break;
    case 3:
        console.log("星期三");
        break;
    case 4:
        console.log("星期四");
        break;
    case 5:
        console.log("星期五");
        break;
    case 6:
    case 7:
        console.log("周末");
        break;
    default:
        console.log("无效");
}

console.log("\n=== 3. 三元运算符 ===");
let a = 10, b = 20;
let max = a > b ? a : b;
console.log(`${a} 和 ${b} 较大的是: ${max}`);

console.log("\n=== 4. for 循环 ===");
for (let i = 0; i < 5; i++) {
    console.log(`第 ${i + 1} 次循环`);
}

console.log("\n=== 5. for...of 遍历 (ES6) ===");
let fruits = ["苹果", "香蕉", "橙子"];
for (let fruit of fruits) {
    console.log("水果:", fruit);
}

console.log("\n=== 6. for...in 遍历对象 ===");
let person = { name: "张三", age: 25, city: "北京" };
for (let key in person) {
    console.log(`${key}: ${person[key]}`);
}

console.log("\n=== 7. while 循环 ===");
let count = 1;
while (count <= 5) {
    console.log(`count = ${count}`);
    count++;
}

console.log("\n=== 8. do-while 循环 ===");
let num = 1;
do {
    console.log(`num = ${num}`);
    num++;
} while (num <= 5);

console.log("\n=== 9. break 和 continue ===");
console.log("break 示例（到5停止）:");
for (let i = 0; i < 10; i++) {
    if (i === 5) break;
    process.stdout.write(i + " ");
}
console.log();

console.log("continue 示例（跳过偶数）:");
for (let i = 0; i < 10; i++) {
    if (i % 2 === 0) continue;
    process.stdout.write(i + " ");
}
console.log();

console.log("\n=== 10. 函数声明 ===");
function greet() {
    console.log("Hello, World!");
}
greet();

function add(x, y) {
    return x + y;
}
console.log("3 + 5 =", add(3, 5));

console.log("\n=== 11. 函数表达式 ===");
const multiply = function(x, y) {
    return x * y;
};
console.log("3 * 4 =", multiply(3, 4));

console.log("\n=== 12. 箭头函数 (ES6) ===");
const square = x => x * x;
console.log("5的平方:", square(5));

const addArrow = (x, y) => x + y;
console.log("3 + 4 =", addArrow(3, 4));

const greetArrow = name => `你好，${name}！`;
console.log(greetArrow("张三"));

console.log("\n=== 13. 默认参数 (ES6) ===");
function power(base, exponent = 2) {
    return Math.pow(base, exponent);
}
console.log("3的平方:", power(3));
console.log("3的立方:", power(3, 3));

console.log("\n=== 14. 剩余参数 (ES6) ===");
function sum(...numbers) {
    return numbers.reduce((acc, cur) => acc + cur, 0);
}
console.log("sum(1,2,3) =", sum(1, 2, 3));
console.log("sum(1,2,3,4,5) =", sum(1, 2, 3, 4, 5));

console.log("\n=== 15. 数组方法 ===");
let nums = [1, 2, 3, 4, 5];

// forEach
console.log("forEach:");
nums.forEach(n => process.stdout.write(n + " "));
console.log();

// map
let doubled = nums.map(n => n * 2);
console.log("map (x2):", doubled);

// filter
let evens = nums.filter(n => n % 2 === 0);
console.log("filter (偶数):", evens);

// reduce
let total = nums.reduce((acc, cur) => acc + cur, 0);
console.log("reduce (求和):", total);

// find
let found = nums.find(n => n > 3);
console.log("find (第一个>3):", found);

// every / some
console.log("every (>0):", nums.every(n => n > 0));
console.log("some (>5):", nums.some(n => n > 5));

// sort
let arr = [3, 1, 4, 1, 5];
arr.sort((x, y) => x - y);
console.log("sort (升序):", arr);

console.log("\n=== 16. 闭包 ===");
function counter() {
    let count = 0;
    return function() {
        return ++count;
    };
}
let c = counter();
console.log("闭包计数:", c());  // 1
console.log("闭包计数:", c());  // 2
console.log("闭包计数:", c());  // 3

console.log("\n=== 17. 递归 ===");
function factorial(n) {
    if (n === 0 || n === 1) return 1;
    return n * factorial(n - 1);
}
console.log("5! =", factorial(5));
