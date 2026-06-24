// ============================================================
// TypeScript 基础语法 - 运算符
// 运行：ts-node 02_运算符.ts
// ============================================================

console.log("=== 1. 算术运算符 ===");

let a = 10;
let b = 3;

console.log(`a = ${a}, b = ${b}`);
console.log(`加法: a + b = ${a + b}`);
console.log(`减法: a - b = ${a - b}`);
console.log(`乘法: a * b = ${a * b}`);
console.log(`除法: a / b = ${a / b}`);
console.log(`取余: a % b = ${a % b}`);
console.log(`幂运算: a ** b = ${a ** b}`);

// 自增自减
let x = 5;
console.log(`x = ${x}`);
console.log(`x++ = ${x++} (后置，先返回后自增)`);
console.log(`现在 x = ${x}`);
console.log(`++x = ${++x} (前置，先自增后返回)`);
console.log(`x-- = ${x--} (后置)`);
console.log(`现在 x = ${x}`);

// ============================================================
// 2. 赋值运算符
// ============================================================

console.log("\n=== 2. 赋值运算符 ===");

let c = 10;
console.log(`c = ${c}`);
c += 5;
console.log(`c += 5 → ${c}`);
c -= 3;
console.log(`c -= 3 → ${c}`);
c *= 2;
console.log(`c *= 2 → ${c}`);
c /= 4;
console.log(`c /= 4 → ${c}`);
c %= 3;
console.log(`c %= 3 → ${c}`);
c **= 2;
console.log(`c **= 2 → ${c}`);

// ============================================================
// 3. 比较运算符
// ============================================================

console.log("\n=== 3. 比较运算符 ===");

let m = 5;
let n = 10;

console.log(`m = ${m}, n = ${n}`);
console.log(`等于: m == n → ${m == n}`);
console.log(`严格等于: m === n → ${m === n}`);
console.log(`不等于: m != n → ${m != n}`);
console.log(`严格不等于: m !== n → ${m !== n}`);
console.log(`大于: m > n → ${m > n}`);
console.log(`小于: m < n → ${m < n}`);
console.log(`大于等于: m >= n → ${m >= n}`);
console.log(`小于等于: m <= n → ${m <= n}`);

// ============================================================
// 4. 逻辑运算符
// ============================================================

console.log("\n=== 4. 逻辑运算符 ===");

let t = true;
let f = false;

console.log(`t = ${t}, f = ${f}`);
console.log(`逻辑与 AND: t && f → ${t && f}`);
console.log(`逻辑或 OR: t || f → ${t || f}`);
console.log(`逻辑非 NOT: !t → ${!t}`);

// 短路求值
console.log("\n短路求值:");
let result1 = undefined && "hello";
console.log(`undefined && "hello" → ${result1}`);
let result2 = "hello" || "world";
console.log(`"hello" || "world" → ${result2}`);

// ============================================================
// 5. 三元运算符
// ============================================================

console.log("\n=== 5. 三元运算符 ===");

let score = 85;
let grade = score >= 60 ? "及格" : "不及格";
console.log(`分数 ${score}: ${grade}`);

let age2 = 20;
let status = age2 >= 18 ? "成年人" : "未成年人";
console.log(`年龄 ${age2}: ${status}`);

// 嵌套三元运算符
let score2 = 92;
let grade2 = score2 >= 90 ? "优秀" : score2 >= 80 ? "良好" : score2 >= 60 ? "及格" : "不及格";
console.log(`分数 ${score2}: ${grade2}`);

// ============================================================
// 6. 类型运算符
// ============================================================

console.log("\n=== 6. 类型运算符 ===");

console.log(`typeof 42 → ${typeof 42}`);
console.log(`typeof "hello" → ${typeof "hello"}`);
console.log(`typeof true → ${typeof true}`);
console.log(`typeof undefined → ${typeof undefined}`);
console.log(`typeof null → ${typeof null}`);
console.log(`typeof {} → ${typeof {}}`);
console.log(`typeof [] → ${typeof []}`);

// ============================================================
// 7. 位运算符
// ============================================================

console.log("\n=== 7. 位运算符 ===");

let p = 5;  // 0101
let q = 3;  // 0011

console.log(`p = ${p} (二进制: ${p.toString(2)})`);
console.log(`q = ${q} (二进制: ${q.toString(2)})`);
console.log(`按位与 AND: p & q = ${p & q} (${(p & q).toString(2)})`);
console.log(`按位或 OR: p | q = ${p | q} (${(p | q).toString(2)})`);
console.log(`按位异或 XOR: p ^ q = ${p ^ q} (${(p ^ q).toString(2)})`);
console.log(`按位取反 NOT: ~p = ${~p}`);
console.log(`左移: p << 1 = ${p << 1} (${(p << 1).toString(2)})`);
console.log(`右移: p >> 1 = ${p >> 1} (${(p >> 1).toString(2)})`);

console.log("\n=== 运行完成 ===");
