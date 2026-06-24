// ============================================================
// TypeScript 流程控制 - 条件语句
// 运行：ts-node 01_条件语句.ts
// ============================================================

console.log("=== 1. if/else 语句 ===");

// 基本 if 语句
let score = 85;
if (score >= 60) {
    console.log("及格了");
}

// if/else 语句
let score2 = 55;
if (score2 >= 60) {
    console.log("及格了");
} else {
    console.log("不及格");
}

// if/else if/else 多分支
let score3 = 92;
if (score3 >= 90) {
    console.log("优秀");
} else if (score3 >= 80) {
    console.log("良好");
} else if (score3 >= 60) {
    console.log("及格");
} else {
    console.log("不及格");
}

// 嵌套 if 语句
let age = 25;
let hasLicense = true;
if (age >= 18) {
    if (hasLicense) {
        console.log("可以开车");
    } else {
        console.log("需要先考驾照");
    }
} else {
    console.log("未成年，不能开车");
}

// ============================================================
// 2. switch 语句
// ============================================================

console.log("\n=== 2. switch 语句 ===");

let day = 3;
let dayName: string;
switch (day) {
    case 1:
        dayName = "星期一";
        break;
    case 2:
        dayName = "星期二";
        break;
    case 3:
        dayName = "星期三";
        break;
    case 4:
        dayName = "星期四";
        break;
    case 5:
        dayName = "星期五";
        break;
    case 6:
        dayName = "星期六";
        break;
    case 7:
        dayName = "星期日";
        break;
    default:
        dayName = "无效日期";
}
console.log(`第 ${day} 天是 ${dayName}`);

// 多个 case 共享一个代码块
let month = 2;
let season: string;
switch (month) {
    case 12:
    case 1:
    case 2:
        season = "冬季";
        break;
    case 3:
    case 4:
    case 5:
        season = "春季";
        break;
    case 6:
    case 7:
    case 8:
        season = "夏季";
        break;
    case 9:
    case 10:
    case 11:
        season = "秋季";
        break;
    default:
        season = "无效月份";
}
console.log(`${month} 月是 ${season}`);

// switch 穿透（没有 break）
console.log("\nswitch 穿透示例:");
let num = 2;
switch (num) {
    case 1:
        console.log("  执行 case 1");
    case 2:
        console.log("  执行 case 2");
    case 3:
        console.log("  执行 case 3");
        break;
    default:
        console.log("  执行 default");
}

// ============================================================
// 3. 三元表达式
// ============================================================

console.log("\n=== 3. 三元表达式 ===");

let age2 = 20;
let isAdult = age2 >= 18 ? true : false;
console.log(`年龄 ${age2}，是否成年: ${isAdult}`);

let x = 10;
let y = 20;
let max = x > y ? x : y;
console.log(`x=${x}, y=${y}，最大值: ${max}`);

// 嵌套三元表达式
let score4 = 88;
let grade = score4 >= 90 ? "优秀" :
            score4 >= 80 ? "良好" :
            score4 >= 60 ? "及格" : "不及格";
console.log(`分数 ${score4}，等级: ${grade}`);

// ============================================================
// 4. 逻辑运算符的短路用法
// ============================================================

console.log("\n=== 4. 逻辑运算符的短路用法 ===");

// && 短路：左边为真时才执行右边
let name: string | null = "张三";
name && console.log(`姓名: ${name}`);

name = null;
name && console.log("这行不会执行");

// || 短路：左边为假时才执行右边
let username: string | undefined = undefined;
let displayName = username || "游客";
console.log(`显示名称: ${displayName}`);

// ?? 空值合并运算符（只判断 null/undefined）
let value: number | null = 0;
let result1 = value || 100;   // 0 被当作假值
let result2 = value ?? 100;   // 0 不是 null/undefined
console.log(`value = ${value}`);
console.log(`value || 100 = ${result1}`);
console.log(`value ?? 100 = ${result2}`);

console.log("\n=== 运行完成 ===");
