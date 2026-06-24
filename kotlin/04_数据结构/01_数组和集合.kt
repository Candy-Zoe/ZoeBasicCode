// ============================================================
// Kotlin 数据结构 - 数组和集合
// 编译运行：kotlinc 01_数组和集合.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 数组 ===")

    // 基本类型数组
    val nums = intArrayOf(1, 2, 3, 4, 5)
    println("IntArray: ${nums.joinToString()}")

    val strs = arrayOf("苹果", "香蕉", "橙子")
    println("Array<String>: ${strs.joinToString()}")

    // 访问元素
    println("第一个元素: ${strs[0]}")
    println("数组大小: ${strs.size}")

    // 修改元素
    strs[1] = "芒果"
    println("修改后: ${strs.joinToString()}")

    // 创建零数组
    val zeros = IntArray(5)
    println("零数组: ${zeros.joinToString()}")

    // 使用 lambda 初始化数组
    val doubles = DoubleArray(3) { it * 1.5 }
    println("DoubleArray: ${doubles.joinToString()}")

    // 其他基本类型数组
    val longs = longArrayOf(1L, 2L, 3L)
    val floats = floatArrayOf(1.1f, 2.2f)
    val chars = charArrayOf('A', 'B', 'C')
    val booleans = booleanArrayOf(true, false, true)
    println("其他数组: longs=${longs.size}, floats=${floats.size}, chars=${chars.size}, booleans=${booleans.size}")

    // ============================================================
    // 2. List 列表
    // ============================================================

    println("\n=== 2. List 列表 ===")

    // 只读 List
    val list = listOf(1, 2, 3, 4, 5)
    println("List: $list")
    println("大小: ${list.size}, 第一个: ${list.first()}, 最后一个: ${list.last()}")

    // 访问元素
    println("索引 2: ${list[2]}")
    println("getOrNull(10): ${list.getOrNull(10)}")
    println("getOrElse(10) { -1 }: ${list.getOrElse(10) { -1 }}")

    // 可变 MutableList
    val mList = mutableListOf("A", "B", "C")
    mList.add("D")
    mList[0] = "AAA"
    println("MutableList: $mList")

    mList.add(1, "BBB")
    println("add(1, \"BBB\"): $mList")

    mList.removeAt(0)
    println("removeAt(0): $mList")

    // list 转 mutableList
    val mutableNums = list.toMutableList()
    mutableNums.add(6)
    println("toMutableList(): $mutableNums")

    // ============================================================
    // 3. Set 集合
    // ============================================================

    println("\n=== 3. Set 集合 ===")

    // 只读 Set - 无序不重复
    val set = setOf(1, 2, 3, 3, 4, 4, 5)
    println("Set: $set")
    println("大小: ${set.size}")

    // 可变 MutableSet
    val mSet = mutableSetOf("a", "b", "c")
    mSet.add("d")
    mSet.add("a")  // 不会重复添加
    println("MutableSet: $mSet")

    mSet.remove("b")
    println("remove(\"b\"): $mSet")

    // LinkedHashSet - 保持插入顺序
    val linkedSet = linkedSetOf(3, 1, 4, 1, 5, 9, 2, 6)
    println("LinkedHashSet: $linkedSet")

    // TreeSet - 排序
    val treeSet = sortedSetOf(3, 1, 4, 1, 5, 9, 2, 6)
    println("SortedSet (TreeSet): $treeSet")

    // ============================================================
    // 4. Map 映射
    // ============================================================

    println("\n=== 4. Map 映射 ===")

    // 只读 Map
    val map = mapOf("name" to "张三", "age" to 25, "city" to "北京")
    println("Map: $map")
    println("姓名: ${map["name"]}")
    println("大小: ${map.size}")

    // 访问安全方式
    println("getOrDefault(\"job\", \"未知\"): ${map.getOrDefault("job", "未知")}")
    println("getOrElse(\"job\") { \"未知\" }: ${map.getOrElse("job") { "未知" }}")

    // 可变 MutableMap
    val mMap = mutableMapOf(1 to "one", 2 to "two")
    mMap[3] = "three"
    mMap.put(4, "four")
    println("MutableMap: $mMap")

    mMap.remove(2)
    println("remove(2): $mMap")

    // 遍历 Map
    println("遍历 Map:")
    for ((key, value) in map) {
        println("  $key: $value")
    }

    // keys 和 values
    println("keys: ${map.keys}")
    println("values: ${map.values}")

    // LinkedHashMap - 保持插入顺序
    val linkedMap = linkedMapOf("c" to 3, "a" to 1, "b" to 2)
    println("LinkedHashMap: $linkedMap")

    // TreeMap - 按键排序
    val treeMap = sortedMapOf("c" to 3, "a" to 1, "b" to 2)
    println("SortedMap (TreeMap): $treeMap")

    // ============================================================
    // 5. 集合转换
    // ============================================================

    println("\n=== 5. 集合转换 ===")

    val numList = listOf(1, 2, 3, 2, 1)

    // List 转 Set
    val setFromList = numList.toSet()
    println("List -> Set: $setFromList")

    // List 转数组
    val arrayFromList = numList.toIntArray()
    println("List -> Array: ${arrayFromList.joinToString()}")

    // Array 转 List
    val listFromArray = nums.toList()
    println("Array -> List: $listFromArray")

    // Pair 列表转 Map
    val pairList = listOf("a" to 1, "b" to 2, "c" to 3)
    val mapFromPairs = pairList.toMap()
    println("Pair List -> Map: $mapFromPairs")

    println("\n=== 运行完成 ===")
}
