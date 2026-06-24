-- ============================================================
-- Lua 高级特性 - 元表和面向对象
-- 运行：lua 01_元表和面向对象.lua
-- ============================================================

print("=== 元表和面向对象 ===")

-- 元表示例 - 重载加法运算符
t1 = {1, 2, 3}
t2 = {4, 5, 6}

mt = {
    __add = function(a, b)
        local result = {}
        for i = 1, #a do
            result[i] = a[i] + b[i]
        end
        return result
    end
}

setmetatable(t1, mt)
setmetatable(t2, mt)

t3 = t1 + t2
print("表相加: " .. table.concat(t3, ", "))

-- 面向对象 - 原型模式
Account = { balance = 0 }

function Account:new(o)
    o = o or {}
    setmetatable(o, self)
    self.__index = self
    return o
end

function Account:deposit(v)
    self.balance = self.balance + v
end

function Account:withdraw(v)
    if v <= self.balance then
        self.balance = self.balance - v
    end
end

a = Account:new{balance = 100}
a:deposit(50)
print("存款后余额: " .. a.balance)
a:withdraw(30)
print("取款后余额: " .. a.balance)

-- 继承
SpecialAccount = Account:new()
function SpecialAccount:withdraw(v)
    if v - self.balance >= 0 then
        self.balance = self.balance - v
    end
end

s = SpecialAccount:new{balance=100}
s:withdraw(200)  -- 可以透支
print("特殊账户余额: " .. s.balance)

print("\n=== 运行完成 ===")
