// ============================================================
// JavaScript 基础语法 - 变量和数据类型
// 运行方式：node 01_变量和数据类型.js  或在浏览器控制台运行
// ============================================================

console.log("=== 1. 变量声明 ===");
var a = 10;          // var 函数作用域
let b = 20;          // let 块级作用域（ES6）
const PI = 3.14;     // const 常量（ES6）

console.log("var a =", a);
console.log("let b =", b);
console.log("const PI =", PI);

console.log("\n=== 2. 数据类型 ===");
// 基本类型
let str = "Hello";           // 字符串 string
let num = 123;               // 数字 number
let bool = true;             // 布尔 boolean
let undef = undefined;       // 未定义 undefined
let nul = null;              // 空值 null
let sym = Symbol("sym");     // 符号 symbol (ES6)
let bigInt = 123n;           // 大整数 bigint (ES2020)

console.log("字符串:", str, typeof str);
console.log("数字:", num, typeof num);
console.log("布尔:", bool, typeof bool);
console.log("undefined:", undef, typeof undef);
console.log("null:", nul, typeof nul);
console.log("Symbol:", sym, typeof sym);
console.log("BigInt:", bigInt, typeof bigInt);

// 引用类型
let arr = [1, 2, 3];         // 数组 object
let obj = {name: "张三"};    // 对象 object
let func = function() {};    // 函数 function

console.log("数组:", arr, typeof arr);
console.log("对象:", obj, typeof obj);
console.log("函数:", func, typeof func);

console.log("\n=== 3. 字符串操作 ===");
let name = "张三";
let greeting = `你好，${name}！`;  // 模板字符串 (ES6)
console.log("模板字符串:", greeting);
console.log("字符串长度:", name.length);
console.log("转大写:", name.toUpperCase());
console.log("拼接:", "Hello " + "World");
console.log("切片:", "Hello World".slice(0, 5));

console.log("\n=== 4. 数字操作 ===");
console.log("10 / 3 =", 10 / 3);
console.log("10 % 3 =", 10 % 3);
console.log("Math.PI =", Math.PI);
console.log("Math.round(3.5) =", Math.round(3.5));
console.log("Math.floor(3.9) =", Math.floor(3.9));
console.log("Math.ceil(3.1) =", Math.ceil(3.1));
console.log("Math.random() =", Math.random());

console.log("\n=== 5. 类型转换 ===");
let numStr = "123";
console.log("字符串转数字:", Number(numStr), typeof Number(numStr));
console.log("parseInt('123') =", parseInt("123"));
console.log("parseFloat('3.14') =", parseFloat("3.14"));
console.log("数字转字符串:", String(456), typeof String(456));
console.log("布尔转换:", Boolean(1), Boolean(0), Boolean(""), Boolean("hello"));

console.log("\n=== 6. 类型判断 ===");
console.log("typeof 'hello' =", typeof 'hello');
console.log("typeof 123 =", typeof 123);
console.log("typeof true =", typeof true);
console.log("typeof undefined =", typeof undefined);
console.log("typeof null =", typeof null);  // 注意：返回 'object'
console.log("typeof [] =", typeof []);
console.log("Array.isArray([]) =", Array.isArray([]));
console.log("{} instanceof Object =", {} instanceof Object);

console.log("\n=== 7. var vs let vs const ===");
console.log("var: 函数作用域，可重复声明，有变量提升");
console.log("let: 块级作用域，不可重复声明，有暂时性死区");
console.log("const: 块级作用域，声明时必须赋值，不可修改（引用类型可改内部）");

console.log("\n=== 8. 解构赋值 (ES6) ===");
let [x, y] = [1, 2];
console.log("数组解构: x =", x, ", y =", y);

let { name: pName, age } = { name: "李四", age: 25 };
console.log("对象解构: name =", pName, ", age =", age);

console.log("\n=== 9. 展开运算符 (ES6) ===");
let arr1 = [1, 2, 3];
let arr2 = [...arr1, 4, 5];
console.log("数组展开:", arr2);

let obj1 = { a: 1, b: 2 };
let obj2 = { ...obj1, c: 3 };
console.log("对象展开:", obj2);

console.log("\n=== 10. 可选链操作符 (ES2020) ===");
let user = { profile: { name: "王五" } };
console.log("可选链:", user?.profile?.name);
console.log("安全访问不存在的属性:", user?.info?.email);

console.log("\n=== 11. 空值合并 (ES2020) ===");
let val1 = null;
let val2 = 0;
console.log("val1 ?? '默认值':", val1 ?? '默认值');
console.log("val2 ?? '默认值':", val2 ?? '默认值');
console.log("val2 || '默认值':", val2 || '默认值');  // 对比 ||
