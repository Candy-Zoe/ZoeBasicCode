-- ============================================================
-- Lua 数据结构 - table 表操作 (数组, 字典, table库函数)
-- 运行：lua 01_table表操作.lua
-- ============================================================

print("=== Table 表操作 ===")

-- 数组（数字索引）
fruits = {"苹果", "香蕉", "橙子"}
print("第一个水果: " .. fruits[1])  -- Lua 索引从 1 开始！
print("数组长度: " .. #fruits)

-- 遍历数组
print("遍历数组:")
for i, v in ipairs(fruits) do
    print("  " .. i .. ". " .. v)
end

-- 字典（字符串索引）
person = {
    name = "张三",
    age = 25,
    city = "北京"
}
print("姓名: " .. person.name)
print("年龄: " .. person["age"])

-- 遍历字典
print("遍历字典:")
for k, v in pairs(person) do
    print("  " .. k .. ": " .. tostring(v))
end

-- 混合表
mixed = {
    10,
    20,
    name = "test",
    value = 100
}
print("混合表 [1]: " .. mixed[1])
print("混合表 name: " .. mixed.name)

-- table 库函数操作
print("\ntable 库函数:")
t = {3, 1, 4, 1, 5, 9}

table.insert(t, 7)   -- 添加元素
print("插入后: " .. table.concat(t, ", "))

table.remove(t, 1)   -- 删除指定位置元素
print("删除第一个后: " .. table.concat(t, ", "))

table.sort(t)        -- 排序
print("排序后: " .. table.concat(t, ", "))

-- table.concat 连接
print("连接: " .. table.concat(t, " - "))

-- 多维表
matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
}
print("矩阵 [2][3]: " .. matrix[2][3])

print("\n=== 运行完成 ===")
