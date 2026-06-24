-- ============================================================
-- Lua 高级特性 - 模块和包
-- 运行：lua 02_模块和包.lua
-- ============================================================

print("=== 模块和包 ===")

-- 创建模块
mymodule = {}
mymodule.version = "1.0"
function mymodule.hello()
    print("Hello from mymodule!")
end

mymodule.hello()
print("版本: " .. mymodule.version)

-- 标准库 - math 库
print("\nmath 库:")
print("  math.pi = " .. math.pi)
print("  math.abs(-5) = " .. math.abs(-5))
print("  math.sqrt(9) = " .. math.sqrt(9))
print("  math.floor(3.7) = " .. math.floor(3.7))
print("  math.ceil(3.1) = " .. math.ceil(3.1))
print("  math.random(1, 10) = " .. math.random(1, 10))

-- 标准库 - table 库
print("\ntable 库:")
t = {3, 1, 2}
table.sort(t)
print("  排序: " .. table.concat(t, ", "))
table.insert(t, 4)
print("  插入后: " .. table.concat(t, ", "))

-- 标准库 - string 库
print("\nstring 库:")
s = "Hello, World!"
print("  长度: " .. #s)
print("  大写: " .. string.upper(s))
print("  查找: " .. string.find(s, "World"))
print("  替换: " .. string.gsub(s, "World", "Lua"))

-- 标准库 - os 库
print("\nos 库:")
print("  当前时间戳: " .. os.time())
print("  日期: " .. os.date("%Y-%m-%d %H:%M:%S"))

-- 标准库 - io 库
print("\nio 库:")
print("  io.write 输出:")
io.write("    Hello from io.write!\n")

print("\n=== 运行完成 ===")
