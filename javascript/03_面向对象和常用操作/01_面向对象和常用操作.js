// ============================================================
// JavaScript 面向对象和常用操作
// 运行方式：node 文件名.js
// ============================================================

console.log("=== 1. 对象字面量 ===");
let person = {
    name: "张三",
    age: 25,
    greet: function() {
        console.log(`你好，我是${this.name}`);
    }
};
console.log(person.name);
person.greet();

console.log("\n=== 2. 构造函数 ===");
function Person(name, age) {
    this.name = name;
    this.age = age;
}
Person.prototype.greet = function() {
    console.log(`你好，我是${this.name}，今年${this.age}岁`);
};

let p1 = new Person("张三", 25);
let p2 = new Person("李四", 30);
p1.greet();
p2.greet();

console.log("\n=== 3. class 类 (ES6) ===");
class Animal {
    constructor(name) {
        this.name = name;
    }
    speak() {
        console.log(`${this.name} 发出声音`);
    }
}

class Dog extends Animal {
    constructor(name, breed) {
        super(name);
        this.breed = breed;
    }
    speak() {
        console.log(`${this.name} 汪汪叫`);
    }
    fetch() {
        console.log(`${this.name} 去捡球了`);
    }
}

let dog = new Dog("旺财", "柴犬");
dog.speak();
dog.fetch();

console.log("\n=== 4. 静态方法和属性 ===");
class MathUtils {
    static PI = 3.14159;
    static square(x) {
        return x * x;
    }
}
console.log("MathUtils.PI =", MathUtils.PI);
console.log("MathUtils.square(5) =", MathUtils.square(5));

console.log("\n=== 5. get/set 访问器 ===");
class Rectangle {
    constructor(width, height) {
        this.width = width;
        this.height = height;
    }
    get area() {
        return this.width * this.height;
    }
}
let rect = new Rectangle(4, 5);
console.log("矩形面积:", rect.area);  // 像属性一样访问

console.log("\n=== 6. Promise (ES6) ===");
function delay(ms) {
    return new Promise((resolve, reject) => {
        setTimeout(() => resolve(`等待了${ms}毫秒`), ms);
    });
}
console.log("Promise 开始...");
delay(100).then(result => {
    console.log("Promise 结果:", result);
});

console.log("\n=== 7. async/await (ES2017) ===");
async function asyncDemo() {
    console.log("async 开始...");
    let result = await delay(100);
    console.log("async 结果:", result);
}
// asyncDemo();  // 取消注释运行

console.log("\n=== 8. 数组常用操作 ===");
let arr = [3, 1, 4, 1, 5, 9, 2, 6];
console.log("原数组:", arr);
console.log("push:", [...arr, 10]);
console.log("pop: 最后一个 =", arr[arr.length - 1]);
console.log("unshift:", [0, ...arr]);
console.log("slice(2,5):", arr.slice(2, 5));
console.log("splice 会修改原数组");
console.log("concat:", arr.concat([7, 8]));
console.log("join('-'):", arr.join("-"));
console.log("reverse:", [...arr].reverse());
console.log("includes(5):", arr.includes(5));
console.log("indexOf(5):", arr.indexOf(5));

console.log("\n=== 9. 对象常用操作 ===");
let obj = { a: 1, b: 2, c: 3 };
console.log("Object.keys:", Object.keys(obj));
console.log("Object.values:", Object.values(obj));
console.log("Object.entries:", Object.entries(obj));
console.log("Object.assign:", Object.assign({}, obj, { d: 4 }));

console.log("\n=== 10. 解构 ===");
let { x, y, ...rest } = { x: 1, y: 2, z: 3, w: 4 };
console.log("x =", x, ", y =", y, ", rest =", rest);

console.log("\n=== 11. 可选链和空值合并 ===");
let user = { profile: { name: "张三" } };
console.log("user?.profile?.name =", user?.profile?.name);
console.log("user?.info?.email ?? '无' =", user?.info?.email ?? "无");

console.log("\n=== 12. 模板字符串标签函数 ===");
function highlight(strings, ...values) {
    return strings.reduce((result, str, i) => {
        return result + str + (values[i] ? `[${values[i]}]` : "");
    }, "");
}
let name = "张三";
let age = 25;
console.log(highlight`我是${name}，今年${age}岁`);

console.log("\n=== 13. 模块化 (ES6 Module) ===");
console.log("export 导出变量/函数/类");
console.log("import 导入");
console.log("export default 默认导出");

console.log("\n=== 14. Set 和 Map (ES6) ===");
// Set
let set = new Set([1, 2, 3, 2, 1]);
console.log("Set:", [...set]);
console.log("Set 大小:", set.size);
set.add(4);
console.log("添加后:", [...set]);
console.log("has(3):", set.has(3));

// Map
let map = new Map();
map.set("name", "张三");
map.set("age", 25);
console.log("Map get name:", map.get("name"));
console.log("Map 大小:", map.size);
for (let [k, v] of map) {
    console.log(`  ${k}: ${v}`);
}

console.log("\n=== 15. 代理 Proxy (ES6) ===");
let target = { name: "张三" };
let proxy = new Proxy(target, {
    get(obj, prop) {
        console.log(`读取属性: ${prop}`);
        return obj[prop] ?? "默认值";
    }
});
console.log("proxy.name =", proxy.name);
console.log("proxy.age =", proxy.age);
