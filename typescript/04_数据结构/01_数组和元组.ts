// ============================================================
// TypeScript 数据结构 - 数组和元组
// 运行：ts-node 01_数组和元组.ts
// ============================================================

console.log("=== 1. 数组 ===");

// 数组声明方式一：类型+方括号
let list1: number[] = [1, 2, 3];
console.log(`number[]: [${list1.join(', ')}]`);

// 数组声明方式二：Array<类型> 泛型
let list2: Array<number> = [1, 2, 3];
console.log(`Array<number>: [${list2.join(', ')}]`);

// 字符串数组
let fruits: string[] = ["苹果", "香蕉", "橙子"];
console.log(`字符串数组: [${fruits.join(', ')}]`);

// 数组索引访问
console.log(`第一个元素: ${fruits[0]}`);
console.log(`数组长度: ${fruits.length}`);

// 修改数组元素
fruits[1] = "芒果";
console.log(`修改后: [${fruits.join(', ')}]`);

// ============================================================
// 2. 数组方法
// ============================================================

console.log("\n=== 2. 数组方法 ===");

let nums = [1, 2, 3];

// push: 末尾添加
nums.push(4);
console.log(`push(4): [${nums}]`);

// pop: 末尾删除
let last = nums.pop();
console.log(`pop(): 弹出 ${last}, 剩余 [${nums}]`);

// unshift: 开头添加
nums.unshift(0);
console.log(`unshift(0): [${nums}]`);

// shift: 开头删除
let first = nums.shift();
console.log(`shift(): 弹出 ${first}, 剩余 [${nums}]`);

// concat: 连接数组
let more = nums.concat([4, 5, 6]);
console.log(`concat: [${more}]`);

// slice: 截取子数组
let sub = more.slice(1, 4);
console.log(`slice(1, 4): [${sub}]`);

// splice: 删除/插入/替换
let arr = [1, 2, 3, 4, 5];
arr.splice(2, 1);  // 从索引2开始删除1个
console.log(`splice(2, 1): [${arr}]`);

arr.splice(2, 0, 3, 3.5);  // 从索引2开始插入
console.log(`splice(2, 0, 3, 3.5): [${arr}]`);

// indexOf / includes
console.log(`indexOf(3): ${arr.indexOf(3)}`);
console.log(`includes(5): ${arr.includes(5)}`);

// join: 连接成字符串
let strArr = ["Hello", "World"];
console.log(`join(' '): ${strArr.join(' ')}`);

// reverse: 反转
let reversed = [1, 2, 3].reverse();
console.log(`reverse: [${reversed}]`);

// sort: 排序
let unsorted = [3, 1, 4, 1, 5, 9, 2, 6];
let sorted = [...unsorted].sort((a, b) => a - b);
console.log(`sort: [${sorted}]`);

// ============================================================
// 3. 数组遍历
// ============================================================

console.log("\n=== 3. 数组遍历 ===");

let colors = ["红", "绿", "蓝"];

// for 循环
console.log("for 循环:");
for (let i = 0; i < colors.length; i++) {
    console.log(`  ${i}: ${colors[i]}`);
}

// for...of
console.log("for...of:");
for (let color of colors) {
    console.log(`  ${color}`);
}

// forEach
console.log("forEach:");
colors.forEach((color, index) => {
    console.log(`  ${index}: ${color}`);
});

// ============================================================
// 4. 数组高级方法
// ============================================================

console.log("\n=== 4. 数组高级方法 ===");

let numbers = [1, 2, 3, 4, 5];

// map: 映射
let doubled = numbers.map(n => n * 2);
console.log(`map (×2): [${doubled}]`);

// filter: 过滤
let evens = numbers.filter(n => n % 2 === 0);
console.log(`filter (偶数): [${evens}]`);

// reduce: 归约
let sum = numbers.reduce((acc, n) => acc + n, 0);
console.log(`reduce (求和): ${sum}`);

// find: 查找第一个满足条件的
let found = numbers.find(n => n > 3);
console.log(`find (>3): ${found}`);

// findIndex
let foundIndex = numbers.findIndex(n => n > 3);
console.log(`findIndex (>3): ${foundIndex}`);

// every: 全部满足
let allPositive = numbers.every(n => n > 0);
console.log(`every (>0): ${allPositive}`);

// some: 至少一个满足
let hasEven = numbers.some(n => n % 2 === 0);
console.log(`some (有偶数): ${hasEven}`);

// flat: 扁平化
let nested = [1, [2, 3], [4, [5, 6]]];
console.log(`flat(): [${nested.flat()}]`);
console.log(`flat(2): [${nested.flat(2)}]`);

// ============================================================
// 5. 元组 Tuple
// ============================================================

console.log("\n=== 5. 元组 Tuple ===");

// 元组声明
let x: [string, number];
x = ["hello", 10];
console.log(`元组: [${x[0]}, ${x[1]}]`);

// 访问元组元素
console.log(`第一个元素: ${x[0]}, 类型: ${typeof x[0]}`);
console.log(`第二个元素: ${x[1]}, 类型: ${typeof x[1]}`);

// 解构元组
let [firstVal, secondVal] = x;
console.log(`解构: firstVal=${firstVal}, secondVal=${secondVal}`);

// 多元素元组
let person: [string, number, boolean] = ["张三", 25, true];
console.log(`人员信息: 姓名=${person[0]}, 年龄=${person[1]}, 在职=${person[2]}`);

// 元组可选元素
let tupleWithOptional: [string, number?] = ["hello"];
console.log(`可选元素元组: [${tupleWithOptional}]`);
tupleWithOptional = ["hello", 42];
console.log(`添加可选元素后: [${tupleWithOptional}]`);

// 元组剩余元素
let tupleWithRest: [string, ...number[]] = ["numbers", 1, 2, 3, 4];
console.log(`剩余元素元组: [${tupleWithRest}]`);

// 只读元组
let readOnlyTuple: readonly [string, number] = ["readonly", 100];
console.log(`只读元组: [${readOnlyTuple}]`);

// 元组使用场景：函数返回多个值
function getPosition(): [number, number] {
    return [100, 200];
}
let [posX, posY] = getPosition();
console.log(`位置: x=${posX}, y=${posY}`);

console.log("\n=== 运行完成 ===");
