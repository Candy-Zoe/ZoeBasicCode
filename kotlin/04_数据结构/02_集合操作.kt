// ============================================================
// Kotlin 数据结构 - 集合操作
// 编译运行：kotlinc 02_集合操作.kt -include-runtime -d out.jar && java -jar out.jar
// ============================================================

fun main() {
    println("=== 1. 映射操作 ===")

    val numbers = (1..10).toList()
    println("原列表: $numbers")

    // map: 转换每个元素
    val doubled = numbers.map { it * 2 }
    println("map (×2): $doubled")

    // mapIndexed: 带索引的映射
    val withIndex = numbers.mapIndexed { index, value -> "[$index]=$value" }
    println("mapIndexed: $withIndex")

    // flatMap: 扁平化映射
    val nestedList = listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
    val flatMapped = nestedList.flatMap { it.map { it * 10 } }
    println("flatMap: $flatMapped")

    // flatten: 扁平化
    val flattened = nestedList.flatten()
    println("flatten: $flattened")

    // ============================================================
    // 2. 过滤操作
    // ============================================================

    println("\n=== 2. 过滤操作 ===")

    // filter: 过滤
    val evens = numbers.filter { it % 2 == 0 }
    println("filter (偶数): $evens")

    val odds = numbers.filter { it % 2 != 0 }
    println("filter (奇数): $odds")

    // filterIndexed: 带索引过滤
    val evenIndex = numbers.filterIndexed { index, _ -> index % 2 == 0 }
    println("filterIndexed (偶数索引): $evenIndex")

    // filterNot: 反向过滤
    val notGreaterThan5 = numbers.filterNot { it > 5 }
    println("filterNot (<=5): $notGreaterThan5")

    // filterNotNull: 过滤 null
    val nullableList = listOf(1, null, 3, null, 5)
    val notNull = nullableList.filterNotNull()
    println("filterNotNull: $notNull")

    // take: 取前 n 个
    val first3 = numbers.take(3)
    println("take(3): $first3")

    // takeLast: 取后 n 个
    val last3 = numbers.takeLast(3)
    println("takeLast(3): $last3")

    // takeWhile: 取满足条件的
    val lessThan5 = numbers.takeWhile { it < 5 }
    println("takeWhile (<5): $lessThan5")

    // drop: 丢弃前 n 个
    val drop3 = numbers.drop(3)
    println("drop(3): $drop3")

    // ============================================================
    // 3. 排序操作
    // ============================================================

    println("\n=== 3. 排序操作 ===")

    val unsorted = listOf(3, 1, 4, 1, 5, 9, 2, 6)
    println("原列表: $unsorted")

    // sorted: 升序排序
    val sorted = unsorted.sorted()
    println("sorted (升序): $sorted")

    // sortedDescending: 降序排序
    val sortedDesc = unsorted.sortedDescending()
    println("sortedDescending (降序): $sortedDesc")

    // sortedBy: 按指定条件排序
    val strings = listOf("apple", "banana", "cherry", "date")
    val sortedByLength = strings.sortedBy { it.length }
    println("sortedBy (长度): $sortedByLength")

    // sortedByDescending
    val sortedByLengthDesc = strings.sortedByDescending { it.length }
    println("sortedByDescending (长度降序): $sortedByLengthDesc")

    // reversed: 反转
    val reversed = numbers.reversed()
    println("reversed: $reversed")

    // ============================================================
    // 4. 聚合操作
    // ============================================================

    println("\n=== 4. 聚合操作 ===")

    println("列表: $numbers")

    // sum
    println("sum: ${numbers.sum()}")

    // average
    println("average: ${numbers.average()}")

    // max / min
    println("maxOrNull: ${numbers.maxOrNull()}")
    println("minOrNull: ${numbers.minOrNull()}")

    // maxBy / minBy
    println("maxByOrNull (长度): ${strings.maxByOrNull { it.length }}")
    println("minByOrNull (长度): ${strings.minByOrNull { it.length }}")

    // reduce: 归约
    val sumReduce = numbers.reduce { acc, n -> acc + n }
    println("reduce (求和): $sumReduce")

    // fold: 带初始值的归约
    val sumFold = numbers.fold(0) { acc, n -> acc + n }
    println("fold (求和): $sumFold")

    val productFold = numbers.fold(1) { acc, n -> acc * n }
    println("fold (乘积): $productFold")

    // count: 计数
    val countGreater5 = numbers.count { it > 5 }
    println("count (>5): $countGreater5")

    // any: 是否有满足条件的
    val hasGreater10 = numbers.any { it > 10 }
    println("any (>10): $hasGreater10")

    // all: 是否全部满足
    val allPositive = numbers.all { it > 0 }
    println("all (>0): $allPositive")

    // none: 是否都不满足
    val noneNegative = numbers.none { it < 0 }
    println("none (<0): $noneNegative")

    // ============================================================
    // 5. 分组操作
    // ============================================================

    println("\n=== 5. 分组操作 ===")

    // groupBy: 分组
    val grouped = numbers.groupBy { it % 2 }
    println("groupBy (奇偶): $grouped")

    // groupBy 带转换
    val groupedByLength = strings.groupBy({ it.length }, { it.uppercase() })
    println("groupBy (长度): $groupedByLength")

    // groupingBy + eachCount
    val eachCount = strings.groupingBy { it.first() }.eachCount()
    println("groupingBy + eachCount (首字母计数): $eachCount")

    // partition: 分成两部分
    val (even, odd) = numbers.partition { it % 2 == 0 }
    println("partition: 偶数=$even, 奇数=$odd")

    // ============================================================
    // 6. 查找操作
    // ============================================================

    println("\n=== 6. 查找操作 ===")

    // find: 查找第一个满足条件的
    val firstGreater5 = numbers.find { it > 5 }
    println("find (>5): $firstGreater5")

    // findLast: 查找最后一个满足条件的
    val lastGreater5 = numbers.findLast { it > 5 }
    println("findLast (>5): $lastGreater5")

    // first / last
    println("first: ${numbers.first()}")
    println("last: ${numbers.last()}")

    // firstOrNull / lastOrNull
    val emptyList = emptyList<Int>()
    println("firstOrNull (空列表): ${emptyList.firstOrNull()}")

    // indexOf / lastIndexOf
    println("indexOf(5): ${numbers.indexOf(5)}")
    println("lastIndexOf(5): ${numbers.lastIndexOf(5)}")

    // binarySearch: 二分查找（需要已排序）
    val sortedList = numbers.sorted()
    println("binarySearch(5): ${sortedList.binarySearch(5)}")

    // contains / in
    println("contains(5): ${numbers.contains(5)}")
    println("5 in numbers: ${5 in numbers}")

    // ============================================================
    // 7. 序列 Sequence
    // ============================================================

    println("\n=== 7. 序列 Sequence ===")

    // 序列是惰性的，适合处理大数据量
    val sequence = numbers.asSequence()
        .filter { it % 2 == 0 }
        .map { it * it }
        .take(3)
        .toList()
    println("序列操作: $sequence")

    // generateSequence: 生成序列
    val fibonacci = generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }
        .map { it.first }
        .take(10)
        .toList()
    println("斐波那契: $fibonacci")

    println("\n=== 运行完成 ===")
}
