-- ============================================================
-- Lua 函数 - 函数基础 (多返回值, 可变参数, 匿名函数, 闭包)
-- 运行：lua 01_函数基础.lua
-- ============================================================

print("=== 函数基础 ===")

-- 基本函数
function add(a, b)
    return a + b
end
print("add(5, 3) = " .. add(5, 3))

-- 多返回值
function divide(a, b)
    return a / b, a % b
end
q, r = divide(10, 3)
print("10 / 3 = " .. q .. " 余 " .. r)

-- 可变参数
function sum(...)
    local total = 0
    local args = {...}
    for i = 1, #args do
        total = total + args[i]
    end
    return total
end
print("sum(1, 2, 3, 4, 5) = " .. sum(1, 2, 3, 4, 5))

-- 匿名函数
local double = function(x)
    return x * 2
end
print("double(5) = " .. double(5))

-- 高阶函数 - 函数作为参数
function apply(arr, func)
    local result = {}
    for i = 1, #arr do
        result[i] = func(arr[i])
    end
    return result
end

nums = {1, 2, 3}
doubled = apply(nums, function(x) return x * 2 end)
print("映射后: " .. table.concat(doubled, ", "))

-- 闭包
function counter()
    local count = 0
    return function()
        count = count + 1
        return count
    end
end

c = counter()
print("计数器 1: " .. c())
print("计数器 2: " .. c())
print("计数器 3: " .. c())

print("\n=== 运行完成 ===")
