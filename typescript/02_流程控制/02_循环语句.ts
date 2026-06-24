// ============================================================
// TypeScript 流程控制 - 循环语句
// 运行：ts-node 02_循环语句.ts
// ============================================================

console.log("=== 1. for 循环 ===");

// 基本 for 循环
console.log("基本 for 循环:");
for (let i = 1; i <= 5; i++) {
    console.log(`  第 ${i} 次循环`);
}

// for 循环计算求和
let sum = 0;
for (let i = 1; i <= 100; i++) {
    sum += i;
}
console.log(`1 到 100 的和: ${sum}`);

// 嵌套 for 循环 - 九九乘法表
console.log("\n九九乘法表:");
for (let i = 1; i <= 9; i++) {
    let row = "";
    for (let j = 1; j <= i; j++) {
        row += `${j}×${i}=${i * j}\t`;
    }
    console.log(row);
}

// ============================================================
// 2. while 循环
// ============================================================

console.log("\n=== 2. while 循环 ===");

// 基本 while 循环
let count = 1;
while (count <= 5) {
    console.log(`  while 循环第 ${count} 次`);
    count++;
}

// while 循环求阶乘
let n = 5;
let factorial = 1;
let i = 1;
while (i <= n) {
    factorial *= i;
    i++;
}
console.log(`${n}! = ${factorial}`);

// ============================================================
// 3. do...while 循环
// ============================================================

console.log("\n=== 3. do...while 循环 ===");

let num = 1;
do {
    console.log(`  do...while 第 ${num} 次`);
    num++;
} while (num <= 3);

// do...while 至少执行一次
let x = 10;
do {
    console.log(`  即使条件不满足也会执行一次: x = ${x}`);
    x++;
} while (x < 5);

// ============================================================
// 4. for...of 循环
// ============================================================

console.log("\n=== 4. for...of 循环 ===");

// 遍历数组
let fruits = ["苹果", "香蕉", "橙子", "葡萄"];
console.log("遍历数组:");
for (let fruit of fruits) {
    console.log(`  ${fruit}`);
}

// 遍历字符串
let str = "Hello";
console.log("遍历字符串:");
for (let char of str) {
    console.log(`  ${char}`);
}

// 遍历 Map
let map = new Map([
    ["name", "张三"],
    ["age", "25"],
    ["city", "北京"]
]);
console.log("遍历 Map:");
for (let [key, value] of map) {
    console.log(`  ${key}: ${value}`);
}

// 遍历 Set
let set = new Set([1, 2, 3, 4, 5]);
console.log("遍历 Set:");
for (let num of set) {
    console.log(`  ${num}`);
}

// ============================================================
// 5. forEach 方法
// ============================================================

console.log("\n=== 5. forEach 方法 ===");

// 数组 forEach
let numbers = [1, 2, 3, 4, 5];
console.log("数组 forEach:");
numbers.forEach(function (num, index) {
    console.log(`  索引 ${index}: ${num}`);
});

// 箭头函数版本
console.log("forEach 箭头函数:");
numbers.forEach(num => console.log(`  ${num} × 2 = ${num * 2}`));

// ============================================================
// 6. for...in 循环
// ============================================================

console.log("\n=== 6. for...in 循环 ===");

// 遍历对象属性
let person = {
    name: "李四",
    age: 30,
    city: "上海",
    job: "程序员"
};

console.log("遍历对象属性:");
for (let key in person) {
    console.log(`  ${key}: ${(person as any)[key]}`);
}

// ============================================================
// 7. break 和 continue
// ============================================================

console.log("\n=== 7. break 和 continue ===");

// break: 跳出循环
console.log("break 示例 (找到 3 就停止):");
for (let i = 1; i <= 5; i++) {
    if (i === 3) {
        break;
    }
    console.log(`  ${i}`);
}

// continue: 跳过当前循环
console.log("continue 示例 (跳过偶数):");
for (let i = 1; i <= 5; i++) {
    if (i % 2 === 0) {
        continue;
    }
    console.log(`  ${i}`);
}

// 带标签的 break
console.log("带标签的 break:");
outer: for (let i = 1; i <= 3; i++) {
    for (let j = 1; j <= 3; j++) {
        if (i === 2 && j === 2) {
            break outer;
        }
        console.log(`  i=${i}, j=${j}`);
    }
}

console.log("\n=== 运行完成 ===");
