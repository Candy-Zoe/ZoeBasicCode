// ============================================================
// TypeScript 高级特性 - 装饰器
// 运行：ts-node 03_装饰器.ts --experimentalDecorators
// 或在 tsconfig.json 中设置 "experimentalDecorators": true
// ============================================================

console.log("=== 1. 类装饰器 ===");

// 类装饰器：密封类
function sealed(constructor: Function) {
    Object.seal(constructor);
    Object.seal(constructor.prototype);
    console.log("类被密封了");
}

@sealed
class Greeter {
    greeting: string;
    constructor(message: string) {
        this.greeting = message;
    }
    greet() {
        return "Hello, " + this.greeting;
    }
}

let greeter = new Greeter("world");
console.log(greeter.greet());

// 类装饰器工厂
function Logger(prefix: string) {
    return function (constructor: Function) {
        console.log(`[${prefix}] 类 ${constructor.name} 被创建`);
    };
}

@Logger("INFO")
class User {
    name: string;
    constructor(name: string) {
        this.name = name;
    }
}

let user = new User("张三");
console.log(`用户: ${user.name}`);

// ============================================================
// 2. 方法装饰器
// ============================================================

console.log("\n=== 2. 方法装饰器 ===");

function enumerable(value: boolean) {
    return function (target: any, propertyKey: string, descriptor: PropertyDescriptor) {
        descriptor.enumerable = value;
    };
}

class MyClass {
    private _value: number = 0;

    @enumerable(false)
    getValue(): number {
        return this._value;
    }

    setValue(v: number): void {
        this._value = v;
    }
}

let obj = new MyClass();
console.log("方法装饰器: @enumerable 设置属性的可枚举性");

// 方法装饰器 - 日志记录
function log(target: any, propertyKey: string, descriptor: PropertyDescriptor) {
    const originalMethod = descriptor.value;
    descriptor.value = function (...args: any[]) {
        console.log(`调用 ${propertyKey}，参数: [${args.join(', ')}]`);
        const result = originalMethod.apply(this, args);
        console.log(`返回值: ${result}`);
        return result;
    };
    return descriptor;
}

class Calculator {
    @log
    add(a: number, b: number): number {
        return a + b;
    }
}

let calc = new Calculator();
calc.add(1, 2);

// ============================================================
// 3. 属性装饰器
// ============================================================

console.log("\n=== 3. 属性装饰器 ===");

function format(formatString: string) {
    return function (target: any, propertyKey: string): void {
        console.log(`属性 ${propertyKey} 使用了格式: ${formatString}`);
    };
}

class User2 {
    @format("YYYY-MM-DD")
    birthday: string = "2000-01-01";
}

console.log("属性装饰器: @format 标记属性");

// 属性装饰器 - 验证
function MinLength(length: number) {
    return function (target: any, propertyKey: string) {
        let value: string;

        const getter = function () {
            return value;
        };

        const setter = function (newVal: string) {
            if (newVal.length < length) {
                console.log(`错误: ${propertyKey} 长度不能小于 ${length}`);
            } else {
                value = newVal;
            }
        };

        Object.defineProperty(target, propertyKey, {
            get: getter,
            set: setter,
            enumerable: true,
            configurable: true
        });
    };
}

class Person {
    @MinLength(2)
    name: string;

    constructor(name: string) {
        this.name = name;
    }
}

let p1 = new Person("张三");
console.log(`姓名: ${p1.name}`);

let p2 = new Person("李");
console.log(`姓名: ${p2.name}`);

// ============================================================
// 4. 参数装饰器
// ============================================================

console.log("\n=== 4. 参数装饰器 ===");

function required(target: Object, propertyKey: string | symbol, parameterIndex: number) {
    console.log(`参数 ${parameterIndex} 是必填的`);
}

class SomeClass {
    someMethod(@required param1: string, param2: number) {
        console.log(`param1: ${param1}, param2: ${param2}`);
    }
}

let sc = new SomeClass();
sc.someMethod("test", 42);

// ============================================================
// 5. 装饰器组合
// ============================================================

console.log("\n=== 5. 装饰器组合 ===");

function first() {
    console.log("first(): 工厂执行");
    return function (target: any, propertyKey: string, descriptor: PropertyDescriptor) {
        console.log("first(): 装饰器执行");
    };
}

function second() {
    console.log("second(): 工厂执行");
    return function (target: any, propertyKey: string, descriptor: PropertyDescriptor) {
        console.log("second(): 装饰器执行");
    };
}

class Example {
    @first()
    @second()
    method() {
        console.log("method 执行");
    }
}

// ============================================================
// 6. 访问器装饰器
// ============================================================

console.log("\n=== 6. 访问器装饰器 ===");

function configurable(value: boolean) {
    return function (target: any, propertyKey: string, descriptor: PropertyDescriptor) {
        descriptor.configurable = value;
    };
}

class Point {
    private _x: number;
    private _y: number;

    constructor(x: number, y: number) {
        this._x = x;
        this._y = y;
    }

    @configurable(false)
    get x() {
        return this._x;
    }

    @configurable(false)
    get y() {
        return this._y;
    }
}

let point = new Point(10, 20);
console.log(`点坐标: (${point.x}, ${point.y})`);

console.log("\n=== 运行完成 ===");
